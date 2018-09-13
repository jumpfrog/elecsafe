/*
Navicat MySQL Data Transfer

Source Server         : charging
Source Server Version : 50537
Source Host           : 172.16.15.55:3306
Source Database       : emcp_1.1

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-09-08 13:23:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dat_es_detector_curstatus
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_curstatus`;
CREATE TABLE `dat_es_detector_curstatus` (
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime DEFAULT NULL,
  `ISALARM` smallint(2) DEFAULT NULL,
  `ISFAULT` smallint(2) DEFAULT NULL,
  `ISOFFLINE` smallint(2) DEFAULT NULL,
  PRIMARY KEY (`DETID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_event
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_event`;
CREATE TABLE `dat_es_detector_event` (
  `EVTID` int(11) NOT NULL AUTO_INCREMENT,
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime NOT NULL,
  `EVTTYPE` smallint(4) NOT NULL,
  `EVTDESC` varchar(512) DEFAULT NULL,
  `DEALSTATUS` smallint(2) DEFAULT NULL,
  `DEALTIME` datetime DEFAULT NULL,
  `DEALREMARK` varchar(512) DEFAULT NULL,
  `DEALACCOUNT` varchar(40) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`EVTID`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_event_deal
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_event_deal`;
CREATE TABLE `dat_es_detector_event_deal` (
  `EVTID` int(11) NOT NULL,
  `DEALTIME` datetime NOT NULL,
  `DEALREMARK` varchar(512) NOT NULL,
  `DEALACCOUNT` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`EVTID`,`DEALTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_l1_his
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_l1_his`;
CREATE TABLE `dat_es_detector_l1_his` (
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime NOT NULL,
  `U` decimal(8,4) DEFAULT NULL,
  `UA` decimal(8,4) DEFAULT NULL,
  `UB` decimal(8,4) DEFAULT NULL,
  `UC` decimal(8,4) DEFAULT NULL,
  `I` decimal(8,4) DEFAULT NULL,
  `IA` decimal(8,4) DEFAULT NULL,
  `IB` decimal(8,4) DEFAULT NULL,
  `IC` decimal(8,4) DEFAULT NULL,
  `INX` decimal(8,4) DEFAULT NULL,
  `IL` decimal(8,4) DEFAULT NULL,
  `TA` decimal(8,4) DEFAULT NULL,
  `TB` decimal(8,4) DEFAULT NULL,
  `TC` decimal(8,4) DEFAULT NULL,
  `TN` decimal(8,4) DEFAULT NULL,
  `TL` decimal(8,4) DEFAULT NULL,
  `TI` decimal(8,4) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DETID`,`DATATIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_l1_real
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_l1_real`;
CREATE TABLE `dat_es_detector_l1_real` (
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime NOT NULL,
  `U` decimal(8,4) DEFAULT NULL,
  `UA` decimal(8,4) DEFAULT NULL,
  `UB` decimal(8,4) DEFAULT NULL,
  `UC` decimal(8,4) DEFAULT NULL,
  `I` decimal(8,4) DEFAULT NULL,
  `IA` decimal(8,4) DEFAULT NULL,
  `IB` decimal(8,4) DEFAULT NULL,
  `IC` decimal(8,4) DEFAULT NULL,
  `INX` decimal(8,4) DEFAULT NULL,
  `IL` decimal(8,4) DEFAULT NULL,
  `TA` decimal(8,4) DEFAULT NULL,
  `TB` decimal(8,4) DEFAULT NULL,
  `TC` decimal(8,4) DEFAULT NULL,
  `TN` decimal(8,4) DEFAULT NULL,
  `TL` decimal(8,4) DEFAULT NULL,
  `TI` decimal(8,4) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DETID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_l2_his
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_l2_his`;
CREATE TABLE `dat_es_detector_l2_his` (
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime NOT NULL,
  `F` decimal(8,4) DEFAULT NULL,
  `FA` decimal(8,4) DEFAULT NULL,
  `FB` decimal(8,4) DEFAULT NULL,
  `FC` decimal(8,4) DEFAULT NULL,
  `P` decimal(12,4) DEFAULT NULL,
  `PA` decimal(12,4) DEFAULT NULL,
  `PB` decimal(12,4) DEFAULT NULL,
  `PC` decimal(12,4) DEFAULT NULL,
  `Q` decimal(12,4) DEFAULT NULL,
  `QA` decimal(12,4) DEFAULT NULL,
  `QB` decimal(12,4) DEFAULT NULL,
  `QC` decimal(12,4) DEFAULT NULL,
  `AP` decimal(12,4) DEFAULT NULL,
  `APA` decimal(12,4) DEFAULT NULL,
  `APB` decimal(12,4) DEFAULT NULL,
  `APC` decimal(12,4) DEFAULT NULL,
  `PF` decimal(8,4) DEFAULT NULL,
  `PFA` decimal(8,4) DEFAULT NULL,
  `PFB` decimal(8,4) DEFAULT NULL,
  `PFC` decimal(8,4) DEFAULT NULL,
  `ENERGYP` decimal(15,3) DEFAULT NULL,
  `ENERGYPA` decimal(15,3) DEFAULT NULL,
  `ENERGYPB` decimal(15,3) DEFAULT NULL,
  `ENERGYPC` decimal(15,3) DEFAULT NULL,
  `ENERGYQ` decimal(15,3) DEFAULT NULL,
  `ENERGYQA` decimal(15,3) DEFAULT NULL,
  `ENERGYQB` decimal(15,3) DEFAULT NULL,
  `ENERGYQC` decimal(15,3) DEFAULT NULL,
  `ENERGYAP` decimal(15,3) DEFAULT NULL,
  `ENERGYAPA` decimal(15,3) DEFAULT NULL,
  `ENERGYAPB` decimal(15,3) DEFAULT NULL,
  `ENERGYAPC` decimal(15,3) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DETID`,`DATATIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dat_es_detector_l2_real
-- ----------------------------
DROP TABLE IF EXISTS `dat_es_detector_l2_real`;
CREATE TABLE `dat_es_detector_l2_real` (
  `DETID` int(11) NOT NULL,
  `DATATIME` datetime NOT NULL,
  `F` decimal(8,4) DEFAULT NULL,
  `FA` decimal(8,4) DEFAULT NULL,
  `FB` decimal(8,4) DEFAULT NULL,
  `FC` decimal(8,4) DEFAULT NULL,
  `P` decimal(12,4) DEFAULT NULL,
  `PA` decimal(12,4) DEFAULT NULL,
  `PB` decimal(12,4) DEFAULT NULL,
  `PC` decimal(12,4) DEFAULT NULL,
  `Q` decimal(12,4) DEFAULT NULL,
  `QA` decimal(12,4) DEFAULT NULL,
  `QB` decimal(12,4) DEFAULT NULL,
  `QC` decimal(12,4) DEFAULT NULL,
  `AP` decimal(12,4) DEFAULT NULL,
  `APA` decimal(12,4) DEFAULT NULL,
  `APB` decimal(12,4) DEFAULT NULL,
  `APC` decimal(12,4) DEFAULT NULL,
  `PF` decimal(8,4) DEFAULT NULL,
  `PFA` decimal(8,4) DEFAULT NULL,
  `PFB` decimal(8,4) DEFAULT NULL,
  `PFC` decimal(8,4) DEFAULT NULL,
  `ENERGYP` decimal(15,3) DEFAULT NULL,
  `ENERGYPA` decimal(15,3) DEFAULT NULL,
  `ENERGYPB` decimal(15,3) DEFAULT NULL,
  `ENERGYPC` decimal(15,3) DEFAULT NULL,
  `ENERGYQ` decimal(15,3) DEFAULT NULL,
  `ENERGYQA` decimal(15,3) DEFAULT NULL,
  `ENERGYQB` decimal(15,3) DEFAULT NULL,
  `ENERGYQC` decimal(15,3) DEFAULT NULL,
  `ENERGYAP` decimal(15,3) DEFAULT NULL,
  `ENERGYAPA` decimal(15,3) DEFAULT NULL,
  `ENERGYAPB` decimal(15,3) DEFAULT NULL,
  `ENERGYAPC` decimal(15,3) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`DETID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for es_detector
-- ----------------------------
DROP TABLE IF EXISTS `es_detector`;
CREATE TABLE `es_detector` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) NOT NULL,
  `TYPE` smallint(2) NOT NULL,
  `STATUS` smallint(2) DEFAULT NULL,
  `MODELID` smallint(4) DEFAULT NULL,
  `COMMADDR` varchar(32) DEFAULT NULL,
  `PROTOCOLID` smallint(4) DEFAULT NULL,
  `OWNERID` int(11) NOT NULL,
  `OWNERTYPE` smallint(2) NOT NULL,
  `INSTALLADDR` varchar(64) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for es_detector_model
-- ----------------------------
DROP TABLE IF EXISTS `es_detector_model`;
CREATE TABLE `es_detector_model` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `BRAND` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for es_detector_threshold
-- ----------------------------
DROP TABLE IF EXISTS `es_detector_threshold`;
CREATE TABLE `es_detector_threshold` (
  `ID` int(11) NOT NULL,
  `OVERU` decimal(8,4) DEFAULT NULL,
  `UNDERU` decimal(8,4) DEFAULT NULL,
  `OVERI` decimal(8,4) DEFAULT NULL,
  `IL` decimal(8,4) DEFAULT NULL,
  `TA` decimal(8,4) DEFAULT NULL,
  `TB` decimal(8,4) DEFAULT NULL,
  `TC` decimal(8,4) DEFAULT NULL,
  `TN` decimal(8,4) DEFAULT NULL,
  `TL` decimal(8,4) DEFAULT NULL,
  `TI` decimal(8,4) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dic_detector_protocol
-- ----------------------------
DROP TABLE IF EXISTS `dic_detector_protocol`;
CREATE TABLE `dic_detector_protocol` (
  `ID` smallint(4) NOT NULL,
  `NAME` varchar(64) NOT NULL,
  `INUSE` smallint(1) NOT NULL,
  `REMARK` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



