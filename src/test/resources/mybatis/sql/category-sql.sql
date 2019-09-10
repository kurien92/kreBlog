CREATE TABLE `kreCategory` (
  `categoryNo` int unsigned NOT NULL AUTO_INCREMENT,
  `categoryParentNo` int DEFAULT NULL,
  `categoryDepth` int NOT NULL,
  `categoryOrder` int NOT NULL,
  `categoryId` varchar(30) NOT NULL UNIQUE,
  `categoryName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`categoryNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;