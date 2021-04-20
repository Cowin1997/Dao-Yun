/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : daoyun

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 20/04/2021 11:58:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checklog
-- ----------------------------
DROP TABLE IF EXISTS `checklog`;
CREATE TABLE `checklog`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ch_sid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_taskid` int NULL DEFAULT NULL,
  `ch_checktime` datetime NULL DEFAULT NULL,
  `ch_checkscore` int NULL DEFAULT NULL,
  `ch_loc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checklog
-- ----------------------------
INSERT INTO `checklog` VALUES (2, '123', 1, NULL, 2, NULL, NULL);
INSERT INTO `checklog` VALUES (3, '123', 2, NULL, 2, NULL, NULL);

-- ----------------------------
-- Table structure for checktask
-- ----------------------------
DROP TABLE IF EXISTS `checktask`;
CREATE TABLE `checktask`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time_limit` int NULL DEFAULT NULL,
  `teacher_id` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checktask
-- ----------------------------
INSERT INTO `checktask` VALUES (1, '1', '2021-04-16 21:22:36', 'test', 60, 2, 1, NULL, b'0');
INSERT INTO `checktask` VALUES (2, '1', '2021-04-16 21:11:33', NULL, 60, 2, 1, NULL, b'1');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `co_code` int NOT NULL,
  `co_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `co_school` int NOT NULL,
  `co_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `co_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `co_schoolcode`(`co_school`) USING BTREE,
  INDEX `co_collegeid`(`co_code`) USING BTREE,
  CONSTRAINT `college_ibfk_1` FOREIGN KEY (`co_school`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, 1038601, '人文学院', 10386, NULL, NULL);
