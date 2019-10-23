package com.rose.service.impl;

import com.rose.repository.HotelRoomDetailRepository;
import com.rose.repository.HotelRoomDetailRepositoryCustom;
import com.rose.service.HotelRoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class HotelRoomDetailServiceImpl implements HotelRoomDetailService {

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Inject
    private HotelRoomDetailRepositoryCustom hotelRoomDetailRepositoryCustom;

}