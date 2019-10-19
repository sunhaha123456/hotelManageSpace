package com.rose.data.entity;

import com.rose.common.data.base.BaseDataIdLong;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 描述：酒店
 * @author sunpeng
 */
@ToString(callSuper = true)
@lombok.Data
@Entity
@Table(name = "tb_hotel_detail")
public class TbHotelDetail extends BaseDataIdLong {

    @Column(name = "hotel_name", columnDefinition = "varchar(255) COMMENT '酒店名称'")
    private String hotelName;

    @Column(name = "hotel_remark", columnDefinition = "varchar(500) COMMENT '酒店备注'")
    private String hotelRemark;
}