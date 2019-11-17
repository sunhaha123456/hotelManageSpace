package com.rose.repository;

import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HotelCustomerCheckInOrderRepository extends CrudRepository<TbHotelCustomerCheckInOrder, Long> {

    @Query(value = "select * from tb_hotel_customer_check_in_order where hotel_id = :hotelId and room_id in :roomIdList " +
                   "and order_status in(0,2) and :planCheckInDate < lock_end_date and :planCheckOutDate > lock_start_date " +
                   "order by room_id,lock_start_date", nativeQuery = true)
    List<TbHotelCustomerCheckInOrder> listByCheckOutDate(@Param("hotelId") Long hotelId,
                                                         @Param("roomIdList") List<Long> roomIdList,
                                                         @Param("planCheckInDate") Date planCheckInDate,
                                                         @Param("planCheckOutDate") Date planCheckOutDate);

    @Modifying
    @Query(value = "update tb_hotel_customer_check_in_order set merch_order_remark = :merchOrderRemark where id = :id", nativeQuery = true)
    int updateRemark(@Param(value = "id") Long id, @Param(value = "merchOrderRemark") String merchOrderRemark);
}