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

 Date: 12/04/2021 22:08:36
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for checklog
-- ----------------------------
DROP TABLE IF EXISTS `checklog`;
CREATE TABLE `checklog`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `ch_checksid`     varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ch_checksname`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ch_checktime`    datetime NULL DEFAULT NULL,
    `ch_checkloc`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ch_checkscore`   int NULL DEFAULT NULL,
    `ch_checkcount`   int NULL DEFAULT NULL,
    `ch_currentscore` int NULL DEFAULT NULL,
    `ch_checkcouseid` int NULL DEFAULT NULL,
    `taskid`          int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX             `checklog_ibfk_1`(`ch_checksid`) USING BTREE,
    INDEX             `checklog_ibfk_2`(`ch_checkcouseid`) USING BTREE,
    INDEX             `taskid`(`taskid`) USING BTREE,
    CONSTRAINT `checklog_ibfk_1` FOREIGN KEY (`ch_checksid`) REFERENCES `student` (`st_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `checklog_ibfk_2` FOREIGN KEY (`ch_checkcouseid`) REFERENCES `course` (`cl_classid`) ON DELETE SET NULL ON UPDATE RESTRICT,
    CONSTRAINT `checklog_ibfk_3` FOREIGN KEY (`taskid`) REFERENCES `checktask` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checklog
