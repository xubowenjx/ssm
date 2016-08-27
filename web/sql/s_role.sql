/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : xbw

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-08-27 18:44:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `valid` int(1) DEFAULT NULL COMMENT '1可用0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'admin', '1');
INSERT INTO `s_role` VALUES ('2', 'comm', '1');
