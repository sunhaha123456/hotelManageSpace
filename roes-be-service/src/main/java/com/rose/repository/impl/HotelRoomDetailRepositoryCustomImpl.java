package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;
import com.rose.repository.HotelRoomDetailRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Repository
public class HotelRoomDetailRepositoryCustomImpl extends BaseRepositoryImpl implements HotelRoomDetailRepositoryCustom {

    @Override
    public PageList<TbHotelRoomDetail> listForEnter(HotelRoomRequest param) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT a.id, a.room_no roomNo, a.room_state roomState, ");
        sql.append(" b.room_type_name roomTypeName, a.room_window_flag roomWindowFlag, ");
        sql.append(" a.room_wc_flag roomWcFlag, a.bed_num bedNum, a.sell_price sellPrice, ");
        sql.append(" a.create_date createDate");
        sql.append(" FROM tb_hotel_room_detail a join tb_hotel_room_type b ON a.room_type_id = b.id ");
        sql.append(" AND a.hotel_id = ? ");
        paramList.add(param.getHotelId());
        HashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("a.id", "asc");
        return queryPage(sql.toString(), TbHotelRoomDetail.class, new PageUtil(param.getPage(), param.getRows()), null, paramList.toArray());
    }
}