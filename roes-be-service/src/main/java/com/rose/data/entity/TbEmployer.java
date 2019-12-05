package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLongDelFlag;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_employer")
public class TbEmployer extends BaseDataIdLongDelFlag implements Serializable {

    // 姓名
    @Column(name = "full_name", columnDefinition = "varchar(255) COMMENT '姓名'")
    private String fullName;

    // 手机号
    @Column(name = "phone", columnDefinition = "varchar(255) COMMENT '手机号'")
    private String phone;

    // 酒店id
    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    // 职位
    @Column(name = "position", columnDefinition = "varchar(255) COMMENT '职位'")
    private String position;

    // 0：在职 1：离职
    @Column(name = "employer_state", columnDefinition = "Int(1) default 0 COMMENT '用户状态'")
    private Integer employerState;

    // 是否发放过薪资 0：未发放过 1：已发放过
    @Transient
    private Integer doSalaryState = 0;
}