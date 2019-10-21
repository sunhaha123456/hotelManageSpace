package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.base.PageParam;
import com.rose.data.entity.TbHotelDetail;

/**
 * 功能：酒店 service
 * @author sunpeng
 * @date 2019
 */
public interface HotelService {

    TbHotelDetail getDetail(Long id);

    PageList<TbHotelDetail> search(PageParam param) throws Exception;

    Object listAll();

    void save(TbHotelDetail param);

    void opert(Long id, Integer state);
}