package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.to.request.EmployerSalaryPaidHistorySearchRequest;
import com.rose.service.EmployerManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：发薪流水 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/employerSalaryHistory")
public class EmployerSalaryHistoryControler {

    @Inject
    private EmployerManageService employerManageService;

    @PostMapping(value= "/search")
    public PageList<TbEmployerSalaryPaidHistory> search(@RequestBody EmployerSalaryPaidHistorySearchRequest param) throws Exception {
        if (param == null) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        return employerManageService.searchEmployerPaidHistory(param);
    }

    @GetMapping(value= "/delete")
    public void delete(@RequestParam Long id) throws Exception {
        employerManageService.deleteEmployerPaidHistory(id);
    }
}