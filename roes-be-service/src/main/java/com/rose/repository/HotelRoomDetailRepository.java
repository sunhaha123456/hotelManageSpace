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

    @Modifying
    @Query(value = "update tb_hotel_room_detail set room_upshelf_state = :newRoomUpshelfState where id = :id and room_upshelf_state = :oldRoomUpshelfState", nativeQuery = true)
    int updateRoomUpshelfState(@Param(value = "id") Long id, @Param(value = "oldRoomUpshelfState") Integer oldRoomUpshelfState, @Param(value = "newRoomUpshelfState") Integer newRoomUpshelfState);

    @Modifying
    @Query(value = "delete from tb_hotel_room_detail where id = :id and room_upshelf_state = 1", nativeQuery = true)
    int deleteById(@Param(value = "id") Long id);

    @Query(value = "select count(1) from tb_hotel_room_detail where room_no = :roomNo", nativeQuery = true)
    int countByRoomNo(@Param(value = "roomNo") String roomNo);

    @Query(value = "select count(1) from tb_hotel_room_detail where id != :id and room_no = :roomNo", nativeQuery = true)
    int countByRoomNo(@Param(value = "id") Long id, @Param(value = "roomNo") String roomNo);
}