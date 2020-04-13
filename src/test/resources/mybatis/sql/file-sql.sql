CREATE TABLE `kreFile` (
  `fileNo` int unsigned NOT NULL AUTO_INCREMENT,
  `fileType` varchar(6) NOT NULL,
  `filePath` varchar(255) NOT NULL,
  `fileName` varchar(255) NOT NULL,
  `fileStoredName` varchar(255) NOT NULL,
  `fileExtension` varchar(10),
  `fileMime` varchar(100),
  `fileSize` int unsigned NOT NULL,
  `fileUploadIp` varchar(100) NOT NULL,
  `fileUploadTime` varchar(100) NOT NULL,
  PRIMARY KEY (`fileNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;