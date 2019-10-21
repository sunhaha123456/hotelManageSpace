package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

@Data
public class HotelRoomTypeSearchRequest extends PageParam {
    private Long hotelId;
}