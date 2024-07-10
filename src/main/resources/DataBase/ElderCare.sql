-- MySQL dump 10.13  Distrib 8.0.37, for Linux (x86_64)
--
-- Host: localhost    Database: ElderCare
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.22.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Bill`
--

DROP TABLE IF EXISTS `Bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Bill` (
  `billId` varchar(10) NOT NULL,
  `date` date DEFAULT NULL,
  `elderId` varchar(10) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`billId`),
  KEY `elderId` (`elderId`),
  CONSTRAINT `Bill_ibfk_1` FOREIGN KEY (`elderId`) REFERENCES `Elder` (`elderId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bill`
--

LOCK TABLES `Bill` WRITE;
/*!40000 ALTER TABLE `Bill` DISABLE KEYS */;
INSERT INTO `Bill` VALUES ('B001','2024-04-10',NULL,5455.00,'gfggf','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713248970263.png'),('B002','2024-04-10',NULL,45354.00,'gfgfg','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713250368789.png'),('B003','2024-04-10','E001',45354.00,'medicine','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713251455631.png'),('B004','2024-04-10','E005',45354.00,'gfgfg','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E005/Bills/saved_image_1713250443119.png'),('B005','2024-04-10','E005',45354.00,'gfgfg','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E005/Bills/saved_image_1713250505889.png'),('B006','2024-04-12','E002',5600.00,'medicine','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1713250904491.png'),('B007','2024-04-12',NULL,5600.00,'medicine','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713250943980.png'),('B008','2024-04-04','E002',43535.00,'for need','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713251455631.png'),('B009','2024-04-10','E002',3453.00,'for need','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713251455631.png'),('B010','2024-04-03',NULL,54.00,'tsfd','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713251455631.png'),('B011','2024-04-01',NULL,1200.00,'phone charges','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713258806143.png'),('B012','2024-04-16','E002',4500.00,'blood report','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1713276545232.png'),('B013','2024-04-18','E002',1000.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1713624852051.png'),('B014','2024-04-18','E002',1000.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1713625313918.png'),('B015','2024-05-01','E001',250.00,'Cake','Elder','/home/nayomal/Pictures/Screenshot from 2024-05-11 12-53-45.png'),('B016','2024-05-01','E001',250.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713250368789.png'),('B017','2024-05-01','E001',251.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713250368789.png'),('B018','2024-05-01','E001',251.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B019','2024-05-01','E001',251.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B020','2024-05-01','E001',251.00,'Cake','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B021','2024-04-10','E001',123.90,'Suger','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B022','2024-04-10','E001',123.90,'Tea','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B023','2024-04-10','E001',1000.00,'for need','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B024','2024-04-10','E001',1000.00,'repair phone','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B025','2024-04-02','E002',230.00,'dompedine ','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1714192347508.png'),('B026','2024-04-02','E002',230.00,'dompedine ','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1714192466055.png'),('B027','2024-04-10','E007',230.00,'dompedine ','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1714192466055.png'),('B028','2024-04-20','E007',230.00,'dompedine ','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713258806143.png'),('B029','2024-05-14','E002',4500.00,'Nessary grosseries ','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715673929302.png'),('B030','2024-05-14','E002',345.00,'Penadol','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715673961859.png'),('B031','2024-05-14','E002',181.00,'Bread\n','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715674027111.png'),('B032','2024-05-14',NULL,4500.00,'Cargills','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1715674371944.png'),('B033','2024-05-13','E002',600.00,'Phone repair','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715674471460.png'),('B034','2024-05-07',NULL,450.00,'item','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1715674935062.png'),('B035','2024-05-09',NULL,344.00,'dfg','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1715675131619.png'),('B036','2024-05-09','E001',344.00,'for need','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/saved_image_1713251455631.png'),('B037','2024-05-09','E001',3244.00,'for need','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B038','2024-05-13','E001',4500.00,'dompedine','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/saved_image_1715675151654.png'),('B039','2024-05-07','E002',1000.00,'bottle','Elder','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Elder/E002/Bills/B039.png'),('B040','2024-05-08',NULL,2000.00,'Mayadunna Stores','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/B040.png'),('B041','2024-05-18',NULL,5000.00,'carg','Item','/home/nayomal/Desktop/projects/Eldercare/src/main/resources/Bill/B041.png'),('B042','2024-06-03','E002',32.00,'svdss','Elder','nullvalue'),('B043','2024-06-03','E002',32.00,'svdss','Elder','nullvalue'),('B044','2024-06-03','E002',32.00,'svdss','Elder','nullvalue'),('B045','2024-06-03','E002',32.00,'svdss','Elder','nullvalue'),('B046','2024-06-03','E002',32.00,'svdss','Elder','nullvalue'),('B047','2024-06-10','E002',5.00,'vhhgg','Elder','nullvalue'),('B048','2024-06-12','E002',23.00,'dfsdfsfsfsfssfd','Elder','nullvalue'),('B049','2024-06-18','E002',34.00,'sfffsdsf','Elder','nullvalue'),('B050','2024-06-11','E002',23.00,'vsvvkv','Elder','nullvalue'),('B051','2024-06-18','E002',12.00,'sfsfdfss','Elder','nullvalue'),('B052','2024-06-18','E002',12.00,'sfsfdfss','Elder','nullvalue'),('B053','2024-06-18','E002',12.00,'sfsfdfss','Elder','nullvalue'),('B054','2024-06-18','E002',12.00,'sfsfdfss','Elder','nullvalue'),('B055','2024-06-12','E002',23.00,'jjhkhjhkh','Elder','/home/nayomal/Documents/Ijse/Layered architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B055.png'),('B056','2024-06-25','E002',32.00,'gyggggggggg','Elder','nullvalue'),('B057','2024-06-11','E002',55.00,'gghjjjjjjj','Elder','/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B057.png'),('B058','2024-06-30','E002',450.00,'help','Elder','/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B058.png'),('B059','2024-06-30','E002',450.00,'help','Elder','/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B059.png'),('B060','2024-07-01','E002',230.00,'helpme','Elder','/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B060.png'),('B061','2024-07-01','E002',230.00,'helpme','Elder','/home/nayomal/Documents/Ijse/Layered_architecture/Eldercare_Project/src/main/resources/Elder/E002/Bills/B061.png');
/*!40000 ALTER TABLE `Bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Elder`
--

DROP TABLE IF EXISTS `Elder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Elder` (
  `elderId` varchar(10) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `nic` varchar(12) NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `admitDate` date DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `advancefee` int DEFAULT NULL,
  `monthlyFee` int DEFAULT NULL,
  `medicalHistory` varchar(1000) DEFAULT NULL,
  `emailAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`elderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Elder`
