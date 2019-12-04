package com.rose.repository;

import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployerSalaryPaidHistoryRepository extends CrudRepository<TbEmployerSalaryPaidHistory, Long> {

    @Modifying
    @Query(value = "update tb_employer_salary_paid_history set del_flag = 1 where employer_id = :employerId", nativeQuery = true)
    int deleteEmployerSalaryByEmployerId(@Param(value = "employerId") Long employerId);

    @Modifying
    @Query(value = "update tb_employer_salary_paid_history set del_flag = 1 where id = :id", nativeQuery = true)
    int deleteEmployerSalaryById(@Param(value = "id") Long id);
}