package com.rose.controler;

import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * 功能：入住详情 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/checkInDetail")
public class CheckInDetailControler {

    @Inject
	private CustomerCheckInService customerCheckInService;

    @PostMapping(value= "/searchOrder")
    public List searchOrder() {
        return customerCheckInService.getFloorList();
    }
}