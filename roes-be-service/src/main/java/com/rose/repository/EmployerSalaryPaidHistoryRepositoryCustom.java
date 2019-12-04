package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;

public interface EmployerSalaryPaidHistoryRepositoryCustom extends BaseRepository {

    /**
     * 功能：薪资发放条件分页查询
     * @param hotelId
     * @param employerFullName
     * @param employerPhone
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbEmployerSalaryPaidHistory> list(Long hotelId, String employerFullName, String employerPhone, Integer pageNo, Integer pageSize) throws Exception;
}