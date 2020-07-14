CREATE TABLE `kreServiceFile` (
  `serviceName` varchar(200) NOT NULL,
  `serviceNo` int unsigned NOT NULL,
  `fileNo` int unsigned NOT NULL,
  `serviceFileWriteTime` datetime NOT NULL,
  `serviceFileWriteIp` varchar(15) NOT NULL,
  PRIMARY KEY (`serviceName`, `serviceNo`, `fileNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4