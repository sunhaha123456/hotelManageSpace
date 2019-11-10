package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.data.entity.TbHotelDetail;
import com.rose.repository.HotelDetailRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Repository
public class HotelDetailRepositoryCustomImpl extends BaseRepositoryImpl implements HotelDetailRepositoryCustom {

    @Override
    public PageList<TbHotelDetail> list(Integer pageNo, Integer pageSize) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT id, hotel_name hotelName, hotel_state hotelState, remark, create_date createDate ");
        sql.append(" FROM tb_hotel_detail ");
        LinkedHashMap<String, String> sortMap = new LinkedHashMap<>();
        sortMap.put("id", "asc");
        return queryPage(sql.toString(), TbHotelDetail.class, new PageUtil(pageNo, pageSize), sortMap, paramList.toArray());
    }
}