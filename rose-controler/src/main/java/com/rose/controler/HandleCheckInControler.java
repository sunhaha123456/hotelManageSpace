package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.DateUtil;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.service.CustomerCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
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
        if (param == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        if (param.getRoomFloorNum() == null) {
            throw new BusinessException("请选择楼层！");
        }
        Date planCheckInDate = param.getPlanCheckInDate();
        Date planCheckOutDate = param.getPlanCheckOutDate();
        if (planCheckInDate == null) {
            throw new BusinessException("请选择入住时间！");
        }
        if (planCheckOutDate == null) {
            throw new BusinessException("请选择退房时间！");
        }
        Date now = new Date();
        Date today_12 = DateUtil.formatStr2Time(DateUtil.getCurrentDate() + " 12:00:00");
        if (now.getTime() < today_12.getTime() &&  planCheckInDate.getTime() <= now.getTime()) {
            throw new BusinessException("入住时间必须晚于当前时间！");
        }
        if (planCheckOutDate.getTime() < planCheckInDate.getTime()) {
            throw new BusinessException("退房时间必须晚于入住时间！");
        }
        return customerCheckInService.searchByFloor(param);
    }

    @PostMapping(value= "/getRoomCheckInDetail")
    public PageList<TbHotelCustomerCheckInOrder> getRoomCheckInDetail(@RequestBody HotelRoomRequest param) throws Exception {
        return customerCheckInService.getRoomCheckInDetail(param);
    }
}