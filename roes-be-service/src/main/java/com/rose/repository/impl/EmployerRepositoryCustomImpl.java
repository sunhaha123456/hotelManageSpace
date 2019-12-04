package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.data.entity.TbEmployer;
import com.rose.repository.EmployerRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EmployerRepositoryCustomImpl extends BaseRepositoryImpl implements EmployerRepositoryCustom {

    @Override
    public PageList<TbEmployer> list(Long hotelId, Integer employerState, Integer pageNo, Integer pageSize) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT id, create_date createDate, full_name fullName, phone, position, employer_state employerState ");
        sql.append(" FROM tb_employer ");
        sql.append(" WHERE hotel_id = ? ");
        paramList.add(hotelId);
        if (employerState != null) {
            sql.append(" AND employer_state = ? ");
            paramList.add(employerState);
        }
        sql.append(" del_flag = 0 ");
        return queryPage(sql.toString(), TbEmployer.class, new PageUtil(pageNo, pageSize), null, paramList.toArray());
    }
}