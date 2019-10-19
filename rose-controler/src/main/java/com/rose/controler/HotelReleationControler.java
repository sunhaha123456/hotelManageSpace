package com.rose.controler;

import com.rose.service.HotelReleationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * 功能：酒店关联 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelReleation")
public class HotelReleationControler {

    @Inject
	private HotelReleationService hotelReleationService;

}