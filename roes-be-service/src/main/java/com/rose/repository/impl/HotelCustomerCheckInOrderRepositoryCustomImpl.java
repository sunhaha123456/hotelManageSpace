package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.common.util.DateUtil;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbHotelCustomerCheckInOrder;
import com.rose.data.to.request.CheckInDetailSearchRequest;
import com.rose.repository.HotelCustomerCheckInOrderRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Repository
public class HotelCustomerCheckInOrderRepositoryCustomImpl extends BaseRepositoryImpl implements HotelCustomerCheckInOrderRepositoryCustom {

    @Override
    public List<TbHotelCustomerCheckInOrder> listByHotelIdAndRoomId(Long hotelId, Long roomId) {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT id, order_no orderNo, room_no roomNo, sell_price sellPrice, deposit_money depositMoney, ");
        sql.append(" check_in_customer_name checkInCustomerName, check_in_customer_link_phone checkInCustomerLinkPhone, ");
        sql.append(" order_type orderType, order_status orderStatus, real_collect_money realCollectMoney, ");
        sql.append(" lock_start_date lockStartDate, lock_end_date lockEndDate, create_date createDate ");
        sql.append(" FROM tb_hotel_customer_check_in_order ");
        sql.append(" WHERE order_status in(0,2) and hotel_id = ? and room_id = ? ");
        sql.append(" order by lock_start_date asc ");
        paramList.add(hotelId);
        paramList.add(roomId);
        return queryList(sql.toString(), TbHotelCustomerCheckInOrder.class, paramList.toArray());
    }

    @Override
    public PageList<TbHotelCustomerCheckInOrder> listByCondition(CheckInDetailSearchRequest param) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT id, order_no orderNo, room_no roomNo, sell_price sellPrice, deposit_money depositMoney, ");
        sql.append(" check_in_customer_name checkInCustomerName, check_in_customer_link_phone checkInCustomerLinkPhone, ");
        sql.append(" order_type orderType, order_status orderStatus, lock_start_date lockStartDate, lock_end_date lockEndDate, ");
        sql.append(" real_collect_money realCollectMoney, create_date createDate ");
        sql.append(" FROM tb_hotel_customer_check_in_order ");
        sql.append(" WHERE 1 = 1 ");
        if (param.getHotelId() != null) {
            sql.append(" and hotel_id = ? ");
            paramList.add(param.getHotelId());
        }
        if (StringUtil.isNotEmpty(param.getRoomNo())) {
            sql.append(" and room_no = ? ");
            paramList.add(param.getRoomNo());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerName())) {
            sql.append(" and instr(check_in_customer_name, ?) > 0 ");
            paramList.add(param.getCheckInCustomerName());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerLinkPhone())) {
            sql.append(" and instr(check_in_customer_link_phone, ?) > 0 ");
            paramList.add(param.getCheckInCustomerLinkPhone());
        }
        if (param.getOrderStatus() != null) {
            sql.append(" and order_status = ? ");
            paramList.add(param.getOrderStatus());
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisStartDate())) {
            sql.append(" and lock_end_date >= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisStartDate()));
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisEndDate())) {
            sql.append(" and lock_end_date <= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisEndDate()));
        }
        LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("lock_start_date", "desc");
        return queryPage(sql.toString(), TbHotelCustomerCheckInOrder.class, new PageUtil(param.getPage(), param.getRows()), sortMap, paramList.toArray());
    }

    @Override
    public String getTotalRealCollectMoneyByCondition(CheckInDetailSearchRequest param) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT sum(real_collect_money) totalRealCollectMoney ");
        sql.append(" FROM tb_hotel_customer_check_in_order ");
        sql.append(" WHERE 1 = 1 ");
        if (param.getHotelId() != null) {
            sql.append(" and hotel_id = ? ");
            paramList.add(param.getHotelId());
        }
        if (StringUtil.isNotEmpty(param.getRoomNo())) {
            sql.append(" and room_no = ? ");
            paramList.add(param.getRoomNo());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerName())) {
            sql.append(" and instr(check_in_customer_name, ?) > 0 ");
            paramList.add(param.getCheckInCustomerName());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerLinkPhone())) {
            sql.append(" and instr(check_in_customer_link_phone, ?) > 0 ");
            paramList.add(param.getCheckInCustomerLinkPhone());
        }
        if (param.getOrderStatus() != null) {
            sql.append(" and order_status = ? ");
            paramList.add(param.getOrderStatus());
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisStartDate())) {
            sql.append(" and lock_end_date >= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisStartDate()));
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisEndDate())) {
            sql.append(" and lock_end_date <= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisEndDate()));
        }
        String res = queryOneObjAttr(sql.toString(), paramList.toArray());
        return StringUtil.isNotEmpty(res) ? res : "0.00";
    }

    @Override
    public String countOrderByCondition(CheckInDetailSearchRequest param) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT count(1) totalRealCollectMoney ");
        sql.append(" FROM tb_hotel_customer_check_in_order ");
        sql.append(" WHERE 1 = 1 ");
        if (param.getHotelId() != null) {
            sql.append(" and hotel_id = ? ");
            paramList.add(param.getHotelId());
        }
        if (StringUtil.isNotEmpty(param.getRoomNo())) {
            sql.append(" and room_no = ? ");
            paramList.add(param.getRoomNo());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerName())) {
            sql.append(" and instr(check_in_customer_name, ?) > 0 ");
            paramList.add(param.getCheckInCustomerName());
        }
        if (StringUtil.isNotEmpty(param.getCheckInCustomerLinkPhone())) {
            sql.append(" and instr(check_in_customer_link_phone, ?) > 0 ");
            paramList.add(param.getCheckInCustomerLinkPhone());
        }
        if (param.getOrderStatus() != null) {
            sql.append(" and order_status = ? ");
            paramList.add(param.getOrderStatus());
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisStartDate())) {
            sql.append(" and lock_end_date >= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisStartDate()));
        }
        if (StringUtil.isNotEmpty(param.getProfitStatisEndDate())) {
            sql.append(" and lock_end_date <= ? ");
            paramList.add(DateUtil.formatStrTime(param.getProfitStatisEndDate()));
        }
        String res = queryOneObjAttr(sql.toString(), paramList.toArray());
        return StringUtil.isNotEmpty(res) ? res : "0";
    }
}