CREATE DATABASE  IF NOT EXISTS `proyds2_cp9tfg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `proyds2_cp9tfg`;
-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: proyds2-cp9tfg.cq0jvqyd57ap.sa-east-1.rds.amazonaws.com    Database: proyds2_cp9tfg
-- ------------------------------------------------------
-- Server version	8.0.13

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='';

--
-- Table structure for table `ADMINISTRADOR`
--

DROP TABLE IF EXISTS `ADMINISTRADOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADMINISTRADOR` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_ADMINISTRADOR_ID` FOREIGN KEY (`ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADMINISTRADOR`
--

LOCK TABLES `ADMINISTRADOR` WRITE;
/*!40000 ALTER TABLE `ADMINISTRADOR` DISABLE KEYS */;
INSERT INTO `ADMINISTRADOR` VALUES (1),(3501),(3551),(3601),(3651),(3702);
/*!40000 ALTER TABLE `ADMINISTRADOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CALIFICACION`
--

DROP TABLE IF EXISTS `CALIFICACION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CALIFICACION` (
  `COMPRADOR_ID` bigint(20) DEFAULT NULL,
  KEY `FK_CALIFICACION_COMPRADOR_ID` (`COMPRADOR_ID`),
  CONSTRAINT `FK_CALIFICACION_COMPRADOR_ID` FOREIGN KEY (`COMPRADOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CALIFICACION`
--

LOCK TABLES `CALIFICACION` WRITE;
/*!40000 ALTER TABLE `CALIFICACION` DISABLE KEYS */;
/*!40000 ALTER TABLE `CALIFICACION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CALIFICACIONPRODUCTO`
--

DROP TABLE IF EXISTS `CALIFICACIONPRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CALIFICACIONPRODUCTO` (
  `ID` bigint(20) NOT NULL,
  `ESTRELLAS` int(11) DEFAULT NULL,
  `COMPRADOR_ID` bigint(20) DEFAULT NULL,
  `PRODUCTO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CALIFICACIONPRODUCTO_PRODUCTO_ID` (`PRODUCTO_ID`),
  KEY `FK_CALIFICACIONPRODUCTO_COMPRADOR_ID` (`COMPRADOR_ID`),
  CONSTRAINT `FK_CALIFICACIONPRODUCTO_COMPRADOR_ID` FOREIGN KEY (`COMPRADOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CALIFICACIONPRODUCTO_PRODUCTO_ID` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `PRODUCTO` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CALIFICACIONPRODUCTO`
--

LOCK TABLES `CALIFICACIONPRODUCTO` WRITE;
/*!40000 ALTER TABLE `CALIFICACIONPRODUCTO` DISABLE KEYS */;
INSERT INTO `CALIFICACIONPRODUCTO` VALUES (401,4,51,152),(551,4,51,154),(1404,4,901,1401),(1568,3,851,1501),(3004,4,1801,152);
/*!40000 ALTER TABLE `CALIFICACIONPRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CALIFICACIONVENDEDOR`
--

DROP TABLE IF EXISTS `CALIFICACIONVENDEDOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CALIFICACIONVENDEDOR` (
  `ID` bigint(20) NOT NULL,
  `ESTRELLAS` int(11) DEFAULT NULL,
  `COMPRADOR_ID` bigint(20) DEFAULT NULL,
  `VENDEDOR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CALIFICACIONVENDEDOR_COMPRADOR_ID` (`COMPRADOR_ID`),
  KEY `FK_CALIFICACIONVENDEDOR_VENDEDOR_ID` (`VENDEDOR_ID`),
  CONSTRAINT `FK_CALIFICACIONVENDEDOR_COMPRADOR_ID` FOREIGN KEY (`COMPRADOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CALIFICACIONVENDEDOR_VENDEDOR_ID` FOREIGN KEY (`VENDEDOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CALIFICACIONVENDEDOR`
--

LOCK TABLES `CALIFICACIONVENDEDOR` WRITE;
/*!40000 ALTER TABLE `CALIFICACIONVENDEDOR` DISABLE KEYS */;
INSERT INTO `CALIFICACIONVENDEDOR` VALUES (801,5,51,101),(3003,4,1801,101),(3355,4,2501,101);
/*!40000 ALTER TABLE `CALIFICACIONVENDEDOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMPRADOR`
--

DROP TABLE IF EXISTS `COMPRADOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMPRADOR` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_COMPRADOR_ID` FOREIGN KEY (`ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMPRADOR`
--

LOCK TABLES `COMPRADOR` WRITE;
/*!40000 ALTER TABLE `COMPRADOR` DISABLE KEYS */;
INSERT INTO `COMPRADOR` VALUES (51),(101),(851),(901),(952),(1556),(1751),(1801),(2501),(3251),(3301),(3451);
/*!40000 ALTER TABLE `COMPRADOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `METODOPAGO`
--

DROP TABLE IF EXISTS `METODOPAGO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `METODOPAGO` (
  `ID` bigint(20) NOT NULL,
  `DTYPE` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `PEDIDO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_METODOPAGO_PEDIDO_ID` (`PEDIDO_ID`),
  CONSTRAINT `FK_METODOPAGO_PEDIDO_ID` FOREIGN KEY (`PEDIDO_ID`) REFERENCES `PEDIDO` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `METODOPAGO`
--

LOCK TABLES `METODOPAGO` WRITE;
/*!40000 ALTER TABLE `METODOPAGO` DISABLE KEYS */;
INSERT INTO `METODOPAGO` VALUES (201,'PagoEfectivo',202),(203,'PagoMonedero',204),(301,'PagoMonedero',302),(351,'PagoEfectivo',352),(353,'PagoEfectivo',354),(751,'PagoMonedero',752),(1302,'PagoEfectivo',NULL),(1355,'PagoEfectivo',NULL),(1402,'PagoEfectivo',1403),(1560,'PagoEfectivo',1561),(1562,'PagoEfectivo',1563),(1564,'PagoEfectivo',1565),(1566,'PagoMonedero',1567),(1601,'PagoEfectivo',1602),(1652,'PagoMonedero',NULL),(1701,'PagoEfectivo',1702),(1851,'PagoMonedero',1852),(1853,'PagoMonedero',1854),(1855,'PagoMonedero',1856),(1951,'PagoMonedero',1952),(2001,'PagoMonedero',2002),(2051,'PagoMonedero',2052),(2101,'PagoMonedero',2102),(2103,'PagoMonedero',2104),(2151,'PagoMonedero',2152),(2201,'PagoMonedero',2202),(2251,'PagoMonedero',2252),(2301,'PagoMonedero',2302),(2303,'PagoMonedero',2304),(2351,'PagoMonedero',2352),(2401,'PagoMonedero',2402),(2403,'PagoMonedero',2404),(2405,'PagoMonedero',2406),(2451,'PagoMonedero',2452),(2551,'PagoMonedero',2552),(2601,'PagoMonedero',2602),(2603,'PagoMonedero',2604),(2605,'PagoMonedero',2606),(2607,'PagoMonedero',2608),(2651,'PagoMonedero',2652),(2701,'PagoMonedero',2702),(2751,'PagoMonedero',2752),(2851,'PagoMonedero',2852),(2901,'PagoMonedero',2902),(2951,'PagoMonedero',2952),(3001,'PagoMonedero',3002),(3051,'PagoMonedero',3052),(3101,'PagoMonedero',3102),(3201,'PagoMonedero',3202),(3351,'PagoMonedero',3352),(3353,'PagoEfectivo',3354),(3751,'PagoMonedero',3752),(3851,'PagoMonedero',3852);
/*!40000 ALTER TABLE `METODOPAGO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAGOEFECTIVO`
--

DROP TABLE IF EXISTS `PAGOEFECTIVO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAGOEFECTIVO` (
  `ID` bigint(20) NOT NULL,
  `NOMBRECLIENTE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_PAGOEFECTIVO_ID` FOREIGN KEY (`ID`) REFERENCES `METODOPAGO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGOEFECTIVO`
--

LOCK TABLES `PAGOEFECTIVO` WRITE;
/*!40000 ALTER TABLE `PAGOEFECTIVO` DISABLE KEYS */;
INSERT INTO `PAGOEFECTIVO` VALUES (201,'Ninguno Jr.'),(351,'Yo'),(353,'Nadie'),(1302,'Nadie'),(1355,'ABC'),(1402,'Nadie'),(1560,'Yp'),(1562,'Yo'),(1564,'Comprador 2'),(1601,'Usuario'),(1701,'Nadie'),(3353,'Axel');
/*!40000 ALTER TABLE `PAGOEFECTIVO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAGOMONEDERO`
--

DROP TABLE IF EXISTS `PAGOMONEDERO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAGOMONEDERO` (
  `ID` bigint(20) NOT NULL,
  `CELULAR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NOMBRECLIENTE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_PAGOMONEDERO_ID` FOREIGN KEY (`ID`) REFERENCES `METODOPAGO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGOMONEDERO`
--

LOCK TABLES `PAGOMONEDERO` WRITE;
/*!40000 ALTER TABLE `PAGOMONEDERO` DISABLE KEYS */;
INSERT INTO `PAGOMONEDERO` VALUES (203,'0999999','Otro Ninguno'),(301,'0999999999','Ninguno Jr.'),(751,'0987654321','Nadie'),(1566,'099999999','Comprador 2'),(1652,'2572092','Fernando'),(1851,'02345','Nadie'),(1853,'099999','Nadie'),(1855,'099999','nadie'),(1951,'099999','Nadie'),(2001,'099999','Nadie'),(2051,'1234','Nadie'),(2101,'099999','Nadie'),(2103,'099999','Nadie'),(2151,'099999','Nadie'),(2201,'0999','Nadie'),(2251,'099','Nadie'),(2301,'099','Nadie'),(2303,'099','Nadie'),(2351,'099','Nadie Jr.'),(2401,'00000','Nadie'),(2403,'099999','Nadie'),(2405,'099999','Ninguno'),(2451,'099999','Comprador Uno'),(2551,'099999','Nadie'),(2601,'099','Nadie'),(2603,'099999','Nadie'),(2605,'09999','Nadie'),(2607,'099999','Nadie'),(2651,'099999','Nadie'),(2701,'099999','Nadie'),(2751,'099999','Nadie'),(2851,'',''),(2901,'099999','Nadie'),(2951,'099999','Nadie'),(3001,'0967344509','Danny '),(3051,'099999','Comprador'),(3101,'0967344509','Danny '),(3201,'00000','Comprador'),(3351,'0988286513','Axel'),(3751,'099999','Comprador'),(3851,'099999','Comprador');
/*!40000 ALTER TABLE `PAGOMONEDERO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PEDIDO`
--

DROP TABLE IF EXISTS `PEDIDO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PEDIDO` (
  `ID` bigint(20) NOT NULL,
  `ESTADO` int(11) DEFAULT NULL,
  `FECHADEENTREGA` datetime DEFAULT NULL,
  `FECHADEPEDIDO` datetime DEFAULT NULL,
  `NUMEROITEMS` int(11) DEFAULT NULL,
  `PRODUCTO_ID` bigint(20) DEFAULT NULL,
  `COMPRADOR_ID` bigint(20) DEFAULT NULL,
  `METODOPAGO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PEDIDO_COMPRADOR_ID` (`COMPRADOR_ID`),
  KEY `FK_PEDIDO_PRODUCTO_ID` (`PRODUCTO_ID`),
  KEY `FK_PEDIDO_METODOPAGO_ID` (`METODOPAGO_ID`),
  CONSTRAINT `FK_PEDIDO_COMPRADOR_ID` FOREIGN KEY (`COMPRADOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PEDIDO_METODOPAGO_ID` FOREIGN KEY (`METODOPAGO_ID`) REFERENCES `METODOPAGO` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_PEDIDO_PRODUCTO_ID` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `PRODUCTO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PEDIDO`
--

LOCK TABLES `PEDIDO` WRITE;
/*!40000 ALTER TABLE `PEDIDO` DISABLE KEYS */;
INSERT INTO `PEDIDO` VALUES (202,1,NULL,'2019-01-17 13:12:52',1,152,51,201),(204,1,NULL,'2019-01-17 13:13:12',2,152,51,203),(302,0,NULL,'2019-01-17 13:17:48',1,152,51,301),(352,1,NULL,'2019-01-07 21:57:20',3,154,51,351),(354,1,NULL,'2019-01-17 21:57:33',1,153,51,353),(752,2,NULL,'2019-01-18 02:08:05',8,152,51,751),(1403,0,NULL,'2019-01-18 23:08:40',1,1401,901,1402),(1561,0,NULL,'2019-01-19 01:59:22',1,1557,51,1560),(1563,0,NULL,'2019-01-19 01:59:43',10,1558,51,1562),(1565,0,NULL,'2019-01-19 02:00:16',1,1501,851,1564),(1567,0,NULL,'2019-01-19 02:00:42',1,1557,851,1566),(1602,2,NULL,'2019-01-20 15:35:24',1,152,51,1601),(1653,0,NULL,'2019-01-20 17:15:21',1,152,1751,1652),(1702,2,NULL,'2019-01-21 02:39:33',1,152,51,1701),(1852,2,NULL,'2019-01-22 00:29:54',1,152,51,1851),(1854,2,NULL,'2019-01-22 00:30:10',1,152,51,1853),(1856,2,NULL,'2019-01-22 00:30:50',1,152,51,1855),(1952,2,NULL,'2019-01-22 00:36:17',1,152,51,1951),(2002,2,NULL,'2019-01-22 00:41:54',1,152,51,2001),(2052,2,NULL,'2019-01-22 00:43:47',1,152,51,2051),(2102,2,NULL,'2019-01-22 00:46:14',10,152,51,2101),(2104,2,NULL,'2019-01-22 00:46:35',1,152,51,2103),(2152,2,NULL,'2019-01-22 00:48:18',1,152,51,2151),(2202,2,NULL,'2019-01-22 01:58:23',10,155,51,2201),(2252,2,NULL,'2019-01-22 01:59:06',10,155,51,2251),(2302,2,NULL,'2019-01-22 02:00:03',10,155,51,2301),(2304,2,NULL,'2019-01-22 02:00:25',1,155,51,2303),(2352,1,NULL,'2019-01-22 02:01:24',1,155,51,2351),(2402,2,NULL,'2019-01-22 13:35:55',1,152,51,2401),(2404,2,NULL,'2019-01-22 13:37:06',1,152,51,2403),(2406,1,NULL,'2019-01-22 13:38:22',1,152,51,2405),(2452,2,NULL,'2019-01-23 13:02:55',1,155,51,2451),(2552,2,NULL,'2019-01-23 21:32:57',1,155,51,2551),(2602,2,NULL,'2019-01-23 21:34:26',1,155,51,2601),(2604,2,NULL,'2019-01-23 21:34:47',1,155,51,2603),(2606,2,NULL,'2019-01-23 21:35:29',1,155,51,2605),(2608,2,NULL,'2019-01-23 21:35:58',1,155,51,2607),(2652,2,NULL,'2019-01-23 21:37:50',1,152,51,2651),(2702,2,NULL,'2019-01-23 21:52:30',1,155,51,2701),(2752,2,NULL,'2019-01-23 21:57:46',1,152,51,2751),(2852,2,NULL,'2019-01-24 00:38:33',1,155,51,2851),(2902,2,NULL,'2019-01-24 13:13:16',1,155,51,2901),(2952,0,NULL,'2019-01-24 16:41:16',1,2801,51,2951),(3002,1,NULL,'2019-01-26 23:56:00',1,152,1801,3001),(3052,2,NULL,'2019-01-27 01:09:56',1,155,51,3051),(3102,0,NULL,'2019-01-28 03:05:10',1,155,1801,3101),(3202,2,NULL,'2019-01-28 18:02:38',1,155,51,3201),(3352,2,NULL,'2019-02-01 18:29:08',1,152,2501,3351),(3354,0,NULL,'2019-02-01 18:29:36',1,152,2501,3353),(3752,2,NULL,'2019-02-02 17:11:25',1,651,51,3751),(3852,0,NULL,'2019-02-02 17:14:28',1,651,51,3851);
/*!40000 ALTER TABLE `PEDIDO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTO`
--

DROP TABLE IF EXISTS `PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCTO` (
  `ID` bigint(20) NOT NULL,
  `CATEGORIA` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DESCRIPCION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DISPONIBLE` tinyint(1) DEFAULT '0',
  `ELIMINADO` tinyint(1) DEFAULT '0',
  `FECHACREACION` datetime DEFAULT NULL,
  `NOMBREARTICULO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NUMVISTAS` int(11) DEFAULT NULL,
  `PRECIO` float DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  `TIEMPOENTREGA` datetime DEFAULT NULL,
  `VENDEDOR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PRODUCTO_VENDEDOR_ID` (`VENDEDOR_ID`),
  CONSTRAINT `FK_PRODUCTO_VENDEDOR_ID` FOREIGN KEY (`VENDEDOR_ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTO`
--

LOCK TABLES `PRODUCTO` WRITE;
/*!40000 ALTER TABLE `PRODUCTO` DISABLE KEYS */;
INSERT INTO `PRODUCTO` VALUES (151,'','',1,0,'2019-01-17 13:40:34','Producto A de Vend2',21,10,1,'1970-01-01 04:00:00',952),(152,'','',1,0,'2019-01-17 13:40:39','Producto A',23,5,8,'1970-01-01 01:30:00',101),(153,'','',1,0,'2019-01-17 13:40:48','Producto C',18,5,3,'1970-01-01 05:00:00',101),(154,'','',1,0,'2019-01-17 13:40:55','Producto E',19,5,7,'1970-01-01 04:00:00',101),(155,'','',1,0,'2019-01-17 13:44:07','Producto EFG',22,10,8,'1970-01-01 04:00:00',101),(651,'','',1,0,'2019-01-18 01:48:14','Producto F',18,10,0,'1970-01-01 04:00:00',101),(701,'','',1,0,'2019-01-18 02:02:29','Producto G',18,10.5,0,'1970-01-01 02:00:00',101),(1401,'','',1,0,'2019-01-18 23:07:17','Producto B de Vend2',19,4,5,'1970-01-01 01:30:00',952),(1451,'','',1,0,'2019-01-18 23:37:24','Producto C de Vend2',17,12,3,'1970-01-01 05:00:00',952),(1501,'','',1,0,'2019-01-19 01:51:36','Producto X',17,3,5,'1970-01-01 05:00:00',101),(1551,'','',1,0,'2019-01-19 01:55:50','Producto Y',14,15,4,'1970-01-01 10:00:00',101),(1552,'','',1,0,'2019-01-19 01:56:01','Producto Z',14,1,1,'1970-01-01 01:00:00',101),(1553,'','',1,0,'2019-01-19 01:56:40','Producto D de Vendedor 2',13,2,4,'1970-01-01 03:00:00',952),(1554,'','',1,0,'2019-01-19 01:56:52','Producto E de Vendedor2',13,45,6,'1970-01-01 05:00:00',952),(1557,'','',1,0,'2019-01-19 01:57:58','Producto A de Vendedor3',19,1,1,'1970-01-01 02:00:00',1556),(1558,'','',1,0,'2019-01-19 01:58:37','Producto B de Vendedor3',16,21,12,'1970-01-01 12:00:00',1556),(1559,'','',1,0,'2019-01-19 01:58:48','Producto C de Vendedor 3',13,1,1,'1970-01-01 01:00:00',1556),(2801,'tecnologia','Compila',1,0,'2019-01-23 22:01:55','SuperCompilador',3,34,89,'1970-01-03 08:00:00',1751);
/*!40000 ALTER TABLE `PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',3900);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `ID` bigint(20) NOT NULL,
  `DTYPE` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ACTIVO` tinyint(1) DEFAULT '0',
  `APELLIDO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CEDULA` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CONTRASENIA` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DIRECCION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `EMAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `MATRICULA` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `NOMBRE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `TELEFONO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `WHATSAPP` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES (1,'Administrador',1,'Uno','','1234','','admin@example.com','','Admin A.','',0),(51,'Comprador',1,'Uno','','1234','','comprador@example.com','','Comprador','099999',0),(101,'Vendedor',1,'Uno','','1234','','vendedor','','Vendedor','',0),(851,'Comprador',1,'Dos','','1234','','comp2','','Comprador','',0),(901,'Comprador',1,'Tres','','1234','','comp3','','Comprador','',0),(952,'Vendedor',1,'Dos','','1234','','vend2','','Vendedor','',0),(1556,'Vendedor',1,'Tres','','1234','','vend3','09999999','Vendedor','',0),(1751,'Vendedor',1,'Moreno','0951260728','123456','Sauces 3 Mz 181 Villa 29','fermor@example.com','2015309','Fernando','0951260728',0),(1801,'Comprador',1,'De La A','0987439814','12345','Guayaquil Sur','leonel_men_@hotmail.com','12345','Danny ','0967344509',1),(2501,'Comprador',1,'Godoy','0952027472','12345','El centenario','ajgodoy@example.com','2016056','Axel','0988286513',0),(3251,'Vendedor',1,'Guayas','0923719203','1234','Duran','guayas@example.com','9001','Usuario creado 28/01/2019','0978564217',0),(3301,'Comprador',1,'Gutierrez','0981948718','1234','Guayaquil|','juan@example.com','8801','Juan','0916498367',0),(3451,'Comprador',1,'Cuatro','','1234','','comp4','','Comprador','',0),(3501,'Administrador',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3551,'Administrador',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3601,'Administrador',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(3651,'Administrador',0,'Hazashi',NULL,NULL,NULL,NULL,NULL,'Hanzo',NULL,0),(3702,'Administrador',1,'Dos','','1234','','admin2','','Administrador','',0);
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VENDEDOR`
--

DROP TABLE IF EXISTS `VENDEDOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VENDEDOR` (
  `ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_VENDEDOR_ID` FOREIGN KEY (`ID`) REFERENCES `USUARIO` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VENDEDOR`
--

LOCK TABLES `VENDEDOR` WRITE;
/*!40000 ALTER TABLE `VENDEDOR` DISABLE KEYS */;
INSERT INTO `VENDEDOR` VALUES (101),(952),(1556),(1751),(3251);
/*!40000 ALTER TABLE `VENDEDOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `productosmasvendidos_ultimostresmeses`
--

DROP TABLE IF EXISTS `productosmasvendidos_ultimostresmeses`;
/*!50001 DROP VIEW IF EXISTS `productosmasvendidos_ultimostresmeses`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `productosmasvendidos_ultimostresmeses` AS SELECT 
 1 AS `NOMBRE_ARTICULO`,
 1 AS `NOMBRE_VENDEDOR`,
 1 AS `ITEMS_TOTALES_VENDIDOS`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `ventassemanales_anio_vendedor`
--

DROP TABLE IF EXISTS `ventassemanales_anio_vendedor`;
/*!50001 DROP VIEW IF EXISTS `ventassemanales_anio_vendedor`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ventassemanales_anio_vendedor` AS SELECT 
 1 AS `ANIO`,
 1 AS `SEMANA`,
 1 AS `VENDEDOR`,
 1 AS `TIPO_PAGO`,
 1 AS `NUM_VENTAS`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'proyds2_cp9tfg'
--

--
-- Final view structure for view `productosmasvendidos_ultimostresmeses`
--

/*!50001 DROP VIEW IF EXISTS `productosmasvendidos_ultimostresmeses`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`masteruser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `productosmasvendidos_ultimostresmeses` AS select `PRODUCTO`.`NOMBREARTICULO` AS `NOMBRE_ARTICULO`,concat(`USUARIO`.`NOMBRE`,' ',`USUARIO`.`APELLIDO`) AS `NOMBRE_VENDEDOR`,sum(`PEDIDO`.`NUMEROITEMS`) AS `ITEMS_TOTALES_VENDIDOS` from ((`USUARIO` join `PRODUCTO` on((`USUARIO`.`ID` = `PRODUCTO`.`VENDEDOR_ID`))) join `PEDIDO` on((`PEDIDO`.`PRODUCTO_ID` = `PRODUCTO`.`ID`))) where ((`PRODUCTO`.`ELIMINADO` = 0) and (`PEDIDO`.`ESTADO` < 2) and ((curdate() - interval 3 month) <= `PEDIDO`.`FECHADEPEDIDO`)) group by `PRODUCTO`.`ID` order by `ITEMS_TOTALES_VENDIDOS` desc limit 10 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ventassemanales_anio_vendedor`
--

/*!50001 DROP VIEW IF EXISTS `ventassemanales_anio_vendedor`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`masteruser`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `ventassemanales_anio_vendedor` AS select year(`PEDIDO`.`FECHADEPEDIDO`) AS `ANIO`,week(`PEDIDO`.`FECHADEPEDIDO`,0) AS `SEMANA`,concat(`USUARIO`.`NOMBRE`,' ',`USUARIO`.`APELLIDO`) AS `VENDEDOR`,`METODOPAGO`.`DTYPE` AS `TIPO_PAGO`,count(`PEDIDO`.`ID`) AS `NUM_VENTAS` from (((`USUARIO` join `PRODUCTO` on((`USUARIO`.`ID` = `PRODUCTO`.`VENDEDOR_ID`))) join `PEDIDO` on((`PRODUCTO`.`ID` = `PEDIDO`.`PRODUCTO_ID`))) join `METODOPAGO` on((`PEDIDO`.`METODOPAGO_ID` = `METODOPAGO`.`ID`))) where ((year(`PEDIDO`.`FECHADEPEDIDO`) = year(curdate())) and (`PEDIDO`.`ESTADO` < 2)) group by week(`PEDIDO`.`FECHADEPEDIDO`,0),`USUARIO`.`ID`,`METODOPAGO`.`DTYPE` order by week(`PEDIDO`.`FECHADEPEDIDO`,0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-02 12:29:56
