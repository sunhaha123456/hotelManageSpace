package com.rose.controler;

import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * 功能：办理入住 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/handleCheckIn")
public class HandleCheckInControler {

    @Inject
	private CustomerCheckInService customerCheckInService;

}