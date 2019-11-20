package com.rose.data.to.request;

import com.rose.data.base.PageParam;
import lombok.Data;

@Data
public class CheckInDetailSearchRequest extends PageParam {
    private Long hotelId;
    private String roomNo;
    private String checkInCustomerName;
    private String checkInCustomerLinkPhone;
    private Integer orderStatus;

    private String profitStatisStartDate;
    private String profitStatisEndDate;
}