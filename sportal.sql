-- MySQL dump 10.13  Distrib 5.7.15, for Win64 (x86_64)
--
-- Host: localhost    Database: sportaldb
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `category_of_news`
--

DROP TABLE IF EXISTS `category_of_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_of_news` (
  `idCategory_of_news` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategory_of_news`),
  KEY `category` (`category`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_of_news`
--

LOCK TABLES `category_of_news` WRITE;
/*!40000 ALTER TABLE `category_of_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_of_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `idComments` int(11) NOT NULL AUTO_INCREMENT,
  `text` mediumtext NOT NULL,
  `date_and_time` datetime DEFAULT NULL,
  `Users_idUsers` int(11) NOT NULL,
  `News_idNews` int(11) NOT NULL,
  `number_of_likes` int(11) DEFAULT NULL,
  `number_of_dislikes` int(11) DEFAULT NULL,
  `title_of_comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idComments`,`Users_idUsers`,`News_idNews`),
  KEY `Users_idUsers` (`Users_idUsers`),
  KEY `News_idNews` (`News_idNews`),
  CONSTRAINT `fk_Comments_News1` FOREIGN KEY (`News_idNews`) REFERENCES `news` (`idNews`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments_Users` FOREIGN KEY (`Users_idUsers`) REFERENCES `users` (`idUsers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dislike_comments_by_user`
--

DROP TABLE IF EXISTS `dislike_comments_by_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dislike_comments_by_user` (
  `Comments_idComments` int(11) NOT NULL,
  `Comments_Users_idUsers` int(11) NOT NULL,
  `Comments_News_idNews` int(11) NOT NULL,
  `users_that_dislike_comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Comments_idComments`,`Comments_Users_idUsers`,`Comments_News_idNews`),
  CONSTRAINT `fk_Dislike_comments_by_user_Comments1` FOREIGN KEY (`Comments_idComments`, `Comments_Users_idUsers`, `Comments_News_idNews`) REFERENCES `comments` (`idComments`, `Users_idUsers`, `News_idNews`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dislike_comments_by_user`
--

LOCK TABLES `dislike_comments_by_user` WRITE;
/*!40000 ALTER TABLE `dislike_comments_by_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `dislike_comments_by_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_comments_by_user`
--

DROP TABLE IF EXISTS `like_comments_by_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_comments_by_user` (
  `Comments_idComments` int(11) NOT NULL,
  `Comments_Users_idUsers` int(11) NOT NULL,
  `Comments_News_idNews` int(11) NOT NULL,
  `users_that_like_comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Comments_idComments`,`Comments_Users_idUsers`,`Comments_News_idNews`),
  KEY `Comments_idComments` (`Comments_idComments`,`Comments_Users_idUsers`,`Comments_News_idNews`),
  CONSTRAINT `fk_Like_or_dislike_comments_by_users_Comments1` FOREIGN KEY (`Comments_idComments`, `Comments_Users_idUsers`, `Comments_News_idNews`) REFERENCES `comments` (`idComments`, `Users_idUsers`, `News_idNews`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_comments_by_user`
--

LOCK TABLES `like_comments_by_user` WRITE;
/*!40000 ALTER TABLE `like_comments_by_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_comments_by_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `idNews` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `number_of_reads` int(11) NOT NULL,
  `picture_address` varchar(150) NOT NULL,
  `video_address` varchar(150) DEFAULT NULL,
  `Category_of_news_idCategory_of_news` int(11) NOT NULL,
  `text` longtext NOT NULL,
  PRIMARY KEY (`idNews`,`Category_of_news_idCategory_of_news`),
  KEY `title` (`Category_of_news_idCategory_of_news`),
  CONSTRAINT `fk_News_Category_of_news1` FOREIGN KEY (`Category_of_news_idCategory_of_news`) REFERENCES `category_of_news` (`idCategory_of_news`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `profile_pic` blob,
  PRIMARY KEY (`idUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-09 20:52:45
