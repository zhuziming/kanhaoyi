/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : kanhaoyi

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-08-06 22:27:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `method` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('1', 'test.action', '测试');
INSERT INTO `action` VALUES ('2', '/back/myFootprint.action', '我的足迹');
INSERT INTO `action` VALUES ('3', '/back/index.action', '个人中心首页');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `course_type_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `click_volume` int(10) unsigned DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `course_path` varchar(255) DEFAULT NULL,
  `quantity` tinyint(4) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('36', 'bootstrap测试发部', '1', '2', '2018-07-29 17:32:10', '0', 'a4736995-5b16-4305-95f0-051286996740.jpg', '/internal/2/42.html', '2');

-- ----------------------------
-- Table structure for course_comment
-- ----------------------------
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `praise` int(10) unsigned DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_comment
-- ----------------------------
INSERT INTO `course_comment` VALUES ('1', '35', '2', '终于成功发布了', '1', '2018-07-29 15:36:33');
INSERT INTO `course_comment` VALUES ('2', '35', '2', '下周开始写导航 ', '1', '2018-07-29 15:37:14');

-- ----------------------------
-- Table structure for course_comment_praise
-- ----------------------------
DROP TABLE IF EXISTS `course_comment_praise`;
CREATE TABLE `course_comment_praise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `comment_id` int(10) unsigned DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_comment_praise
-- ----------------------------
INSERT INTO `course_comment_praise` VALUES ('1', '2', '1', '2018-07-29 15:36:57');
INSERT INTO `course_comment_praise` VALUES ('2', '2', '2', '2018-07-29 15:37:19');

-- ----------------------------
-- Table structure for course_detail
-- ----------------------------
DROP TABLE IF EXISTS `course_detail`;
CREATE TABLE `course_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned DEFAULT NULL,
  `course_detail_name` varchar(255) DEFAULT NULL,
  `course_path` varchar(255) DEFAULT NULL,
  `video_id` int(10) unsigned DEFAULT NULL,
  `sequence` tinyint(4) unsigned DEFAULT NULL,
  `click_volume` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_detail
-- ----------------------------
INSERT INTO `course_detail` VALUES ('42', '36', '第一', '/internal/2/42.html', '110', '1', '0', '2018-07-29 17:32:11');
INSERT INTO `course_detail` VALUES ('43', '36', '简介二', '/internal/2/43.html', '111', '2', '0', '2018-07-29 17:32:11');

-- ----------------------------
-- Table structure for course_people
-- ----------------------------
DROP TABLE IF EXISTS `course_people`;
CREATE TABLE `course_people` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned DEFAULT NULL,
  `people_part_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_people
-- ----------------------------

-- ----------------------------
-- Table structure for course_type
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `namespace` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_type
-- ----------------------------
INSERT INTO `course_type` VALUES ('1', '内科', 'internal');
INSERT INTO `course_type` VALUES ('2', '外科', 'surgery');
INSERT INTO `course_type` VALUES ('3', '耳鼻喉科', 'ENT');
INSERT INTO `course_type` VALUES ('4', '泌尿科', 'urinary');
INSERT INTO `course_type` VALUES ('5', '男科', 'andrology');
INSERT INTO `course_type` VALUES ('6', '肛肠科', 'proctology');
INSERT INTO `course_type` VALUES ('7', '皮肤科', 'dermatology');
INSERT INTO `course_type` VALUES ('8', '遗传科', 'heredity');
INSERT INTO `course_type` VALUES ('9', '妇产科', 'gynaecology');
INSERT INTO `course_type` VALUES ('10', '中医科', 'TCM');
INSERT INTO `course_type` VALUES ('11', '眼科', 'ophthalmology');
INSERT INTO `course_type` VALUES ('12', '口腔抖', 'stomatology');
INSERT INTO `course_type` VALUES ('13', '养生科', 'beautify');
INSERT INTO `course_type` VALUES ('14', '儿科', 'pediatric');

-- ----------------------------
-- Table structure for essay
-- ----------------------------
DROP TABLE IF EXISTS `essay`;
CREATE TABLE `essay` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `essay` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of essay
-- ----------------------------
INSERT INTO `essay` VALUES ('1', '冬天泡脚有助于睡眠，当水中的热量流便全身，可以让身体进入放松状态');
INSERT INTO `essay` VALUES ('2', '碳酸饮料不易多喝，因为其中的水分并不能解渴');

