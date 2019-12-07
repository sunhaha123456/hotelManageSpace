package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbEmployer;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.to.request.EmployerSalaryPaidHistorySearchRequest;
import com.rose.data.to.request.EmployerSearchRequest;

import java.util.Date;
import java.util.List;

/**
 * 功能：员工管理 service
 * @author sunpeng
 * @date 2018
 */
public interface EmployerManageService {

    PageList<TbEmployer> searchEmployer(EmployerSearchRequest param) throws Exception;

    TbEmployer getDetail(Long id);

    void saveEmployer(TbEmployer param);

    void deleteEmployer(Long id);

    void paySalary(TbEmployerSalaryPaidHistory param);

    void deleteEmployerPaidHistory(Long id);

    PageList<TbEmployerSalaryPaidHistory> searchEmployerPaidHistory(EmployerSalaryPaidHistorySearchRequest param) throws Exception;

    List<TbEmployerSalaryPaidHistory> getSalaryDetail(Long employerId, String salaryDate);
}