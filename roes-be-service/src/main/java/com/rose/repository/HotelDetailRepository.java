package com.rose.repository;

import com.rose.data.entity.TbHotelDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelDetailRepository extends CrudRepository<TbHotelDetail, Long> {

    @Query(value = "select count(1) from tb_hotel_detail where hotel_name = :hotelName", nativeQuery = true)
    long countByName(@Param(value = "hotelName") String hotelName);

    @Query(value = "select count(1) from tb_hotel_detail where hotel_name = :hotelName and id != :id", nativeQuery = true)
    long countByMenuName(@Param(value = "hotelName") String hotelName, @Param("id") Long id);

    @Modifying
    @Query(value = "update tb_hotel_detail set hotel_state = :hotelState where id = :id", nativeQuery = true)
    int updateStateById(@Param(value = "id") Long id, @Param(value = "hotelState") Integer hotelState);

    @Query(value = "select * from tb_hotel_detail order by id asc", nativeQuery = true)
    List<TbHotelDetail> findAll();
}