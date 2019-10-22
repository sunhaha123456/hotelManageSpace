package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.data.entity.TbHotelRoomDetail;
import com.rose.data.entity.TbHotelRoomType;
import com.rose.data.to.request.HotelRoomTypeSearchRequest;
import com.rose.repository.HotelRoomDetailRepository;
import com.rose.repository.HotelRoomTypeRepository;
import com.rose.repository.HotelRoomTypeRepositoryCustom;
import com.rose.service.HotelRoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HotelRoomTypeServiceImpl implements HotelRoomTypeService {

    @Inject
    private HotelRoomTypeRepository hotelRoomTypeRepository;

    @Inject
    private HotelRoomTypeRepositoryCustom hotelRoomTypeRepositoryCustom;

    @Inject
    private HotelRoomDetailRepository hotelRoomDetailRepository;

    @Override
    public TbHotelRoomType getDetail(Long id) {
        return hotelRoomTypeRepository.findOne(id);
    }

    @Override
    public PageList<TbHotelRoomType> search(HotelRoomTypeSearchRequest param) throws Exception {
        return hotelRoomTypeRepositoryCustom.list(param.getHotelId(), param.getPage(), param.getRows());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TbHotelRoomType param) {
        if (param.getId() == null) {
            long c = hotelRoomTypeRepository.countByName(param.getHotelId(), param.getHotelName());
            if (0 < c) {
                throw new BusinessException("房间类别已存在！");
            }
            param.setCreateDate(new Date());
            hotelRoomTypeRepository.save(param);
        } else {
            TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(param.getId());
            if (roomType == null) {
                throw new BusinessException("房间类别不存在！");
            }
            long c = hotelRoomTypeRepository.countByMenuName(param.getHotelId(), param.getHotelName(), param.getId());
            if (0 < c) {
                throw new BusinessException("房间类别已存在！");
            }
            roomType.setRoomTypeName(param.getRoomTypeName());
            roomType.setRemark(param.getRemark());
            hotelRoomTypeRepository.save(roomType);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        TbHotelRoomType roomType = hotelRoomTypeRepository.findOne(id);
        if (roomType == null) {
            throw new BusinessException("房间类别不存在！");
        }
        List<TbHotelRoomDetail> roomList = hotelRoomDetailRepository.findRoomIdList(id);
        if (roomList != null && roomList.size() > 0) {
            List<Long> roomIdList = roomList.stream().map(TbHotelRoomDetail::getId).collect(Collectors.toList());
            int c = hotelRoomDetailRepository.updateRoomTypeIdByIdList(roomIdList, null);
            if (c != roomIdList.size()) {
                throw new BusinessException(ResponseResultCode.OPERT_ERROR);
            }
        }
    }
}