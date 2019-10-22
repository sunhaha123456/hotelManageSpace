package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomTypeSearchRequest;
import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：房间类别 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelRoomType")
public class HotelRoomTypeControler {

    @Inject
	private HotelRoomTypeService hotelRoomTypeService;

    @GetMapping(value= "/getDetail")
    public TbHotelRoomType getDetail(@RequestParam Long id) {
        return hotelRoomTypeService.getDetail(id);
    }

    @PostMapping(value= "/search")
    public PageList<TbHotelRoomType> search(@RequestBody HotelRoomTypeSearchRequest param) throws Exception {
        return hotelRoomTypeService.search(param);
    }

    @PostMapping(value= "/save")
    public void save(@RequestBody TbHotelRoomType param) {
        if (param == null || param.getHotelId() == null || StringUtil.isEmpty(param.getRoomTypeName())) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        hotelRoomTypeService.save(param);
    }

    @GetMapping(value= "/delete")
    public void delete(@RequestParam Long id) {
        hotelRoomTypeService.delete(id);
    }
}