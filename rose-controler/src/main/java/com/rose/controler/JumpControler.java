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
     * 功能：跳转运营酒店管理页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toOperationHotelManage")
    public String toOperationHotelManage() {
        return "menu/operationHotelManage";
    }

    /**
     * 功能：跳转运营酒店房间类别页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toOperationHotelRoomTypeManage")
    public String toOperationHotelRoomTypeManage() {
        return "menu/operationHotelRoomTypeManage";
    }

    /**
     * 功能：跳转房间录入页面
     * @return
     */
    @GetMapping(value = "/user/menuManage/toHotelRoomManage")
    public String toHotelRoomManage() {
        return "menu/hotelRoomManage";
    }
}