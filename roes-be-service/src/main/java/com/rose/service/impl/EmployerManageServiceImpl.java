package com.rose.service.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.ValueHolder;
import com.rose.data.entity.TbEmployer;
import com.rose.data.entity.TbEmployerSalaryPaidHistory;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.EmployerSalaryPaidHistorySearchRequest;
import com.rose.data.to.request.EmployerSearchRequest;
import com.rose.repository.*;
import com.rose.service.EmployerManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

@Slf4j
@Service
public class EmployerManageServiceImpl implements EmployerManageService {

    @Inject
    private SysUserRepository sysUserRepository;
    @Inject
    private EmployerRepository employerRepository;
    @Inject
    private EmployerRepositoryCustom employerRepositoryCustom;
    @Inject
    private EmployerSalaryPaidHistoryRepository employerSalaryPaidHistoryRepository;
    @Inject
    private EmployerSalaryPaidHistoryRepositoryCustom employerSalaryPaidHistoryRepositoryCustom;
    @Inject
    private ValueHolder valueHolder;

    @Override
    public PageList<TbEmployer> searchEmployer(EmployerSearchRequest param) throws Exception {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return employerRepositoryCustom.list(user.getHotelId(), param.getEmployerState(), param.getPage(), param.getRows());
    }

    @Override
    public TbEmployer getDetail(Long id) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return employerRepository.findEmployer(user.getHotelId(), id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveEmployer(TbEmployer param) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }

        Date now = new Date();
        if (param.getId() == null) {
            param.setCreateDate(now);
            param.setLastModified(now);
            param.setHotelId(user.getHotelId());
            param.setDelFlag(0);
        } else {
            TbEmployer old = employerRepository.findEmployer(user.getHotelId(), param.getId());
            if (old == null) {
                throw new BusinessException("对应修改员工不存在！");
            }
            param.setCreateDate(old.getCreateDate());
            param.setLastModified(now);
            param.setHotelId(user.getHotelId());
            param.setDelFlag(0);
        }

        employerRepository.save(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmployer(Long id) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbEmployer employer = employerRepository.findEmployer(user.getHotelId(), id);
        if (employer == null) {
            throw new BusinessException("对应删除员工不存在！");
        }
        int c1 = employerRepository.deleteEmployer(user.getHotelId(), id);
        if (c1 == 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
        employerSalaryPaidHistoryRepository.deleteEmployerSalaryByEmployerId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void paySalary(TbEmployerSalaryPaidHistory param) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbEmployer employer = employerRepository.findEmployer(user.getHotelId(), param.getEmployerId());
        if (employer == null) {
            throw new BusinessException("对应发放薪资员工不存在！");
        }
        Date now = new Date();
        param.setId(null);
        param.setCreateDate(now);
        param.setLastModified(now);
        param.setHotelId(user.getHotelId());
        param.setDelFlag(0);
        employerSalaryPaidHistoryRepository.save(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmployerPaidHistory(Long id) {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        TbEmployerSalaryPaidHistory history = employerSalaryPaidHistoryRepository.findOne(id);
        if (history == null) {
            throw new BusinessException("对应薪资发放记录不存在！");
        }
        if (!user.getHotelId().equals(history.getHotelId())) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        int c = employerSalaryPaidHistoryRepository.deleteEmployerSalaryById(id);
        if (c == 0) {
            throw new BusinessException(ResponseResultCode.OPERT_ERROR);
        }
    }

    @Override
    public PageList<TbEmployerSalaryPaidHistory> searchEmployerPaidHistory(EmployerSalaryPaidHistorySearchRequest param) throws Exception {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null || user.getHotelId() == null) {
            throw new BusinessException(ResponseResultCode.NO_AUTH_ERROR);
        }
        return employerSalaryPaidHistoryRepositoryCustom.list(user.getHotelId(), param.getEmployerFullName(), param.getEmployerPhone(), param.getPage(), param.getRows());
    }
}