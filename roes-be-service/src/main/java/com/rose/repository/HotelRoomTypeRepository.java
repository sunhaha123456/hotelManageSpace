package com.rose.repository;

import com.rose.data.entity.TbHotelRoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HotelRoomTypeRepository extends CrudRepository<TbHotelRoomType, Long> {
    @Query(value = "select count(1) from tb_hotel_room_type where hotel_id = :hotelId and room_type_name = :roomTypeName", nativeQuery = true)
    long countByName(@Param("hotelId") Long hotelId, @Param(value = "roomTypeName") String roomTypeName);

    @Query(value = "select count(1) from tb_hotel_room_type where hotel_id = :hotelId and room_type_name = :roomTypeName and id != :id", nativeQuery = true)
    long countByMenuName(@Param("hotelId") Long hotelId, @Param(value = "roomTypeName") String roomTypeName, @Param("id") Long id);

}