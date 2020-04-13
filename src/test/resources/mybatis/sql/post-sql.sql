CREATE TABLE `krePost` (
  `postNo` int unsigned NOT NULL AUTO_INCREMENT,
  `categoryId` varchar(30) DEFAULT NULL,
  `postAuthor` varchar(30) NOT NULL,
  `postPassword` varchar(100) DEFAULT NULL,
  `postSubject` varchar(100) NOT NULL,
  `postContent` text NOT NULL,
  `postView` tinyint(1) NOT NULL DEFAULT '0',
  `postPublish` tinyint(1) NOT NULL DEFAULT '0',
  `postWriteTime` datetime NOT NULL,
  `postReservationTime` datetime DEFAULT NULL,
  `postWriteIp` varchar(15) NOT NULL,
  PRIMARY KEY (`postNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4