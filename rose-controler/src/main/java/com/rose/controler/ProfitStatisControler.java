package com.rose.controler;

import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.to.request.CheckInDetailSearchRequest;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

/**
 * 功能：营收统计 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/profitStatis")
public class ProfitStatisControler {

    @Inject
	private CustomerCheckInService customerCheckInService;

    @PostMapping(value= "/searchOrder")
    public Map<String, Object> searchOrder(@RequestBody CheckInDetailSearchRequest param) throws Exception {
        return customerCheckInService.searchOrderForProfitStatis(param);
    }

    @GetMapping(value= "/getDetail")
    public TbHotelCustomerCheckInOrder getDetail(@RequestParam Long id) throws Exception {
        return customerCheckInService.getDetail(id);
    }
}