package com.rose.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能：跳转 controller
 * @author sunpeng
 * @date 2018
 */
@Slf4j
@Controller
public class JumpControler {
    /**
     * 功能：跳转用户管理页面
     * @return
     */
    @GetMapping(value = "/user/userManage/toUserManage")
    public String toUserManage() {
        return "menu/userManage";
    }

    /**
     * 功能：跳转角色管理页面
     * @return
     */
    @GetMapping(value = "/user/roleManage/toRoleManage")
    public String toRoleManage() {
        return "menu/roleManage";
    }

    /**
     * 功能：跳转菜单管理页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toMenuManage")
    public String toMenuManage() {
        return "menu/menuManage";
    }

    /**
     * 功能：跳转运营酒店录入页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toOperationHotelEnter")
    public String toOperationHotelManage() {
        return "menu/operationHotelEnter";
    }

    /**
     * 功能：跳转运营酒店房间类别录入页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toOperationHotelRoomTypeEnter")
    public String toOperationHotelRoomTypeManage() {
        return "menu/operationHotelRoomTypeEnter";
    }

    /**
     * 功能：跳转房间录入页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toHotelRoomEnter")
    public String toHotelRoomManage() {
        return "menu/hotelRoomEnter";
    }

    /**
     * 功能：跳转办理入住页面
     * @return
     */
    @GetMapping(value = "/user/userManage/toHandleCheckIn")
    public String toHandleCheckIn() {
        return "menu/handleCheckIn";
    }
}