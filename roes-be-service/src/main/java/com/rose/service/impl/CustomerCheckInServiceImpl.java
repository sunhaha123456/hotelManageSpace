package com.rose.service.impl;

import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbSysUser;
import com.rose.repository.HotelCustomerCheckInOrderRepository;
import com.rose.repository.HotelCustomerCheckInOrderRepositoryCustom;
import com.rose.repository.HotelRoomDetailRepository;
import com.rose.repository.SysUserRepository;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    private SysUserRepository sysUserRepository;

    @Inject
    private ValueHolder valueHolder;

    @Override
    public List getFloorList() {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            return new ArrayList<>();
        }
        return hotelRoomDetailRepository.findFloorList(user.getHotelId());
    }
}