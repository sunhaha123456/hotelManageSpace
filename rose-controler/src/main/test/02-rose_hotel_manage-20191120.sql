/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : rose_hotel_manage

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-11-20 15:34:58
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `deposit_money` decimal(19,2) DEFAULT NULL,
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
  `sell_price` decimal(19,2) DEFAULT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '2019-11-20 14:46:44', '2019-11-20 14:46:47', '1', 'fa-folder', '用户管理', '0', '0', '1', null);
INSERT INTO `tb_menu` VALUES ('2', '2019-11-20 14:47:58', '2019-11-20 14:48:00', '2', 'fa-circle-o', '用户管理', '0', '1', '3', 'user/userManage/toUserManage');
INSERT INTO `tb_menu` VALUES ('3', '2019-11-20 14:48:20', '2019-11-20 14:48:57', '2', 'fa-circle-o', '角色管理', '0', '1', '2', 'user/roleManage/toRoleManage');
INSERT INTO `tb_menu` VALUES ('4', '2019-11-20 14:49:04', '2019-11-20 14:49:44', '2', 'fa-circle-o', '菜单管理', '0', '1', '1', 'user/menuManage/toMenuManage');
INSERT INTO `tb_menu` VALUES ('5', '2019-11-20 15:11:32', '2019-11-20 15:11:32', '1', 'fa-circle-o', '入住管理', '0', '0', '4', null);
INSERT INTO `tb_menu` VALUES ('6', '2019-11-20 15:12:04', '2019-11-20 15:12:04', '2', 'fa-circle-o', '办理入住', '0', '5', '3', 'user/userManage/toHandleCheckIn');
INSERT INTO `tb_menu` VALUES ('7', '2019-11-20 15:12:27', '2019-11-20 15:12:27', '2', 'fa-circle-o', '入住详情', '0', '5', '2', 'user/menuManage/toCheckInDeatil');
INSERT INTO `tb_menu` VALUES ('8', '2019-11-20 15:12:45', '2019-11-20 15:12:45', '2', 'fa-circle-o', '营收统计', '0', '5', '1', 'user/menuManage/toProfitStatis');
INSERT INTO `tb_menu` VALUES ('9', '2019-11-20 15:13:25', '2019-11-20 15:13:25', '1', 'fa-folder', '房间管理', '0', '0', '3', null);
INSERT INTO `tb_menu` VALUES ('10', '2019-11-20 15:14:07', '2019-11-20 15:14:07', '2', 'fa-circle-o', '房间录入', '0', '9', '1', 'user/menuManage/toHotelRoomEnter');
INSERT INTO `tb_menu` VALUES ('11', '2019-11-20 15:14:45', '2019-11-20 15:14:45', '1', 'fa-folder', '运营管理', '0', '0', '2', null);
INSERT INTO `tb_menu` VALUES ('12', '2019-11-20 15:15:11', '2019-11-20 15:15:11', '2', 'fa-circle-o', '酒店录入', '0', '11', '1', 'user/menuManage/toOperationHotelEnter');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_menu_role_group_releation
-- ----------------------------
INSERT INTO `tb_menu_role_group_releation` VALUES ('5', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '1', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('6', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '2', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('7', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '3', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('8', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '4', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('9', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '5', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('10', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '6', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('11', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '7', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('12', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '8', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('13', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '9', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('14', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '10', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('15', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '11', '1');
INSERT INTO `tb_menu_role_group_releation` VALUES ('16', '2019-11-20 15:15:24', '2019-11-20 15:15:24', '12', '1');

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
