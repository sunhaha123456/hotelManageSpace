package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    // 销售价描述
    @Column(name = "sell_price_desc", columnDefinition = "varchar(255) COMMENT '销售价描述'")
    private String sellPriceDesc;

    // 退房规则描述
    @Column(name = "check_out_desc", columnDefinition = "varchar(500) COMMENT '退房规则描述'")
    private String checkOutDesc;

    // 此单利润
    @Column(name = "profit_money", columnDefinition = "decimal(19,2) NOT NULL DEFAULT 0.00 COMMENT '房间类型'")
    private BigDecimal profitMoney;

    // 房间备注
    @Column(name = "room_remark", columnDefinition = "varchar(800) COMMENT '房间备注'")
    private String roomRemark;

    @Column(name = "check_in_customer_name", columnDefinition = "varchar(255) COMMENT '入住客户姓名'")
    private String checkInCustomerName;

    @Column(name = "check_in_customer_link_phone", columnDefinition = "varchar(255) COMMENT '入住客户联系手机号'")
    private String checkInCustomerLinkPhone;

    @Column(name = "check_in_customer_id_no", columnDefinition = "varchar(255) COMMENT '入住客户身份证号'")
    private String checkInCustomerIdNo;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in_date", columnDefinition = "datetime COMMENT '入住时间'")
    private Date checkInDate;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out_date", columnDefinition = "datetime COMMENT '退房时间'")
    private Date checkOutDate;
}