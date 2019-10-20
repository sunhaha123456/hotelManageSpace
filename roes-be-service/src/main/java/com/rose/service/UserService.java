package com.rose.service;

import com.rose.common.data.base.PageList;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserAddRequest;
import com.rose.data.to.request.UserSearchRequest;
import com.rose.data.to.vo.UserRedisVo;

/**
 * 功能：user service
 * @author sunpeng
 * @date 2018
 */
public interface UserService {

    PageList<TbSysUser> search(UserSearchRequest param) throws Exception;

    void add(UserAddRequest param) throws Exception;

    /**
     * 功能：对用户状态进行操作
     * @param id
     * @param state 0：解冻恢复正常 1：冻结 2：注销（只有冻结后，才能注销）
     */
    void opert(Long id, Integer state);

    void updateRole(Long id, Long roleId);

    void updateReleationHotelId(Long id, Long hotelId);

    TbSysUser getDetail(Long id);

    void updatePasswodAdmin(Long id, String userNewPassword) throws Exception;

    void updatePasswodSelf(String userOldPassword, String userNewPassword) throws Exception;

    void userRedisInfoSave(String redisKey, UserRedisVo userRedis);

    void updateUserNickName(Long id, String userNickName);
}