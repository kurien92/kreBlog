CREATE TABLE `kreAccount` (
    `accountNo` int unsigned NOT NULL AUTO_INCREMENT,
    `accountId` varchar(100) NOT NULL UNIQUE,
    `accountPassword` varchar(200) NOT NULL,
    `accountEmail` varchar(200) NOT NULL,
    `accountNickname` varchar(30) NOT NULL,
    `accountBlock` tinyint(1) NOT NULL DEFAULT 0,
    `accountSignUpDate` datetime NOT NULL DEFAULT now(),
    `accountSignUpIp` varchar(15),
    PRIMARY KEY (`accountNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4