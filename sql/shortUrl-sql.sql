CREATE TABLE `kreShortUrl` (
  `shortUrlNo` int unsigned NOT NULL AUTO_INCREMENT,
  `realUrl` varchar(3000) NOT NULL,
  `encodedUrl` varchar(200) NOT NULL DEFAULT '',
  `visitCount` int unsigned NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  `createIp` varchar(15) NOT NULL,
  PRIMARY KEY (`shortUrlNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4