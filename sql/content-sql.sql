CREATE TABLE `kreContent` (
  `contentNo` int unsigned NOT NULL AUTO_INCREMENT,
  `contentId` varchar(30) NOT NULL UNIQUE,
  `contentTitle` varchar(100) NOT NULL,
  `content` mediumtext NOT NULL,
  `contentView` tinyint(1) NOT NULL DEFAULT '0',
  `contentWriteTime` datetime NOT NULL,
  PRIMARY KEY (`contentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4