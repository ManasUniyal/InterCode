-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: intercode
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserName` varchar(255) DEFAULT NULL,
  `UserID` varchar(100) DEFAULT NULL,
  `PhoneNo` varchar(255) DEFAULT NULL,
  `EmailAddress` varchar(255) DEFAULT NULL,
  `ImageExtension` varchar(100) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Mode` int(10) DEFAULT NULL,
  `ResumeExtension` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('Manas Uniyal','manas_uni','9149379200','manasuniyal100@gmail.com','.png','MANAS$uni1998',NULL,NULL),('asdsad','asdasdasdadsa','asdasd','asd','png','asdasd',2,NULL),('','sakdmalsd','asdsa','asd','xyz','asdasd',2,'pdf'),('edseddsa','rgrtht','asddas','asdasd','png','asd',2,NULL),('kmflm','plm','asd','asd','png','asd',2,NULL),('asdkmasldm','ikljhl,','sdfksdkf','sdfskdfn','PNG','dkfsdmf',2,NULL),('fdfgfg','dfgdfgdfg','sdfdfsdfdds','dfgfdggb','png','fdgdfgdfg',2,NULL),('wqjeqkwjej`','ehekjfhsk','jkfhjkshfkjsdf','ejkhkwerh','png','wekjhfjkwfh',2,NULL),('asdklaskdlj','asjsadkjsadhk','sduhksf','sdjkhaskdjhkjadhk','png','sdalskjlkasjdl',2,NULL),('sadhkdk','djdskhsjkdfhjskdfh','sdjkhfsjdkfhjksdhfk','kjdhskjdfhkjh','png','jsdkhjkhdfkjh',2,NULL),('sdjkshdfjkdhsf','sddf','sdsdsddsf','sddsf','PNG','sdfsdffds',2,NULL),('askjdksh','sjdkhkdsjfhkj','sdfsdffsd','jksdfhnjksdf','PNG','dskjhfkjsdfh',2,NULL),('dsfjksdhfksd','sdufhusdifhi','sdduhsdiuhsd','sudihsdifuh','png','sdfhufsd',2,NULL),('askdmsalkn','klsjdlkdsjfklfsd','sddsfsdf','sdsfd','png','sdsdf',2,NULL),('sabsdbsam','asjkdhsdk','sdjkhksdjfhds','sdjkfhsdkjfh','png','sdjkfhksdjfh',1,NULL),('sdfjhfk','sdfjksdfhkdf','sdfsdfds','sdfsdf','png','sddfs',2,NULL),('Hello','kdjsflsdfj','sdjkfhjsdkfdsf','lksdjflsdf','png','lskdfjlsdfj',2,'pdf'),('Hello','sdjkfhjsfdkh','sdfsdsdf','sdfsdfds','png','sdfsdf',1,'pdf'),('Hello','skdjhkjsdhfk','dsfjkhsdfkdassadfs','ljl@gmail.com','png','sdkjhkjdsfhk',2,'pdf'),('Hello','ajskdhsadk','dskjfhksdhfdsf','ljda@gmail.com','png','sjkdhfdskj',2,'pdf'),('sdjksdjksfdh','kjhfkjshdf','akjhskdfhhksdf','dskjfhskdjfh','png','kjsdhfkjhfk',2,NULL),('assabdmnasdb','samnbamsdb','ndmbmsnf','dsmnbsmdnfb','png','nmsbdmdsnmfb',1,NULL),('sadmbsda','jsadkfnk','sjkdfhsdkjf','sdkjfhsjf','png','skfjdhsdkj',2,NULL),('sajkasdjk','jskdhjsdkfhfsdk','sdsjdfhjksdf','asdjkfhsfkjhfsd','png','sdfjksdfkdhfs',2,'pdf'),('sdfsdfbj','jkhfdskjhjksdfh','asdkjhsdkjfksdf','ajskdhsdkjfhsdkf','png','sadjkkhsdkh',2,NULL),('sdhjdshg','jhgdejsgjfg','dsjgjsdgjfsdf','jhgdhsjgshg','png','jhgsdjsgfhjsfdg',2,NULL),('xcjkzxkch','dsjkhkjdsfh','kdjsfhksjdf','kjshsdfkjh','png','kjdfshkjdfh',1,NULL),('Shreyas','shreyasdob','1234567890','shreyasdobhal@gmail.com','png','12',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-07 17:08:16
