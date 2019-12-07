package com.rose.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rose.common.data.base.BaseDataIdLongDelFlag;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_employer_salary_paid_history")
public class TbEmployerSalaryPaidHistory extends BaseDataIdLongDelFlag implements Serializable {

    // 员工id
    @Column(name = "employer_id", columnDefinition = "Int(20) COMMENT '员工id'")
    private Long employerId;

    // 酒店id
    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    // 薪资月份，比如 2019-12，表示是19年12月薪资
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "salary_date", columnDefinition = "datetime COMMENT '薪资月份'")
    private Date salaryDate;

    @Transient
    private String salaryDateStr;

    // 支付金额，单位：元
    @Column(name = "paid_money", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额'")
    private BigDecimal paidMoney;

    // 备注
    @Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注'")
    private String remark;

    // 员工姓名
    @Transient
    private String fullName;

    // 手机号
    @Transient
    private String phone;

    // 职位
    @Transient
    private String position;

    // 0：在职 1：离职
    @Transient
    private Integer employerState;
}