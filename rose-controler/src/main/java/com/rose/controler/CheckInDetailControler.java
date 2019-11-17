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

    /**
     * 功能：调整入住信息
     * @param param
     * @throws Exception
     */
    @PostMapping(value= "/updateOrderInfo")
    public void updateOrderInfo(@RequestBody TbHotelCustomerCheckInOrder param) throws Exception {
        customerCheckInService.updateOrderInfo(param);
    }

    /**
     * 功能：取消
     * @param id
     * @throws Exception
     */
    @GetMapping(value= "/cancleOrder")
    public void cancleOrder(@RequestParam Long id) {
        customerCheckInService.cancleOrder(id);
    }

    /**
     * 功能：已入住退房
     * @param param
     * @throws Exception
     */
    @PostMapping(value= "/checkInCheckOut")
    public void checkInCheckOut(@RequestBody TbHotelCustomerCheckInOrder param) throws Exception {
        customerCheckInService.checkInCheckOut(param);
    }
}