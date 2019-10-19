package com.rose.service.impl;

import com.rose.repository.HotelDetailRepository;
import com.rose.service.HotelReleationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class HotelReleationServiceImpl implements HotelReleationService {

    @Inject
    private HotelDetailRepository hotelDetailRepository;

}