INSERT INTO `college` VALUES (2, 1038602, '国际学院', 10386, NULL, NULL);
INSERT INTO `college` VALUES (3, 1038603, '经济学院', 10386, NULL, NULL);
INSERT INTO `college` VALUES (4, 1038604, '数计学院', 10386, NULL, NULL);
INSERT INTO `college` VALUES (5, 1038605, '马克思主义学院', 10386, NULL, NULL);
INSERT INTO `college` VALUES (6, 1038401, '软件学院', 10384, NULL, NULL);
INSERT INTO `college` VALUES (7, 1038402, '海洋学院', 10384, NULL, NULL);
INSERT INTO `college` VALUES (8, 1038403, '航空航天学院', 10384, NULL, NULL);
INSERT INTO `college` VALUES (9, 1038404, '电气工程与自动化学院', 10384, NULL, NULL);
INSERT INTO `college` VALUES (10, 1038405, '数学与计算机科学学院', 10384, NULL, NULL);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `cs_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_tid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_loc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_status` bit(1) NULL DEFAULT NULL,
  `cs_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cs_school` int NULL DEFAULT NULL,
  `cs_major` int NULL DEFAULT NULL,
  `cs_college` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cs_school`(`cs_school`) USING BTREE,
  INDEX `cs_major`(`cs_major`) USING BTREE,
  INDEX `cs_college`(`cs_college`) USING BTREE,
  INDEX `cs_id`(`cs_id`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`cs_school`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`cs_major`) REFERENCES `major` (`ma_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`cs_college`) REFERENCES `college` (`co_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '1', 'string', '123', 'string', '5-8节', b'1', 'string', 10386, 12349, 1038604);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典码',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` int NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (1, 'education', '学历', NULL, '学历', NULL);
INSERT INTO `dict` VALUES (2, NULL, '高中', 1, '', 1);
INSERT INTO `dict` VALUES (3, NULL, '本科', 2, '', 1);
INSERT INTO `dict` VALUES (4, NULL, '专科', 3, '', 1);
INSERT INTO `dict` VALUES (5, NULL, '硕士', 4, '', 1);
INSERT INTO `dict` VALUES (14, 'stri2ng', '112', NULL, 'st2ring', NULL);
INSERT INTO `dict` VALUES (15, 'string', 'string', NULL, 'string', 11);
INSERT INTO `dict` VALUES (26, NULL, '1', NULL, '1', 14);
INSERT INTO `dict` VALUES (27, NULL, '博士', 5, '', 1);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_code` int NOT NULL,
  `ma_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ma_college` int NULL DEFAULT NULL,
  `ma_school` int NULL DEFAULT NULL,
  `ma_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ma_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ma_collegeid`(`ma_college`) USING BTREE,
  INDEX `ma_schoolcode`(`ma_school`) USING BTREE,
  INDEX `ma_majorid`(`ma_code`) USING BTREE,
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`ma_college`) REFERENCES `college` (`co_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `major_ibfk_2` FOREIGN KEY (`ma_school`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, 12345, '网络工程', 1038405, 10384, NULL, NULL);
INSERT INTO `major` VALUES (2, 12346, '数字媒体', 1038405, 10384, NULL, NULL);
INSERT INTO `major` VALUES (3, 12347, '软件工程', 1038405, 10384, NULL, NULL);
INSERT INTO `major` VALUES (4, 12348, '软件工程', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (5, 12349, '电子信息', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (6, 12350, '信息与计算科学系', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (7, 12351, '数学与应用数学', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (8, 12352, '网络工程', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (9, 12353, '信息安全', 1038604, 10386, NULL, NULL);
INSERT INTO `major` VALUES (10, 12354, '数据科学与大数据技术', 1038604, 10386, NULL, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentid` int NULL DEFAULT NULL,
  `ord` int NULL DEFAULT NULL,
  `roleid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentid`(`parentid`) USING BTREE,
  INDEX `uri`(`uri`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'iconfont icon-wode', '', '系统管理', NULL, 1, '2|1|4|3|');
INSERT INTO `menu` VALUES (3, 'iconfont icon-Customermanagement-fill', '/admin', '管理员管理', NULL, 3, '2|1|4|');
INSERT INTO `menu` VALUES (4, 'iconfont icon-office-supplies', '/class', '班课管理', NULL, 4, '2|4|3|1|');
INSERT INTO `menu` VALUES (5, 'iconfont icon-office-supplies', '/teacher', '教师管理', NULL, 5, '2|4|1|');
INSERT INTO `menu` VALUES (6, '', '/sys-param', '系统参数', 1, 4, '2|4|1|');
INSERT INTO `menu` VALUES (7, NULL, '/index', '系统首页', 1, 1, '2|1|4|3|');
INSERT INTO `menu` VALUES (16, NULL, '/user-manger', '权限管理', 1, 2, '2|4|1|');
INSERT INTO `menu` VALUES (17, 'iconfont icon-all', '', '学生管理', NULL, 2, '2|4|3|1|');
INSERT INTO `menu` VALUES (19, 'iconfont icon-all', '/dict-param', '数据字典', 1, 3, '1|2|');
INSERT INTO `menu` VALUES (20, NULL, '/student', '学生列表', 17, 1, '1|2|3|');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理员', b'1');
INSERT INTO `role` VALUES (2, '普通管理员', '普通管理员', b'1');
INSERT INTO `role` VALUES (3, '教师', '教师', b'1');
INSERT INTO `role` VALUES (4, '学生', '学生', b'1');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sch_code` int NOT NULL,
  `sch_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sch_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sch_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sch_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sch_code`(`sch_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, 10001, '北京大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (2, 10384, '厦门大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (3, 10386, '福州大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (4, 1003, '清华大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (5, 10385, '华侨大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (6, 10388, '福建工程学院', NULL, NULL, NULL);
INSERT INTO `school` VALUES (7, 10389, '福建农林大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (8, 10390, '集美大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (9, 10392, '福建医科大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (10, 10393, '福建中医药学院', NULL, NULL, NULL);
INSERT INTO `school` VALUES (11, 10394, '福建师范大学', NULL, NULL, NULL);
INSERT INTO `school` VALUES (12, 10395, '闽江学院', NULL, NULL, NULL);
INSERT INTO `school` VALUES (13, 10399, '泉州师范学院', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `st_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cs_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `st_id`(`st_id`) USING BTREE,
  INDEX `cs_id`(`cs_id`) USING BTREE,
  CONSTRAINT `selectcourse_ibfk_1` FOREIGN KEY (`st_id`) REFERENCES `student` (`st_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `selectcourse_ibfk_2` FOREIGN KEY (`cs_id`) REFERENCES `course` (`cs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse` VALUES (1, '200327001', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `st_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_phone` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_email` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_school` int NULL DEFAULT NULL,
  `st_college` int NULL DEFAULT NULL,
  `st_major` int NULL DEFAULT NULL,
  `st_grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `st_id_2`(`st_id`) USING BTREE,
  INDEX `st_school`(`st_school`) USING BTREE,
  INDEX `st_major`(`st_major`) USING BTREE,
  INDEX `st_college`(`st_college`) USING BTREE,
  INDEX `st_id`(`st_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`st_school`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`st_major`) REFERENCES `major` (`ma_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`st_college`) REFERENCES `college` (`co_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '200327001', '学生1', '男', '123456789', '123456789@qq.com', 10386, 1038604, 12349, NULL);
INSERT INTO `student` VALUES (3, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `arg_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `arg_value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `arg_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES (2, 'EXP', '2', '签到经验值');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `te_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `te_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_school` int NULL DEFAULT NULL,
  `te_college` int NULL DEFAULT NULL,
  `te_major` int NULL DEFAULT NULL,
  `te_sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_rank` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_edu` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `te_id`(`te_id`) USING BTREE,
  INDEX `te_school`(`te_school`) USING BTREE,
  INDEX `te_college`(`te_college`) USING BTREE,
  INDEX `te_major`(`te_major`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`te_school`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`te_college`) REFERENCES `college` (`co_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`te_major`) REFERENCES `major` (`ma_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (6, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `us_username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_registtime` datetime NULL DEFAULT NULL,
  `us_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_roleid` int NULL DEFAULT NULL,
  `us_status` bit(1) NULL DEFAULT NULL,
  `us_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `us_phone`(`us_phone`) USING BTREE,
  UNIQUE INDEX `us_username`(`us_username`) USING BTREE,
  UNIQUE INDEX `us_id`(`us_id`) USING BTREE,
  INDEX `us_roleid`(`us_roleid`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`us_roleid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (41, 'admin', '13015959118', 'admin', '2021-04-23 20:52:08', NULL, 1, b'1', NULL);
INSERT INTO `user` VALUES (42, '1234568999521', '130xx2xx456', '123444456', '2021-04-19 09:12:51', '12344456@qq.com', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
