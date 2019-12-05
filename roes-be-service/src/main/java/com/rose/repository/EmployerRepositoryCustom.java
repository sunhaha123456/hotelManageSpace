package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbEmployer;

public interface EmployerRepositoryCustom extends BaseRepository {
    /**
     * 功能：员工条件分页查询
     * @param hotelId
     * @param fullName
     * @param phone
     * @param employerState
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbEmployer> list(Long hotelId, String fullName, String phone, Integer employerState, Integer pageNo, Integer pageSize) throws Exception;
}