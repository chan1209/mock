/*
Navicat MySQL Data Transfer

Source Server         : st
Source Server Version : 50173
Source Host           : 172.17.53.61:3306
Source Database       : TC_DEV

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-20 15:22:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mock_data
-- ----------------------------
DROP TABLE IF EXISTS `mock_data`;
CREATE TABLE `mock_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mockServiceName` varchar(255) NOT NULL,
  `mockUrlPath` varchar(255) NOT NULL,
  `mockResponse` varchar(4096) NOT NULL,
  `mockParams` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
