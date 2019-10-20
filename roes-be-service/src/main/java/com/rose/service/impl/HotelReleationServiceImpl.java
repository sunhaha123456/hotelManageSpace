package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.exception.BusinessException;
import com.rose.common.repository.RedisRepositoryCustom;
import com.rose.data.base.PageParam;
import com.rose.data.entity.TbHotelDetail;
import com.rose.data.entity.TbSysUser;
import com.rose.repository.HotelDetailRepository;
import com.rose.repository.HotelDetailRepositoryCustom;
import com.rose.repository.SysUserRepository;
import com.rose.service.HotelReleationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class HotelReleationServiceImpl implements HotelReleationService {

    @Inject
    private HotelDetailRepository hotelDetailRepository;

    @Inject
    private HotelDetailRepositoryCustom hotelDetailRepositoryCustom;

    @Inject
    private SysUserRepository sysUserRepository;

    @Inject
    private RedisRepositoryCustom redisRepositoryCustom;

    @Override
    public TbHotelDetail getDetail(Long id) {
        return hotelDetailRepository.findOne(id);
    }

    @Override
    public PageList<TbHotelDetail> search(PageParam param) throws Exception {
        return hotelDetailRepositoryCustom.list(param.getPage(), param.getRows());
    }

    @Override
    public Object listAll() {
        return hotelDetailRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TbHotelDetail param) {
        if (param.getId() == null) {
            long c = hotelDetailRepository.countByName(param.getHotelName());
            if (0 < c) {
                throw new BusinessException("酒店名称已存在！");
            }
            param.setCreateDate(new Date());
            param.setHotelState(0);
            hotelDetailRepository.save(param);
        } else {
            TbHotelDetail hotel = hotelDetailRepository.findOne(param.getId());
            if (hotel == null) {
                throw new BusinessException("酒店不存在！");
            }
            long c = hotelDetailRepository.countByMenuName(param.getHotelName(), hotel.getId());
            if (0 < c) {
                throw new BusinessException("酒店名称已存在！");
            }
            hotel.setHotelName(param.getHotelName());
            hotel.setRemark(param.getRemark());
            hotel.setLastModified(new Date());
            hotelDetailRepository.save(hotel);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void opert(Long id, Integer state) {
        TbHotelDetail hotel = hotelDetailRepository.findOne(id);
        if (hotel == null) {
            throw new BusinessException("酒店不存在！");
        }
        if (state == 0) { // 上架
            if (hotel.getHotelState() == 0) {
                throw new BusinessException("该酒店原已处于上架状态！");
            }
            hotelDetailRepository.updateStateById(id, 0);
        } else { // 下架
            if (hotel.getHotelState() == 0) {
                throw new BusinessException("该酒店原已处于下架状态！");
            }
            hotelDetailRepository.updateStateById(id, 1);
            List<TbSysUser> userList = sysUserRepository.findByHotelId(id);
            redisRepositoryCustom.deleteUserKeys(userList);
        }
    }
}