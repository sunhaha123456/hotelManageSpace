package com.rose.repository.impl;

import com.rose.common.data.base.PageList;
import com.rose.common.data.base.PageUtil;
import com.rose.common.repository.impl.BaseRepositoryImpl;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbSysUser;
import com.rose.repository.SysUserRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class SysUserRepositoryCustomImpl extends BaseRepositoryImpl implements SysUserRepositoryCustom {

    @Override
    public PageList<TbSysUser> list(String uname, Integer pageNo, Integer pageSize) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList();
        sql.append(" SELECT u.id, u.uname, u.nick_name nickName, u.user_state userState, ");
        sql.append(" rg.role_name roleGroupName, u.role_group_id roleGroupId, u.create_date createDate, ");
        sql.append(" h.hotel_name hotelName ");
        sql.append(" FROM tb_sys_user u JOIN tb_role_group rg on u.role_group_id = rg.id ");
        sql.append(" LEFT JOIN tb_hotel_detail h on u.hotel_id = h.id ");
        sql.append(" WHERE u.user_state != 2 ");
        if (StringUtil.isNotEmpty(uname)) {
            sql.append(" AND instr(u.uname, ?) > 0 ");
            paramList.add(uname);
        }
        return queryPage(sql.toString(), TbSysUser.class, new PageUtil(pageNo, pageSize), null, paramList.toArray());
    }
}