CREATE TABLE `kreAuthority` (
    `accountId` varchar(100) NOT NULL,
    `authorityName` varchar(20) NOT NULL,
    PRIMARY KEY (`accountId`, `authorityName`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4