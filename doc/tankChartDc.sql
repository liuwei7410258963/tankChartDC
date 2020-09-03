/*
 Navicat Premium Data Transfer

 Source Server         : longer
 Source Server Type    : MySQL
 Source Server Version : 50561
 Source Host           : localhost:3306
 Source Schema         : tank_chart_dc

 Target Server Type    : MySQL
 Target Server Version : 50561
 File Encoding         : 65001

 Date: 17/03/2020 09:05:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_gps
-- ----------------------------
DROP TABLE IF EXISTS `address_gps`;
CREATE TABLE `address_gps`  (
    `id` int(11) DEFAULT NULL,
    `station_id` int(11) NOT NULL,
    `detail_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    `longitude` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '经度',
    `latitude` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '纬度',
    PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地理位置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for address_location
-- ----------------------------
DROP TABLE IF EXISTS `address_location`;
CREATE TABLE `address_location`  (
    `id` int(11) DEFAULT NULL,
    `station_id` int(11) NOT NULL,
    `detail_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '围栏',
    PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地理位置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for address_mail
-- ----------------------------
DROP TABLE IF EXISTS `address_mail`;
CREATE TABLE `address_mail`  (
    `id` int(11) DEFAULT NULL,
    `station_id` int(11) NOT NULL,
    `detail_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `zip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '通讯位置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for address_url
-- ----------------------------
DROP TABLE IF EXISTS `address_url`;
CREATE TABLE `address_url`  (
    `id` int(11) DEFAULT NULL,
    `station_id` int(11) NOT NULL,
    `detail_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `update_time` datetime DEFAULT NULL,
    `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '网址' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bd_hose
-- ----------------------------
DROP TABLE IF EXISTS `bd_hose`;
CREATE TABLE `bd_hose`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `hose_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '逻辑加油枪id',
    `hose_status` int(1) DEFAULT NULL COMMENT '逻辑油枪状态1:在线，0离线',
    `host_status_time` datetime DEFAULT NULL COMMENT '逻辑油枪状态变化时间',
    `dispenser_id` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '逻辑加油机id',
    `dispenser_brand` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '逻辑加油机品牌',
    `fueling_point` int(4) DEFAULT NULL COMMENT '加油点',
    `reserve` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bd_oil_info
-- ----------------------------
DROP TABLE IF EXISTS `bd_oil_info`;
CREATE TABLE `bd_oil_info`  (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `FULL_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `SHORT_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `TYPE` int(11) DEFAULT NULL,
    `CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `DENSITY` decimal(6, 2) DEFAULT NULL,
    `STATUS` smallint(6) NOT NULL COMMENT '状态',
    `COLOR` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `REMARK` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bd_station
-- ----------------------------
DROP TABLE IF EXISTS `bd_station`;
CREATE TABLE `bd_station`  (
    `station_id` int(11) NOT NULL AUTO_INCREMENT,
    `station_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `station_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `station_abbreviation` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `status` int(1) DEFAULT NULL COMMENT '1启用2禁用',
    PRIMARY KEY (`station_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '加油站' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bd_tank
-- ----------------------------
DROP TABLE IF EXISTS `bd_tank`;
CREATE TABLE `bd_tank`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `tank_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '油罐号',
    `oil_no` int(2) DEFAULT NULL COMMENT '油品编号',
    `tank_volume` int(10) DEFAULT NULL COMMENT '油罐罐容',
    `submit_time` datetime DEFAULT NULL COMMENT '采集时间',
    `active` tinyint(1) DEFAULT NULL COMMENT '是否启用    A表示启用，P表示停用',
    `reverse` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bd_tank_rel_hose
-- ----------------------------
DROP TABLE IF EXISTS `bd_tank_rel_hose`;
CREATE TABLE `bd_tank_rel_hose`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `tank_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '油罐号',
    `oil_gun_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '油枪号',
    `oil_no` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '油品号',
    `fill_date` datetime DEFAULT NULL COMMENT '填报时间',
    `submit_time` datetime DEFAULT NULL COMMENT '获取时间',
    `reverse` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '预留字段',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bz_delivery
-- ----------------------------
DROP TABLE IF EXISTS `bz_delivery`;
CREATE TABLE `bz_delivery`  (
    `ID` bigint(40) NOT NULL AUTO_INCREMENT,
    `DELIVERY_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卸油编号',
    `STATION_ID` int(11) NOT NULL COMMENT '油站id',
    `STATION_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '油站编码',
    `TANK_NO` int(11) DEFAULT NULL COMMENT '油罐编号',
    `OIL_CODE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    `OIL_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品信息',
    `START_TIME` datetime DEFAULT NULL COMMENT '开始时间',
    `END_TIME` datetime DEFAULT NULL COMMENT '结束时间',
    `RECEIVE_TIME` datetime DEFAULT NULL COMMENT '收油时间',
    `AUDITOR_TIME` datetime DEFAULT NULL COMMENT '审核时间',
    `VOLUME` decimal(10, 2) DEFAULT NULL COMMENT '体积',
    `AUDITOR` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核人',
    `DELIVERY_TYPE` smallint(6) DEFAULT NULL COMMENT '卸油类型',
    `SERIAL_NUM` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '站内唯一序列号',
    `LOSS` decimal(10, 2) DEFAULT NULL COMMENT '损益',
    `STOCK_VOLUME` decimal(10, 2) DEFAULT NULL COMMENT '库发总量',
    `LOSS_ALARM` smallint(6) DEFAULT NULL COMMENT '损益告警：0-正常；1-告警',
    `NOZZLE_OUT` decimal(10, 2) DEFAULT NULL COMMENT '加油机付出量',
    `REMARK` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `finished` tinyint(1) DEFAULT NULL COMMENT '是否完成',
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bz_delivery_loss_alarm
-- ----------------------------
DROP TABLE IF EXISTS `bz_delivery_loss_alarm`;
CREATE TABLE `bz_delivery_loss_alarm`  (
    `ID` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `STATION_ID` int(11) NOT NULL,
    `STATION_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
    `OIL_CODE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `OIL_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `TIME` datetime DEFAULT NULL,
    `DELIVERY_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `VOLUME` decimal(10, 2) DEFAULT NULL,
    `LOSS` decimal(10, 2) DEFAULT NULL,
    `STOCK_VOLUME` decimal(10, 2) DEFAULT NULL,
    `LOSS_ALARM` tinyint(4) DEFAULT NULL,
    `REMARK` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bz_nozzle_out
-- ----------------------------
DROP TABLE IF EXISTS `bz_nozzle_out`;
CREATE TABLE `bz_nozzle_out`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `volume` double DEFAULT NULL COMMENT '体积',
    `start_time` datetime DEFAULT NULL COMMENT '开始时间',
    `end_time` datetime DEFAULT NULL COMMENT '结束时间',
    `nozzle_no` int(11) DEFAULT NULL COMMENT '油枪id',
    `pump_sum` int(11) DEFAULT NULL COMMENT '累计泵码数',
    `type` smallint(6) DEFAULT NULL COMMENT '类型0-正常，1-脱机',
    `OIL_NAME` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品名称',
    `OIL_CODE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1238624208558473219 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '油枪付出数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bz_nozzle_out_last
-- ----------------------------
DROP TABLE IF EXISTS `bz_nozzle_out_last`;
CREATE TABLE `bz_nozzle_out_last`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `volume` double DEFAULT NULL COMMENT '体积',
    `start_time` datetime DEFAULT NULL COMMENT '开始时间',
    `end_time` datetime DEFAULT NULL COMMENT '结束时间',
    `nozzle_no` int(11) DEFAULT NULL COMMENT '油枪id',
    `pump_sum` int(11) DEFAULT NULL COMMENT '累计泵码数',
    `type` smallint(6) DEFAULT NULL COMMENT '类型0-正常，1-脱机',
    `OIL_NAME` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品名称',
    `OIL_CODE` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1238622564047372290 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '油枪付出数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bz_nozzle_out_when_delivery
-- ----------------------------
DROP TABLE IF EXISTS `bz_nozzle_out_when_delivery`;
CREATE TABLE `bz_nozzle_out_when_delivery`  (
    `ID` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `STATION_ID` int(11) NOT NULL,
    `STATION_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
    `TANK_NO` int(11) DEFAULT NULL,
    `OIL_CODE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `OIL_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `END_TIME` datetime DEFAULT NULL,
    `START_TIME` datetime DEFAULT NULL,
    `DELIVERY_NO` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `VOLUME` decimal(10, 2) DEFAULT NULL,
    `NOZZLE_OUT` decimal(10, 2) DEFAULT NULL,
    `REMARK` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for db_inventory
-- ----------------------------
DROP TABLE IF EXISTS `db_inventory`;
CREATE TABLE `db_inventory`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tank_inventory_trace_id` bigint(20) DEFAULT NULL,
    `level` double DEFAULT NULL,
    `tank_no` smallint(6) DEFAULT NULL,
    `water_level` double DEFAULT NULL,
    `temperature` double DEFAULT NULL,
    `volume` double DEFAULT NULL,
    `time` datetime DEFAULT NULL,
    `water_volume` double DEFAULT NULL,
    `max_volume` double DEFAULT NULL COMMENT '最大罐容',
    `oil_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品名称',
    `oil_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `tank_inventory_trace_id`(`tank_inventory_trace_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108166 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for db_inventory_last
-- ----------------------------
DROP TABLE IF EXISTS `db_inventory_last`;
CREATE TABLE `db_inventory_last`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tank_inventory_trace_id` bigint(20) DEFAULT NULL,
    `level` double DEFAULT NULL,
    `tank_no` smallint(6) DEFAULT NULL,
    `water_level` double DEFAULT NULL,
    `temperature` double DEFAULT NULL,
    `volume` double DEFAULT NULL,
    `time` datetime DEFAULT NULL,
    `water_volume` double DEFAULT NULL,
    `max_volume` double DEFAULT NULL COMMENT '最大罐容',
    `oil_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品名称',
    `oil_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `db_inventory_last_tank_inventory_trace_id_index`(`tank_inventory_trace_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for db_tank_inventory_trace
-- ----------------------------
DROP TABLE IF EXISTS `db_tank_inventory_trace`;
CREATE TABLE `db_tank_inventory_trace`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tank_id` smallint(6) DEFAULT NULL,
    `oil_id` int(11) DEFAULT NULL,
    `start_time` datetime DEFAULT NULL,
    `end_Time` datetime DEFAULT NULL,
    `start_level` double DEFAULT NULL,
    `end_level` double DEFAULT NULL,
    `start_water_Level` double DEFAULT NULL,
    `end_water_Level` double DEFAULT NULL,
    `start_volume` double DEFAULT NULL,
    `end_volume` double DEFAULT NULL,
    `start_temperature` double DEFAULT NULL,
    `end_temperature` double DEFAULT NULL,
    `detail_info` text CHARACTER SET utf8 COLLATE utf8_general_ci,
    `start_water_volume` double DEFAULT NULL,
    `end_water_volume` double DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '油罐罐存数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_dispenser
-- ----------------------------
DROP TABLE IF EXISTS `dev_dispenser`;
CREATE TABLE `dev_dispenser`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `manufacturer_product_id` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品id',
    `detail_info` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详情信息',
    `product_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品型号',
    `maintenance_manager_id` int(11) DEFAULT NULL COMMENT '运维单位id',
    `install_time` datetime DEFAULT NULL COMMENT '安装时间',
    `check_time` datetime DEFAULT NULL COMMENT '检修时间',
    `device_type` int(11) DEFAULT NULL COMMENT '设备类型',
    `status` int(11) DEFAULT NULL COMMENT '状态；0-启用；1-禁用',
    `remark` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
    `tank_no` int(11) DEFAULT NULL,
    `dispenser_no` int(11) DEFAULT NULL COMMENT '油枪编号',
    `tank_nos` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '枪罐关系',
    `product_id` int(11) DEFAULT NULL COMMENT '产品id',
    `STATION_ID` int(11) DEFAULT NULL COMMENT '油站id',
    `MAINTENANCE_MANAGER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运维单位名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_dispenser_error_item
-- ----------------------------
DROP TABLE IF EXISTS `dev_dispenser_error_item`;
CREATE TABLE `dev_dispenser_error_item`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `sequence` int(11) NOT NULL COMMENT '序号。按照时间顺序排序从0开始排序',
    `nozzle_id` int(11) NOT NULL COMMENT '油枪id',
    `nozzle_no` int(11) NOT NULL COMMENT '油枪编号',
    `from_date` date DEFAULT NULL COMMENT '开始时间',
    `end_date` date DEFAULT NULL COMMENT '结束时间',
    `error_coe` double DEFAULT NULL COMMENT '油枪误差',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '油枪精度' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_maintenance_manager
-- ----------------------------
DROP TABLE IF EXISTS `dev_maintenance_manager`;
CREATE TABLE `dev_maintenance_manager`  (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `LINK_NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `STATUS` int(11) DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_nozzle
-- ----------------------------
DROP TABLE IF EXISTS `dev_nozzle`;
CREATE TABLE `dev_nozzle`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `manufacturer_product_id` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品id',
    `detail_info` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详情信息',
    `product_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品型号',
    `maintenance_manager_id` int(11) DEFAULT NULL COMMENT '运维单位id',
    `install_time` datetime DEFAULT NULL COMMENT '安装时间',
    `check_time` datetime DEFAULT NULL COMMENT '检修时间',
    `device_type` int(11) DEFAULT NULL COMMENT '设备类型',
    `dispenser_no` int(11) DEFAULT NULL COMMENT '加油机id',
    `tank_no` int(11) DEFAULT NULL COMMENT '油罐编号',
    `status` int(11) DEFAULT NULL COMMENT '状态',
    `remark` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `product_id` int(11) DEFAULT NULL COMMENT '产品id',
    `ifsf_node` int(11) DEFAULT NULL COMMENT 'ifsf节点',
    `ifsf_fueling_point` int(11) DEFAULT NULL COMMENT 'ifsf加油点',
    `online_status` tinyint(1) DEFAULT NULL COMMENT '在线状态',
    `online_status_change_time` datetime DEFAULT NULL COMMENT '在线状态修改时间',
    `oil_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编码',
    `oil_name` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品名称',
    `nozzle_no` int(11) DEFAULT NULL COMMENT '加油枪编号',
    `dispenser_id` int(11) DEFAULT NULL,
    `STATION_ID` int(11) DEFAULT NULL COMMENT '油站id',
    `MAINTENANCE_MANAGER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运维单位名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_product
-- ----------------------------
DROP TABLE IF EXISTS `dev_product`;
CREATE TABLE `dev_product`  (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `MANUFACTURER` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `STATUS` smallint(11) DEFAULT NULL,
    `CHECK_DATE` int(11) DEFAULT NULL COMMENT '检修周期,单位：月',
    `PRODUCT_TYPE` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备类型',
    `dealer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经销商',
    `DEVICE_TYPE` int(11) NOT NULL COMMENT '设备类型',
    PRIMARY KEY (`ID`) USING BTREE,
    UNIQUE INDEX `idx_code`(`NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_tank_info
-- ----------------------------
DROP TABLE IF EXISTS `dev_tank_info`;
CREATE TABLE `dev_tank_info`  (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `TANK_NO` int(5) NOT NULL,
    `STATION_ID` int(11) DEFAULT NULL,
    `OIL_ID` int(11) DEFAULT NULL,
    `HEIGHT` int(10) DEFAULT NULL,
    `MAX_VOLUME` int(10) DEFAULT NULL,
    `DIAMETER` int(10) DEFAULT 0,
    `STATUS` int(1) NOT NULL DEFAULT 1,
    `HIGH_OIL_ALARM` int(11) DEFAULT NULL,
    `HIGH_OIL_WARN` int(11) DEFAULT NULL,
    `LOW_OIL_ALARM` int(11) DEFAULT NULL,
    `LOW_OIL_WARN` int(11) DEFAULT NULL,
    `HIGH_WATER_ALARM` int(11) DEFAULT NULL,
    `HIGH_TEMP_ALARM` int(11) DEFAULT NULL,
    `LOW_TEMP_ALARM` int(11) DEFAULT NULL,
    `WATER_BLIND` double(11, 0) DEFAULT NULL,
    `OIL_BLIND` double(11, 0) DEFAULT NULL,
    `OIL_SPIL_ALARM` int(11) DEFAULT NULL COMMENT '溢油报警',
    `manufacturer_product_id` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品id',
    `detail_info` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详情信息',
    `product_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品型号',
    `maintenance_manager_id` int(11) DEFAULT NULL COMMENT '运维单位id',
    `install_time` date DEFAULT NULL COMMENT '安装时间',
    `check_time` date DEFAULT NULL COMMENT '检修时间',
    `device_type` int(11) DEFAULT NULL COMMENT '设备类型',
    `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `OIL_NAME` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品全称',
    `OIL_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '油品编号',
    `PRODUCT_ID` int(11) DEFAULT NULL COMMENT '产品id',
    `MAINTENANCE_MANAGER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '运维单位名称',
    `D1` double(10, 2) DEFAULT NULL COMMENT '主圆筒平均直径，单位mm',
    `TRUE_D1` tinyint(1) DEFAULT 1 COMMENT 'D1主圆桶是否已知；默认主圆桶直径即为罐高，故默认已知；',
    `D2` double(10, 2) DEFAULT NULL COMMENT '封头直边内直径，默认与D1相同',
    `TRUE_D2` tinyint(1) DEFAULT 1 COMMENT '封头直边直径D2是否已知；默认与D1相同且已知',
    `L1` double(10, 2) DEFAULT NULL COMMENT '主圆筒内长',
    `TRUE_L1` tinyint(1) DEFAULT 1 COMMENT '主圆桶长度是否已知；默认未知',
    `L2` double(10, 2) DEFAULT NULL COMMENT '两端封头直边内长的平均值',
    `TRUE_L2` tinyint(1) DEFAULT 1 COMMENT '封头直边圆桶长度已知；默认已知',
    `L3` double(10, 2) DEFAULT NULL COMMENT '两端封头内长的平均值',
    `TRUE_L3` tinyint(1) DEFAULT 0 COMMENT '封头长度是否已知；默认未知',
    `HEAD_TYPE` smallint(4) DEFAULT NULL COMMENT '封头类型',
    `TRUE_TYPE` tinyint(1) DEFAULT 0 COMMENT '封头类型是否已知；默认未知',
    PRIMARY KEY (`ID`) USING BTREE,
    INDEX `NewIndex1`(`STATION_ID`, `TANK_NO`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_version
-- ----------------------------
DROP TABLE IF EXISTS `dev_version`;
CREATE TABLE `dev_version`  (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `device_id` int(11) DEFAULT NULL,
    `device_version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `device_soft_version` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `copyright` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
    `version_date` datetime DEFAULT NULL,
    `device_type` smallint(6) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备版本' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
