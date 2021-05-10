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

 Date: 23/04/2021 15:53:35
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
    `id`            int                                                    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `taskid`        int                                                    NOT NULL COMMENT '签到任务的ID',
    `checksid`      varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '签到学生ID',
    `checkcid`      varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到任务的班级号',
    `longitude`     double NULL DEFAULT NULL COMMENT '签到经度',
    `latitude`      double NULL DEFAULT NULL COMMENT '签到维度',
    `checkscore`    int NULL DEFAULT NULL COMMENT '本次签到分数',
    `checkloc`      varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到地点',
    `checktime`     datetime NULL DEFAULT NULL COMMENT '签到时间',
    `info`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到说明',
    `checkdistance` int NULL DEFAULT NULL COMMENT '签到距离',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checklog
-- ----------------------------
INSERT INTO `checklog`
VALUES (2, 0, '123', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `checklog`
VALUES (3, 0, '123', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for checktask
-- ----------------------------
DROP TABLE IF EXISTS `checktask`;
CREATE TABLE `checktask`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `class_id`    varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级ID',
    `teacher_id`  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任课教师ID',
    `password`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手势签到密码',
    `time_limit`  int NULL DEFAULT NULL COMMENT '任务时间限时',
    `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `type`        int NULL DEFAULT NULL COMMENT '签到类型：0为限时签到,1为手势签到',
    `info`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到任务说明',
    `status`      bit(1) NULL DEFAULT NULL COMMENT '签到是否结束：1未结束，0结束',
    `longitude`   double NULL DEFAULT NULL COMMENT '发起签到任务的经度',
    `latitude`    double NULL DEFAULT NULL COMMENT '发起签到任务的维度',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `class_id`(`class_id`) USING BTREE,
    INDEX         `teacher_id`(`teacher_id`) USING BTREE,
    CONSTRAINT `checktask_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `course` (`cs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `checktask_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`te_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checktask
-- ----------------------------
INSERT INTO `checktask`
VALUES (1, '1', '123', 'test', 60, '2021-04-22 19:38:27', 1, NULL, b'0', NULL, NULL);
INSERT INTO `checktask`
VALUES (2, '1', '123', NULL, 60, '2021-04-22 19:38:31', 1, NULL, b'1', NULL, NULL);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `cs_id`      varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_name`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_tid`     varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_loc`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_time`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_status`  bit(1) NULL DEFAULT NULL,
    `cs_info`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `cs_school`  int NULL DEFAULT NULL,
    `cs_major`   int NULL DEFAULT NULL,
    `cs_college` int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX        `cs_school`(`cs_school`) USING BTREE,
    INDEX        `cs_major`(`cs_major`) USING BTREE,
    INDEX        `cs_college`(`cs_college`) USING BTREE,
    INDEX        `cs_id`(`cs_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course`
VALUES (1, '1', 'string', '123', 'string', '5-8节', b'1', 'string', 10386, 12349, 1038604);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`
(
    `id`               int NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `code`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典码',
    `type`             varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
    `value`            int NULL DEFAULT NULL COMMENT '字典值，字典子项拥有该字段',
    `detail`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
    `type_id`          int NULL DEFAULT NULL COMMENT '父类id',
    `creator`          bigint NULL DEFAULT NULL COMMENT '创建者',
    `reviser`          bigint NULL DEFAULT NULL COMMENT '修改者',
    `last_revise_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX              `type_id`(`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict`
VALUES (1, 'education', '学历', NULL, '学历', NULL, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (2, NULL, '高中', 1, '', 1, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (3, NULL, '本科', 2, '', 1, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (4, NULL, '专科', 3, '', 1, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (5, NULL, '硕士', 4, '', 1, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (27, NULL, '博士', 5, '', 1, NULL, NULL, NULL);
INSERT INTO `dict`
VALUES (28, 'code', 'type', NULL, 'detail', NULL, 41, 41, '2021-04-22 13:54:05');
INSERT INTO `dict`
VALUES (29, NULL, 'type1', NULL, NULL, 28, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu`
VALUES (1, 'iconfont icon-wode', '', '系统管理', NULL, 1, '2|1|4|3|');
INSERT INTO `menu`
VALUES (3, 'iconfont icon-Customermanagement-fill', '/admin', '管理员管理', NULL, 3, '2|1|4|');
INSERT INTO `menu`
VALUES (4, 'iconfont icon-office-supplies', '/class', '班课管理', NULL, 4, '2|4|3|1|');
INSERT INTO `menu`
VALUES (5, 'iconfont icon-office-supplies', '/teacher', '教师管理', NULL, 5, '2|4|1|');
INSERT INTO `menu`
VALUES (6, '', '/sys-param', '系统参数', 1, 4, '2|4|1|');
INSERT INTO `menu`
VALUES (7, NULL, '/index', '系统首页', 1, 1, '2|1|4|3|');
INSERT INTO `menu`
VALUES (16, NULL, '/user-manger', '权限管理', 1, 2, '2|4|1|');
INSERT INTO `menu`
VALUES (17, 'iconfont icon-all', '', '学生管理', NULL, 2, '2|4|3|1|');
INSERT INTO `menu`
VALUES (19, 'iconfont icon-all', '/dict-param', '数据字典', 1, 3, '1|2|');
INSERT INTO `menu`
VALUES (20, NULL, '/student', '学生列表', 17, 1, '1|2|3|');

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `name`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学校/学院/专业名称',
    `desc`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
    `code`     int NULL DEFAULT NULL COMMENT '学校/学院/专业编码',
    `type`     int NOT NULL COMMENT '类型：1学校,2学院,3专业',
    `parentid` int NULL DEFAULT NULL COMMENT '父节点',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX      `parentid`(`parentid`) USING BTREE,
    INDEX      `code`(`code`) USING BTREE,
    CONSTRAINT `organization_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `organization` (`code`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization`
VALUES (1, '厦门大学', NULL, 10384, 1, NULL);
INSERT INTO `organization`
VALUES (2, '华侨大学', NULL, 10385, 1, NULL);
INSERT INTO `organization`
VALUES (3, '福州大学', NULL, 10386, 1, NULL);
INSERT INTO `organization`
VALUES (4, '福建工程学院', NULL, 10388, 1, NULL);
INSERT INTO `organization`
VALUES (5, '福建农林大学', NULL, 10389, 1, NULL);
INSERT INTO `organization`
VALUES (6, '集美大学', NULL, 10390, 1, NULL);
INSERT INTO `organization`
VALUES (7, '福建医科大学', NULL, 10392, 1, NULL);
INSERT INTO `organization`
VALUES (8, '福建中医学院', NULL, 10393, 1, NULL);
INSERT INTO `organization`
VALUES (9, '福建师范大学', NULL, 10394, 1, NULL);
INSERT INTO `organization`
VALUES (10, '闽江学院', NULL, 10395, 1, NULL);
INSERT INTO `organization`
VALUES (11, '数计学院', NULL, 1038601, 2, 10386);
INSERT INTO `organization`
VALUES (12, '经济学院', NULL, 1038602, 2, 10386);
INSERT INTO `organization`
VALUES (13, '人文学院', NULL, 1038603, 2, 10386);
INSERT INTO `organization`
VALUES (14, '国际学院', NULL, 1038604, 2, 10386);
INSERT INTO `organization`
VALUES (15, '电子信息', NULL, 80701, 3, 1038601);

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
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse`
(
    `id`        int                                                    NOT NULL AUTO_INCREMENT,
    `st_id`     varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `cs_id`     varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `score`     int NULL DEFAULT NULL COMMENT '签到总分',
    `taskSize`  int NULL DEFAULT NULL,
    `checkSize` int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX       `st_id`(`st_id`) USING BTREE,
    INDEX       `cs_id`(`cs_id`) USING BTREE,
    CONSTRAINT `selectcourse_ibfk_1` FOREIGN KEY (`st_id`) REFERENCES `student` (`st_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `selectcourse_ibfk_2` FOREIGN KEY (`cs_id`) REFERENCES `course` (`cs_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse`
VALUES (1, '200327001', '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `st_id`      varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_name`    varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_sex`     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_phone`   varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_email`   varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `st_school`  int NULL DEFAULT NULL,
    `st_college` int NULL DEFAULT NULL,
    `st_major`   int NULL DEFAULT NULL,
    `st_grade`   int NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX        `st_id`(`st_id`) USING BTREE,
    INDEX        `st_school`(`st_school`) USING BTREE,
    INDEX        `st_college`(`st_college`) USING BTREE,
    INDEX        `st_major`(`st_major`) USING BTREE,
    CONSTRAINT `student_ibfk_1` FOREIGN KEY (`st_school`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `student_ibfk_2` FOREIGN KEY (`st_college`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `student_ibfk_3` FOREIGN KEY (`st_major`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student`
VALUES (1, '200327001', '学生1', '男', '123456789', '123456789@qq.com', 10386, 1038601, 10393, NULL);
INSERT INTO `student`
VALUES (4, '111222', 'ysj8', '1', '18812341234', '123456@qq.com', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`
(
    `id`               int                                                    NOT NULL AUTO_INCREMENT,
    `arg_key`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
    `arg_value`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
    `arg_desc`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
    `creator`          bigint NULL DEFAULT NULL COMMENT '创建者',
    `reviser`          bigint NULL DEFAULT NULL COMMENT '修改者',
    `last_revise_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param`
VALUES (2, 'EXP', '2', '签到经验值', 41, NULL, '2021-04-07 20:17:51');
INSERT INTO `sys_param`
VALUES (13, 'string', 'string', 'string', 41, 41, '2021-04-22 13:07:52');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`         int                                                    NOT NULL AUTO_INCREMENT,
    `te_id`      varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `te_name`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_phone`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_email`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_school`  int NULL DEFAULT NULL,
    `te_college` int NULL DEFAULT NULL,
    `te_major`   int NULL DEFAULT NULL,
    `te_sex`     varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_info`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `te_edu`     varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `te_id`(`te_id`) USING BTREE,
    INDEX        `te_school`(`te_school`) USING BTREE,
    INDEX        `te_college`(`te_college`) USING BTREE,
    INDEX        `te_major`(`te_major`) USING BTREE,
    CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`te_school`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`te_college`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`te_major`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher`
VALUES (6, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `teacher`
VALUES (9, '12345678', '老师1', '18850566407', NULL, NULL, NULL, NULL, '1', NULL, NULL);

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
    `us_id`         varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `us_phone`(`us_phone`) USING BTREE,
    UNIQUE INDEX `us_username`(`us_username`) USING BTREE,
    UNIQUE INDEX `us_id`(`us_id`) USING BTREE,
    INDEX           `us_roleid`(`us_roleid`) USING BTREE,
    CONSTRAINT `user_ibfk_1` FOREIGN KEY (`us_roleid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (41, 'admin', '13015959117', 'admin', '2021-04-23 20:52:08', NULL, 1, b'1', NULL);
INSERT INTO `user`
VALUES (44, 'xuesheng', '18812341234', 'a123456', '2021-04-14 15:46:58', '123456@qq.com', 4, b'1', '111222');
INSERT INTO `user`
VALUES (45, 'laoshi', '18850566407', 'a123456', '2021-04-20 15:47:35', NULL, 3, b'1', '12345678');

SET
FOREIGN_KEY_CHECKS = 1;
