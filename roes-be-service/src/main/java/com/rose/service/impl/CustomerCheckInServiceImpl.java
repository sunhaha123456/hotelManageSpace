package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbSysUser;
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
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        param.setHotelId(user.getHotelId());
        Date planCheckInDate = param.getPlanCheckInDate();
        Date planCheckOutDate = param.getPlanCheckOutDate();
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
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbHotelRoomDetail room = hotelRoomDetailRepository.findOne(param.getRoomId());
        if (room == null || room.getRoomUpshelfState() != 0 || !user.getHotelId().equals(room.getHotelId())) {
            throw new BusinessException("所选房间已下架！");
        }
        // 判断房间是否可以预定


        Date now = new Date();
        param.setId(null);
        param.setCreateDate(now);
        param.setLastModified(now);
        hotelCustomerCheckInOrderRepository.save(param);
    }
}