package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：酒店房间标签表
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_room_tag")
public class TbHotelRoomTag extends BaseDataIdLong {

    // 标签名
    @Column(name = "tag_name", columnDefinition = "varchar(255) COMMENT '标签名'")
    private String tagName;

    // 标签描述
    @Column(name = "tag_desc", columnDefinition = "varchar(500) COMMENT '标签描述'")
    private String tagDesc;
}