package com.rose.service.impl;

import com.rose.repository.HotelCustomerCheckInOrderRepository;
import com.rose.repository.HotelCustomerCheckInOrderRepositoryCustom;
import com.rose.repository.HotelRoomDetailRepository;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class CustomerCheckInServiceImpl implements CustomerCheckInService {

    @Inject
    private HotelCustomerCheckInOrderRepositoryCustom hotelCustomerCheckInOrderRepositoryCustom;

    @Inject
    private HotelCustomerCheckInOrderRepository hotelCustomerCheckInOrderRepository;

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;



}