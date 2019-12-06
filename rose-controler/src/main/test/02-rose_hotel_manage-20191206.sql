/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : rose_hotel_manage

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-12-06 18:28:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_employer
-- ----------------------------
DROP TABLE IF EXISTS `tb_employer`;
CREATE TABLE `tb_employer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `del_flag` int(1) DEFAULT '0',
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `employer_state` int(1) DEFAULT '0' COMMENT '用户状态',
  `full_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_employer
-- ----------------------------

-- ----------------------------
-- Table structure for tb_employer_salary_paid_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_employer_salary_paid_history`;
CREATE TABLE `tb_employer_salary_paid_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `del_flag` int(1) DEFAULT '0',
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `employer_id` int(20) DEFAULT NULL COMMENT '员工id',
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `paid_money` decimal(19,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `salary_date` datetime DEFAULT NULL COMMENT '薪资月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_employer_salary_paid_history
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hotel_customer_check_in_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel_customer_check_in_order`;
CREATE TABLE `tb_hotel_customer_check_in_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `check_in_customer_id_no` varchar(255) DEFAULT NULL COMMENT '入住客人身份证号',
  `check_in_customer_link_phone` varchar(255) DEFAULT NULL COMMENT '入住客人联系手机号',
  `check_in_customer_name` varchar(255) DEFAULT NULL COMMENT '入住客人姓名',
  `check_out_desc` varchar(500) DEFAULT NULL COMMENT '退房规则描述',
  `deposit_money` decimal(19,2) DEFAULT '0.00' COMMENT '实际收取押金',
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `lock_end_date` datetime DEFAULT NULL COMMENT '房间锁定结束时间',
  `lock_start_date` datetime DEFAULT NULL COMMENT '房间锁定开始时间',
  `merch_order_remark` varchar(800) DEFAULT NULL COMMENT '商户对此订单备注',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `order_status` int(1) DEFAULT '0' COMMENT '订单状态',
  `order_type` int(1) DEFAULT '0' COMMENT '订单类别',
  `real_collect_money` decimal(19,2) NOT NULL DEFAULT '0.00' COMMENT '实际收取金额',
  `room_id` int(20) DEFAULT NULL COMMENT '房间id',
  `room_no` varchar(255) DEFAULT NULL COMMENT '房间编号',
  `room_overall_desc` varchar(500) DEFAULT NULL COMMENT '房间整体描述',
  `room_type_name` varchar(255) DEFAULT NULL COMMENT '房间类型',
  `sell_price` decimal(19,2) DEFAULT '0.00' COMMENT '销售价',
  `sell_price_desc` varchar(255) DEFAULT NULL COMMENT '销售价描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_hotel_customer_check_in_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hotel_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel_detail`;
CREATE TABLE `tb_hotel_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hotel_name` varchar(255) DEFAULT NULL COMMENT '酒店名称',
  `hotel_state` int(10) DEFAULT '0' COMMENT '酒店状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '酒店备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_hotel_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hotel_room_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel_room_detail`;
CREATE TABLE `tb_hotel_room_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bed_num` int(10) DEFAULT '1' COMMENT '床铺数量',
  `calc_check_in_num_bed_flag` int(10) DEFAULT '0' COMMENT '是否根据床位计算可入住人数',
  `check_out_desc` varchar(500) DEFAULT NULL COMMENT '退房规则描述',
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `hour_room_flag` int(10) DEFAULT '0' COMMENT '是否是钟点房',
  `room_floor_num` int(10) DEFAULT '0' COMMENT '房间所在楼层',
  `room_no` varchar(255) DEFAULT NULL COMMENT '房间编号',
  `room_overall_desc` varchar(500) DEFAULT NULL COMMENT '房间整体描述',
  `room_type_id` int(20) DEFAULT NULL COMMENT '房间种类id',
  `room_upshelf_state` int(10) DEFAULT '0' COMMENT '房间上下架状态',
  `room_wc_flag` int(10) DEFAULT '1' COMMENT '有无独立卫生间',
  `room_window_flag` int(10) DEFAULT '0' COMMENT '有无窗户',
  `sell_price` decimal(19,2) DEFAULT NULL,
  `sell_price_desc` varchar(255) DEFAULT NULL COMMENT '销售价描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_hotel_room_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_hotel_room_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel_room_type`;
CREATE TABLE `tb_hotel_room_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `remark` varchar(500) DEFAULT NULL COMMENT '房间类别备注',
  `room_type_name` varchar(255) DEFAULT NULL COMMENT '房间类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_hotel_room_type
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dir_level` int(1) DEFAULT NULL COMMENT '目录深度',
  `icon_cls` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_state` int(1) DEFAULT '0' COMMENT '菜单状态',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级目录id',
  `sort` int(20) DEFAULT NULL COMMENT '排序',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `bg_url` varchar(2000) DEFAULT NULL COMMENT '菜单对应后台接口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '2019-11-20 14:46:44', '2019-12-03 15:09:18', '1', 'fa-folder', '用户管理', '0', '0', '1', null, null);
INSERT INTO `tb_menu` VALUES ('2', '2019-11-20 14:47:58', '2019-12-03 15:19:39', '2', 'fa-circle-o', '用户管理', '0', '1', '3', 'user/userManage/toUserManage', '/user/userManage/toUserManage|/user/userManage/opert|/user/userManage/getDetail|/user/userManage/add|/user/userManage/updateRole|/user/userManage/updateReleationHotelId|/user/userManage/updatePasswodAdmin|/user/userManage/updateUserNickName|/user/userManage/search|/user/roleManage/listForEnter|/user/operationHotel/listAll');
INSERT INTO `tb_menu` VALUES ('3', '2019-11-20 14:48:20', '2019-12-03 15:23:20', '2', 'fa-circle-o', '角色管理', '0', '1', '2', 'user/roleManage/toRoleManage', '/user/roleManage/toRoleManage|/user/roleManage/getDetail|/user/roleManage/getMenuTreeByRoleId|/user/roleManage/add|/user/roleManage/update|/user/roleManage/menuSave|/user/roleManage/opert|/user/roleManage/listForEnter');
INSERT INTO `tb_menu` VALUES ('4', '2019-11-20 14:49:04', '2019-12-03 15:27:26', '2', 'fa-circle-o', '菜单管理', '0', '1', '1', 'user/menuManage/toMenuManage', '/user/menuManage/toMenuManage|/user/menuManage/getDetail|/user/menuManage/getChild|/user/menuManage/save|/user/menuManage/searchRoot|/user/menuManage/searchRoot|/user/menuManage/getDetail');
INSERT INTO `tb_menu` VALUES ('5', '2019-11-20 15:11:32', '2019-12-06 13:44:03', '1', 'fa-circle-o', '入住管理', '0', '0', '5', null, null);
INSERT INTO `tb_menu` VALUES ('6', '2019-11-20 15:12:04', '2019-12-03 15:31:27', '2', 'fa-circle-o', '办理入住', '0', '5', '3', 'user/userManage/toHandleCheckIn', '/user/userManage/toHandleCheckIn|/user/handleCheckIn/searchByFloor|/user/handleCheckIn/searchByFloor|/user/handleCheckIn/getFloorList|/user/handleCheckIn/getRoomCheckInDetail|/user/handleCheckIn/handleCustomerCheckIn|/user/hotelRoomEnter/getDetail');
INSERT INTO `tb_menu` VALUES ('7', '2019-11-20 15:12:27', '2019-12-03 15:36:37', '2', 'fa-circle-o', '入住详情', '0', '5', '2', 'user/menuManage/toCheckInDeatil', '/user/menuManage/toCheckInDeatil|/user/checkInDetail/searchOrder|/user/checkInDetail/getDetail|/user/checkInDetail/updateOrderInfo|/user/checkInDetail/checkInCheckOut|/user/checkInDetail/cancleOrder|/user/checkInDetail/reserveOrderCheckIn\r\n');
INSERT INTO `tb_menu` VALUES ('8', '2019-11-20 15:12:45', '2019-12-03 15:39:23', '2', 'fa-circle-o', '营收统计', '0', '5', '1', 'user/menuManage/toProfitStatis', '/user/menuManage/toProfitStatis|/user/profitStatis/searchOrder|/user/profitStatis/getStatis|/user/profitStatis/searchOrder|/user/profitStatis/getDetail\r\n');
INSERT INTO `tb_menu` VALUES ('9', '2019-11-20 15:13:25', '2019-12-06 13:44:09', '1', 'fa-folder', '房间管理', '0', '0', '4', null, null);
INSERT INTO `tb_menu` VALUES ('10', '2019-11-20 15:14:07', '2019-12-03 15:43:35', '2', 'fa-circle-o', '房间录入', '0', '9', '1', 'user/menuManage/toHotelRoomEnter', '/user/menuManage/toHotelRoomEnter|/user/hotelRoomEnter/getDetail|/user/hotelRoomEnter/save|/user/hotelRoomEnter/opert|/user/hotelRoomEnter/search|/user/operationHotel/search|/user/hotelRoomEnter/listHotelRoomTypeByCurrentUserReleatHotel');
INSERT INTO `tb_menu` VALUES ('11', '2019-11-20 15:14:45', '2019-11-20 15:14:45', '1', 'fa-folder', '运营管理', '0', '0', '2', null, null);
INSERT INTO `tb_menu` VALUES ('12', '2019-11-20 15:15:11', '2019-12-03 15:46:40', '2', 'fa-circle-o', '酒店录入', '0', '11', '1', 'user/menuManage/toOperationHotelEnter', '/user/menuManage/toOperationHotelEnter|/user/operationHotel/getDetail|/user/operationHotel/save|/user/operationHotel/opert|/user/operationHotel/search');
INSERT INTO `tb_menu` VALUES ('13', '2019-12-06 13:45:03', '2019-12-06 13:45:03', '1', 'fa-folder', '员工管理', '0', '0', '3', null, null);
INSERT INTO `tb_menu` VALUES ('14', '2019-12-06 13:47:23', '2019-12-06 18:26:49', '2', 'fa-circle-o', '员工录入', '0', '13', '3', 'user/menuManage/toEmployerEnter', '/user/menuManage/toEmployerEnter|/user/employerEnter/search|/user/employerEnter/getDetail|/user/employerEnter/save|/user/employerEnter/delete');
INSERT INTO `tb_menu` VALUES ('15', '2019-12-06 13:48:57', '2019-12-06 18:27:02', '2', 'fa-circle-o', '进行发薪', '0', '13', '2', 'user/menuManage/toDoEmployerSalary', '/user/menuManage/toDoEmployerSalary|/user/doEmployerSalary/search|/user/doEmployerSalary/getSalaryPaidHistory|/user/doEmployerSalary/paySalary|/user/doEmployerSalary/deleteSalary');
INSERT INTO `tb_menu` VALUES ('16', '2019-12-06 13:49:24', '2019-12-06 18:27:18', '2', 'fa-circle-o', '发薪流水', '0', '13', '1', 'user/menuManage/toEmployerSalaryHistory', '/user/menuManage/toEmployerSalaryHistory|/user/employerSalaryHistory/search|/user/employerSalaryHistory/delete');

-- ----------------------------
-- Table structure for tb_menu_role_group_releation
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role_group_releation`;
CREATE TABLE `tb_menu_role_group_releation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `menu_id` int(20) DEFAULT NULL COMMENT '菜单id',
  `role_group_id` int(20) DEFAULT NULL COMMENT '角色组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_menu_role_group_releation
-- ----------------------------
INSERT INTO `tb_menu_role_group_releation` VALUES ('17', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '1', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('18', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '2', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('19', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '3', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('20', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '4', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('21', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '5', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('22', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '6', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('23', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '7', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('24', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '8', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('25', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '9', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('26', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '10', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('27', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '11', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('28', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '12', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('29', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '13', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('30', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '14', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('31', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '15', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('32', '2019-12-06 13:49:43', '2019-12-06 13:49:43', '16', '1');

-- ----------------------------
-- Table structure for tb_role_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_group`;
CREATE TABLE `tb_role_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_remark` varchar(255) DEFAULT NULL COMMENT '角色备注',
  `role_state` int(1) DEFAULT '0' COMMENT '角色状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role_group
-- ----------------------------
INSERT INTO `tb_role_group` VALUES ('1', '2019-11-20 14:42:13', '2019-11-20 14:42:15', '超级管理员', '超级管理员', '0');

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hotel_id` int(20) DEFAULT NULL COMMENT '酒店id',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `role_group_id` int(20) DEFAULT NULL COMMENT '角色组id',
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `upwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `user_state` int(1) DEFAULT '0' COMMENT '用户状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
INSERT INTO `tb_sys_user` VALUES ('1', '2019-11-20 14:41:25', '2019-11-20 14:58:33', null, null, '1', 'superAdmin', 'e10adc3949ba59abbe56e057f20f883e', '0');

-- ----------------------------
-- Table structure for tb_sys_user_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user_log`;
CREATE TABLE `tb_sys_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `args` text COMMENT '参数',
  `ip` varchar(100) DEFAULT NULL COMMENT 'ip地址',
  `ret` text COMMENT '返回',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_sys_user_log
-- ----------------------------
