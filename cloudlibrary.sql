/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : cloudlibrary

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 28/03/2022 19:32:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(32) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_isbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书标准ISBN编号\r',
  `book_press` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书出版社\r',
  `book_author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_pagination` int(32) NULL DEFAULT NULL COMMENT '图书页数\r',
  `book_price` double(32, 0) NULL DEFAULT NULL,
  `book_uploadtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书上架时间\r',
  `book_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书状态（0：可借阅，1:已借阅，2：归还中，3：已下架）',
  `book_borrower` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅人',
  `book_borrowtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅时间\r',
  `book_returntime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书预计归还时间\r',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'java基础案例教程', '9787115547477', '人民邮电出版社', '传智播客', 291, 59, '2020-12-11', '0', '', '', '');
INSERT INTO `book` VALUES (2, '挪威的森林', '9787546205618', '上海译文出版社', '村上春树', 380, 34, '2020-12-12', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (3, 'Java基础入门', '9787302511410', '清华大学出版社', '传智播客', 413, 59, '2020-12-15', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (4, 'Spark大数据分析', '9787302534327', '清华大学出版社', '传智播客', 228, 49, '2020-12-18', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (5, '边城', '9787543067028', '武汉出版社', '沈从文', 368, 26, '2020-12-20', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (6, '沉默的巡游', '9787544280662', '南海出版公司', '东野圭吾', 400, 59, '2020-12-23', '1', '张三', '2022-03-26', '2022-03-27');
INSERT INTO `book` VALUES (7, 'Java编程思想', '9787542150564', '机械工业出版社', '埃克尔', 351, 66, '2022-03-26', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (8, 'Unity3D', '9787584550564', '人民邮电出版社', '吴亚峰', 388, 59, '2020-05-28', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (9, '格局', '9787521708554', '中信出版集团', '吴军', 282, 59, '2020-08-19', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (10, '英语语法', '9787513117944', '开明出版社', '薄冰', 613, 48, '2020-06-05', '0', NULL, NULL, NULL);
INSERT INTO `book` VALUES (11, '软件工程理论与实践', '9787111571438', '机械工业出版社', '吕云翔', 354, 59, '2020-01-02', '0', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '借阅记录id\r',
  `record_bookname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅的图书名称\r',
  `record_bookisbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借阅的图书的ISBN编号',
  `record_borrower` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅人\r',
  `record_borrowtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书借阅时间',
  `record_remandtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书归还时间\r',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 'java基础案例教程', '9787115547477', '张三', '2022-03-28', '2022-03-28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户角色（ADMIN：管理员，USER：普通用户）',
  `user_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户状态（0：正常，1：禁用）\r',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123', 'zhangsan@qq.com', 'ADMIN', '0');
INSERT INTO `user` VALUES (2, '李四', '123', 'lisi@qq.com', 'USER', '0');
INSERT INTO `user` VALUES (3, '黑马程序员', '123', 'heima@qq.com', 'USER', '0');
INSERT INTO `user` VALUES (4, '王五', '123', 'wangwu@qq.com', 'USER', '0');
INSERT INTO `user` VALUES (5, '赵六', '123', 'zhaoliu@qq.com', 'USER', '0');

SET FOREIGN_KEY_CHECKS = 1;
