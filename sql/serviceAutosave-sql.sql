create table `kreServiceAutosave` (
    `serviceName` varchar(200) NOT NULL,
    `asNo` int unsigned NOT NULL,
    `serviceAsUsername` varchar(20) NOT NULL,
    `serviceAsWriteTime` datetime NOT NULL,
    `serviceAsWriteIp` varchar(15) NOT NULL,
    `serviceAsExpireTime` datetime NOT NULL,
    PRIMARY KEY (`serviceName`, `asNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4