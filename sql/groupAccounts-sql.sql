CREATE TABLE `kreGroupAccounts` (
    `groupId` varchar(20) NOT NULL,
    `accountId` varchar(100) NOT NULL,
    PRIMARY KEY (`groupId`, `accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4