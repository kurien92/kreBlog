CREATE TABLE `kreServiceShortUrl` (
  `serviceName` varchar(200) NOT NULL,
  `serviceNo` int unsigned NOT NULL,
  `shortUrlNo` int unsigned NOT NULL,
  `createTime` datetime NOT NULL,
  `createIp` varchar(15) NOT NULL,
  PRIMARY KEY (`serviceName`, `serviceNo`, `shortUrlNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4