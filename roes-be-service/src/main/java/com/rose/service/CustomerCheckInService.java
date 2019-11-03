package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;

import java.util.List;

/**
 * 功能：客人入住 service
 * @author sunpeng
 * @date 2019
 */
public interface CustomerCheckInService {

    List getFloorList();

    PageList<TbHotelRoomDetail> searchByFloor(HotelRoomRequest param) throws Exception;
}