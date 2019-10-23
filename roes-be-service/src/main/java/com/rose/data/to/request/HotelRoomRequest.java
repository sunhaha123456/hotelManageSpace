package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HotelRoomRequest extends PageParam {
    private String roomName;    // 房间名称
    private Date checkInDate;   // 入住时间
    private Date checkOutDate;  // 退房时间
    private Long roomTypeId;    // 房间类别
    private Integer roomWindowFlag; // 有无窗户 0：无，1：有窗户，但不能看到海，2：有窗户，且能看到海
    private Integer roomWcFlag; // 有无独立卫生间 0：无，1：有
    private Integer bedNum;     // 床铺数量
    private BigDecimal sellPriceStart;  // 销售价格范围，开始
    private BigDecimal sellPriceEnd;    // 销售价格范围，结束
}