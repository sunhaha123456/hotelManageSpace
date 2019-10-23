package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.repository.HotelRoomDetailRepository;
import com.rose.repository.HotelRoomDetailRepositoryCustom;
import com.rose.repository.SysUserRepository;
import com.rose.service.HotelRoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Slf4j
@Service
public class HotelRoomDetailServiceImpl implements HotelRoomDetailService {

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Inject
    private HotelRoomDetailRepositoryCustom hotelRoomDetailRepositoryCustom;

    @Inject
    private SysUserRepository sysUserRepository;

    @Inject
    private ValueHolder valueHolder;

    @Override
    public PageList<TbHotelRoomDetail> search(HotelRoomRequest param) throws Exception {
        return hotelRoomDetailRepositoryCustom.list(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        TbHotelRoomDetail roomDetail = hotelRoomDetailRepository.findOne(id);
        if (roomDetail == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (!roomDetail.getHotelId().equals(user.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        hotelRoomDetailRepository.delete(id);
    }
}