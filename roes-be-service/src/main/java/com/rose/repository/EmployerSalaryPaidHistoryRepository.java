package com.rose.repository;

import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface EmployerSalaryPaidHistoryRepository extends CrudRepository<TbEmployerSalaryPaidHistory, Long> {

    @Modifying
    @Query(value = "update tb_employer_salary_paid_history set del_flag = 1 where employer_id = :employerId", nativeQuery = true)
    int deleteEmployerSalaryByEmployerId(@Param(value = "employerId") Long employerId);

    @Modifying
    @Query(value = "update tb_employer_salary_paid_history set del_flag = 1 where id = :id", nativeQuery = true)
    int deleteEmployerSalaryById(@Param(value = "id") Long id);

    @Query(value = "select * from tb_employer_salary_paid_history where employer_id in :employerIdSet and salary_date = :salaryDate and del_flag = 0", nativeQuery = true)
    List<TbEmployerSalaryPaidHistory> findEmployerSalary(@Param(value = "employerIdSet") Set<Long> employerIdSet, @Param(value = "salaryDate") Date salaryDate);

    @Query(value = "select * from tb_employer_salary_paid_history where employer_id = :employerId and salary_date = :salaryDate and del_flag = 0 order by salary_date asc", nativeQuery = true)
    List<TbEmployerSalaryPaidHistory> findEmployerSalary(@Param(value = "employerId") Long employerId, @Param(value = "salaryDate") String salaryDate);
}