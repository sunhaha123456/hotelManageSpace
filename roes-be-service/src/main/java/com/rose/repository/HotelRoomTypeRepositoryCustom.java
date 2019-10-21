package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelRoomType;

public interface HotelRoomTypeRepositoryCustom extends BaseRepository {

    /**
     * 功能：分页查询
     * @param hotelId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageList<TbHotelRoomType> list(Long hotelId, Integer pageNo, Integer pageSize) throws Exception;
}