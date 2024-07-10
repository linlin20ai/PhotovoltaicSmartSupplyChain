/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : pssc

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 10/07/2024 20:31:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `inventory_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '库存ID，主键',
  `warehouse_id` int(0) NOT NULL COMMENT '仓库ID',
  `material_id` int(0) NOT NULL COMMENT '物料ID',
  `quantity` int(0) NOT NULL COMMENT '物料数量',
  `last_updated` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`inventory_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (1, 1, 1, 1000, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (2, 1, 2, 500, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (3, 1, 3, 300, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (4, 1, 4, 700, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (5, 1, 5, 600, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (6, 1, 6, 800, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (7, 1, 7, 400, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (8, 1, 8, 100, '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory` VALUES (9, 1, 9, 200, '2024-07-10 00:00:00', '2024-07-10 00:00:00');

-- ----------------------------
-- Table structure for inventory_changes
-- ----------------------------
DROP TABLE IF EXISTS `inventory_changes`;
CREATE TABLE `inventory_changes`  (
  `change_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '变动记录ID，主键',
  `inventory_id` int(0) NOT NULL COMMENT '库存ID',
  `change_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '变动类型',
  `quantity_change` int(0) NOT NULL COMMENT '数量变动',
  `change_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '变动原因',
  `change_date` datetime(0) NOT NULL COMMENT '变动时间',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`change_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory_changes
-- ----------------------------
INSERT INTO `inventory_changes` VALUES (1, 1, '入库', 500, '新批货物到达', '2024-07-10 09:00:00', '2024-07-10 00:00:00');
INSERT INTO `inventory_changes` VALUES (2, 1, '出库', -200, '客户订单发货', '2024-07-10 15:00:00', '2024-07-10 00:00:00');

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials`  (
  `material_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '材料ID，主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '材料名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '材料描述',
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '材料单位',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`material_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materials
-- ----------------------------
INSERT INTO `materials` VALUES (1, '太阳能电池片', '用于光伏发电的核心组件', '个', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (2, '光伏组件', '将太阳能电池片组装成整体发电单元', '个', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (3, '逆变器', '将直流电转换为交流电的设备', '台', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (4, '光伏电池', '用于存储太阳能发电的电池', '组', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (5, '光伏系统', '整个光伏发电系统的集成部分', '套', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (6, '光伏玻璃', '光伏电池组件的保护外壳', '块', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (7, '光伏线缆', '用于连接光伏组件的电线', '米', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (8, '光伏支架', '支撑和固定光伏组件的支架结构', '套', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `materials` VALUES (9, '光伏涂层', '提升太阳能电池片效率的涂层材料', '升', '2024-07-10 00:00:00', '2024-07-10 00:00:00');

-- ----------------------------
-- Table structure for merchants
-- ----------------------------
DROP TABLE IF EXISTS `merchants`;
CREATE TABLE `merchants`  (
  `merchant_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `zip_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `country` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`merchant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchants
-- ----------------------------
INSERT INTO `merchants` VALUES (1, '华光硅业有限公司', '提供高纯度硅原材料', '李华', '123-456-7890', 'lihua@huaguangsilicon.com', '123 硅产业路', '北京', 'BJ', '100000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (2, '晶科能源股份有限公司', '专注于光伏晶体的研发和生产', '王强', '234-567-8901', 'wangqiang@jingkotech.com', '456 晶体大道', '上海', 'SH', '200000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (3, '天合光能有限公司', '制造高效光伏组件', '张伟', '345-678-9012', 'zhangwei@trinasolar.com', '789 组件街', '广州', 'GD', '510000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (4, '华为逆变器系统有限公司', '提供高性能的光伏逆变器', '陈杰', '456-789-0123', 'chenjie@huaweinverters.com', '101 逆变器巷', '深圳', 'GD', '518000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (5, '阳光电源股份有限公司', '专业的光伏电站设计和建设', '赵丽', '567-890-1234', 'zhaoli@sungrowpower.com', '202 设计路', '杭州', 'ZJ', '310000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (6, '隆基绿能科技股份有限公司', '生产高效太阳能电池片', '刘敏', '678-901-2345', 'liumin@longisolar.com', '303 电池片街', '南京', 'JS', '210000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (7, '中电光伏集成有限公司', '光伏系统集成及解决方案', '杨洋', '789-012-3456', 'yangyang@cecpgv.com', '404 集成大道', '成都', 'SC', '610000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (8, '宁德时代新能源科技股份有限公司', '提供可靠的储能解决方案', '何芳', '890-123-4567', 'hefang@catlstorage.com', '505 储能路', '武汉', 'HB', '430000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (9, '特变电工新能源运维有限公司', '光伏电站的运营和维护服务', '周斌', '901-234-5678', 'zhoubin@tbeaom.com', '606 运维巷', '西安', 'SN', '710000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');
INSERT INTO `merchants` VALUES (10, '晶澳太阳能股份有限公司', '推广太阳能产品和解决方案', '吴涛', '012-345-6789', 'wutao@jasolar.com', '707 推广路', '长沙', 'HN', '410000', '中国', '2024-07-08 17:20:16', '2024-07-08 17:20:16');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(0) NULL DEFAULT NULL,
  `seller_id` int(0) NULL DEFAULT NULL,
  `item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` int(0) NOT NULL,
  `price_per_unit` decimal(10, 2) NOT NULL,
  `total_amount` decimal(10, 2) GENERATED ALWAYS AS ((`quantity` * `price_per_unit`)) STORED NULL,
  `transaction_date` datetime(0) NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'pending',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `buyer_id`(`buyer_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`buyer_id`) REFERENCES `merchants` (`merchant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `merchants` (`merchant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 2, '太阳能电池片', 1000, 5.00, DEFAULT, '2024-07-01 10:00:00', '北京', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (2, 2, 3, '光伏组件', 500, 10.00, DEFAULT, '2024-07-02 11:00:00', '上海', 'pending', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (3, 3, 4, '逆变器', 300, 15.00, DEFAULT, '2024-07-03 12:00:00', '广州', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (4, 4, 5, '储能系统', 200, 20.00, DEFAULT, '2024-07-04 13:00:00', '深圳', 'cancelled', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (5, 5, 6, '光伏电池', 700, 8.00, DEFAULT, '2024-07-05 14:00:00', '杭州', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (6, 6, 7, '光伏系统', 600, 12.00, DEFAULT, '2024-07-06 15:00:00', '南京', 'pending', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (7, 7, 8, '光伏玻璃', 800, 3.00, DEFAULT, '2024-07-07 16:00:00', '成都', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (8, 8, 9, '光伏线缆', 400, 2.50, DEFAULT, '2024-07-08 17:00:00', '武汉', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (9, 9, 10, '光伏支架', 100, 25.00, DEFAULT, '2024-07-09 18:00:00', '西安', 'pending', '2024-07-08 18:25:01', '2024-07-08 18:25:01');
INSERT INTO `orders` VALUES (10, 10, 1, '光伏涂层', 200, 30.00, DEFAULT, '2024-07-10 19:00:00', '长沙', 'completed', '2024-07-08 18:25:01', '2024-07-08 18:25:01');

-- ----------------------------
-- Table structure for pre_orders
-- ----------------------------
DROP TABLE IF EXISTS `pre_orders`;
CREATE TABLE `pre_orders`  (
  `pre_order_id` int(0) NOT NULL AUTO_INCREMENT,
  `seller_id` int(0) NULL DEFAULT NULL,
  `item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantity` int(0) NOT NULL,
  `price_per_unit` decimal(10, 2) NOT NULL,
  `estimated_total_amount` decimal(10, 2) GENERATED ALWAYS AS ((`quantity` * `price_per_unit`)) STORED NULL,
  `expected_transaction_date` datetime(0) NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'pending',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`pre_order_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  CONSTRAINT `pre_orders_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `merchants` (`merchant_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_orders
-- ----------------------------
INSERT INTO `pre_orders` VALUES (1, 1, '太阳能电池片', 1000, 5.00, DEFAULT, '2024-07-15 10:00:00', '北京', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (2, 2, '光伏组件', 500, 10.00, DEFAULT, '2024-07-16 11:00:00', '上海', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (3, 3, '逆变器', 300, 15.00, DEFAULT, '2024-07-17 12:00:00', '广州', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (4, 4, '储能系统', 200, 20.00, DEFAULT, '2024-07-18 13:00:00', '深圳', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (5, 5, '光伏电池', 700, 8.00, DEFAULT, '2024-07-19 14:00:00', '杭州', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (6, 6, '光伏系统', 600, 12.00, DEFAULT, '2024-07-20 15:00:00', '南京', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (7, 7, '光伏玻璃', 800, 3.00, DEFAULT, '2024-07-21 16:00:00', '成都', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (8, 8, '光伏线缆', 400, 2.50, DEFAULT, '2024-07-22 17:00:00', '武汉', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (9, 9, '光伏支架', 100, 25.00, DEFAULT, '2024-07-23 18:00:00', '西安', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');
INSERT INTO `pre_orders` VALUES (10, 10, '光伏涂层', 200, 30.00, DEFAULT, '2024-07-24 19:00:00', '长沙', 'pending', '2024-07-08 18:58:36', '2024-07-08 18:58:36');

-- ----------------------------
-- Table structure for warehouses
-- ----------------------------
DROP TABLE IF EXISTS `warehouses`;
CREATE TABLE `warehouses`  (
  `warehouse_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '仓库ID，主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '仓库名称',
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库位置或地址',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '仓库描述',
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`warehouse_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouses
-- ----------------------------
INSERT INTO `warehouses` VALUES (1, '北京仓库', '北京市朝阳区', '专门存放光伏材料的仓库', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `warehouses` VALUES (2, '上海仓库', '上海市浦东新区', '光伏材料的中转和分发中心', '2024-07-10 00:00:00', '2024-07-10 00:00:00');
INSERT INTO `warehouses` VALUES (3, '广州仓库', '广州市天河区', '提供快速供应服务的仓库', '2024-07-10 00:00:00', '2024-07-10 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
