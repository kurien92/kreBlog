CREATE TABLE `kreComment` (
  `commentNo` int unsigned NOT NULL AUTO_INCREMENT,
  `postNo` int unsigned NOT NULL,
  `parentCommentNo` int unsigned,
  `commentOrder` int unsigned DEFAULT '1',
  `commentDepth` int unsigned DEFAULT '1',
  `accountNo` int unsigned,
  `author` varchar(100),
  `password` varchar(100),
  `comment` text NOT NULL,
  `writeTime` datetime NOT NULL,
  `writeIp` varchar(15),
  `deleteYn` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`commentNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4