package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：酒店房间 与 标签关联表
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_room_tag_relation")
public class TbHotelRoomTagRelation extends BaseDataIdLong {

    @Column(name = "room_id", columnDefinition = "Int(20) COMMENT '房间id'")
    private Long roomId;

    @Column(name = "room_tag_id", columnDefinition = "Int(20) COMMENT '标签id'")
    private Long roomTagId;
}