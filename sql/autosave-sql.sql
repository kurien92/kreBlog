create table `kreAutosave` (
    `asNo` int unsigned NOT NULL AUTO_INCREMENT,
    `asJsonData` json NOT NULL,
    `asSaveTime` datetime NOT NULL,
    PRIMARY KEY (`asNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4