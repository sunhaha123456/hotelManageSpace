package com.rose.data.to.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rose.data.base.PageParam;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HotelRoomRequest extends PageParam {
    private Long hotelId;                // 酒店id
    private Long roomId;                 // 房间id
    private String roomNo;               // 房间编号
    private Integer roomFloorNum;       // 房间楼层
    private Long roomTypeId;            // 房间类别
    private Integer roomUpshelfState;  // 房间上下架状态 0：上架 1：下架
    private Integer roomWindowFlag;    // 有无窗户 0：无，1：有窗户，但不能看到海，2：有窗户，且能看到海
    private Integer roomWcFlag;         // 有无独立卫生间 0：无，1：有
    private Integer bedNum;             // 床铺数量
    private String planCheckInDate;      // 计划入住时间
    private String planCheckOutDate;     // 计划退房时间
    private BigDecimal sellPriceStart;  // 销售价格范围，开始
    private BigDecimal sellPriceEnd;    // 销售价格范围，结束
}