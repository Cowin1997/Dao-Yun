/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : daoyun5

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 22/05/2021 17:02:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_log
-- ----------------------------
DROP TABLE IF EXISTS `check_log`;
CREATE TABLE `check_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task_id` bigint NULL DEFAULT NULL,
  `student_sid` bigint NULL DEFAULT NULL,
  `longitude` double NULL DEFAULT NULL,
  `latitude` double NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_log
-- ----------------------------
INSERT INTO `check_log` VALUES (9, 9, 200327101, 101.3, 30.2, '2021-05-22 16:35:32', NULL);
INSERT INTO `check_log` VALUES (10, 9, 200327102, 101.3, 30.2, '2021-05-22 16:36:17', NULL);
INSERT INTO `check_log` VALUES (11, 9, 200327103, 101.3, 30.2, '2021-05-22 16:36:52', NULL);
INSERT INTO `check_log` VALUES (12, 11, 200327101, 0, 0, '2021-05-22 16:42:45', NULL);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cid` bigint NOT NULL COMMENT '班课号',
  `teacher_tid` bigint NULL DEFAULT NULL COMMENT '教工号',
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '是否可加入',
  `coursename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班课名',
  `detailinfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班课描述',
  `school_code` bigint NULL DEFAULT NULL COMMENT '学校',
  `college_code` bigint NULL DEFAULT NULL COMMENT '学院',
  `major_code` bigint NULL DEFAULT NULL COMMENT '专业',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `course_ibfk_1`(`teacher_tid`) USING BTREE,
  INDEX `course_ibfk_2`(`school_code`) USING BTREE,
  INDEX `course_ibfk_3`(`major_code`) USING BTREE,
  INDEX `course_ibfk_4`(`college_code`) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_tid`) REFERENCES `teacher` (`tid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`school_code`) REFERENCES `organization` (`code`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`major_code`) REFERENCES `organization` (`code`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_4` FOREIGN KEY (`college_code`) REFERENCES `organization` (`code`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 10002, 100001, b'1', '工程实践1', '教室xxx xxx', 10386, 1038601, 80701, '2021-05-13 20:40:54', NULL);
INSERT INTO `course` VALUES (2, 10003, 100001, b'1', '工程实践2', '教室xxx xxx', 10386, 1038601, 80701, '2021-05-13 20:41:39', NULL);
INSERT INTO `course` VALUES (8, 40007753, 100002, b'1', 'xx', 'xx', 10386, 1038601, 80701, '2021-05-22 15:33:45', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int NULL DEFAULT NULL COMMENT '上级菜单ID',
  `sub_count` int NULL DEFAULT NULL COMMENT '子菜单数目',
  `type` int NULL DEFAULT NULL COMMENT '菜单类型,一级菜单0,二级菜单1',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `menu_sort` int NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `reviser` bigint NULL DEFAULT NULL COMMENT '修改者',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, NULL, NULL, 0, '系统管理', '', 'layout', 1, 'fa fa-slack', '/sys', NULL, 1, 1, '2021-05-12 20:21:59', '2021-05-12 20:22:01');
INSERT INTO `menu` VALUES (5, 1, NULL, 1, '角色管理', NULL, 'sys/role/index', 1, 'fa fa-slack', '/sys/role', NULL, 1, 1, '2021-05-14 19:06:41', '2021-05-14 19:06:43');
INSERT INTO `menu` VALUES (6, 1, NULL, 1, '用户管理', NULL, 'sys/user/index', 2, 'fa fa-slack', '/sys/user', NULL, 1, 1, '2021-05-14 21:49:11', NULL);
INSERT INTO `menu` VALUES (7, 1, NULL, 1, '系统参数', NULL, 'sys/param/index', 3, 'fa fa-slack', '/sys/param', NULL, 1, 1, '2021-05-16 13:35:39', NULL);
INSERT INTO `menu` VALUES (8, 1, NULL, 1, '数据字典', NULL, 'sys/dict/index', 4, 'fa fa-slack', '/sys/dict', NULL, 1, 1, '2021-05-16 13:54:23', NULL);
INSERT INTO `menu` VALUES (9, 6, NULL, 2, '用户新增', NULL, NULL, 2, NULL, NULL, 'user:add', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (10, 6, NULL, 2, '用户编辑', NULL, NULL, 3, NULL, NULL, 'user:edit', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (11, 6, NULL, 2, '用户删除', NULL, NULL, 4, NULL, NULL, 'user:del', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (12, 5, NULL, 2, '角色新增', NULL, NULL, 2, NULL, NULL, 'role:add', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (13, 5, NULL, 2, '角色编辑', NULL, NULL, 3, NULL, NULL, 'role:edit', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (14, 5, NULL, 2, '角色删除', NULL, NULL, 4, NULL, NULL, 'role:del', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (15, 7, NULL, 2, '参数新增', NULL, NULL, 2, NULL, NULL, 'param:add', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (16, 7, NULL, 2, '参数编辑', NULL, NULL, 3, NULL, NULL, 'param:edit', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (17, 7, NULL, 2, '参数删除', NULL, NULL, 4, NULL, NULL, 'param:del', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (18, 8, NULL, 2, '字典新增', NULL, NULL, 2, NULL, NULL, 'dict:add', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (19, 8, NULL, 2, '字典编辑', NULL, NULL, 3, NULL, NULL, 'dict:edit', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (20, 8, NULL, 2, '字典删除', NULL, NULL, 4, NULL, NULL, 'dict:del', NULL, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (21, NULL, NULL, 0, '学生管理', NULL, 'layout', 2, 'fa fa-slack', '/student', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (22, 21, NULL, 1, '学生列表', NULL, 'student/list/index', 1, 'fa fa-slack', '/student/list', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (23, NULL, NULL, 0, '教师管理', NULL, 'layout', 3, 'fa fa-slack', '/teacher', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (24, 23, NULL, 1, '教师列表', NULL, 'teacher/list/index', 1, 'fa fa-slack', '/teacher/list', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (25, NULL, NULL, 0, '班课管理', NULL, 'layout', 4, 'fa fa-slack', '/course', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (26, 25, NULL, 1, '班课列表', NULL, 'course/list/index', 2, 'fa fa-slack', '/course/list', NULL, 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (27, 1, NULL, 1, '菜单管理', NULL, 'sys/menu/index', 5, 'fa fa-slack', '/sys/menu', '', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (28, 27, NULL, 2, '菜单编辑', NULL, NULL, 1, NULL, NULL, 'menu:edit', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (30, 22, NULL, 2, '学生编辑', NULL, NULL, 2, NULL, NULL, 'student:edit', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (31, 22, NULL, 2, '学生删除', NULL, NULL, 3, NULL, NULL, 'student:del', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (32, 24, NULL, 2, '教师编辑', NULL, NULL, 2, NULL, NULL, 'teacher:edit', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (33, 24, NULL, 2, '教师删除', NULL, NULL, 2, NULL, NULL, 'teacher:del', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (34, 26, NULL, 2, '班课编辑', NULL, NULL, 3, NULL, NULL, 'course:edit', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (35, 26, NULL, 2, '班课删除', NULL, NULL, 4, NULL, NULL, 'course:del', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (36, 22, NULL, 2, '学生添加', NULL, NULL, 1, NULL, NULL, 'student:add', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (37, 24, NULL, 2, '教师添加', NULL, NULL, 1, NULL, NULL, 'teacher:add', 1, 1, NULL, NULL);
INSERT INTO `menu` VALUES (38, 26, NULL, 2, '班课添加', NULL, NULL, 1, NULL, NULL, 'course:add', 1, 1, NULL, NULL);

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` bigint NULL DEFAULT NULL,
  `type` bigint NOT NULL,
  `parentid` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentid`(`parentid`) USING BTREE,
  INDEX `code`(`code`) USING BTREE,
  CONSTRAINT `organization_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (3, '福州大学', NULL, 10386, 1, NULL);
INSERT INTO `organization` VALUES (11, '数计学院', NULL, 1038601, 2, 10386);
INSERT INTO `organization` VALUES (12, '经济学院', NULL, 1038602, 2, 10386);
INSERT INTO `organization` VALUES (15, '电子信息', NULL, 80701, 3, 1038601);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `menu_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 889 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (855, 1, 1);
INSERT INTO `permission` VALUES (856, 1, 5);
INSERT INTO `permission` VALUES (857, 1, 12);
INSERT INTO `permission` VALUES (858, 1, 13);
INSERT INTO `permission` VALUES (859, 1, 14);
INSERT INTO `permission` VALUES (860, 1, 6);
INSERT INTO `permission` VALUES (861, 1, 9);
INSERT INTO `permission` VALUES (862, 1, 10);
INSERT INTO `permission` VALUES (863, 1, 11);
INSERT INTO `permission` VALUES (864, 1, 7);
INSERT INTO `permission` VALUES (865, 1, 15);
INSERT INTO `permission` VALUES (866, 1, 16);
INSERT INTO `permission` VALUES (867, 1, 17);
INSERT INTO `permission` VALUES (868, 1, 8);
INSERT INTO `permission` VALUES (869, 1, 18);
INSERT INTO `permission` VALUES (870, 1, 19);
INSERT INTO `permission` VALUES (871, 1, 20);
INSERT INTO `permission` VALUES (872, 1, 27);
INSERT INTO `permission` VALUES (873, 1, 28);
INSERT INTO `permission` VALUES (874, 1, 21);
INSERT INTO `permission` VALUES (875, 1, 22);
INSERT INTO `permission` VALUES (876, 1, 36);
INSERT INTO `permission` VALUES (877, 1, 30);
INSERT INTO `permission` VALUES (878, 1, 31);
INSERT INTO `permission` VALUES (879, 1, 23);
INSERT INTO `permission` VALUES (880, 1, 24);
INSERT INTO `permission` VALUES (881, 1, 37);
INSERT INTO `permission` VALUES (882, 1, 32);
INSERT INTO `permission` VALUES (883, 1, 33);
INSERT INTO `permission` VALUES (884, 1, 25);
INSERT INTO `permission` VALUES (885, 1, 26);
INSERT INTO `permission` VALUES (886, 1, 38);
INSERT INTO `permission` VALUES (887, 1, 34);
INSERT INTO `permission` VALUES (888, 1, 35);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `level` int NULL DEFAULT NULL COMMENT '角色级别',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `modifier` bigint NULL DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_user_creator_id`(`creator`) USING BTREE,
  INDEX `fk_role_user_modifier_id`(`modifier`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 1, '超级管理员', '2021-05-26 22:40:56', '2021-05-17 14:42:23', 1, 1);
INSERT INTO `role` VALUES (2, '普通管理员', 2, '普通管理员', '2021-05-09 13:49:07', '2021-05-17 14:38:57', 1, 1);
INSERT INTO `role` VALUES (3, '教师', 3, '教师', '2021-05-19 13:49:26', '2021-05-13 13:49:52', 1, 1);
INSERT INTO `role` VALUES (4, '学生', 4, '学生', '2021-05-05 13:49:45', '2021-05-12 13:49:47', 1, 1);
INSERT INTO `role` VALUES (5, 'unkown', 5, '快速注册用户类型', '2021-04-30 14:24:08', '2021-07-16 14:24:12', 1, 1);

-- ----------------------------
-- Table structure for select_course
-- ----------------------------
DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_cid` bigint NOT NULL COMMENT '选课ID',
  `student_sid` bigint NOT NULL,
  `score` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '签到得分',
  `totalcheck` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '总的签到次数',
  `hascheck` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '已签到次数',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `select_course_ibfk_1`(`course_cid`) USING BTREE,
  INDEX `select_course_ibfk_2`(`student_sid`) USING BTREE,
  CONSTRAINT `select_course_ibfk_1` FOREIGN KEY (`course_cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `select_course_ibfk_2` FOREIGN KEY (`student_sid`) REFERENCES `student` (`sid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES (1, 10002, 200327101, 0000000004, 0000000003, 0000000002, '2021-05-13 20:41:57', NULL);
INSERT INTO `select_course` VALUES (2, 10002, 200327102, 0000000002, 0000000003, 0000000001, '2021-05-13 20:42:11', NULL);
INSERT INTO `select_course` VALUES (4, 10002, 200327103, 0000000002, 0000000003, 0000000000, '2021-05-14 17:40:35', NULL);
INSERT INTO `select_course` VALUES (5, 10002, 200327104, 0000000000, 0000000003, 0000000000, '2021-05-14 17:40:51', NULL);
INSERT INTO `select_course` VALUES (6, 10002, 200327105, 0000000000, 0000000003, 0000000000, '2021-05-14 17:41:39', NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT 'user_id',
  `sid` bigint NOT NULL COMMENT '学号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `school_code` bigint NULL DEFAULT NULL COMMENT '学校',
  `college_code` bigint NULL DEFAULT NULL COMMENT '学院',
  `major_code` bigint NULL DEFAULT NULL COMMENT '专业',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `school_code`(`school_code`) USING BTREE,
  INDEX `college_code`(`college_code`) USING BTREE,
  INDEX `major_code`(`major_code`) USING BTREE,
  INDEX `sid`(`sid`) USING BTREE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`school_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`college_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_4` FOREIGN KEY (`major_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (3, 19, 200327101, '张三', '女', '13000000001', 10386, 1038601, 80701, '2021-05-09 15:34:04', '2021-05-19 11:30:09');
INSERT INTO `student` VALUES (4, 23, 200327102, '李四', '男', '13000000003', 10386, 1038601, 80701, '2021-05-13 11:25:09', '2021-05-19 11:29:41');
INSERT INTO `student` VALUES (5, 24, 200327103, '王五', '女', '13000000004', 10386, 1038601, 80701, '2021-05-14 16:52:57', NULL);
INSERT INTO `student` VALUES (6, 25, 200327104, '张一', '女', '13000000005', 10386, 1038601, 80701, '2021-05-14 16:58:28', NULL);
INSERT INTO `student` VALUES (7, 26, 200327105, '李二', '男', '13000000006', 10386, 1038601, 80701, '2021-05-14 16:59:01', NULL);
INSERT INTO `student` VALUES (11, 39, 200327165, '165', '男', '13000000236', 10386, 1038601, 80701, '2021-05-21 22:25:07', '2021-05-21 22:35:08');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典码',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `value` int NULL DEFAULT NULL COMMENT '字典值，字典子项拥有该字段',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父类id',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `reviser` bigint NULL DEFAULT NULL COMMENT '修改者',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`parent_id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  CONSTRAINT `sys_dict_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `sys_dict` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'edu', '学历', NULL, '学历字典 ', NULL, 1, 1, '2021-05-09 16:19:28', NULL);
INSERT INTO `sys_dict` VALUES (28, NULL, NULL, 1, '高中', 1, 1, 1, '2021-05-09 16:20:49', NULL);
INSERT INTO `sys_dict` VALUES (29, NULL, NULL, 2, '本科', 1, 1, 1, '2021-05-09 16:21:14', '2021-05-09 16:21:16');
INSERT INTO `sys_dict` VALUES (30, NULL, NULL, 3, '硕士', 1, 1, 1, '2021-05-09 16:21:40', '2021-05-09 16:21:42');
INSERT INTO `sys_dict` VALUES (37, NULL, NULL, 4, '博士', 1, 1, 1, '2021-05-09 19:00:05', NULL);
INSERT INTO `sys_dict` VALUES (104, 'xx', 'xx', NULL, 'xxx', NULL, 1, NULL, '2021-05-16 17:54:56', NULL);
INSERT INTO `sys_dict` VALUES (109, NULL, NULL, 1, 'xxx', 104, 1, NULL, '2021-05-18 16:19:12', NULL);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `arg_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `arg_value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `arg_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `reviser` bigint NULL DEFAULT NULL COMMENT '修改者',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_param_ibfk_1`(`creator`) USING BTREE,
  INDEX `sys_param_ibfk_2`(`reviser`) USING BTREE,
  CONSTRAINT `sys_param_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_param_ibfk_2` FOREIGN KEY (`reviser`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES (13, 'EXP', '2', '签到经验值', 1, 1, '2021-05-09 19:05:35', '2021-05-09 19:05:38');
INSERT INTO `sys_param` VALUES (15, 'string', 'string2222', 'string222', 1, 1, '2021-05-09 19:31:29', '2021-05-16 18:36:42');
INSERT INTO `sys_param` VALUES (19, '1', '1', '1', 1, 1, '2021-05-16 18:20:41', '2021-05-16 18:36:46');
INSERT INTO `sys_param` VALUES (20, '1', '1', '1', 1, 1, '2021-05-16 18:22:20', '2021-05-16 18:36:48');
INSERT INTO `sys_param` VALUES (21, '2', '2', '2', 1, 1, '2021-05-16 18:25:08', '2021-05-16 19:50:33');
INSERT INTO `sys_param` VALUES (22, '2', '2', '2', 1, 1, '2021-05-16 18:25:31', '2021-05-16 19:50:35');
INSERT INTO `sys_param` VALUES (23, '2', '2', '2', 1, 1, '2021-05-16 18:26:04', '2021-05-16 19:50:39');
INSERT INTO `sys_param` VALUES (33, '2', '2', '2', 1, 1, '2021-05-16 19:48:37', '2021-05-16 19:50:40');
INSERT INTO `sys_param` VALUES (34, '213', '312', '312', 1, 1, '2021-05-16 19:48:54', '2021-05-16 19:50:42');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_cid` bigint NOT NULL COMMENT '签到任务班级ID',
  `longitude` double NULL DEFAULT NULL COMMENT '签到经度',
  `latitude` double NULL DEFAULT NULL COMMENT '签到维度',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  `disabled` bit(1) NULL DEFAULT NULL COMMENT '签到是否结束',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签到类型：0为限时签到,1为手势签到',
  `time_limit` int NULL DEFAULT NULL COMMENT '限时时间',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手势密码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_ibfk_1`(`course_cid`) USING BTREE,
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`course_cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (9, 10002, 101.3, 30.2, '2021-05-22 16:34:24', '2021-05-22 16:34:27', b'0', '1', 5000, NULL);
INSERT INTO `task` VALUES (10, 10002, 100, 100, NULL, NULL, b'0', '1', 120, NULL);
INSERT INTO `task` VALUES (11, 10002, 100, 100, NULL, NULL, b'0', '1', 120, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `tid` bigint NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school_code` bigint NULL DEFAULT NULL,
  `college_code` bigint NULL DEFAULT NULL,
  `major_code` bigint NULL DEFAULT NULL,
  `gmt_create` date NOT NULL,
  `gmt_modified` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `te_id`(`tid`) USING BTREE,
  INDEX `te_school`(`school_code`) USING BTREE,
  INDEX `te_college`(`college_code`) USING BTREE,
  INDEX `te_major`(`major_code`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`school_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_3` FOREIGN KEY (`college_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `teacher_ibfk_4` FOREIGN KEY (`major_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (10, 20, 100001, '老师1', '男', '13000000002', 10386, 1038601, 80701, '2021-05-09', NULL);
INSERT INTO `teacher` VALUES (18, 28, 100002, '老师2', '女', '13000000028', 10386, 1038601, 80701, '2021-05-21', NULL);
INSERT INTO `teacher` VALUES (19, 40, 100003, '老师3', '女', '13000000632', 10386, 1038601, 80701, '2021-05-22', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '账号是否可用',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '13015959118', NULL, 1, 1, '2021-05-01 22:44:13', '2021-05-13 22:44:17');
INSERT INTO `user` VALUES (19, 'student1', '13000000001', NULL, 4, 1, '2021-05-09 15:34:04', NULL);
INSERT INTO `user` VALUES (20, 'teacher1', '13000000002', NULL, 3, 1, '2021-05-09 15:36:10', NULL);
INSERT INTO `user` VALUES (23, 'student2', '13000000003', NULL, 4, 1, '2021-05-13 11:23:20', NULL);
INSERT INTO `user` VALUES (24, 'student3', '13000000004', NULL, 4, 1, '2021-05-14 16:42:13', NULL);
INSERT INTO `user` VALUES (25, 'student4', '13000000005', NULL, 4, 1, '2021-05-14 16:44:10', NULL);
INSERT INTO `user` VALUES (26, 'student5', '13000000006', NULL, 4, 1, '2021-05-14 16:45:02', NULL);
INSERT INTO `user` VALUES (27, 'student6', '13000000007', NULL, 4, 1, '2021-05-14 16:46:29', NULL);
INSERT INTO `user` VALUES (28, 'teacher2', '13000000008', NULL, 3, 1, '2021-05-14 16:47:21', NULL);
INSERT INTO `user` VALUES (31, 'teacher', '13000000015', NULL, 3, 1, '2021-05-17 16:50:07', NULL);
INSERT INTO `user` VALUES (35, 'admin1', '13015689632', NULL, 2, 1, '2021-05-17 19:40:57', NULL);
INSERT INTO `user` VALUES (39, '165', '13000000236', NULL, 4, 1, '2021-05-21 22:25:07', NULL);
INSERT INTO `user` VALUES (40, '老师3', '13000000632', NULL, 3, 1, '2021-05-22 13:32:17', NULL);

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `identity_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `identifier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credential` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_auth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES (1, 1, 'local', 'admin', '$2a$10$zkavtFtdqrvCnMR9eSN0KeNutn0cbEevIVCkaBrKLQwOhwy3ZhDgS', '2021-05-01 22:46:07', '2021-05-11 22:46:12');
INSERT INTO `user_auth` VALUES (2, 1, 'phone', '13015959118', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-08 17:32:26', '2021-05-08 17:32:29');
INSERT INTO `user_auth` VALUES (28, 19, 'local', 'student1', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-09 15:34:04', '2021-05-17 17:00:17');
INSERT INTO `user_auth` VALUES (29, 19, 'phone', '13000000001', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-09 15:34:04', '2021-05-17 17:00:19');
INSERT INTO `user_auth` VALUES (30, 20, 'local', 'teacher1', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-09 15:36:10', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (31, 20, 'phone', '13000000002', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-09 15:36:10', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (35, 23, 'local', 'student2', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-13 11:23:49', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (36, 23, 'phone', '13000000003', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-13 11:24:11', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (39, 24, 'local', 'student3', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:42:43', '2021-05-07 17:00:31');
INSERT INTO `user_auth` VALUES (40, 24, 'phone', '13000000004', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:43:31', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (41, 25, 'local', 'student4', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:44:26', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (42, 25, 'phone', '13000000005', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:44:51', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (43, 26, 'local', 'student5', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:45:18', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (44, 26, 'phone', '13000000006', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:45:35', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (45, 27, 'local', 'student6', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:46:47', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (46, 27, 'phone', '13000000007', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:47:05', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (47, 28, 'local', 'teacher2', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:47:41', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (48, 28, 'phone', '13000000008', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-14 16:47:55', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (49, 31, 'local', 'teacher', '$2a$10$fR0izHO1BMMsS1TEpfXL4.wNAbqg3qGcuBkXy6dH5mOjsi45odzS.', '2021-05-17 16:50:34', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (50, 31, 'phone', '13000000015', '$2a$10$fR0izHO1BMMsS1TEpfXL4.wNAbqg3qGcuBkXy6dH5mOjsi45odzS.', '2021-05-17 16:51:07', '2021-05-17 17:00:21');
INSERT INTO `user_auth` VALUES (53, 35, 'local', 'admin1', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-17 19:40:57', NULL);
INSERT INTO `user_auth` VALUES (54, 35, 'phone', '13015689632', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-17 19:40:57', NULL);
INSERT INTO `user_auth` VALUES (57, 39, 'local', 'studentxxxx', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-21 22:25:07', NULL);
INSERT INTO `user_auth` VALUES (58, 39, 'phone', '13000000236', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-21 22:25:07', NULL);
INSERT INTO `user_auth` VALUES (59, 40, 'local', 'teacher123', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-22 13:32:17', NULL);
INSERT INTO `user_auth` VALUES (60, 40, 'phone', '13000000632', '$2a$10$r0kc433EWd.7PpOi4yKBB.a3ck0mNx7ZnNjdgomdD1ZZwTwqPV7dK', '2021-05-22 13:32:17', NULL);

SET FOREIGN_KEY_CHECKS = 1;
