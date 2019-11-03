package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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

    @GetMapping(value= "/getFloorList")
    public List getFloorList() {
        return customerCheckInService.getFloorList();
    }

    @PostMapping(value= "/searchByFloor")
    public PageList<TbHotelRoomDetail> searchByFloor(@RequestBody HotelRoomRequest param) throws Exception {
        if (param == null || param.getRoomFloorNum() == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        return customerCheckInService.searchByFloor(param);
    }
}