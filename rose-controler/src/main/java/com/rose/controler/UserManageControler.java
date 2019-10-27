package com.rose.controler;

import com.rose.common.data.base.PageList;
import com.rose.common.data.response.ResponseResultCode;
import com.rose.common.exception.BusinessException;
import com.rose.common.util.JsonUtil;
import com.rose.common.util.StringUtil;
import com.rose.data.entity.TbSysUser;
import com.rose.data.to.request.UserAddRequest;
import com.rose.data.to.request.UserSearchRequest;
import com.rose.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * 功能：用户管理 controller
 * @author sunpeng
 * @date 2018
 */
@Slf4j
@RestController
@RequestMapping("/user/userManage")
public class UserManageControler {

    @Inject
    private UserService userService;

    @PostMapping(value= "/searchForEnter")
    public PageList<TbSysUser> search(@RequestBody UserSearchRequest param) throws Exception {
        return userService.search(param);
    }

    @PostMapping(value= "/add")
    public void add(@RequestBody @Validated(UserAddRequest.BaseInfo.class) UserAddRequest param, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            log.info("【接口 -/user/userManage/add】【参数错误】【前端入参：{}】", JsonUtil.objectToJson(param));
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        userService.add(param);
    }

    /**
     * 功能：操作用户状态
     * 备注：用户冻结或注销后，该角色下的用户，会立刻生效
     * @param id
     * @param state 0：解冻恢复正常 1：冻结 2：注销（只有冻结后，才能注销）
     * @throws Exception
     */
    @GetMapping(value= "/opert")
    public void opert(@RequestParam Long id, @RequestParam Integer state) throws Exception {
        userService.opert(id, state);
    }

    @GetMapping(value= "/updateRole")
    public void updateRole(@RequestParam Long id, @RequestParam Long roleId) throws Exception {
        userService.updateRole(id, roleId);
    }

    @PostMapping(value= "/updatePasswodAdmin")
    public void updatePasswodAdmin(@RequestParam Long id, @RequestParam String userNewPassword) throws Exception {
        if (StringUtil.isEmpty(userNewPassword)) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        userService.updatePasswodAdmin(id, userNewPassword);
    }

    @PostMapping(value= "/updatePasswodSelf")
    public void updatePasswodSelf(HttpServletRequest request, @RequestParam String userOldPassword, @RequestParam String userNewPassword) throws Exception {
        if (StringUtil.isEmpty(userOldPassword) || StringUtil.isEmpty(userNewPassword)) {
            throw new BusinessException(ResponseResultCode.PARAM_ERROR);
        }
        userService.updatePasswodSelf(userOldPassword, userNewPassword);
    }

    @GetMapping(value= "/getDetail")
    public TbSysUser getDetail(@RequestParam Long id) throws Exception {
        return userService.getDetail(id);
    }

    @PostMapping(value= "/updateUserNickName")
    public void updateUserNickName(@RequestParam Long id, @RequestParam(defaultValue = "") String userNickName) {
        userService.updateUserNickName(id, userNickName);
    }

    @GetMapping(value= "/updateReleationHotelId")
    public void updateReleationHotelId(@RequestParam Long id, @RequestParam Long hotelId) throws Exception {
        userService.updateReleationHotelId(id, hotelId);
    }
}