-- ----------------------------
INSERT INTO `checklog`
VALUES (2, '200327014', NULL, '2021-03-26 12:12:46', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (3, '200327015', NULL, '2021-04-05 13:03:04', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (4, '200327012', NULL, '2021-04-05 12:14:42', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (5, '200327016', NULL, '2021-04-11 12:17:03', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (6, '200327017', NULL, '2021-04-11 12:18:03', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (7, '200327018', NULL, '2021-04-11 12:18:30', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (8, '200327019', NULL, '2021-04-11 12:18:53', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (9, '200327020', NULL, '2021-04-07 12:19:31', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (10, '200327021', NULL, '2021-04-08 12:19:49', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (11, '200327022', NULL, '2021-04-11 12:20:10', '福州大学', 2, 1, 2, 10057, 1);
INSERT INTO `checklog`
VALUES (12, '200327014', NULL, '2021-03-17 21:29:15', '福州大学', 2, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (14, '200327015', NULL, '2021-04-12 13:46:44', '', 2, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (15, '200327016', NULL, '2021-04-12 13:53:04', '', 2, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (16, '200327017', NULL, '2021-04-12 13:55:51', '', 2, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (17, '200327019', NULL, '2021-04-12 13:57:01', '', 3, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (18, '200327012', NULL, '2021-04-12 13:58:33', '', 3, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (19, '200327020', NULL, '2021-04-12 14:03:27', '', 3, NULL, NULL, 10057, 2);
INSERT INTO `checklog`
VALUES (20, '200327021', NULL, '2021-04-12 14:08:00', '', 3, NULL, NULL, 10057, 2);

-- ----------------------------
-- Table structure for checktask
-- ----------------------------
DROP TABLE IF EXISTS `checktask`;
CREATE TABLE `checktask`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `class_id`    int NULL DEFAULT NULL,
    `create_time` datetime NULL DEFAULT NULL,
    `password`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `time_limit`  int NULL DEFAULT NULL,
    `teacher_id`  int NULL DEFAULT NULL,
    `type`        int NULL DEFAULT NULL,
    `info`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`      bit(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checktask
-- ----------------------------
INSERT INTO `checktask`
VALUES (1, 10057, '2021-04-01 19:52:44', 'test', 60, 2, 1, NULL, b'0');
INSERT INTO `checktask`
VALUES (2, 10057, '2021-04-09 20:55:11', NULL, 60, 2, 1, NULL, b'1');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`
(
    `id`             int                                                    NOT NULL AUTO_INCREMENT,
    `co_collegeid`   int                                                    NOT NULL,
    `co_collegename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `co_schoolcode`  int                                                    NOT NULL,
    `co_phone`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `co_info`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `co_schoolcode`(`co_schoolcode`) USING BTREE,
    INDEX            `co_collegeid`(`co_collegeid`) USING BTREE,
    CONSTRAINT `college_ibfk_1` FOREIGN KEY (`co_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college`
VALUES (1, 1038601, '人文学院', 10386, NULL, NULL);
INSERT INTO `college`
VALUES (2, 1038602, '国际学院', 10386, NULL, NULL);
INSERT INTO `college`
VALUES (3, 1038603, '经济学院', 10386, NULL, NULL);
INSERT INTO `college`
VALUES (4, 1038604, '数计学院', 10386, NULL, NULL);
INSERT INTO `college`
VALUES (5, 1038605, '马克思主义学院', 10386, NULL, NULL);
INSERT INTO `college`
VALUES (6, 1038401, '软件学院', 10384, NULL, NULL);
INSERT INTO `college`
VALUES (7, 1038402, '海洋学院', 10384, NULL, NULL);
INSERT INTO `college`
VALUES (8, 1038403, '航空航天学院', 10384, NULL, NULL);
INSERT INTO `college`
VALUES (9, 1038404, '电气工程与自动化学院', 10384, NULL, NULL);
INSERT INTO `college`
VALUES (10, 1038405, '数学与计算机科学学院', 10384, NULL, NULL);

-- ----------------------------
-- Table structure for configdata
-- ----------------------------
DROP TABLE IF EXISTS `configdata`;
CREATE TABLE `configdata`
(
    `id`    int NOT NULL AUTO_INCREMENT,
    `code`  int NULL DEFAULT NULL,
    `key`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `info`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of configdata
-- ----------------------------
INSERT INTO `configdata`
VALUES (1, 10000, 'exp', '3', '签到经验值');
INSERT INTO `configdata`
VALUES (6, 10001, 'level 1', '10', '签到等级');
INSERT INTO `configdata`
VALUES (7, 10001, 'level 2', '20', '签到等级');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`             int                                                    NOT NULL AUTO_INCREMENT,
    `cl_classid`     int                                                    NOT NULL,
    `cl_classname`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `cl_teachid`     int NULL DEFAULT NULL,
    `cl_classloc`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cl_begin`       datetime NULL DEFAULT NULL,
    `cl_end`         datetime NULL DEFAULT NULL,
    `info`           varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cl_createtime`  datetime NULL DEFAULT NULL,
    `cl_creatorid`   int NULL DEFAULT NULL,
    `cl_creatorname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cl_teachname`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cl_schoolcode`  int NULL DEFAULT NULL,
    `cl_collegeid`   int NULL DEFAULT NULL,
    `cl_majorid`     int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `cl_schoolcode`(`cl_schoolcode`) USING BTREE,
    INDEX            `cl_collegeid`(`cl_collegeid`) USING BTREE,
    INDEX            `cl_majorid`(`cl_majorid`) USING BTREE,
    INDEX            `course_ibfk_1`(`cl_teachid`) USING BTREE,
    INDEX            `cl_classid`(`cl_classid`) USING BTREE,
    CONSTRAINT `course_ibfk_1` FOREIGN KEY (`cl_teachid`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `course_ibfk_2` FOREIGN KEY (`cl_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `course_ibfk_3` FOREIGN KEY (`cl_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `course_ibfk_4` FOREIGN KEY (`cl_majorid`) REFERENCES `major` (`ma_majorid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course`
VALUES (2, 10057, '工程训练2', 2, '数计2号楼503', NULL, NULL, '周三 5-9节', NULL, NULL, NULL, NULL, 10386, 1038604, 12349);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`
(
    `id`            int                                                    NOT NULL AUTO_INCREMENT,
    `ma_majorid`    int                                                    NOT NULL,
    `ma_majorname`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `ma_collegeid`  int NULL DEFAULT NULL,
    `ma_schoolcode` int NULL DEFAULT NULL,
    `ma_phone`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ma_info`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX           `ma_collegeid`(`ma_collegeid`) USING BTREE,
    INDEX           `ma_schoolcode`(`ma_schoolcode`) USING BTREE,
    INDEX           `ma_majorid`(`ma_majorid`) USING BTREE,
    CONSTRAINT `major_ibfk_1` FOREIGN KEY (`ma_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `major_ibfk_2` FOREIGN KEY (`ma_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major`
VALUES (1, 12345, '网络工程', 1038405, 10384, NULL, NULL);
INSERT INTO `major`
VALUES (2, 12346, '数字媒体', 1038405, 10384, NULL, NULL);
INSERT INTO `major`
VALUES (3, 12347, '软件工程', 1038405, 10384, NULL, NULL);
INSERT INTO `major`
VALUES (4, 12348, '软件工程', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (5, 12349, '电子信息', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (6, 12350, '信息与计算科学系', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (7, 12351, '数学与应用数学', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (8, 12352, '网络工程', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (9, 12353, '信息安全', 1038604, 10386, NULL, NULL);
INSERT INTO `major`
VALUES (10, 12354, '数据科学与大数据技术', 1038604, 10386, NULL, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `icon`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `uri`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `title`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `parentid` int NULL DEFAULT NULL,
    `ord`      int NULL DEFAULT NULL,
    `roleid`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX      `parentid`(`parentid`) USING BTREE,
    INDEX      `uri`(`uri`) USING BTREE,
    CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu`
VALUES (1, 'iconfont icon-wode', '', '系统管理', NULL, 1, '2|1|4|3|');
INSERT INTO `menu`
VALUES (3, 'iconfont icon-Customermanagement-fill', '/admin', '管理员管理', NULL, 3, '2|1|4|');
INSERT INTO `menu`
VALUES (4, 'iconfont icon-office-supplies', '/class', '班课管理', NULL, 4, '2|1|4|3|');
INSERT INTO `menu`
VALUES (5, 'iconfont icon-office-supplies', '/teacher', '教师管理', NULL, 5, '2|1|4|');
INSERT INTO `menu`
VALUES (6, '', '/data', '数据字典', 1, 2, '2|4|1|');
INSERT INTO `menu`
VALUES (7, NULL, '/index', '系统首页', 1, 1, '2|1|4|3|');
INSERT INTO `menu`
VALUES (16, NULL, '/user-manger', '角色管理', 1, 3, '2|4|1|');
INSERT INTO `menu`
VALUES (17, 'iconfont icon-all', '/student', '学生管理', NULL, 2, '2|4|3|1|');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `roleid`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `menuuri`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `code`     int NOT NULL,
    `menuid`   int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX      `menuuri`(`menuuri`) USING BTREE,
    INDEX      `menuid`(`menuid`) USING BTREE,
    CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`menuuri`) REFERENCES `menu` (`uri`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1009 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES (1001, '2|1|3|', '/student', '新增学生', '新增学生', 1001, 17);
INSERT INTO `permission`
VALUES (1002, '1|3|', '/student', '删除学生', '删除学生', 1002, 17);
INSERT INTO `permission`
VALUES (1003, '1|3|', '/student', '修改学生', '修改学生', 1003, 17);
INSERT INTO `permission`
VALUES (1004, '1|3|', '/student', '查找学生', '查找学生', 1004, 17);
INSERT INTO `permission`
VALUES (1005, '1|', '', '无', '无', 1005, 1);
INSERT INTO `permission`
VALUES (1006, '1|', NULL, '无', '无', 1006, 3);
INSERT INTO `permission`
VALUES (1007, '1|', NULL, '无', '无', 1007, 4);
INSERT INTO `permission`
VALUES (1008, '1|', NULL, '无', '无', 1008, 5);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `role_name`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `role_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`        bit(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, '超级管理员', '超级管理员', b'1');
INSERT INTO `role`
VALUES (2, '普通管理员', '普通管理员', b'1');
INSERT INTO `role`
VALUES (3, '教师', '教师', b'1');
INSERT INTO `role`
VALUES (4, '学生', '学生', b'1');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT,
    `sch_code`  int                                                    NOT NULL,
    `sch_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `sch_addr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `sch_info`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `sch_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX       `sch_code`(`sch_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school`
VALUES (1, 10001, '北京大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (2, 10384, '厦门大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (3, 10386, '福州大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (4, 1003, '清华大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (5, 10385, '华侨大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (6, 10388, '福建工程学院', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (7, 10389, '福建农林大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (8, 10390, '集美大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (9, 10392, '福建医科大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (10, 10393, '福建中医药学院', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (11, 10394, '福建师范大学', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (12, 10395, '闽江学院', NULL, NULL, NULL);
INSERT INTO `school`
VALUES (13, 10399, '泉州师范学院', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`               int                                                    NOT NULL AUTO_INCREMENT,
    `st_id`            varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `st_name`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `st_sex`           varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_phone`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_email`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_schoolcode`    int NULL DEFAULT NULL,
    `st_collegecode`   int NULL DEFAULT NULL,
    `st_majorcode`     int NULL DEFAULT NULL,
    `st_classid`       int NULL DEFAULT NULL,
    `st_checkscore`    int NULL DEFAULT 0,
    `st_checkcount`    int NULL DEFAULT 0,
    `st_lastchecktime` datetime NULL DEFAULT NULL,
    `st_lastcheckloc`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_checklevel`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_info`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_grade`         int NULL DEFAULT NULL,
    `st_userid`        int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX              `st_schoolcode`(`st_schoolcode`) USING BTREE,
    INDEX              `st_collegecode`(`st_collegecode`) USING BTREE,
    INDEX              `st_majorcode`(`st_majorcode`) USING BTREE,
    INDEX              `st_userid`(`st_userid`) USING BTREE,
    INDEX              `st_id`(`st_id`) USING BTREE,
    CONSTRAINT `student_ibfk_1` FOREIGN KEY (`st_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `student_ibfk_2` FOREIGN KEY (`st_collegecode`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `student_ibfk_3` FOREIGN KEY (`st_majorcode`) REFERENCES `major` (`ma_majorid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `student_ibfk_4` FOREIGN KEY (`st_userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student`
VALUES (1, '200327012', '张三', '女', '13015698456', '435699874@qq.com', 10386, 1038603, 12349, 10057, 5, 2,
        '2021-04-01 11:50:48', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (4, '200327015', '陈二', '女', '15698742331', '1236547852@qq.com', 10386, 1038603, 12349, 10057, 4, 2,
        '2021-04-01 11:51:12', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (6, '200327014', '王五', '男', '13658951231', '1598745632@qq.com', 10386, 1038603, 12349, 10057, 4, 2,
        '2021-04-01 11:51:16', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (7, '200327016', '赵六', '男', '13658951232', '1593745632@qq.com', 10386, 1038603, 12349, 10057, 4, 2,
        '2021-04-01 11:51:19', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (8, '200327017', '孙七', '女', '13658951233', '1198745632@qq.com', 10386, 1038603, 12349, 10057, 4, 2,
        '2021-04-01 11:51:23', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (9, '200327018', '周八', '女', '13658951234', '1598745332@qq.com', 10386, 1038603, 12349, 10057, 2, 1,
        '2021-04-01 11:51:26', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (10, '200327019', '吴九', '女', '13658951235', '1348745632@qq.com', 10386, 1038603, 12349, 10057, 5, 2,
        '2021-04-01 11:51:30', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (11, '200327020', '郑十', '女', '13658951236', '1598745632@qq.com', 10386, 1038603, 12349, 10057, 5, 2,
        '2021-04-01 11:51:34', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (12, '200327021', '刘一一', '男', '13658951237', '1598535632@qq.com', 10386, 1038603, 12349, 10057, 5, 2,
        '2021-04-01 11:51:37', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (13, '200327022', '刘一二', '女', '13658951238', '1438745632@qq.com', 10386, 1038603, 12349, 10057, 2, 1,
        '2021-04-01 11:51:43', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (14, '200327023', '刘一三', '女', '13658951239', '1593515632@qq.com', 10386, 1038603, 12349, 10057, NULL, 0,
        '2021-04-01 11:51:46', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (15, '200327024', '刘一四', '女', '13658951240', '2398745632@qq.com', 10386, 1038603, 12349, 10057, NULL, 0,
        '2021-04-01 11:51:49', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (16, '200327025', '刘一五', '男', '13658951241', '4498745632@qq.com', 10386, 1038603, 12349, 10057, NULL, 0,
        '2021-04-01 11:51:52', '福州大学第二田径场', NULL, NULL, NULL, NULL);
INSERT INTO `student`
VALUES (17, '200327026', '刘一六', '女', '13658951242', '2298745632@qq.com', 10386, 1038603, 12349, 10056, NULL, 0,
        '2021-04-01 11:51:57', '福州大学第二田径场', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`            int                                                    NOT NULL AUTO_INCREMENT,
    `te_teachid`    int                                                    NOT NULL,
    `te_teachname`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `te_phone`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_email`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_schoolcode` int NULL DEFAULT NULL,
    `te_collegeid`  int NULL DEFAULT NULL,
    `te_majorid`    int NULL DEFAULT NULL,
    `te_sex`        varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_info`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_rank`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_edu`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_uid`        int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX           `te_teachid`(`te_teachid`) USING BTREE,
    INDEX           `te_schoolcode`(`te_schoolcode`) USING BTREE,
    INDEX           `te_collegeid`(`te_collegeid`) USING BTREE,
    INDEX           `te_majorid`(`te_majorid`) USING BTREE,
    INDEX           `te_uid`(`te_uid`) USING BTREE,
    CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`te_schoolcode`) REFERENCES `school` (`sch_code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`te_collegeid`) REFERENCES `college` (`co_collegeid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`te_majorid`) REFERENCES `major` (`ma_majorid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `teacher_ibfk_4` FOREIGN KEY (`te_uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher`
VALUES (2, 123, '张三123', '1256984661123', 'teacher@163.com', 10386, 1038604, 12349, '男', NULL, NULL, NULL, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`            int                                                    NOT NULL AUTO_INCREMENT,
    `us_username`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `us_phone`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `us_password`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `us_registtime` datetime NULL DEFAULT NULL,
    `us_email`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `us_roleid`     int NULL DEFAULT NULL,
    `us_status`     bit(1) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX           `us_roleid`(`us_roleid`) USING BTREE,
    CONSTRAINT `user_ibfk_1` FOREIGN KEY (`us_roleid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (2, 'admin', '13015959113', '123', '2021-04-07 21:51:38', '12345678@qq.com', 1, b'1');
INSERT INTO `user`
VALUES (3, 'teacher', '12345678999', '123', '2021-04-13 21:05:50', NULL, 3, b'1');
INSERT INTO `user`
VALUES (9, 'admin2', '13015959558', 'admin2', '2021-04-11 04:23:43', '1156327956@qq.comn', 2, b'1');
INSERT INTO `user`
VALUES (10, 'admin3', '13413345558', 'admin3', '2021-04-11 04:24:01', '1253127956@qq.comn', 2, b'0');
INSERT INTO `user`
VALUES (11, 'admin4', '15936345558', 'admin4', '2021-04-11 04:24:25', '3413442956@qq.comn', 1, b'1');

-- ----------------------------
-- Triggers structure for table checklog
-- ----------------------------
DROP TRIGGER IF EXISTS `d`;
delimiter;;
CREATE TRIGGER `d`
    AFTER INSERT
    ON `checklog`
    FOR EACH ROW
BEGIN
    UPDATE student
    SET st_checkcount=(SELECT COUNT(*)
                       from checklog
                       where student.st_id = checklog.ch_checksid
                         and student.st_classid = checklog.ch_checkcouseid);
    UPDATE student
    SET st_checkscore = (SELECT SUM(ch_checkscore)
                         from checklog
                         where student.st_id = checklog.ch_checksid
                           and student.st_classid = checklog.ch_checkcouseid);
END
;;
delimiter;

-- ----------------------------
-- Triggers structure for table checklog
-- ----------------------------
DROP TRIGGER IF EXISTS `dd`;
delimiter;;
CREATE TRIGGER `dd`
    AFTER UPDATE
    ON `checklog`
    FOR EACH ROW
BEGIN
    UPDATE student
    SET st_checkcount=(SELECT COUNT(*)
                       from checklog
                       where student.st_id = checklog.ch_checksid
                         and student.st_classid = checklog.ch_checkcouseid);
    UPDATE student
    SET st_checkscore = (SELECT SUM(ch_checkscore)
                         from checklog
                         where student.st_id = checklog.ch_checksid
                           and student.st_classid = checklog.ch_checkcouseid);
END
;;
delimiter;

SET
FOREIGN_KEY_CHECKS = 1;
