package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;

/**
 * 功能：房间房间 service
 * @author sunpeng
 * @date 2019
 */
public interface HotelRoomDetailService {

    PageList<TbHotelRoomDetail> search(HotelRoomRequest param) throws Exception;

    void delete(Long id);
}