CREATE TABLE `kreComment` (
  `commentNo` int unsigned NOT NULL AUTO_INCREMENT,
  `postNo` int unsigned NOT NULL,
  `parentCommentNo` int unsigned,
  `commentOrder` int unsigned DEFAULT '1',
  `commentDepth` int unsigned DEFAULT '1',
  `author` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `comment` text NOT NULL,
  `writeTime` datetime NOT NULL,
  `writeIp` varchar(15),
  `deleteYn` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`commentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4