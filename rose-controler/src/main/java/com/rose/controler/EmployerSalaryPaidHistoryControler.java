package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.to.request.EmployerSalaryPaidHistorySearchRequest;
import com.rose.service.EmployerManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * 功能：发薪详情 controller
 * @author sunpeng
 * @date 2019
 */
@Slf4j
@RestController
@RequestMapping("/user/employerEnter")
public class EmployerSalaryPaidHistoryControler {

    @Inject
    private EmployerManageService employerManageService;

    @PostMapping(value= "/search")
    public PageList<TbEmployerSalaryPaidHistory> search(@RequestBody EmployerSalaryPaidHistorySearchRequest param) throws Exception {
        return employerManageService.searchEmployerPaidHistory(param);
    }

    @GetMapping(value= "/delete")
    public void delete(@RequestParam Long id) throws Exception {
        employerManageService.deleteEmployerPaidHistory(id);
    }
}