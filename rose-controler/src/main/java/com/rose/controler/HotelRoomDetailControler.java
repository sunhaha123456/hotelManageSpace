package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.service.HotelRoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：酒店房间 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelRoomDetail")
public class HotelRoomDetailControler {

    @Inject
    private HotelRoomDetailService hotelRoomDetailService;

    @PostMapping(value= "/search")
    public PageList<TbHotelRoomDetail> search(@RequestBody HotelRoomRequest param) throws Exception {
        return hotelRoomDetailService.search(param);
    }

    // 功能：删除房间
    @GetMapping(value = "/operationDelete")
    public void delete(@RequestParam Long id) {
        hotelRoomDetailService.delete(id);
    }
}