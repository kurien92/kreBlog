CREATE TABLE `kre_post` (
  `post_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `post_author` varchar(30) NOT NULL,
  `post_password` varchar(100) DEFAULT NULL,
  `post_title` varchar(100) NOT NULL,
  `post_content` text NOT NULL,
  `post_view` tinyint(1) NOT NULL DEFAULT '0',
  `post_publish` tinyint(1) NOT NULL DEFAULT '0',
  `post_write_time` datetime NOT NULL,
  `post_reservation_time` datetime DEFAULT NULL,
  `post_write_ip` varchar(15) NOT NULL,
  PRIMARY KEY (`post_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8