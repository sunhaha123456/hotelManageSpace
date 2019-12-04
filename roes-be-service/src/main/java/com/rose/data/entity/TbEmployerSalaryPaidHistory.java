package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLongDelFlag;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_employer_salary_paid_history")
public class TbEmployerSalaryPaidHistory extends BaseDataIdLongDelFlag implements Serializable {

    // 员工id
    @Column(name = "employer_id", columnDefinition = "Int(20) COMMENT '员工id'")
    private String employerId;

    // 支付金额，单位：元
    @Column(name = "paid_money", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额'")
    private BigDecimal paidMoney;

    // 酒店id
    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    // 0：在职 1：离职
    @Column(name = "employer_state", columnDefinition = "Int(1) default 0 COMMENT '用户状态'")
    private Integer employerState;
}