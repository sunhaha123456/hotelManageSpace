package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

@Data
public class EmployerSearchRequest extends PageParam {
    private String fullName;
    private String phone;
    private Integer employerState;
    private String salaryDate;
}