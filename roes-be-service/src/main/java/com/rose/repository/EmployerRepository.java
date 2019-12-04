package com.rose.repository;

import com.rose.data.entity.TbEmployer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployerRepository extends CrudRepository<TbEmployer, Long> {

    @Query(value = "select * from tb_employer where hotel_id = :hotelId and id = :id and del_flag = 0", nativeQuery = true)
    TbEmployer findEmployer(@Param(value = "hotelId") Long hotelId, @Param(value = "id") Long id);

    @Modifying
    @Query(value = "update tb_employer set del_flag = 1 where hotel_id = :hotelId and id = :id", nativeQuery = true)
    int deleteEmployer(@Param(value = "hotelId") Long hotelId, @Param(value = "id") Long id);
}