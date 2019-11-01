package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.service.HotelRoomDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * 功能：酒店房间录入 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/hotelRoomEnter")
public class HotelRoomEnterControler {

    @Inject
    private HotelRoomDetailService hotelRoomDetailService;

    /**
     * 功能：查询
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping(value= "/search")
    public PageList<TbHotelRoomDetail> search(@RequestBody HotelRoomRequest param) throws Exception {
        if (param == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        return hotelRoomDetailService.searchForMerchEnter(param);
    }

    /**
     * 功能：详情
     * @param id
     * @return
     */
    @GetMapping(value= "/getDetail")
    public TbHotelRoomDetail getDetail(@RequestParam Long id) {
        return hotelRoomDetailService.getDetail(id);
    }

    /**
     * 功能：新增 / 修改
     * @param param
     */
    @PostMapping(value= "/save")
    public void save(@RequestBody TbHotelRoomDetail param) {
        if (param == null || StringUtil.isEmpty(param.getRoomNo()) || param.getRoomFloorNum() == null || param.getBedNum() == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        hotelRoomDetailService.save(param);
    }

    /**
     * 功能：操作酒店房间
     * 备注：只有下架才能删除
     * @param id
     * @param state 0：上架 1：下架 2：删除
     */
    @GetMapping(value= "/opert")
    public void opert(@RequestParam Long id, @RequestParam Integer state) {
        hotelRoomDetailService.opert(id, state);
    }

    /**
     * 功能：获取酒店房间类别
     * @return
     */
    @GetMapping(value= "/listHotelRoomTypeByCurrentUserReleatHotel")
    public List<TbHotelRoomType> listHotelRoomType() {
        return hotelRoomDetailService.listHotelRoomTypeByCurrentUserReleatHotel();
    }
}