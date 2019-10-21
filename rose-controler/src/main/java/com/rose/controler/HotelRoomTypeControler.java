package com.rose.controler;

import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * 功能：房间类别 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelRoomType")
public class HotelRoomTypeControler {

    @Inject
	private HotelRoomTypeService hotelRoomTypeService;

}