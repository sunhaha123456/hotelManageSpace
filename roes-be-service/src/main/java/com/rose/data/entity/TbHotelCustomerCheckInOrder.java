package com.rose.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述：酒店客人入住订单
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_customer_check_in_order")
public class TbHotelCustomerCheckInOrder extends BaseDataIdLong {

    // 订单编号
    @Column(name = "order_no", columnDefinition = "varchar(255) COMMENT '订单编号'")
    private String orderNo;

    // 酒店id
    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    // 房间id
    @Column(name = "room_id", columnDefinition = "Int(20) COMMENT '房间id'")
    private Long roomId;

    // 房间编号
    @Column(name = "room_no", columnDefinition = "varchar(255) COMMENT '房间编号'")
    private String roomNo;

    // 房间类型名称
    @Column(name = "room_type_name", columnDefinition = "varchar(255) COMMENT '房间类型'")
    private String roomTypeName;

    // 销售价，单位：元
    @Column(name = "sell_price", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '销售价'")
    private BigDecimal sellPrice;

    // 实收到押金，单位：元
    @Column(name = "deposit_money", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '实收到押金'")
    private BigDecimal depositMoney;

    // 销售价描述
    @Column(name = "sell_price_desc", columnDefinition = "varchar(255) COMMENT '销售价描述'")
    private String sellPriceDesc;

    // 退房规则描述
    @Column(name = "check_out_desc", columnDefinition = "varchar(500) COMMENT '退房规则描述'")
    private String checkOutDesc;

    // 房间整体描述
    @Column(name = "room_overall_desc", columnDefinition = "varchar(500) COMMENT '房间整体描述'")
    private String roomOverallDesc;

    @Column(name = "check_in_customer_name", columnDefinition = "varchar(255) COMMENT '入住客人姓名'")
    private String checkInCustomerName;

    @Column(name = "check_in_customer_link_phone", columnDefinition = "varchar(255) COMMENT '入住客人联系手机号'")
    private String checkInCustomerLinkPhone;

    @Column(name = "check_in_customer_id_no", columnDefinition = "varchar(255) COMMENT '入住客人身份证号'")
    private String checkInCustomerIdNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lock_start_date", columnDefinition = "datetime COMMENT '房间锁定开始时间'")
    private Date lockStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lock_end_date", columnDefinition = "datetime COMMENT '房间锁定结束时间'")
    private Date lockEndDate;

    // 实际收取金额，单位：元
    @Column(name = "real_collect_money", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '实际收取金额'")
    private BigDecimal realCollectMoney;

    // 订单类别 0：已到店直接入住类型订单 1：未到店预定入住类型订单
    @Column(name = "order_type", columnDefinition = "int(1) default 0 COMMENT '订单类别'")
    private Integer orderType;

    // 订单状态
    // 0：已入住
    // 1：已退房
    // 2：已预订
    // 3：已取消
    @Column(name = "order_status", columnDefinition = "int(1) default 0 COMMENT '订单状态'")
    private Integer orderStatus;

    // 商户对此订单备注
    @Column(name = "merch_order_remark", columnDefinition = "varchar(800) COMMENT '商户对此订单备注'")
    private String merchOrderRemark;

    // 计划入住时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    private Date planCheckInDate;

    // 计划退房时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Transient
    private Date planCheckOutDate;
}