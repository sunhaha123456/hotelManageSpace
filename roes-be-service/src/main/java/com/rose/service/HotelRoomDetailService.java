package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomRequest;

import java.util.List;

/**
 * 功能：房间房间 service
 * @author sunpeng
 * @date 2019
 */
public interface HotelRoomDetailService {

    void save(TbHotelRoomDetail param);

    void opert(Long id, Integer state);

    /**
     * 功能：商户录入酒店房间
     * @param param
     * @return
     * @throws Exception
     */
    PageList<TbHotelRoomDetail> searchForMerchEnter(HotelRoomRequest param) throws Exception;

    TbHotelRoomDetail getDetail(Long id);

    List<TbHotelRoomType> listHotelRoomTypeByCurrentUserReleatHotel();
}