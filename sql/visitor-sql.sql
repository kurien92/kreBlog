CREATE TABLE `kreVisitor` (
  `visitorNo` int unsigned NOT NULL AUTO_INCREMENT,
  `userCookie` varchar(200) NOT NULL,
  `userAgent` text,
  `currentUrl` text,
  `referrer` text, -- document.referrer
  `resolutionX` int unsigned NOT NULL, -- screen.width
  `resolutionY` int unsigned NOT NULL, -- screen.height
  `visitTime` datetime NOT NULL,
  `visitorIp` varchar(15) NOT NULL,
  PRIMARY KEY (`visitorNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4