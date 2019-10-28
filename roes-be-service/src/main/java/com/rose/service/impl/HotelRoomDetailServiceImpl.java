package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbHotelDetail;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.repository.*;
import com.rose.service.HotelRoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class HotelRoomDetailServiceImpl implements HotelRoomDetailService {

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Inject
    private HotelRoomDetailRepositoryCustom hotelRoomDetailRepositoryCustom;

    @Inject
    private HotelDetailRepository hotelDetailRepository;

    @Inject
    private SysUserRepository sysUserRepository;

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

    @Inject
    private ValueHolder valueHolder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TbHotelRoomDetail param) {
        Long id = param.getId();
        Long hotelId = param.getHotelId();
        String roomNo = param.getRoomNo().trim();
        TbHotelDetail hotel = hotelDetailRepository.findOne(hotelId);
        if (hotel == null || hotel.getHotelState() != 0) {
            throw new BusinessException("酒店已被下架！");
        }
        if (id == null) { // 新增
            int  c = hotelRoomDetailRepository.countByRoomNo(roomNo);
            if (c > 0) {
                throw new BusinessException("房间编号重复！");
            }
        } else { // 修改
            int  c = hotelRoomDetailRepository.countByRoomNo(id, roomNo);
            if (c > 0) {
                throw new BusinessException("房间编号重复！");
            }
        }
        hotelRoomDetailRepository.save(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void opert(Long id, Integer state) {
        TbHotelRoomDetail roomDetail = hotelRoomDetailRepository.findOne(id);
        if (roomDetail == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (!roomDetail.getHotelId().equals(user.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        if (state == 0) { // 上架
            if (roomDetail.getRoomUpshelfState() == 0) {
                throw new BusinessException("该房间原已处于上架状态！");
            }
            hotelRoomDetailRepository.updateRoomUpshelfState(id, 1, 0);
        } else if (state == 1) { // 下架
            if (roomDetail.getRoomUpshelfState() == 1) {
                throw new BusinessException("该房间原已处于下架状态！");
            }
            hotelRoomDetailRepository.updateRoomUpshelfState(id, 0, 1);
        } else if (state == 2) { // 删除
            hotelRoomDetailRepository.deleteById(id);
        }
    }

    @Override
    public PageList<TbHotelRoomDetail> searchForEnter(HotelRoomRequest param) throws Exception {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null) {
            throw new BusinessException(ResponseResultCode.SERVER_ERROR);
        }
        param.setHotelId(user.getHotelId());
        return hotelRoomDetailRepositoryCustom.listForEnter(param);
    }

    @Override
    public List<TbHotelRoomType> listHotelRoomType() {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        List<TbHotelRoomType> resList = new ArrayList<>();
        TbHotelRoomType choose = new TbHotelRoomType();
        choose.setId(-1L);
        choose.setRoomTypeName("请选择");
        resList.add(choose);
        List<TbHotelRoomType> list = hotelRoomTypeRepository.listByHotelId(user.getHotelId());
        if (list != null) {
            resList.addAll(list);
        }
        return resList;
    }
}