-- ----------------------------
-- Table structure for index_news
-- ----------------------------
DROP TABLE IF EXISTS `index_news`;
CREATE TABLE `index_news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `course_id` int(10) unsigned DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of index_news
-- ----------------------------

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(10) unsigned DEFAULT NULL,
  `info_model_id` int(10) unsigned DEFAULT NULL,
  `info_title` varchar(255) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------

-- ----------------------------
-- Table structure for info_model
-- ----------------------------
DROP TABLE IF EXISTS `info_model`;
CREATE TABLE `info_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_model
-- ----------------------------

-- ----------------------------
-- Table structure for people_part
-- ----------------------------
DROP TABLE IF EXISTS `people_part`;
CREATE TABLE `people_part` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `part_name` varchar(255) DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_part
-- ----------------------------
INSERT INTO `people_part` VALUES ('1', '头部', 'head');
INSERT INTO `people_part` VALUES ('2', '胸部', 'chest');
INSERT INTO `people_part` VALUES ('3', '腹部', 'abdomen');
INSERT INTO `people_part` VALUES ('4', '阴部', 'perineum');
INSERT INTO `people_part` VALUES ('5', '上肢', 'hand');
INSERT INTO `people_part` VALUES ('6', '下肢', 'leg');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通人', 'ordinary');
INSERT INTO `role` VALUES ('2', '普通管理员', 'ordinaryManager');
INSERT INTO `role` VALUES ('3', '超级管理员', 'admin');
INSERT INTO `role` VALUES ('4', '老师', 'teacher');

-- ----------------------------
-- Table structure for role_action
-- ----------------------------
DROP TABLE IF EXISTS `role_action`;
CREATE TABLE `role_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rid` int(10) unsigned DEFAULT NULL,
  `aid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_action
-- ----------------------------
INSERT INTO `role_action` VALUES ('1', '1', '1');
INSERT INTO `role_action` VALUES ('2', '1', '2');
INSERT INTO `role_action` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account` char(20) DEFAULT NULL,
  `nickname` char(20) DEFAULT NULL,
  `password` char(40) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `info_num` int(3) unsigned DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '18237126723', 'sss', '60A68175A5E9879CC3F8A1F4B7E65B6B', '18237126723', '18237126723@163.com', '/default.jpg', '男', null, '2018-04-19 10:33:12');
INSERT INTO `user` VALUES ('7', 'admin', '管理员', '60A68175A5E9879CC3F8A1F4B7E65B6B', '18237126723', '', null, '', null, '2018-05-27 16:25:44');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned DEFAULT NULL,
  `rid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', '1');
INSERT INTO `user_role` VALUES ('2', '2', '3');
INSERT INTO `user_role` VALUES ('3', '2', '4');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `letter_name` varchar(255) DEFAULT NULL,
  `account_id` int(10) unsigned DEFAULT NULL,
  `group_id` int(10) unsigned DEFAULT NULL,
  `remove` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('110', 'bootstrap介绍', 'd9f326c5-3813-405a-abb2-04dcc2ae0403.mp4', '2', '17', '0', '2018-07-29 17:31:38');
INSERT INTO `video` VALUES ('111', 'bootstrap介绍二', '25edd139-402e-4e2b-b977-8485f2626315.mp4', '2', '17', '0', '2018-07-29 17:31:45');

-- ----------------------------
-- Table structure for video_group
-- ----------------------------
DROP TABLE IF EXISTS `video_group`;
CREATE TABLE `video_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_group
-- ----------------------------
INSERT INTO `video_group` VALUES ('1', '消化系统', '1');
INSERT INTO `video_group` VALUES ('13', '消化道', '2');
INSERT INTO `video_group` VALUES ('14', '呼吸系统', '2');
INSERT INTO `video_group` VALUES ('15', '呼吸系统2', '2');
INSERT INTO `video_group` VALUES ('16', '呼级系统3', '2');
INSERT INTO `video_group` VALUES ('17', 'IT介绍', '2');
