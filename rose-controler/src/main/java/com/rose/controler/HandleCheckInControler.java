package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
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
        if (param == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        if (param.getRoomFloorNum() == null) {
            throw new BusinessException("请选择楼层！");
        }
        return customerCheckInService.searchByFloor(param);
    }

    @GetMapping(value= "/getRoomCheckInDetail")
    public List<TbHotelCustomerCheckInOrder> getRoomCheckInDetail(@RequestParam Long roomId) throws Exception {
        return customerCheckInService.getRoomCheckInDetail(roomId);
    }

    /**
     * 功能：办理客人入住
     * @param param
     * @throws Exception
     */
    @PostMapping(value= "/handleCustomerCheckIn")
    public void handleCustomerCheckIn(@RequestBody TbHotelCustomerCheckInOrder param) throws Exception {
        if (param == null || param.getRoomId() == null || param.getPlanCheckInDate() == null || param.getPlanCheckOutDate() == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        if (StringUtil.isEmpty(param.getCheckInCustomerName())) {
            throw new BusinessException("请填写客人姓名！");
        }
        if (param.getDepositMoney() == null) {
            throw new BusinessException("请填写实收押金金额！");
        }
        if (param.getOrderType() == null) {
            throw new BusinessException("请选择入住类型！");
        }
        customerCheckInService.handleCustomerCheckIn(param);
    }
}