package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbEmployer;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.to.request.EmployerSearchRequest;
import com.rose.service.EmployerManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：员工录入 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/employerEnter")
public class EmployerEnterControler {

    @Inject
    private EmployerManageService employerManageService;

    @PostMapping(value= "/search")
    public PageList<TbEmployer> search(@RequestBody EmployerSearchRequest param) throws Exception {
        return employerManageService.searchEmployer(param);
    }

    @GetMapping(value= "/getDetail")
    public TbEmployer getDetail(@RequestParam Long id) throws Exception {
        return employerManageService.getDetail(id);
    }

    @PostMapping(value= "/save")
    public void save(@RequestBody TbEmployer param) throws Exception {
        if (param == null || StringUtil.isEmpty(param.getFullName()) || StringUtil.isEmpty(param.getPhone())) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        employerManageService.saveEmployer(param);
    }

    @GetMapping(value= "/delete")
    public void delete(@RequestParam Long id) throws Exception {
        employerManageService.deleteEmployer(id);
    }

    @PostMapping(value= "/paySalary")
    public void paySalary(@RequestBody TbEmployerSalaryPaidHistory param) throws Exception {
        if (param == null || param.getEmployerId() == null || param.getPaidMoney() == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        employerManageService.paySalary(param);
    }
}