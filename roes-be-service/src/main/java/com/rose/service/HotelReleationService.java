package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.base.PageParam;
import com.rose.data.entity.TbHotelDetail;
import com.rose.data.entity.TbSysUser;

import java.util.List;

/**
 * 功能：酒店关联 service
 * @author sunpeng
 * @date 2019
 */
public interface HotelReleationService {

    TbHotelDetail getDetail(Long id);

    PageList<TbHotelDetail> search(PageParam param) throws Exception;

    void save(TbHotelDetail param);

    void opert(Long id, Integer state);

    List<TbSysUser> listUserByHotelId(Long hotelId);
}