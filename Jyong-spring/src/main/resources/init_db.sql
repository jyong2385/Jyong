
create database if EXISTS jyong_db;
use jyong_db;
-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
                        `id` varchar(32) DEFAULT NULL,
                        `name` varchar(32) DEFAULT NULL,
                        `CountryCode` varchar(32) DEFAULT NULL,
                        `District` varchar(32) DEFAULT NULL,
                        `Population` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '张三', '2', '3', '4');
