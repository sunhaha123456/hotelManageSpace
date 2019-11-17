package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.to.request.CheckInDetailSearchRequest;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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
    public PageList<TbHotelCustomerCheckInOrder> searchOrder(@RequestBody CheckInDetailSearchRequest param) throws Exception {
        return customerCheckInService.searchOrder(param);
    }

    @GetMapping(value= "/getDetail")
    public TbHotelCustomerCheckInOrder getDetail(@RequestParam Long id) throws Exception {
        return customerCheckInService.getDetail(id);
    }

    @PostMapping(value= "/updateOrder")
    public void updateOrder(@RequestBody TbHotelCustomerCheckInOrder param) throws Exception {
        customerCheckInService.updateOrder(param);
    }
}