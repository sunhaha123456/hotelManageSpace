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
    List<TbHotelCustomerCheckInOrder> listByCheckInOutDate(@Param("hotelId") Long hotelId,
                                                           @Param("roomIdList") List<Long> roomIdList,
                                                           @Param("planCheckInDate") Date planCheckInDate,
                                                           @Param("planCheckOutDate") Date planCheckOutDate);

    @Query(value =  "select * from tb_hotel_customer_check_in_order where hotel_id = :hotelId and room_id in :roomIdList " +
                    "and order_status in(0,2) and :planCheckInDate < lock_end_date and :planCheckOutDate > lock_start_date " +
                    "and id != :orderId " +
                    "order by room_id,lock_start_date", nativeQuery = true)
    List<TbHotelCustomerCheckInOrder> listByCheckInOutDate(@Param("hotelId") Long hotelId,
                                                           @Param("roomIdList") List<Long> roomIdList,
                                                           @Param("planCheckInDate") Date planCheckInDate,
                                                           @Param("planCheckOutDate") Date planCheckOutDate,
                                                           @Param("orderId") Long orderId);

    @Modifying
    @Query(value = "update tb_hotel_customer_check_in_order set order_status = :orderStatusNew where id = :id and order_status = :orderStatusOld", nativeQuery = true)
    int updateStatus(@Param("id") Long id, @Param("orderStatusNew") Integer orderStatusNew, @Param("orderStatusOld") Integer orderStatusOld);

    @Modifying
    @Query(value = "update tb_hotel_customer_check_in_order set order_status = 3, merch_order_remark = :merchRemark where id = :id and order_status in (0, 1, 2) ", nativeQuery = true)
    int cancelOrder(@Param("id") Long id, @Param("merchRemark") String merchRemark);
}