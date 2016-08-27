/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : xbw

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-08-27 18:44:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_type` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'C',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', 'A');
INSERT INTO `s_user` VALUES ('2', 'xbw', '202cb962ac59075b964b07152d234b70', 'C');
