package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.repository.HotelRoomTypeRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Repository
public class HotelRoomTypeRepositoryCustomImpl extends BaseRepositoryImpl implements HotelRoomTypeRepositoryCustom {

    @Override
    public PageList<TbHotelRoomType> list(Long hotelId, Integer pageNo, Integer pageSize) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT a.id, a.create_date createDate, a.room_type_name roomTypeName, a.remark, b.hotel_name hotelName ");
        sql.append(" FROM tb_hotel_room_type a JOIN tb_hotel_detail b on a.hotel_id = b.id ");
        if (hotelId != null) {
            sql.append(" AND a.hotel_id = ? ");
            paramList.add(hotelId);
        }
        LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("a.id", "asc");
        return queryPage(sql.toString(), TbHotelRoomType.class, new PageUtil(pageNo, pageSize), sortMap, paramList.toArray());
    }
}