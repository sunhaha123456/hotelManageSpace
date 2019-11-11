package com.rose.repository.impl;

import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.repository.HotelCustomerCheckInOrderRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class HotelCustomerCheckInOrderRepositoryCustomImpl extends BaseRepositoryImpl implements HotelCustomerCheckInOrderRepositoryCustom {

    @Override
    public List<TbHotelCustomerCheckInOrder> listByHotelIdAndRoomId(Long hotelId, Long roomId) {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT id, room_no roomNo, sell_price sellPrice, deposit_money depositMoney, ");
        sql.append(" check_in_customer_name checkInCustomerName, check_in_customer_link_phone checkInCustomerLinkPhone, ");
        sql.append(" order_type orderType, order_status orderStatus, ");
        sql.append(" lock_start_date lockStartDate, create_date createDate ");
        sql.append(" FROM tb_hotel_customer_check_in_order ");
        sql.append(" WHERE order_status in(0,2) and hotel_id = ? and room_id = ? ");
        sql.append(" order by lock_start_date asc ");
        paramList.add(hotelId);
        paramList.add(roomId);
        return queryList(sql.toString(), TbHotelCustomerCheckInOrder.class, paramList.toArray());
    }
}