-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema datadb
--

CREATE DATABASE IF NOT EXISTS datadb;
USE datadb;

--
-- Definition of table `agentrequest`
--

DROP TABLE IF EXISTS `agentrequest`;
CREATE TABLE `agentrequest` (
  `idAgentRequest` int(10) unsigned NOT NULL auto_increment,
  `description` varchar(45) NOT NULL,
  `ditributorid` int(10) unsigned NOT NULL,
  `udate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `sentFromIp` varchar(45) NOT NULL,
  `agentId` int(10) unsigned NOT NULL,
  `status` int(11) NOT NULL default '-1',
  PRIMARY KEY  (`idAgentRequest`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agentrequest`
--

/*!40000 ALTER TABLE `agentrequest` DISABLE KEYS */;
INSERT INTO `agentrequest` (`idAgentRequest`,`description`,`ditributorid`,`udate`,`sentFromIp`,`agentId`,`status`) VALUES 
 (1,'Need Data for Osmanpura region',144,'2012-03-03 23:47:10','192.168.0.108',143,-1),
 (2,'need pune data',143,'2012-03-29 14:10:57','192.168.0.108',143,1),
 (3,'',145,'2012-04-13 23:29:13','90-E6-BA-BA-38-9B',143,-1),
 (4,'Need Data',145,'2012-04-14 00:55:00','90-E6-BA-BA-38-9B',146,-1);
/*!40000 ALTER TABLE `agentrequest` ENABLE KEYS */;


--
-- Definition of table `backupinfo`
--

DROP TABLE IF EXISTS `backupinfo`;
CREATE TABLE `backupinfo` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Sender` int(10) unsigned NOT NULL,
  `FolderPath` varchar(255) default NULL,
  `TotalSize` varchar(10) default NULL,
  `Receiver` int(10) unsigned default NULL,
  `SaveTimestamp` datetime default NULL,
  `encryptionKey` varchar(100) NOT NULL,
  `originalPath` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  USING BTREE (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=98 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `backupinfo`
--

/*!40000 ALTER TABLE `backupinfo` DISABLE KEYS */;
INSERT INTO `backupinfo` (`ID`,`Sender`,`FolderPath`,`TotalSize`,`Receiver`,`SaveTimestamp`,`encryptionKey`,`originalPath`,`description`) VALUES 
 (79,143,'D:/test/PHP Brochure.dmp','1077024',143,'2012-02-24 10:47:17','DDDAB61613B70AF79BE0B459FA9F388C','PHP Brochure',''),
 (80,143,'D:/test/PHP Brochure.dmp','1077024',143,'2012-03-01 12:11:20','DDDAB61613B70AF79BE0B459FA9F388C','PHP Brochure',''),
 (81,143,'D:/test/Doc1.dmp','621600',144,'2013-03-02 10:59:17','DDDAB61613B70AF79BE0B459FA9F388C','Doc1',''),
 (82,143,'D:/test/carpooling.dmp','400',144,'2013-03-02 11:00:16','AD7AFACBD2E08C458A7D22CC2405DA2F','carpooling',''),
 (83,143,'D:/test/carpooling.dmp','400',144,'2013-03-02 11:02:19','DDDAB61613B70AF79BE0B459FA9F388C','carpooling.txt',''),
 (84,144,'//192.168.0.108/a/datadb/FileOrDirectory.dmp','768',143,'2012-03-03 10:43:47','DDDAB61613B70AF79BE0B459FA9F388C','FileOrDirectory.java',''),
 (85,144,'//192.168.0.108/a/datadb/ProgressBarExample.dmp','1424',144,'2012-03-03 11:07:04','DDDAB61613B70AF79BE0B459FA9F388C','ProgressBarExample.java',''),
 (86,144,'//192.168.0.108/a/datadb/DialANumber.dmp','1584',143,'2012-03-03 13:31:38','DDDAB61613B70AF79BE0B459FA9F388C','DialANumber.java','COmpany Personal Info'),
 (87,144,'//192.168.0.108/a/datadb/cmd.dmp','992',143,'2012-03-03 13:48:12','DDDAB61613B70AF79BE0B459FA9F388C','cmd.txt','Test Data'),
 (88,143,'/1330785367390/cmd.dmp','992',144,'2012-03-03 20:06:07','DDDAB61613B70AF79BE0B459FA9F388C','cmd.txt','command details'),
 (89,143,'/1330788910686/DialANumber.dmp','1584',144,'2012-03-03 21:05:10','DDDAB61613B70AF79BE0B459FA9F388C','DialANumber.java','sdafsdf'),
 (90,143,'/1330788910686/DialANumber.dmp','1584',145,'2012-03-03 21:05:10','DDDAB61613B70AF79BE0B459FA9F388C','DialANumber.java','sdafsdf'),
 (91,143,'/1330788996983/HashMapDemo.dmp','896',145,'2012-03-03 21:06:37','DDDAB61613B70AF79BE0B459FA9F388C','HashMapDemo.java','my document'),
 (92,143,'/1333023772690/Q1.dmp','6320',144,'2012-03-29 17:52:58','DDDAB61613B70AF79BE0B459FA9F388C','Q1.txt','rajesh'),
 (93,143,'/1334343411695/CurrencyConverter.dmp','1600',144,'2012-04-14 00:26:51','DDDAB61613B70AF79BE0B459FA9F388C','CurrencyConverter.java','my data'),
 (94,143,'/1334343868617/CurrencyConverter.dmp','1600',144,'2012-04-14 00:34:28','DDDAB61613B70AF79BE0B459FA9F388C','CurrencyConverter.java','my data'),
 (95,143,'/1334344342945/Dos.dmp','1200',144,'2012-04-14 00:42:23','DDDAB61613B70AF79BE0B459FA9F388C','Dos.java','Test data'),
 (96,143,'/1334345154961/Dos.dmp','1200',147,'2012-04-14 00:55:55','DDDAB61613B70AF79BE0B459FA9F388C','Dos.java','rajesh'),
 (97,143,'/1334345490711/TextAreaExample.dmp','512',147,'2012-04-14 01:01:30','DDDAB61613B70AF79BE0B459FA9F388C','TextAreaExample.java','rajesh');
/*!40000 ALTER TABLE `backupinfo` ENABLE KEYS */;


--
-- Definition of table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `rollId` int(10) unsigned NOT NULL auto_increment,
  `rollDesc` varchar(255) NOT NULL,
  PRIMARY KEY  (`rollId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`rollId`,`rollDesc`) VALUES 
 (1,'Distributor'),
 (2,'Agent'),
 (3,'Admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


--
-- Definition of table `senderreceiver`
--

DROP TABLE IF EXISTS `senderreceiver`;
CREATE TABLE `senderreceiver` (
  `Sender` int(10) unsigned NOT NULL,
  `Receiver` int(10) unsigned NOT NULL,
  `ARFlag` tinyint(3) unsigned default NULL,
  `RequestTimestamp` datetime NOT NULL,
  `ARTimestamp` datetime default NULL,
  `LogID` int(10) unsigned NOT NULL auto_increment,
  PRIMARY KEY  (`LogID`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `senderreceiver`
--

/*!40000 ALTER TABLE `senderreceiver` DISABLE KEYS */;
INSERT INTO `senderreceiver` (`Sender`,`Receiver`,`ARFlag`,`RequestTimestamp`,`ARTimestamp`,`LogID`) VALUES 
 (131,135,1,'2010-01-30 13:41:59','2010-01-30 13:42:12',51),
 (135,131,1,'2010-01-30 13:41:20','2010-01-30 13:41:42',50);
/*!40000 ALTER TABLE `senderreceiver` ENABLE KEYS */;


--
-- Definition of table `trackdownloads`
--

DROP TABLE IF EXISTS `trackdownloads`;
CREATE TABLE `trackdownloads` (
  `idtrackdownloads` int(10) unsigned NOT NULL auto_increment,
  `userid` int(10) unsigned NOT NULL,
  `fileid` int(10) unsigned NOT NULL,
  `ipaddress` varchar(45) NOT NULL,
  `downloadtimestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`idtrackdownloads`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trackdownloads`
--

/*!40000 ALTER TABLE `trackdownloads` DISABLE KEYS */;
INSERT INTO `trackdownloads` (`idtrackdownloads`,`userid`,`fileid`,`ipaddress`,`downloadtimestamp`) VALUES 
 (1,143,86,'192.168.0.108','2012-03-03 13:38:32'),
 (2,143,87,'192.168.0.108','2012-03-29 14:11:09'),
 (3,143,86,'90-E6-BA-BA-38-9B','2012-04-14 00:45:35'),
 (4,147,97,'90-E6-BA-BA-38-9B','2012-04-14 01:04:06');
/*!40000 ALTER TABLE `trackdownloads` ENABLE KEYS */;


--
-- Definition of table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `LoginId` varchar(35) NOT NULL,
  `UserPassword` varchar(255) NOT NULL,
  `UserId` int(10) unsigned NOT NULL auto_increment,
  `FName` varchar(35) NOT NULL,
  `LName` varchar(35) NOT NULL,
  `IPAddress` varchar(30) NOT NULL,
  `CellNumber` varchar(15) NOT NULL,
  `EmailAddress` varchar(45) NOT NULL,
  `ActiveFlag` tinyint(1) NOT NULL default '2',
  `LogStatus` varchar(45) NOT NULL default '0',
  `LogOnTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `LogOffTime` timestamp NOT NULL default '0000-00-00 00:00:00',
  `rollId` int(10) unsigned default '1',
  `city` varchar(45) default NULL,
  `pincode` varchar(45) default NULL,
  `changepassword` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`UserId`)
) ENGINE=MyISAM AUTO_INCREMENT=148 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `userinfo`
--

/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`LoginId`,`UserPassword`,`UserId`,`FName`,`LName`,`IPAddress`,`CellNumber`,`EmailAddress`,`ActiveFlag`,`LogStatus`,`LogOnTime`,`LogOffTime`,`rollId`,`city`,`pincode`,`changepassword`) VALUES 
 ('rajesh','9FECE5B695F97680C68F1FA6CA3502CD',143,'rajesh','agrawal','192.168.0.109','9860923474','mail.rajesh.agrawal@gmail.com',1,'Online','2012-04-14 03:24:59','2012-02-13 09:59:47',3,'Akola',NULL,'2012-04-14 03:24:59'),
 ('kaustubh','4B81685BA11CF4E5023A0DE569A86966',144,'Kaustubh','bojewar','192.168.0.109','9096356472','kau@gmail.com',1,'Online','2012-03-29 17:52:30','2012-02-13 17:14:15',2,'Aurangabad',NULL,'0000-00-00 00:00:00'),
 ('raj','9FECE5B695F97680C68F1FA6CA3502CD',145,'Raj1','Agrawal1','192.168.0.109','9860923474','mail.rajesh.agrawal@gmail.com',1,'Online','2012-02-13 17:14:15','2012-02-13 17:14:15',1,'Pune','431103','0000-00-00 00:00:00'),
 ('agent','E5BEEAB8B8A4451E319335F30EC23146',146,'agent','agent','90-E6-BA-BA-38-9B','9860923474','a@gmail.com',1,'Online','2012-04-14 03:31:55','2012-04-14 00:51:59',2,'pune','431021','2012-04-14 03:31:55'),
 ('agent2','B34FD244C89C0AD59E6E42A455E57254',147,'agent2','agent2','90-E6-BA-BA-38-9B','9860923474','a@gmail.com',1,'Online','2012-04-14 00:53:25','2012-04-14 00:52:31',2,'pune','431032','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
