package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelDetail;

public interface HotelDetailRepositoryCustom extends BaseRepository {

    /**
     * 功能：分页查询
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbHotelDetail> list(Integer pageNo, Integer pageSize) throws Exception;
}