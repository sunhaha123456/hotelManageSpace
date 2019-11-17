package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.DateUtil;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.CheckInDetailSearchRequest;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.repository.*;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerCheckInServiceImpl implements CustomerCheckInService {

    @Inject
    private HotelCustomerCheckInOrderRepositoryCustom hotelCustomerCheckInOrderRepositoryCustom;

    @Inject
    private HotelCustomerCheckInOrderRepository hotelCustomerCheckInOrderRepository;

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Inject
    private HotelRoomDetailRepositoryCustom hotelRoomDetailRepositoryCustom;

    @Inject
    private SysUserRepository sysUserRepository;

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

    @Inject
    private ValueHolder valueHolder;

    @Override
    public List getFloorList() {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return hotelRoomDetailRepository.findFloorList(user.getHotelId());
    }

    @Override
    public PageList<TbHotelRoomDetail> searchByFloor(HotelRoomRequest param) throws Exception {
        Date planCheckInDate = param.getPlanCheckInDate();
        Date planCheckOutDate = param.getPlanCheckOutDate();
        validateCheckInDateAndCheckOutDate(planCheckInDate, planCheckOutDate);

        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        param.setHotelId(user.getHotelId());
        PageList<TbHotelRoomDetail> page = hotelRoomDetailRepositoryCustom.listForFloor(param);
        if (page != null) {
            List<TbHotelRoomDetail> pageList = page.getRows();
            if (pageList != null && pageList.size() > 0) {
                List<Long> roomIdList = pageList.stream().map(TbHotelRoomDetail::getId).collect(Collectors.toList());
                List<TbHotelCustomerCheckInOrder> orderList = hotelCustomerCheckInOrderRepository.listByCheckOutDate(param.getHotelId(), roomIdList, planCheckInDate, planCheckOutDate);
                if (orderList == null || orderList.size() == 0) { // 当没查找到入住、预订记录
                    pageList.stream().forEach(o -> o.setCheckInState(0));
                } else { // 当查找到入住、预订记录
                    Map<Long, List<TbHotelCustomerCheckInOrder>> orderMap = new HashMap<>(); // 转换成map K：roomId，V：order
                    List<TbHotelCustomerCheckInOrder> list = null;
                    for (TbHotelCustomerCheckInOrder order : orderList) {
                        list = orderMap.get(order.getRoomId());
                        if (list == null) {
                            list = new ArrayList<>();
                            orderMap.put(order.getRoomId(), list);
                        }
                        list.add(order);
                    }

                    for (TbHotelRoomDetail room : pageList) { // 进行房间状态处理
                        list = orderMap.get(room.getId());
                        if (list == null) { // 当未查找到
                            room.setCheckInState(0);
                            continue;
                        }
                        // 当查找到
                        if (room.getCalcCheckInNumBedFlag() != null && room.getCalcCheckInNumBedFlag() == 0) { // 当不是根据床位计算可入住人数时
                            room.setCheckInState(1);
                        } else { // 当是根据床位计算可入住人数时
                            room.setCheckInState(room.getBedNum() > list.size() ? 0 : 1);
                        }
                    }
                }
            }
        }
        return page;
    }

    @Override
    public List<TbHotelCustomerCheckInOrder> getRoomCheckInDetail(Long roomId) throws Exception {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        List<TbHotelCustomerCheckInOrder> list = hotelCustomerCheckInOrderRepositoryCustom.listByHotelIdAndRoomId(user.getHotelId(), roomId);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleCustomerCheckIn(TbHotelCustomerCheckInOrder param) {
        Date planCheckInDate = param.getPlanCheckInDate();
        Date planCheckOutDate = param.getPlanCheckOutDate();
        validateCheckInDateAndCheckOutDate(planCheckInDate, planCheckOutDate);

        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbHotelRoomDetail room = hotelRoomDetailRepository.findOne(param.getRoomId());
        if (room == null || room.getRoomUpshelfState() != 0 || !user.getHotelId().equals(room.getHotelId())) {
            throw new BusinessException("所选房间已下架！");
        }
        List<TbHotelCustomerCheckInOrder> orderList = hotelCustomerCheckInOrderRepository.listByCheckOutDate(room.getHotelId(), Arrays.asList(room.getId()), planCheckInDate, planCheckOutDate);
        if (orderList != null && orderList.size() > 0) {
            if (room.getCalcCheckInNumBedFlag() == 0) { // 不是根据床位计算可入住人数
                throw new BusinessException("所选时间已有客人入住或预定！");
            } else { // 是根据床位计算可入住人数
                if ((orderList.size() + 1) > room.getBedNum()) {
                    throw new BusinessException("所选时间床位已满！");
                }
            }
        }

        Date now = new Date();
        param.setId(null);
        param.setCreateDate(now);
        param.setLastModified(now);
        param.setHotelId(room.getHotelId());
        param.setRoomId(room.getId());
        param.setRoomNo(room.getRoomNo());
        if (room.getRoomTypeId() != null) {
            TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(room.getRoomTypeId());
            if (roomType == null) {
                throw new BusinessException("房间类别不存在！");
            }
            param.setRoomTypeName(roomType.getRoomTypeName());
        } else {
            param.setRoomTypeName("");
        }
        param.setSellPrice(room.getSellPrice());
        param.setSellPriceDesc(room.getSellPriceDesc());
        param.setCheckOutDesc(room.getCheckOutDesc());
        param.setRoomOverallDesc(room.getRoomOverallDesc());
        if (param.getOrderType() == 0) { // 已到店直接入住类型订单
            param.setRealCheckInDate(param.getPlanCheckInDate());
            param.setRealCheckOutDate(null);
            param.setOrderStatus(0);
        } else { // 未到店预定入住类型订单
            param.setRealCheckInDate(null);
            param.setRealCheckOutDate(null);
            param.setOrderStatus(2);
        }
        param.setLockStartDate(param.getPlanCheckInDate());
        param.setLockEndDate(param.getPlanCheckOutDate());
        hotelCustomerCheckInOrderRepository.save(param);
    }

    @Override
    public PageList<TbHotelCustomerCheckInOrder> searchOrder(CheckInDetailSearchRequest param) throws Exception {
        return hotelCustomerCheckInOrderRepositoryCustom.listByCondition(param);
    }

    @Override
    public TbHotelCustomerCheckInOrder getDetail(Long id) {
        return hotelCustomerCheckInOrderRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderInfo(TbHotelCustomerCheckInOrder param) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbHotelCustomerCheckInOrder order = hotelCustomerCheckInOrderRepository.findOne(param.getId());
        if (!user.getHotelId().equals(order.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        Integer orderStatusParam = param.getOrderStatus();
        Integer orderStatusOld = order.getOrderStatus();
        switch (orderStatusOld) {
            case 0: { // 已入住
                if (orderStatusParam == 1) { // 修改成 已退房

                } else if (orderStatusParam == 3) { // 修改成 已取消

                }
                break;
            }
            case 1: { // 已退房
                throw new BusinessException("已退房订单，不能变更！");
            }
            case 2: { // 已预订
                if (orderStatusParam == 1) { // 修改成 已入住

                } else if (orderStatusParam == 3) { // 修改成 已取消

                }
                break;
            }
            case 3: { // 已取消
                throw new BusinessException("已取消订单，不能变更！");
            }
            default:
                throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancleOrder(Long id) {

    }

    private void validateCheckInDateAndCheckOutDate(Date planCheckInDate, Date planCheckOutDate) {
        if (planCheckInDate == null) {
            throw new BusinessException("请选择入住时间！");
        }
        if (planCheckOutDate == null) {
            throw new BusinessException("请选择退房时间！");
        }
        Date todayMinDate = DateUtil.formatStr2Time(DateUtil.getCurrentDate() + " 00:00:00");
        if (planCheckInDate.getTime() <= todayMinDate.getTime()) {
            throw new BusinessException("入住时间必须是今天及以后时间！");
        }
        if (planCheckOutDate.getTime() <= new Date().getTime()) {
            throw new BusinessException("退房时间必须晚于当前时间！");
        }
        if (planCheckOutDate.getTime() <= planCheckInDate.getTime()) {
            throw new BusinessException("退房时间必须晚于入住时间！");
        }
    }
}