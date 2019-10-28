package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.data.base.PageParam;
import com.rose.data.entity.TbHotelDetail;
import com.rose.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：运营端 酒店管理 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/operationHotel")
public class OperationHotelControler {

    @Inject
	private HotelService hotelService;

    @GetMapping(value= "/getDetail")
    public TbHotelDetail getDetail(@RequestParam Long id) {
        return hotelService.getDetail(id);
    }

    @PostMapping(value= "/search")
    public PageList<TbHotelDetail> search(@RequestBody PageParam param) throws Exception {
        return hotelService.search(param);
    }

    @GetMapping(value= "/listAll")
    public Object listAll() {
        return hotelService.listAll();
    }

    @PostMapping(value= "/save")
    public void save(@RequestBody TbHotelDetail param) {
        if (param == null || StringUtil.isEmpty(param.getHotelName())) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        hotelService.save(param);
    }

    /**
     * 功能：操作酒店状态
     * 备注：当下架时，该酒店关联的用户，处于登录状态的用户，将会失效
     * @param id
     * @param state 0：上架 1：下架
     */
    @GetMapping(value= "/opert")
    public void opert(@RequestParam Long id, @RequestParam Integer state) {
        hotelService.opert(id, state);
    }
}