package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomTypeSearchRequest;
import com.rose.repository.HotelRoomTypeRepository;
import com.rose.repository.HotelRoomTypeRepositoryCustom;
import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class HotelRoomTypeServiceImpl implements HotelRoomTypeService {

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

    @Inject
    private HotelRoomTypeRepositoryCustom hotelRoomTypeRepositoryCustom;

    @Override
    public TbHotelRoomType getDetail(Long id) {
        return hotelRoomTypeRepository.findOne(id);
    }

    @Override
    public PageList<TbHotelRoomType> search(HotelRoomTypeSearchRequest param) throws Exception {
        return hotelRoomTypeRepositoryCustom.list(param.getHotelId(), param.getPage(), param.getRows());
    }
}