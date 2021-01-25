CREATE TABLE `kreGroupAuthorities` (
    `groupId` varchar(20) NOT NULL,
    `groupAuthority` varchar(100) NOT NULL,
    PRIMARY KEY (`groupId`, `groupAuthority`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4