package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.repository.EmployerSalaryPaidHistoryRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EmployerSalaryPaidHistoryRepositoryCustomImpl extends BaseRepositoryImpl implements EmployerSalaryPaidHistoryRepositoryCustom {

    @Override
    public PageList<TbEmployerSalaryPaidHistory> list(Long hotelId, String employerFullName, String employerPhone, Integer pageNo, Integer pageSize) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT a.full_name fullName, a.phone, a.employer_state employerState, b.paid_money paidMoney, b.remark ");
        sql.append(" FROM tb_employer a join tb_employer_salary_paid_history b on a.id = b.employer_id and a.del_flag = 0 and b.del_flag = 0 ");
        sql.append(" WHERE a.hotel_id = ? ");
        paramList.add(hotelId);
        if (StringUtil.isNotEmpty(employerFullName)) {
            sql.append(" AND instr(a.full_name, ?) > 0 ");
            paramList.add(employerFullName);
        }
        if (StringUtil.isNotEmpty(employerPhone)) {
            sql.append(" AND instr(a.phone, ?) > 0 ");
            paramList.add(employerPhone);
        }
        return queryPage(sql.toString(), TbEmployerSalaryPaidHistory.class, new PageUtil(pageNo, pageSize), null, paramList.toArray());
    }
}