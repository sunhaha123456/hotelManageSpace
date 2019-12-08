package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.HotelRoomTypeSearchRequest;
import com.rose.repository.HotelRoomDetailRepository;
import com.rose.repository.HotelRoomTypeRepository;
import com.rose.repository.HotelRoomTypeRepositoryCustom;
import com.rose.repository.SysUserRepository;
import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HotelRoomTypeServiceImpl implements HotelRoomTypeService {

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

    @Inject
    private HotelRoomTypeRepositoryCustom hotelRoomTypeRepositoryCustom;

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Inject
    private SysUserRepository sysUserRepository;

    @Inject
    private ValueHolder valueHolder;

    @Override
    public TbHotelRoomType getDetail(Long id) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(id);
        if (roomType == null) {
            throw new BusinessException("无对应酒店房间类别！");
        }
        if (!user.getHotelId().equals(roomType.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return roomType;
    }

    @Override
    public PageList<TbHotelRoomType> search(HotelRoomTypeSearchRequest param) throws Exception {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return hotelRoomTypeRepositoryCustom.list(user.getHotelId(), param.getPage(), param.getRows());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TbHotelRoomType param) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        if (param.getId() == null) {
            long c = hotelRoomTypeRepository.countByName(user.getHotelId(), param.getHotelName());
            if (0 < c) {
                throw new BusinessException("房间类别已存在！");
            }
            Date now = new Date();
            param.setCreateDate(now);
            param.setLastModified(now);
            param.setHotelId(user.getHotelId());
            hotelRoomTypeRepository.save(param);
        } else {
            TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(param.getId());
            if (roomType == null) {
                throw new BusinessException("房间类别不存在！");
            }
            if (!user.getHotelId().equals(roomType.getHotelId())) {
                throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
            }
            long c = hotelRoomTypeRepository.countByMenuName(user.getHotelId(), param.getHotelName(), param.getId());
            if (0 < c) {
                throw new BusinessException("房间类别已存在！");
            }
            roomType.setLastModified(new Date());
            roomType.setRoomTypeName(param.getRoomTypeName());
            roomType.setRemark(param.getRemark());
            hotelRoomTypeRepository.save(roomType);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(id);
        if (roomType == null) {
            throw new BusinessException("房间类别不存在！");
        }
        if (!user.getHotelId().equals(roomType.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        hotelRoomTypeRepository.delete(id);
        List<TbHotelRoomDetail> roomList = hotelRoomDetailRepository.findRoomIdList(id);
        if (roomList != null && roomList.size() > 0) {
            List<Long> roomIdList = roomList.stream().map(TbHotelRoomDetail::getId).collect(Collectors.toList());
            int c = hotelRoomDetailRepository.updateRoomTypeIdByIdList(roomIdList, null);
            if (c != roomIdList.size()) {
                throw new BusinessException(ResponseResultCode.OPERT_ERROR);
            }
        }
    }
}