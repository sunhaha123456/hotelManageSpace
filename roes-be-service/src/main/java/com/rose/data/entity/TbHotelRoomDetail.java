package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 描述：酒店房间详情
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_room_detail")
public class TbHotelRoomDetail extends BaseDataIdLong {

    // 房间编号
    @Column(name = "room_no", columnDefinition = "varchar(255) COMMENT '房间编号'")
    private String roomNo;

    // 房间面积
    @Column(name = "room_area_measure", columnDefinition = "varchar(255) COMMENT '房间面积'")
    private String roomAreaMeasure ;

    // 有无窗户 0：无，1：有
    @Column(name = "room_window_flag", columnDefinition = "Int(10) default 0 COMMENT '有无窗户'")
    private Integer roomWindowFlag;

    // 当有窗户时，房间窗户是否能看到海 0：看不到，1：能看到
    @Column(name = "room_window_see_sea_flag", columnDefinition = "Int(10) default 0 COMMENT '当有窗户时，房间窗户是否能看到海'")
    private Integer roomWindowSeeSeaFlag;

    // 有无独立卫生间 0：无，1：有
    @Column(name = "room_wc_flag", columnDefinition = "Int(10) default 1 COMMENT '有无独立卫生间'")
    private Integer roomWcFlag;

    // 床铺数量
    @Column(name = "bed_num", columnDefinition = "Int(10) default 1 COMMENT '床铺数量'")
    private Integer bedNum;

    // 销售价，单位：元
    @Column(name = "sell_price", columnDefinition = "Int(10) default 1 COMMENT '销售价'")
    private Integer sellPrice;

    // 成本价，单元：元
    @Column(name = "cost_price", columnDefinition = "Int(10) default 1 COMMENT '成本价'")
    private Integer costPrice;

    // 价格描述
    @Column(name = "price_desc", columnDefinition = "varchar(500) COMMENT '价格描述'")
    private String priceDesc;

    // 房间整体描述
    @Column(name = "room_overall_desc", columnDefinition = "varchar(500) COMMENT '房间整体描述'")
    private String roomOverallDesc;
}