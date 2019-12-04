package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

@Data
public class EmployerSalaryPaidHistorySearchRequest extends PageParam {
    private String employerFullName; //员工姓名
    private String employerPhone; //员工姓名
}