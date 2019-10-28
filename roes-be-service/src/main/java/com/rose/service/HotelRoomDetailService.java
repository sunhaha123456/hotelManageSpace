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

    PageList<TbHotelRoomDetail> searchForEnter(HotelRoomRequest param) throws Exception;

    List<TbHotelRoomType> listHotelRoomType();
}