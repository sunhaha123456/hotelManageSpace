package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.CheckInDetailSearchRequest;
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

    List<TbHotelCustomerCheckInOrder> getRoomCheckInDetail(Long roomId) throws Exception;

    void handleCustomerCheckIn(TbHotelCustomerCheckInOrder param);

    PageList<TbHotelCustomerCheckInOrder> searchOrder(CheckInDetailSearchRequest param) throws Exception;

    TbHotelCustomerCheckInOrder getDetail(Long id);

    void updateOrderInfo(TbHotelCustomerCheckInOrder param);

    void cancleOrder(Long id, String merchRemark);

    void checkInCheckOut(TbHotelCustomerCheckInOrder param);
}