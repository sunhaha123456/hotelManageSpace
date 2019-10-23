package com.rose.controler;

import com.rose.service.HotelCustomerCheckInOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * 功能：房间入住 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelCustomerCheckInOrder")
public class HotelCustomerCheckInOrderlControler {

    @Inject
	private HotelCustomerCheckInOrderService hotelCustomerCheckInOrderService;

}