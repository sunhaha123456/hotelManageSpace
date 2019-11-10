package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;

public interface HotelCustomerCheckInOrderRepositoryCustom extends BaseRepository {

    /**
     * 功能：分页查询
     * @param hotelId
     * @param roomId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbHotelCustomerCheckInOrder> listByHotelIdAndRoomId(Long hotelId, Long roomId, Integer pageNo, Integer pageSize) throws Exception;
}