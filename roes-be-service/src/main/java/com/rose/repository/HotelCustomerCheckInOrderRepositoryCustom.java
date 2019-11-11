package com.rose.repository;

import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;

import java.util.List;

public interface HotelCustomerCheckInOrderRepositoryCustom extends BaseRepository {

    /**
     * 功能：条件查询
     * @param hotelId
     * @param roomId
     * @return
     * @throws Exception
     */
    List<TbHotelCustomerCheckInOrder> listByHotelIdAndRoomId(Long hotelId, Long roomId);
}