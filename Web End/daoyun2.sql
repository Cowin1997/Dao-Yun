/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : daoyun2

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 05/04/2021 22:34:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ad_username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `ad_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `ad_lastpassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员上次的密码',
  `ad_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员电话',
  `ad_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `ad_lastlogintime` datetime NULL DEFAULT NULL COMMENT '管理员上次登录时间',
  `ad_lastloginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员上次登录ip',
  `ad_createtime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ad_createid` int NULL DEFAULT NULL COMMENT '创建者id',
  `ad_issuperadmin` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否是超级管理员',
  `ad_accountstatus` bit(1) NOT NULL DEFAULT b'1' COMMENT '账号状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '4297f44b13955235245b2487399d7a93', '13015959117', '435455792@qq.com', '2021-03-30 20:27:49', '127.0.0.1', '2021-03-10 20:28:05', 1, b'1', b'1');

-- ----------------------------
-- Table structure for checklog
-- ----------------------------
DROP TABLE IF EXISTS `checklog`;
CREATE TABLE `checklog`  (
  `id` int NOT NULL,
  `ch_checksid` int NULL DEFAULT NULL,
  `ch_checksname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_checktime` datetime NULL DEFAULT NULL,
  `ch_checkloc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ch_checkscore` int NULL DEFAULT NULL,
  `ch_checkcount` int NULL DEFAULT NULL,
  `ch_currentscore` int NULL DEFAULT NULL,
  `ch_checkcouseid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checklog
-- ----------------------------

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `co_collegeid` int NOT NULL,
  `co_collegename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `co_schoolcode` int NOT NULL,
  `co_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `co_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `co_schoolcode`(`co_schoolcode`) USING BTREE,
  INDEX `co_collegeid`(`co_collegeid`) USING BTREE,
  CONSTRAINT `college_ibfk_1` FOREIGN KEY (`co_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` int NULL DEFAULT NULL,
  `value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `cl_classid` int NOT NULL,
  `cl_classname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cl_teachid` int NULL DEFAULT NULL,
  `cl_classloc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cl_begin` datetime NULL DEFAULT NULL,
  `cl_end` datetime NULL DEFAULT NULL,
  `cl_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cl_createtime` datetime NULL DEFAULT NULL,
  `cl_creatorid` int NULL DEFAULT NULL,
  `cl_creatorname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cl_teachname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cl_schoolcode` int NULL DEFAULT NULL,
  `cl_collegeid` int NULL DEFAULT NULL,
  `cl_majorid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cl_teachid`(`cl_teachid`) USING BTREE,
  INDEX `cl_schoolcode`(`cl_schoolcode`) USING BTREE,
  INDEX `cl_collegeid`(`cl_collegeid`) USING BTREE,
  INDEX `cl_majorid`(`cl_majorid`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`cl_teachid`) REFERENCES `teacher` (`te_teachid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`cl_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`cl_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_4` FOREIGN KEY (`cl_majorid`) REFERENCES `major` (`ma_majorid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 10056, '工程训练', 5689, NULL, '2021-03-02 20:36:11', '2021-05-06 20:36:14', NULL, NULL, NULL, NULL, NULL, 10386, 1038604, 12349);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma_majorid` int NOT NULL,
  `ma_majorname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ma_collegeid` int NULL DEFAULT NULL,
  `ma_schoolcode` int NULL DEFAULT NULL,
  `ma_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ma_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ma_collegeid`(`ma_collegeid`) USING BTREE,
  INDEX `ma_schoolcode`(`ma_schoolcode`) USING BTREE,
  INDEX `ma_majorid`(`ma_majorid`) USING BTREE,
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`ma_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `major_ibfk_2` FOREIGN KEY (`ma_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `admin` bit(1) NULL DEFAULT NULL,
  `superadmin` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 'iconfont icon-wode', 'index', '系统首页', b'1', b'1');
INSERT INTO `menu` VALUES (2, 'iconfont icon-all', 'student', '学生管理', b'1', b'1');
INSERT INTO `menu` VALUES (3, 'iconfont icon-Customermanagement-fill', 'admin', '管理员管理', b'0', b'1');
INSERT INTO `menu` VALUES (4, 'iconfont icon-office-supplies', 'class', '班级管理', b'1', b'1');
INSERT INTO `menu` VALUES (5, 'iconfont icon-office-supplies', 'teacher', '教师管理', b'1', b'1');
INSERT INTO `menu` VALUES (6, 'iconfont icon-wode', 'data', '数据字典', b'1', b'1');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `st_id` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `st_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `st_sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_schoolcode` int NULL DEFAULT NULL,
  `st_collegecode` int NULL DEFAULT NULL,
  `st_majorcode` int NULL DEFAULT NULL,
  `st_classid` int NULL DEFAULT NULL,
  `st_checkscore` int NULL DEFAULT 0,
  `st_checkcount` int NULL DEFAULT 0,
  `st_lastchecktime` datetime NULL DEFAULT NULL,
  `st_lastcheckloc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_checklevel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `st_grade` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '200327012', '张三', '男', '13015698456', '435699874@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (2, '200327013', '李四', '男', '136547893215', '456326987@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (3, '200327014', '刘一', '女', '13658951230', '1598745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (4, '200327015', '陈二', '女', '15698742331', '1236547852@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (6, '200327015', '王五', '男', '13658951231', '1598745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (7, '200327016', '赵六', '男', '13658951232', '1593745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (8, '200327017', '孙七', '女', '13658951233', '1198745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (9, '200327018', '周八', '女', '13658951234', '1598745332@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (10, '200327019', '吴九', '女', '13658951235', '1348745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (11, '200327020', '郑十', '女', '13658951236', '1598745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (12, '200327021', '刘一一', '男', '13658951237', '1598535632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (13, '200327022', '刘一二', '女', '13658951238', '1438745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (14, '200327023', '刘一三', '女', '13658951239', '1593515632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (15, '200327024', '刘一四', '女', '13658951240', '2398745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (16, '200327025', '刘一五', '男', '13658951241', '4498745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student` VALUES (17, '200327026', '刘一六', '女', '13658951242', '2298745632@qq.com', 10386, 1038603, 12349, 10056, 0, 0, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `te_teachid` int NOT NULL,
  `te_teachname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `te_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_schoolcode` int NULL DEFAULT NULL,
  `te_collegeid` int NULL DEFAULT NULL,
  `te_majorid` int NULL DEFAULT NULL,
  `te_sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_rank` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `te_edu` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `te_teachid`(`te_teachid`) USING BTREE,
  INDEX `te_schoolcode`(`te_schoolcode`) USING BTREE,
  INDEX `te_collegeid`(`te_collegeid`) USING BTREE,
  INDEX `te_majorid`(`te_majorid`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`te_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`te_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`te_majorid`) REFERENCES `major` (`ma_majorid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 5689, '张三', '13056987446', '1569845634@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `us_username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `us_type` bit(1) NOT NULL DEFAULT b'0' COMMENT '1为老师,0为学生',
  `us_registtime` datetime NULL DEFAULT NULL,
  `us_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '13015959118', 'test', b'0', '2021-03-18 17:13:18', '1134956893@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
