package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.DateUtil;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbEmployer;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.to.request.EmployerSearchRequest;
import com.rose.service.EmployerManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * 功能：进行发薪 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/doEmployerSalary")
public class DoEmployerSalaryControler {

    @Inject
    private EmployerManageService employerManageService;

    @PostMapping(value= "/search")
    public PageList<TbEmployer> search(@RequestBody EmployerSearchRequest param) throws Exception {
        if (param == null || DateUtil.format(param.getSalaryDate(), DateUtil.YYYYMM) == null) {
            throw new BusinessException("工资年月时间格式错误！");
        }
        return employerManageService.searchEmployer(param);
    }

    @GetMapping(value= "/getSalaryPaidHistory")
    public List<TbEmployerSalaryPaidHistory> getSalaryDetail(@RequestParam Long employerId, @RequestParam(defaultValue = "") String salaryDate) {
        Date formatSalaryDate = DateUtil.format(salaryDate, DateUtil.YYYYMM);
        if (formatSalaryDate == null) {
            throw new BusinessException("工资年月时间格式错误！");
        }
        return employerManageService.getSalaryDetail(employerId, formatSalaryDate);
    }

    @PostMapping(value= "/paySalary")
    public void paySalary(@RequestBody TbEmployerSalaryPaidHistory param) throws Exception {
        if (param == null || param.getEmployerId() == null || param.getPaidMoney() == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        param.setSalaryDate(DateUtil.format(param.getSalaryDateStr(), DateUtil.YYYYMM));
        if (param.getSalaryDate() ==  null) {
            throw new BusinessException("工资年月时间格式错误！");
        }
        employerManageService.paySalary(param);
    }

    @GetMapping(value= "/deleteSalary")
    public void deleteSalary(@RequestParam Long id) throws Exception {
        employerManageService.deleteEmployerPaidHistory(id);
    }
}