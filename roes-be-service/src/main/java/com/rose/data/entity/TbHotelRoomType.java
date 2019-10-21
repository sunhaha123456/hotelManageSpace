package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 描述：房间类别
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_room_type")
public class TbHotelRoomType extends BaseDataIdLong {

    @Column(name = "hotel_id", columnDefinition = "Int(20) COMMENT '酒店id'")
    private Long hotelId;

    @Column(name = "room_type_name", columnDefinition = "varchar(255) COMMENT '房间类别名称'")
    private String roomTypeName;

    @Column(name = "remark", columnDefinition = "varchar(500) COMMENT '房间类别备注'")
    private String remark;

    @Transient
    private String hotelName;
}