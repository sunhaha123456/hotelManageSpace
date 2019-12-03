package com.rose.service.impl;

import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.repository.RedisRepositoryCustom;
import com.rose.common.util.*;
import com.rose.data.constant.SystemConstant;
import com.rose.data.entity.TbHotelDetail;
import com.rose.data.entity.TbMenu;
import com.rose.data.entity.TbRoleGroup;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserLoginRequest;
import com.rose.data.to.vo.UserRedisVo;
import com.rose.repository.HotelDetailRepository;
import com.rose.repository.MenuRepositoryCustom;
import com.rose.repository.RoleGroupRepository;
import com.rose.repository.SysUserRepository;
import com.rose.service.LoginService;
import com.rose.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Inject
    private SysUserRepository sysUserRepository;
    @Inject
    private RoleGroupRepository roleGroupRepository;
    @Inject
    private HotelDetailRepository hotelDetailRepository;
    @Inject
    private MenuRepositoryCustom menuRepositoryCustom;
    @Inject
    private RedisRepositoryCustom redisRepositoryCustom;
    @Inject
    private UserService userService;
    @Inject
    private ValueHolder valueHolder;

    @Override
    public Map<String, Object> verify(UserLoginRequest user) throws Exception {
        // 校验验证码
        String codeRedis = redisRepositoryCustom.getString(SystemConstant.LOGIN_CODE_PREFIX + user.getKey());
        String codeFront = Md5Util.MD5Encode(user.getCode());
        if (StringUtil.isEmpty(codeRedis) || StringUtil.isEmpty(codeFront) || !codeRedis.equals(codeFront)) {
            throw new BusinessException(ResponseResultCode.CODE_ERROR);
        }
        // 校验用户名和密码，并且用户状态正常
        TbSysUser sysUser = sysUserRepository.findUserByNameAndPwd(user.getUname(), Md5Util.MD5Encode(user.getUpwd()));
        if (sysUser == null) {
            throw new BusinessException(ResponseResultCode.LOGIN_ERROR);
        }
        if (!Arrays.asList(0, 1, 2).contains(sysUser.getUserState())) {
            throw new BusinessException(ResponseResultCode.USER_STATE_ERROR);
        }
        if (sysUser.getUserState() == 1) {
            throw new BusinessException("用户已被冻结！");
        }
        if (sysUser.getUserState() == 2) {
            throw new BusinessException("用户已被注销！");
        }
        if (sysUser.getRoleGroupId() == null) {
            throw new BusinessException("用户未分配角色！");
        }
        TbRoleGroup role = roleGroupRepository.findOne(sysUser.getRoleGroupId());
        if (role == null) {
            log.error("【接口 -/login/verify】【登录失败，用户所属角色组未查找到！】【用户信息：{}】", JsonUtil.objectToJson(sysUser));
            throw new BusinessException("用户信息异常！");
        }
        if (role.getRoleState() != 0) {
            throw new BusinessException("用户所属角色组已被冻结！");
        }
        if (sysUser.getHotelId() != null) {
            TbHotelDetail hotel = hotelDetailRepository.findOne(sysUser.getHotelId());
            if (hotel == null || hotel.getHotelState() == 1) {
                throw new BusinessException("用户所属酒店已被下架！");
            }
        }
        // 进行后台可访问路径校验
        List<TbMenu> roleMenuList = menuRepositoryCustom.queryMenuListByRoleId(role.getId());
        if (roleMenuList == null || roleMenuList.size() == 0) {
            throw new BusinessException("无后台访问权限！");
        }
        List<String> buUrlList = new ArrayList<>();
        for (TbMenu m : roleMenuList) {
            if (StringUtil.isNotEmpty(m.getBgUrl())) {
                buUrlList.add(m.getBgUrl());
            }
        }
        if (buUrlList.size() == 0) {
            throw new BusinessException("无后台访问权限！");
        }
        // 更新redis用户信息，更新用户token、用户状态
        UserRedisVo userRedis = redisRepositoryCustom.getClassObj(RedisKeyUtil.getRedisUserInfoKey(sysUser.getId()), UserRedisVo.class);
        String token = IdUtil.getID() + IdUtil.getID();
        if (userRedis == null) {
            userRedis = new UserRedisVo(token, buUrlList);
        } else {
            if (StringUtil.isEmpty(userRedis.getToken())) {
                userRedis.setToken(token);
            }
            userRedis.setBgUrlList(buUrlList);
        }
        userService.userRedisInfoSave(RedisKeyUtil.getRedisUserInfoKey(sysUser.getId()), userRedis);
        // 删除redis验证码
        redisRepositoryCustom.delete(SystemConstant.LOGIN_CODE_PREFIX + user.getKey());
        valueHolder.setUserIdHolder(sysUser.getId());
        user.setUpwd(""); // 屏蔽日志记录中的密码
        Map<String, Object> res = new HashMap();
        res.put("token", userRedis.getToken());
        res.put("userId", sysUser.getId());
        return res;
    }

    @Override
    public void out() throws Exception {
        redisRepositoryCustom.delete(valueHolder.getTokenHolder());
    }

    @Override
    public boolean tokenValidate(HttpServletRequest request) {
        String method = request.getMethod();
        if ("OPTIONS".equals(method.toUpperCase())) {
            return true;
        }
        String token = request.getHeader(SystemConstant.SYSTEM_TOKEN_NAME);
        String userId = request.getHeader(SystemConstant.SYSTEM_USER_ID);
        if (StringUtil.isEmpty(token)) {
            token = request.getParameter(SystemConstant.SYSTEM_TOKEN_NAME);
        }
        if (StringUtil.isEmpty(userId)) {
            userId = request.getParameter(SystemConstant.SYSTEM_USER_ID);
        }
        String url = request.getServletPath().toLowerCase();
        if (StringUtil.isEmpty(token) || token.length() != 64 || StringUtil.isEmpty(userId)) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：001-请求不合法！", url, method, userId, token);
            return false;
        }
        UserRedisVo userRedis = redisRepositoryCustom.getClassObj(RedisKeyUtil.getRedisUserInfoKey(userId), UserRedisVo.class);
        if (userRedis == null) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：002-redis中userId对应键值已超时！", url, method, userId, token);
            return false;
        }
        if (!token.equals(userRedis.getToken())) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：003-redis中userId对应redis中用户信息的token，与前端传入token，不一致！", url, method, userId, token);
            return false;
        }
        List<String> bgUrlList = userRedis.getBgUrlList();
        if (bgUrlList == null || !bgUrlList.contains(url)) {
            log.error("Request url：{}，method：{}，userId：{}，token：{}，拦截此请求：004-redis中userId对应redis中用户信息的bgUrlList，不包含正请求的地址！", url, method, userId, token);
            return false;
        }
        userService.userRedisInfoSave(RedisKeyUtil.getRedisUserInfoKey(userId), userRedis);
        request.setAttribute(SystemConstant.SYSTEM_TOKEN_NAME, token);
        request.setAttribute(SystemConstant.SYSTEM_USER_ID, userId);
        valueHolder.setTokenHolder(token);
        valueHolder.setUserIdHolder(Long.valueOf(userId));
        return true;
    }

    @Override
    public String getHomePageShowUname() {
        TbSysUser user = sysUserRepository.findOne(valueHolder.getUserIdHolder());
        if (user == null) {
            log.error("getHomePageShowUname，异常，userId：{}，查无对应用户！", valueHolder.getUserIdHolder());
            throw new BusinessException(ResponseResultCode.SERVER_ERROR);
        }
        if (StringUtil.isNotEmpty(user.getNickName())) {
            return user.getNickName();
        }
        if (user.getHotelId() == null) {
            return user.getUname();
        }
        TbHotelDetail hotel = hotelDetailRepository.findOne(user.getHotelId());
        if (hotel == null) {
            log.error("getHomePageShowUname，异常，hotelId：{}，查无对应酒店！", user.getHotelId());
            throw new BusinessException(ResponseResultCode.SERVER_ERROR);
        }
        return hotel.getHotelName() + "|" + user.getUname();
    }
}