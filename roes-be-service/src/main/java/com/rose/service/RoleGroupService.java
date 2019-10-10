package com.rose.service;

import com.rose.data.entity.TbRoleGroup;

import java.util.List;

/**
 * 功能：roleGroup service
 * @author sunpeng
 * @date 2018
 */
public interface RoleGroupService {

    List<TbRoleGroup> list(Integer roleState) throws Exception;

    void add(String roleName, String roleRemark);

    void update(Long id, String roleName, String roleRemark);

    /**
     * 功能：操作角色
     * @param id
     * @param state 0：解冻恢复正常 1：冻结
     */
    void opert(Long id, Integer state);

    TbRoleGroup getDetail(Long id);
}