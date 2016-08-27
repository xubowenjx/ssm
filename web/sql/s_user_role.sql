/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : xbw

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-08-27 18:44:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `rela_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT ' 用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`rela_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户和角色的关系表';

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '2', '2');
