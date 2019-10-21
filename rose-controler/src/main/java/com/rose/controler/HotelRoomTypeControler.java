package com.rose.controler;

import com.rose.common.data.base.PageList;
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
}