-- MySQL dump 10.13  Distrib 5.6.25, for Win64 (x86_64)
--
-- Host: localhost    Database: scd_batch
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_assetsstat`
--

DROP TABLE IF EXISTS `t_assetsstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_assetsstat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date DEFAULT NULL COMMENT '日期',
  `CURRENT` double DEFAULT NULL COMMENT '流动性产品',
  `FIX_PLAN` double DEFAULT NULL COMMENT '定期计划',
  `FIX_PROJECT` double DEFAULT NULL COMMENT '定期项目',
  `BALANCE` double DEFAULT NULL COMMENT '财主账户余额',
  `FROZEN` double DEFAULT NULL COMMENT '冻结金额',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_ASSETSSTAT_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台资产管理规模报表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_assetsstat`
--

LOCK TABLES `t_assetsstat` WRITE;
/*!40000 ALTER TABLE `t_assetsstat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_assetsstat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_borrowerrepayplan`
--

DROP TABLE IF EXISTS `t_borrowerrepayplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_borrowerrepayplan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DUE_DATE` date DEFAULT NULL COMMENT '到期日',
  `BORROWER_ID` bigint(20) DEFAULT NULL COMMENT '融资方ID',
  `BORROWER_NAME` varchar(256) DEFAULT NULL COMMENT '融资方名称',
  `PROJECT_CODE` varchar(64) DEFAULT NULL COMMENT '项目编号',
  `PROJECT_NAME` varchar(256) DEFAULT NULL COMMENT '项目名称',
  `REPAY_TYPE` int(11) DEFAULT NULL COMMENT '还款方式',
  `DUE_PRINCIPAL` double DEFAULT NULL,
  `DUE_INTEREST` double DEFAULT NULL,
  `DUE_SUM` double DEFAULT NULL,
  `REPAY_PRINCIPAL` double DEFAULT NULL,
  `REPAY_INTEREST` double DEFAULT NULL,
  `REPAY_SUM` double DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_BORROWERREPAYPLAN_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='融资方还款计划表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_borrowerrepayplan`
--

LOCK TABLES `t_borrowerrepayplan` WRITE;
/*!40000 ALTER TABLE `t_borrowerrepayplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_borrowerrepayplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_expenditurestat`
--

DROP TABLE IF EXISTS `t_expenditurestat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_expenditurestat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `DATE` date DEFAULT NULL COMMENT '日期',
  `TYPE` int(11) DEFAULT NULL COMMENT '支出类型',
  `AMOUNT` double DEFAULT NULL COMMENT '金额',
  `VERSION` int(11) DEFAULT NULL COMMENT '版本',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_EXPENDITURESTAT_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台线上支出报表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_expenditurestat`
--

LOCK TABLES `t_expenditurestat` WRITE;
/*!40000 ALTER TABLE `t_expenditurestat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_expenditurestat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_fixproductduestat`
--

DROP TABLE IF EXISTS `t_fixproductduestat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_fixproductduestat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `DUE_DATE` date DEFAULT NULL COMMENT '到期日期',
  `FIX_PLAN_PRINCIPAL` double DEFAULT NULL COMMENT '定期计划到期本金',
  `FIX_PLAN_INTEREST` double DEFAULT NULL COMMENT '定期计划到期利息',
  `FIX_PROJECT_PRINCIPAL` double DEFAULT NULL COMMENT '定期项目到期本金',
  `FIX_PROJECT_INTEREST` double DEFAULT NULL COMMENT '定期项目到期利息',
  `SUM_AMT` double DEFAULT NULL COMMENT '总计',
  `VERSION` int(11) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT NULL,
  `MODIFIED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_FIXPRODUCTDUESTAT_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定期产品到期时间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_fixproductduestat`
--

LOCK TABLES `t_fixproductduestat` WRITE;
/*!40000 ALTER TABLE `t_fixproductduestat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_fixproductduestat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_fundstat`
--

DROP TABLE IF EXISTS `t_fundstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_fundstat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECHARGE` double DEFAULT NULL COMMENT '充值汇总金额',
  `WITHDRAW` double DEFAULT NULL COMMENT '提现汇总金额',
  `TRANS_DATE` date DEFAULT NULL COMMENT '业务日期',
  `VERSION` int(11) DEFAULT '0',
  `CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_FUNDSTAT_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_fundstat`
--

LOCK TABLES `t_fundstat` WRITE;
/*!40000 ALTER TABLE `t_fundstat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_fundstat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_projectlimit`
--

DROP TABLE IF EXISTS `t_projectlimit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_projectlimit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROJECT_CODE` varchar(64) DEFAULT NULL COMMENT '项目编号',
  `PROJECT_NAME` varchar(256) DEFAULT NULL COMMENT '项目标的名称',
  `TOTAL` double DEFAULT NULL COMMENT '项目计划融资额度',
  `BID` double DEFAULT NULL COMMENT '已发标额度',
  `UNBID` double DEFAULT NULL COMMENT '未发标额度',
  `WITHDRAW` double DEFAULT NULL COMMENT '已提现金额',
  `PAID_PRINCIPAL` double DEFAULT NULL COMMENT '已还本金',
  `UNPAID_PRINCIPAL` double DEFAULT NULL,
  `PAID_INTEREST` double DEFAULT NULL,
  `UNPAID_INTEREST` double DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `column_13` int(11) DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_PROJECTLIMIT_ID_uindex` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目额度报表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_projectlimit`
--

LOCK TABLES `t_projectlimit` WRITE;
/*!40000 ALTER TABLE `t_projectlimit` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_projectlimit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-12 21:36:23
