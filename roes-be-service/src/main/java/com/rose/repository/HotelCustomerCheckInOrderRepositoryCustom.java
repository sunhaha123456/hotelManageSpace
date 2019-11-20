package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.to.request.CheckInDetailSearchRequest;

import java.util.List;

public interface HotelCustomerCheckInOrderRepositoryCustom extends BaseRepository {

    /**
     * 功能：根据酒店房间id查询
     * @param hotelId
     * @param roomId
     * @return
     */
    List<TbHotelCustomerCheckInOrder> listByHotelIdAndRoomId(Long hotelId, Long roomId);

    /**
     * 功能：入住 条件分页查询
     * @return
     */
    PageList<TbHotelCustomerCheckInOrder> listByCondition(CheckInDetailSearchRequest param) throws Exception;

    /**
     * 功能：查询营收金额
     * @return
     */
    String getTotalRealCollectMoneyByCondition(CheckInDetailSearchRequest param) throws Exception;
}