--

LOCK TABLES `Elder` WRITE;
/*!40000 ALTER TABLE `Elder` DISABLE KEYS */;
INSERT INTO `Elder` VALUES ('E001','Anula','Wicrmasinha','jayawardana','411850960V',NULL,NULL,'1941-10-02','2010-02-10','2024-01-17',NULL,25000,NULL,'john20decker@gmail.com','active'),('E002','Don','Leon','Abeywardana','472240595V','Colombo','0765531696','1947-11-08','2015-10-18','2024-04-24',560000,35000,'pain','john20decker@gmail.com','active'),('E003','Singakkarage','nandavathi','gallage','535560994V',NULL,NULL,'1956-03-04','2024-04-25','2024-04-30',NULL,45000,NULL,'john20decker@gmail.com','active'),('E004','Amal','Samaarasinha','jayawicrama','1978654798','Panadura','0786534465','1978-04-10','2024-04-25','2024-03-24',45000,35000,'none','john20decker@gmail.com','active'),('E005','Kamal','Siripala','arachchige','1978654798','','','1974-04-24','2024-03-05','2024-04-22',55,60000,'','john20decker@gmail.com','active'),('E007','gaithma','nirmani','disanayake','1978654798','66/2 wata mavatha, wijerama','077569802','2014-02-26','2022-04-17','2022-05-02',45000,30000,'none','john20decker@gmail.com','active'),('E008','kamal','Sirisena','rathnayake','200305677789','','0765531696','1979-06-26','2024-05-16','2024-05-16',5465,45000,'','harshika@gamil.com','active'),('E009','henry','patrick','jayasuriya','491782790V','85/21,Plohengoda,colombo 05','0777251126','1949-06-26','2024-05-10','2024-05-10',30000,30000,'leg pain','john20decker@gmail.com','active'),('E010','Nihal ','mendis','muthugala','491782790V','85/21,Plohengoda,colombo 05','0777251126','1979-06-26','2024-05-10','2024-05-10',30000,30000,'leg pain','john20decker@gmail.com','active'),('E011','Chandana','Sisirakuamara','perera','491782790V','85/21,Plohengoda,colombo 05','0777251126','1939-06-26','2024-05-10','2024-05-10',30000,30000,'leg pain','john20decker@gmail.com','active'),('E012','nimal','',' +\n                ','','',' +\n       ','2024-07-09','2024-07-17','2024-07-17',43,4,'','','active'),('E013','dfgdgf','d','','','','','2024-07-10','2024-07-24','2024-07-02',5,35,'','','deactive');
/*!40000 ALTER TABLE `Elder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `empId` varchar(10) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `nic` varchar(12) NOT NULL,
  `pastWorkingdetails` varchar(2500) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `admitDate` date DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('Emp001','Kamal','Abesinha','Gallage','200305700645','none','1978-02-10','Jaffna','0765432871','2022-10-21',30000.00,'Cook','active'),('Emp002','Janith','','','','','2024-04-08','','',NULL,43.00,'','deactive'),('Emp003','Susan','Leposa','Lawrence','19887543568','Quatar agent company','1998-08-08','Canada ','0765436789','2024-04-22',53000.00,'Clean rooms','active'),('Emp004','Mahinda','rajapaksha','','197856789875','none','1988-06-09','Araliaya Mandiraya','0765531696','2024-05-15',34000.00,'cook','active'),('Emp005','wewewe','Namal','GIhan','dgfdg','dgfdg','2024-07-09','ggd','dgd','2024-07-17',45.00,'gdgd','deactive');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fee`
--

DROP TABLE IF EXISTS `Fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Fee` (
  `feeId` int NOT NULL AUTO_INCREMENT,
  `yearMonth` date NOT NULL,
  `elderId` varchar(10) DEFAULT NULL,
  `medicalFees` decimal(10,2) DEFAULT '0.00',
  `empCharges` decimal(10,2) DEFAULT '0.00',
  `chargesForGoods` decimal(10,2) DEFAULT '0.00',
  `totalAmount` decimal(10,2) DEFAULT '0.00',
  `emailStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`feeId`),
  KEY `elderId` (`elderId`),
  CONSTRAINT `Fee_ibfk_1` FOREIGN KEY (`elderId`) REFERENCES `Elder` (`elderId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fee`
--

LOCK TABLES `Fee` WRITE;
/*!40000 ALTER TABLE `Fee` DISABLE KEYS */;
INSERT INTO `Fee` VALUES (1,'2024-01-15','E002',0.00,0.00,0.00,35000.00,'notSent'),(2,'2024-02-15','E002',5.00,0.00,0.00,35005.00,'notSent'),(3,'2024-04-18','E002',3188.00,0.00,7537.00,45725.00,'sent'),(4,'2024-02-21','E004',0.01,0.00,0.00,35000.01,'sent'),(5,'2024-04-02','E007',1000.00,0.00,1247.80,32247.80,'sent'),(6,'2024-04-01','E003',140.00,0.00,84040.00,129180.00,'notSent'),(7,'2023-04-23','E003',10.00,500.00,550.00,46060.00,'sent'),(10,'2024-04-18','E001',4500.00,1000.00,0.00,30500.00,'sent'),(11,'2024-05-22','E005',0.00,0.00,0.00,60000.00,'sent'),(12,'2024-05-24','E002',755.00,0.00,0.00,35755.00,'notSent'),(13,'2024-05-24','E004',0.00,0.00,0.00,35000.00,'sent'),(14,'2024-05-30','E003',0.00,0.00,0.00,45000.00,'notSent'),(15,'2024-06-02','E007',0.00,0.00,0.00,30000.00,'notSent'),(16,'2024-07-12','E009',0.00,0.00,0.00,30000.00,'notSent'),(17,'2024-06-10','E010',0.00,0.00,0.00,30000.00,'notSent'),(18,'2024-06-10','E011',0.00,0.00,0.00,30000.00,'notSent'),(19,'2024-06-16','E008',0.00,0.00,0.00,45000.00,'sent'),(20,'2024-06-16','E008',0.00,0.00,0.00,45000.00,'sent'),(21,'2024-06-16','E008',0.00,0.00,0.00,45000.00,'sent'),(22,'2024-06-16','E008',0.00,0.00,0.00,45000.00,'notSent'),(23,'2024-06-17','E001',0.00,0.00,0.00,25000.00,'notSent'),(24,'2024-06-22','E005',0.00,0.00,0.00,60000.00,'notSent'),(25,'2024-06-24','E002',1324.00,0.00,680.00,37004.00,'notSent'),(26,'2024-06-24','E004',0.00,0.00,0.00,35000.00,'notSent'),(27,'2024-07-17','E012',0.00,0.00,0.00,4.00,'notSent'),(28,'2024-07-02','E013',0.00,0.00,0.00,35.00,'notSent');
/*!40000 ALTER TABLE `Fee` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `insert_totalAmount_Monthly_Fee` BEFORE INSERT ON `Fee` FOR EACH ROW BEGIN
    SET NEW.totalAmount = (SELECT e.monthlyFee FROM Elder e WHERE e.elderId = NEW.elderId);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `set_total_amount` BEFORE UPDATE ON `Fee` FOR EACH ROW BEGIN
       
        SET New.totalAmount = (SELECT e.monthlyFee FROM Elder e WHERE e.elderId = NEW.elderId)+NEW
.medicalFees  + New.empCharges + NEW.chargesForGoods ;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `FeeCreationTable`
--

DROP TABLE IF EXISTS `FeeCreationTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FeeCreationTable` (
  `lastVisitDate` date NOT NULL,
  PRIMARY KEY (`lastVisitDate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FeeCreationTable`
--

LOCK TABLES `FeeCreationTable` WRITE;
/*!40000 ALTER TABLE `FeeCreationTable` DISABLE KEYS */;
INSERT INTO `FeeCreationTable` VALUES ('2024-04-25'),('2024-05-12'),('2024-05-13'),('2024-05-14'),('2024-05-15'),('2024-05-16'),('2024-05-17'),('2024-05-18'),('2024-05-19'),('2024-05-24'),('2024-05-25'),('2024-06-12'),('2024-06-22'),('2024-06-25');
/*!40000 ALTER TABLE `FeeCreationTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guardian`
--

DROP TABLE IF EXISTS `Guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Guardian` (
  `guardianId` varchar(10) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `relation` varchar(100) DEFAULT NULL,
  `elderId` varchar(10) DEFAULT NULL,
  `empId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nic` varchar(12) NOT NULL,
  `address` varchar(500) DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`guardianId`),
  KEY `elderId` (`elderId`),
  KEY `empId` (`empId`),
  CONSTRAINT `Guardian_ibfk_1` FOREIGN KEY (`elderId`) REFERENCES `Elder` (`elderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Guardian_ibfk_2` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guardian`
--

LOCK TABLES `Guardian` WRITE;
/*!40000 ALTER TABLE `Guardian` DISABLE KEYS */;
INSERT INTO `Guardian` VALUES ('G001',NULL,NULL,NULL,NULL,'E004',NULL,'20037005456',NULL,NULL),('G002','Namal','delon','Silva +                 +                 +\n                ','son','E002',NULL,'200245678972','Panadura','0764568723'),('G003','sdfsf','','','','E005',NULL,'','',''),('G004','Sunil','Lakshan','Gallage','Cousin',NULL,'Emp001','200305700645','Ampara','0769543455'),('G005','sfsf','','','',NULL,'Emp002','','',''),('G006','Jagath','Somapala','Gurusinha','Husband','E007','Emp003','19765753432','Colombo','0786453242'),('G007','nimal','','','','E008',NULL,'200305677789','panadura','0765531696'),('G008','roshimi','jayasuriya','','Daughter','E009',NULL,'715112027V','colmbo','0777251126'),('G009','Mahinda','rajapaksha','','son',NULL,'Emp004','197856789875','Colombo','0765531696'),('G010','kamal','',' +\n                ','','E012',NULL,'','',''),('G011','fdgfd','','','','E013',NULL,'','',''),('G012','nimal','dfgd','wrwe          ','gfdg',NULL,'Emp005','fd','gf','gdg');
/*!40000 ALTER TABLE `Guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `itemId` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `qtyOnHand` int unsigned DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `unitPrice` decimal(10,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES ('I001','red rice',50,'kg',250.00,'active'),('I002','white rice',47,'kg',178.00,'active'),('I003','noodles',26,'kg',300.00,'active'),('I004','chicken',4,'kg',1100.00,'active'),('I005','coconut oil',10,'l',420.00,'deactive'),('I006','pototoes',10,'kg',285.00,'active'),('I007','Eggs',37,'',56.00,'active'),('I008','Carrot',9,'Kg',1200.00,'active'),('I009','Bread',2,'Packet',54.00,'active'),('I010','ChiliPowder',5,'Kg',560.00,'active'),('I011',' Source',2,'Packet',340.00,'active'),('I012','df',5,'l',546.00,'deactive'),('I013','fgv',5,'l',4.00,'deactive'),('I014','Astra',2,'Kg',800.00,'active'),('I015','Union',20,'Kg',360.00,'active'),('I016','chickpeas',40,'Kg',340.00,'active'),('I017','dfsd',4,'l',543.00,'deactive');
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Meal`
--

DROP TABLE IF EXISTS `Meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Meal` (
  `Date` date NOT NULL,
  `BreakFast` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Lunch` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Dinner` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meal`
--

LOCK TABLES `Meal` WRITE;
/*!40000 ALTER TABLE `Meal` DISABLE KEYS */;
INSERT INTO `Meal` VALUES ('2024-05-02','er','gv','jj'),('2024-05-03','er','gv','jj'),('2024-05-04','er','gv','jj'),('2024-05-05','er','gv','jj'),('2024-05-06','er','gv','jj'),('2024-05-07','er','gv','jj'),('2024-05-17','baeken','pizza','rice'),('2024-05-18','hfh','grhhghh','rh'),('2024-05-19','beaken and eggs','pizza','mixed rice'),('2024-05-24','milk rice ,Lunu miris\nred rice\nChickpeas','red rice and white rice \nchicken\npotatoes\ncarrot\n','noodles\neggs\ndhal'),('2024-07-01','mmmmmmmmmmmmmmm','dgfdg','fdgdg'),('2024-07-02','eeeeeeeeeef','wwwwwwww','eeee');
/*!40000 ALTER TABLE `Meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Payment` (
  `payId` varchar(10) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `empId` varchar(10) DEFAULT NULL,
  `feeId` int DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `responsiblePerson` varchar(30) DEFAULT NULL,
  `month` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`payId`),
  KEY `empId` (`empId`),
  KEY `feeId` (`feeId`),
  CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `Employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Payment_ibfk_2` FOREIGN KEY (`feeId`) REFERENCES `Fee` (`feeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES ('P001','2024-01-15',NULL,NULL,1,35000.00,NULL,'January'),('P002','2024-02-16',NULL,NULL,5,34000.00,NULL,NULL),('P003','2024-03-17',NULL,NULL,2,36010.00,NULL,NULL),('P004','2024-02-23',NULL,NULL,4,24000.00,NULL,NULL),('P005','2023-01-10',NULL,'Emp001',NULL,45000.00,NULL,'January'),('P006','2024-04-17','rest',NULL,1,20000.00,NULL,NULL),('P007','2024-02-29','Salary for february','Emp001',NULL,51000.00,NULL,'February'),('P008','2024-04-03','for March','Emp001',NULL,30000.00,NULL,'March'),('P009','2024-05-03','asok','Emp001',NULL,31000.00,NULL,'April'),('P010','2024-05-01','as','Emp001',NULL,18000.00,NULL,'May'),('P011','2024-04-04','','Emp001',NULL,32423.00,NULL,'June'),('P012','2024-07-23','pay\n','Emp001',NULL,42000.00,NULL,'July'),('P013','2024-04-29','foods pay',NULL,2,36000.00,NULL,NULL),('P014','2024-04-19','',NULL,2,25000.00,NULL,NULL),('P015','2024-04-21','',NULL,2,60000.00,NULL,NULL),('P016','2024-05-11','',NULL,2,35000.00,NULL,NULL),('P017','2024-05-14','',NULL,3,40000.00,NULL,NULL),('P018','2024-08-31','','Emp001',NULL,34000.00,NULL,'August'),('P019','2024-04-02','',NULL,10,26000.00,NULL,NULL),('P020','2024-04-24','',NULL,3,4683.00,NULL,NULL),('P021','2024-05-18','pay',NULL,3,1500.00,'admin',NULL),('P022','2024-07-21','payments',NULL,12,30000.00,NULL,NULL),('P023','2024-07-29','pay',NULL,12,5890.00,NULL,NULL),('P024','2024-07-16','','Emp001',NULL,3422.00,NULL,'September');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `userName` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `userLevel` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('admin',NULL,'1234','Manager'),('amal123','amal','1234','Clerk'),('janak123','janak','1234','Owner'),('lal','lal','1234','Servant');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `date_meal_item_details`
--

DROP TABLE IF EXISTS `date_meal_item_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `date_meal_item_details` (
  `Date` date DEFAULT NULL,
  `itemId` varchar(10) DEFAULT NULL,
  `Qty` double DEFAULT NULL,
  KEY `Date` (`Date`),
  KEY `itemId` (`itemId`),
  CONSTRAINT `date_meal_item_details_ibfk_1` FOREIGN KEY (`Date`) REFERENCES `Meal` (`Date`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `date_meal_item_details_ibfk_2` FOREIGN KEY (`itemId`) REFERENCES `Item` (`itemId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date_meal_item_details`
--

LOCK TABLES `date_meal_item_details` WRITE;
/*!40000 ALTER TABLE `date_meal_item_details` DISABLE KEYS */;
INSERT INTO `date_meal_item_details` VALUES ('2024-05-02','I001',10),('2024-05-02','I010',1),('2024-05-17','I009',1),('2024-05-18','I001',10),('2024-05-17','I001',10),('2024-05-17','I007',3),('2024-05-19',NULL,3),('2024-05-24','I001',4),('2024-05-24','I002',6),('2024-05-24','I016',5),('2024-05-24','I010',1),('2024-05-24','I004',3),('2024-05-24','I003',4),('2024-05-24','I008',1),('2024-05-24','I007',20),('2024-07-01','I001',2),('2024-07-02','I011',9);
/*!40000 ALTER TABLE `date_meal_item_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ElderCare'
--
/*!50003 DROP FUNCTION IF EXISTS `generate_next_fee_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `generate_next_fee_id`(current_id VARCHAR(5)) RETURNS varchar(5) CHARSET utf8mb4
    DETERMINISTIC
BEGIN
  DECLARE next_id VARCHAR(5);
  IF current_id IS NOT NULL THEN
    SET next_id = CONCAT('F', CAST(CAST(SUBSTRING_INDEX(current_id, 'F', -1) + 1 AS UNSIGNED) AS CHAR));
  ELSE
    SET next_id = 'F1';
  END IF;
  RETURN next_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10 19:48:38
