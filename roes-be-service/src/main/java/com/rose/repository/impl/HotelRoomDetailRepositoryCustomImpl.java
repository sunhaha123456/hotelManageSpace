package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.common.util.StringUtil;
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
        sql.append(" SELECT a.id, a.room_no roomNo, a.room_upshelf_state roomUpshelfState, ");
        sql.append(" b.room_type_name roomTypeName, a.room_window_flag roomWindowFlag, ");
        sql.append(" a.room_wc_flag roomWcFlag, a.bed_num bedNum, a.sell_price sellPrice, ");
        sql.append(" a.create_date createDate, a.room_floor_num roomFloorNum, ");
        sql.append(" a.hour_room_flag hourRoomFlag");
        sql.append(" FROM tb_hotel_room_detail a left join tb_hotel_room_type b ON a.room_type_id = b.id ");
        sql.append(" WHERE a.hotel_id = ? ");
        paramList.add(param.getHotelId());
        if (StringUtil.isNotEmpty(param.getRoomNo())) {
            sql.append(" AND instr(a.room_no, ?) > 0 ");
            paramList.add(param.getRoomNo());
        }
        if (param.getRoomTypeId() != null) {
            sql.append(" AND b.id = ? ");
            paramList.add(param.getRoomTypeId());
        }
        if (param.getRoomFloorNum() != null) {
            sql.append(" AND a.room_floor_num = ? ");
            paramList.add(param.getRoomFloorNum());
        }
        if (param.getRoomUpshelfState() != null) {
            sql.append(" AND a.room_upshelf_state = ? ");
            paramList.add(param.getRoomUpshelfState());
        }
        HashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("a.id", "asc");
        return queryPage(sql.toString(), TbHotelRoomDetail.class, new PageUtil(param.getPage(), param.getRows()), null, paramList.toArray());
    }

    @Override
    public PageList<TbHotelRoomDetail> listForFloor(HotelRoomRequest param) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT a.id, a.room_no roomNo, a.room_upshelf_state roomUpshelfState, ");
        sql.append(" b.room_type_name roomTypeName, a.room_window_flag roomWindowFlag, ");
        sql.append(" a.room_wc_flag roomWcFlag, a.bed_num bedNum, a.sell_price sellPrice, ");
        sql.append(" a.create_date createDate, a.room_floor_num roomFloorNum, ");
        sql.append(" a.hour_room_flag hourRoomFlag");
        sql.append(" FROM tb_hotel_room_detail a left join tb_hotel_room_type b ON a.room_type_id = b.id ");
        sql.append(" WHERE a.room_upshelf_state = 0 ");
        sql.append(" AND a.hotel_id = ? ");
        paramList.add(param.getHotelId());
        sql.append(" AND a.room_floor_num = ? ");
        paramList.add(param.getRoomFloorNum());
        HashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("a.room_no", "asc");
        return queryPage(sql.toString(), TbHotelRoomDetail.class, new PageUtil(param.getPage(), param.getRows()), null, paramList.toArray());
    }
}