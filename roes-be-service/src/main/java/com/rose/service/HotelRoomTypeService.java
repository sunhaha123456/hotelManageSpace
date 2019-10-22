package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomTypeSearchRequest;

/**
 * 功能：房间类别 service
 * @author sunpeng
 * @date 2019
 */
public interface HotelRoomTypeService {

    TbHotelRoomType getDetail(Long id);

    PageList<TbHotelRoomType> search(HotelRoomTypeSearchRequest param) throws Exception;

    void save(TbHotelRoomType param);

    void delete(Long id);
}