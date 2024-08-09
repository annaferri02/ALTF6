-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: test_schema
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `BIGLIETTO`
--

DROP TABLE IF EXISTS `BIGLIETTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BIGLIETTO` (
  `ID_biglietto` char(50) NOT NULL,
  `Prezzo` int DEFAULT NULL,
  `ID_utente` char(50) DEFAULT NULL,
  `ID_evento` char(50) DEFAULT NULL,
  `PDF_biglietto` blob,
  `QRcode` blob,
  `Posto` smallint DEFAULT NULL,
  `Tipologia` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_biglietto`),
  KEY `ID_utente` (`ID_utente`),
  KEY `ID_evento` (`ID_evento`),
  CONSTRAINT `biglietto_ibfk_1` FOREIGN KEY (`ID_utente`) REFERENCES `UTENTE` (`ID_utente`),
  CONSTRAINT `biglietto_ibfk_2` FOREIGN KEY (`ID_evento`) REFERENCES `EVENTO` (`ID_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BIGLIETTO`
--

LOCK TABLES `BIGLIETTO` WRITE;
/*!40000 ALTER TABLE `BIGLIETTO` DISABLE KEYS */;
INSERT INTO `BIGLIETTO` VALUES ('1XHVuZP7sX',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('3MBUa9N8EL',70,'1','E0001',NULL,NULL,229,'Tribuna Centrale'),('5XmrlarWBo',70,'1','E0001',NULL,NULL,257,'Tribuna Centrale'),('9QICKxqz1l',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('a1y2pgkpDN',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('B0001',21,'1','E0001',_binary 'pdf_content_1',_binary 'qrcode_content_1',1,'Tribuna Frontale'),('B0002',16,'2','E0002',_binary 'pdf_content_2',_binary 'qrcode_content_2',NULL,NULL),('B0003',30,'3','E0003',_binary 'pdf_content_3',_binary 'qrcode_content_3',NULL,NULL),('B0005',37,'001','E0001',_binary 'pdf_content_1',_binary 'qrcode_content_1',12,'Tribuna Frontale'),('B0006',45,'2','E0001',_binary 'pdf_content_1',_binary 'qrcode_content_1',14,'Tribuna Frontale'),('el3vJBQeKy',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('fDUX6CJ6yY',90,'1','E0001',NULL,NULL,144,'Tribuna Destra'),('GVPmXOD3Yr',50,'1','E0001',NULL,NULL,0,'Parterre'),('ismG3WoDgg',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('jZhDM8PnPe',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('KYckAbu8Xs',70,'1','E0001',NULL,NULL,297,'Tribuna Centrale'),('l24LjgfaRB',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('lDlQ6e9buh',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('ltsRurHVf9',90,'1','E0001',NULL,NULL,46,'Tribuna Sinistra'),('pW3TKnVmQa',70,'1','E0001',NULL,NULL,298,'Tribuna Centrale'),('qiH2AfPPm9',90,'1','E0001',NULL,NULL,42,'Tribuna Sinistra'),('RAh14s4ZJL',50,'1','E0001',NULL,NULL,0,'Parterre'),('RRCSDgAhkU',90,'1','E0001',NULL,NULL,34,'Tribuna Sinistra'),('rZmsU0mmRy',70,'1','E0001',NULL,NULL,251,'Tribuna Centrale'),('sz9YQTFP6y',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('T4dU5f17D6',90,'1','E0001',NULL,NULL,153,'Tribuna Destra'),('TxHScVViXe',70,'1','E0001',NULL,NULL,256,'Tribuna Centrale'),('uMlYbBjZes',90,'1','E0001',NULL,NULL,43,'Tribuna Sinistra'),('y5i3Eimx5F',90,'1','E0001',NULL,NULL,153,'Tribuna Destra'),('yqs8xy8929',100,'1','E0001',NULL,NULL,0,'Parterre VIP'),('yVNLmFpQ4d',90,'1','E0001',NULL,NULL,144,'Tribuna Destra');
/*!40000 ALTER TABLE `BIGLIETTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Evento`
--

DROP TABLE IF EXISTS `Evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Evento` (
  `ID_evento` char(50) NOT NULL,
  `Nome` char(255) DEFAULT NULL,
  `Data` date DEFAULT NULL,
  `Descrizione` char(255) DEFAULT NULL,
  `ID_organizzatore` char(50) DEFAULT NULL,
  `Flag_ticket` tinyint(1) DEFAULT NULL,
  `IdLuogo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_evento`),
  KEY `ID_organizzatore` (`ID_organizzatore`),
  KEY `FK_IdLuogo` (`IdLuogo`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`ID_organizzatore`) REFERENCES `ORGANIZZATORE` (`ID_organizzatore`),
  CONSTRAINT `FK_IdLuogo` FOREIGN KEY (`IdLuogo`) REFERENCES `Luogo` (`IdLuogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evento`
--

LOCK TABLES `Evento` WRITE;
/*!40000 ALTER TABLE `Evento` DISABLE KEYS */;
INSERT INTO `Evento` VALUES ('E0001','Concerto Live','2024-04-20','Concerto live di artisti famosi','O0001',1,'L00001'),('E0002','Mostra d\'Arte','2024-05-10','Mostra d\'arte contemporanea','O0002',1,'L00002'),('E0003','Seminario Tecnico','2024-06-15','Seminario tecnico su machine learning','O0003',0,'L00001'),('E0004','Sassssooo','2024-08-29','Sasssoooo','O0001',1,'L00001');
/*!40000 ALTER TABLE `Evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Luogo`
--

DROP TABLE IF EXISTS `Luogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Luogo` (
  `IdLuogo` varchar(10) NOT NULL,
  `Via` varchar(255) DEFAULT NULL,
  `NumCivico` smallint DEFAULT NULL,
  `CAP` varchar(50) DEFAULT NULL,
  `Provincia` varchar(2) DEFAULT NULL,
  `Stato` varchar(255) DEFAULT NULL,
  `Città` varchar(255) DEFAULT NULL,
  `Capienza` int NOT NULL,
  `Tipologia` varchar(255) DEFAULT NULL,
  `Nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdLuogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Luogo`
--

LOCK TABLES `Luogo` WRITE;
/*!40000 ALTER TABLE `Luogo` DISABLE KEYS */;
INSERT INTO `Luogo` VALUES ('L00001','Via Roma',10,'00100','RM','Italia','Roma',500,'Indoor','Teatro Nuovo'),('L00002','Corso Italia',25,'20100','MI','Italia','Milano',300,'Indoor','Stadio San Siro'),('L00003','Via Garibaldi',15,'50100','FI','Italia','Firenze',200,'Outdoor','Ippodromo'),('L00004','Piazza Duomo',1,'80100','NA','Italia','Napoli',150,'Outdoor','Piazza Ariostea'),('L00005','Via Dante',20,'40100','BO','Italia','Bologna',100,'Outdoor','Piazza Trento Trieste'),('L00006','Via Mazzini',30,'90100','PA','Italia','Palermo',450,'Indoor','Stadio Paolo Mazza'),('L00007','Viale della LibertÃ ',50,'70100','BA','Italia','Bari',400,'Outdoor','Ippodromo Ferrara'),('L00008','Via Verdi',5,'10100','TO','Italia','Torino',600,'Indoor','Discoteca 1'),('L00009','Piazza San Marco',10,'30100','VE','Italia','Venezia',250,'Indoor','Discoteca 2'),('L00010','Via Cavour',40,'16100','GE','Italia','Genova',350,'Outdoor','Anfiteatro 1');
/*!40000 ALTER TABLE `Luogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORGANIZZATORE`
--

DROP TABLE IF EXISTS `ORGANIZZATORE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ORGANIZZATORE` (
  `ID_organizzatore` char(50) NOT NULL,
  `Nome` char(255) DEFAULT NULL,
  `Cognome` char(255) DEFAULT NULL,
  `Luogo_nascita` char(255) DEFAULT NULL,
  `Via_residenza` char(255) DEFAULT NULL,
  `Civico_residenza` char(50) DEFAULT NULL,
  `Citta_residenza` char(255) DEFAULT NULL,
  `Cap_residenza` char(50) DEFAULT NULL,
  `Email` char(255) DEFAULT NULL,
  `Password` char(255) DEFAULT NULL,
  PRIMARY KEY (`ID_organizzatore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORGANIZZATORE`
--

LOCK TABLES `ORGANIZZATORE` WRITE;
/*!40000 ALTER TABLE `ORGANIZZATORE` DISABLE KEYS */;
INSERT INTO `ORGANIZZATORE` VALUES ('O0001','Giovanni','Ferrari','Roma','Via Roma','10','Roma','00100','giovanni@email.com','password123'),('O0002','Laura','Bianchi','Milano','Via Milano','20','Milano','20100','laura@email.com','password456'),('O0003','Luca','Verdi','Napoli','Via Napoli','30','Napoli','80100','luca@email.com','password789');
/*!40000 ALTER TABLE `ORGANIZZATORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPONSORIZZAZIONE`
--

DROP TABLE IF EXISTS `SPONSORIZZAZIONE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPONSORIZZAZIONE` (
  `IdSponsorizzazione` int NOT NULL AUTO_INCREMENT,
  `ID_evento` char(50) DEFAULT NULL,
  `Tipologia` varchar(10) DEFAULT NULL,
  `Costo` decimal(10,2) DEFAULT NULL,
  `Timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdSponsorizzazione`),
  KEY `ID_evento` (`ID_evento`),
  CONSTRAINT `sponsorizzazione_ibfk_1` FOREIGN KEY (`ID_evento`) REFERENCES `Evento` (`ID_evento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPONSORIZZAZIONE`
--

LOCK TABLES `SPONSORIZZAZIONE` WRITE;
/*!40000 ALTER TABLE `SPONSORIZZAZIONE` DISABLE KEYS */;
INSERT INTO `SPONSORIZZAZIONE` VALUES (6,'E0004','1',17.00,'2024-08-07 13:55:08');
/*!40000 ALTER TABLE `SPONSORIZZAZIONE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transazioni`
--

DROP TABLE IF EXISTS `Transazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Transazioni` (
  `idutente` varchar(50) NOT NULL,
  `costo` decimal(10,2) NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transazioni`
--

LOCK TABLES `Transazioni` WRITE;
/*!40000 ALTER TABLE `Transazioni` DISABLE KEYS */;
INSERT INTO `Transazioni` VALUES ('2',80.00,'2024-06-22 06:35:22'),('2',50.00,'2024-06-22 06:35:22'),('1',90.00,'2024-08-07 13:53:24');
/*!40000 ALTER TABLE `Transazioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UTENTE`
--

DROP TABLE IF EXISTS `UTENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UTENTE` (
  `ID_utente` char(50) NOT NULL,
  `Nome` char(255) DEFAULT NULL,
  `Cognome` char(255) DEFAULT NULL,
  `Luogo_nascita` char(255) DEFAULT NULL,
  `Via_residenza` char(255) DEFAULT NULL,
  `Civico_residenza` char(50) DEFAULT NULL,
  `Citta_residenza` char(255) DEFAULT NULL,
  `Cap_residenza` char(50) DEFAULT NULL,
  `Email` char(255) DEFAULT NULL,
  `Password` char(255) DEFAULT NULL,
  PRIMARY KEY (`ID_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UTENTE`
--

LOCK TABLES `UTENTE` WRITE;
/*!40000 ALTER TABLE `UTENTE` DISABLE KEYS */;
INSERT INTO `UTENTE` VALUES ('001','Mario','Rossi','Milano','Via Roma','10','Roma','00100','mario.rossi@example.com','password123'),('1','Mario','Rossi','Milano','Via Roma','12','Milano','20100','mario.rossi@example.com','password123'),('2','Luca','Bianchi','Roma','Via Garibaldi','25','Roma','00100','luca.bianchi@example.com','securepassword'),('3','Anna','Verdi','Napoli','Via Napoli','8','Napoli','80100','anna.verdi@example.com','topsecret123');
/*!40000 ALTER TABLE `UTENTE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-07 17:04:41
