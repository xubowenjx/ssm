/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : 127.0.0.1:3306
Source Database       : xbw

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2016-08-27 18:44:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_role_perm_rela
-- ----------------------------
DROP TABLE IF EXISTS `s_role_perm_rela`;
CREATE TABLE `s_role_perm_rela` (
  `pr_rela_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pr_rela_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色权限项关系表';

-- ----------------------------
-- Records of s_role_perm_rela
-- ----------------------------
INSERT INTO `s_role_perm_rela` VALUES ('1', '2', '1');
