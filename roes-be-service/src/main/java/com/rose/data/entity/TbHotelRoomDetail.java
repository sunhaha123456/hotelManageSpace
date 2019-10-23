package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 描述：酒店房间详情
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_room_detail")
public class TbHotelRoomDetail extends BaseDataIdLong {

    // 酒店id
    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    // 房间编号
    @Column(name = "room_no", columnDefinition = "varchar(255) COMMENT '房间编号'")
    private String roomNo;

    // 房间状态
    // 0：房间不可入住，指房间目前不具备入住条件
    // 1：房间无人入住，指房间可以入住，并且还未有人入住
    // 2：房间还未住满，指房间已有人入住，但仍可继续入住
    // 3：房间已经住满
    @Column(name = "room_state", columnDefinition = "Int(10) default 0 COMMENT '房间状态'")
    private Integer roomState;

    @Column(name = "room_type_id", columnDefinition = "Int(20) COMMENT '房间种类id'")
    private Long roomTypeId;

    // 房间类别名称
    @Transient
    private String roomTypeName;

    // 有无窗户 0：无，1：有窗户，但不能看到海，2：有窗户，且能看到海
    @Column(name = "room_window_flag", columnDefinition = "Int(10) default 0 COMMENT '有无窗户'")
    private Integer roomWindowFlag;

    // 有无独立卫生间 0：无，1：有
    @Column(name = "room_wc_flag", columnDefinition = "Int(10) default 1 COMMENT '有无独立卫生间'")
    private Integer roomWcFlag;

    // 床铺数量
    @Column(name = "bed_num", columnDefinition = "Int(10) default 1 COMMENT '床铺数量'")
    private Integer bedNum;

    // 销售价，单位：元
    @Column(name = "sell_price")
    private BigDecimal sellPrice;

    // 销售价描述
    @Column(name = "sell_price_desc", columnDefinition = "varchar(255) COMMENT '销售价描述'")
    private String sellPriceDesc;

    // 退房规则描述
    @Column(name = "check_out_desc", columnDefinition = "varchar(500) COMMENT '退房规则描述'")
    private String checkOutDesc;

    // 房间整体描述
    @Column(name = "room_overall_desc", columnDefinition = "varchar(500) COMMENT '房间整体描述'")
    private String roomOverallDesc;
}