package com.rose.service.impl;

import com.rose.repository.HotelRoomTypeRepository;
import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class HotelRoomTypeServiceImpl implements HotelRoomTypeService {

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

}