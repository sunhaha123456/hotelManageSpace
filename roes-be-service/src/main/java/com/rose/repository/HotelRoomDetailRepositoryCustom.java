package com.rose.repository;

import com.rose.common.data.base.PageList;
import com.rose.common.repository.BaseRepository;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.to.request.HotelRoomRequest;

public interface HotelRoomDetailRepositoryCustom extends BaseRepository {

    /**
     * 功能：查询 for 房间录入
     * @return
     * @throws Exception
     */
    PageList<TbHotelRoomDetail> listForEnter(HotelRoomRequest param) throws Exception;

    /**
     * 功能：查询 for 办理入住下，根据楼层查询
     * @param param
     * @return
     * @throws Exception
     */
    PageList<TbHotelRoomDetail> listForFloor(HotelRoomRequest param) throws Exception;
}