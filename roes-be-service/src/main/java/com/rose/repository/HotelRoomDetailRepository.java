package com.rose.repository;

import com.rose.data.entity.TbHotelRoomDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRoomDetailRepository extends CrudRepository<TbHotelRoomDetail, Long> {

    @Query(value = "select * from tb_hotel_room_detail where room_type_id = :roomTypeId", nativeQuery = true)
    List<TbHotelRoomDetail> findRoomIdList(@Param(value = "roomTypeId") Long roomTypeId);

    @Modifying
    @Query(value = "update tb_hotel_room_detail set room_type_id = :roomIypeId where id in :idList", nativeQuery = true)
    int updateRoomTypeIdByIdList(@Param(value = "idList") List<Long> idList, @Param(value = "roomIypeId") Long roomIypeId);
}