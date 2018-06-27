/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.6.20 : Database - codeuc
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`codeuc` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `codeuc`;

/*Table structure for table `tb_code` */

DROP TABLE IF EXISTS `tb_code`;

CREATE TABLE `tb_code` (
  `code_id` int(50) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) NOT NULL,
  `is_public` tinyint(1) NOT NULL DEFAULT '1',
  `creat_time` datetime NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `code_name` varchar(100) NOT NULL,
  `image_path` varchar(100) NOT NULL,
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `tb_code` */

insert  into `tb_code`(`code_id`,`path`,`is_public`,`creat_time`,`description`,`code_name`,`image_path`) values (31,'testpath',1,'2018-06-24 00:00:00','test','test1','123'),(32,'testpath',1,'2018-06-24 00:00:00','test','test2','123'),(33,'F:\\myEclWorkplace\\codingSoft\\user\\269182663@qq.com\\code\\test',1,'2018-06-24 00:00:00','test','test3','123'),(34,'D:\\test-folder\\rep',1,'2018-06-24 00:00:00','这是一个小测试，希望期末全过！！！','test4','123'),(35,'F:\\myEclipse\\user\\269182663@qq.com\\code\\TAS2',1,'2018-06-26 00:00:00','简单的教务管理系统','TAS2','resources/img/time.jpg');

/*Table structure for table `tb_code_user` */

DROP TABLE IF EXISTS `tb_code_user`;

CREATE TABLE `tb_code_user` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `user_id` int(100) NOT NULL,
  `permissions` int(10) NOT NULL,
  `code_id` int(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tb_code_user` (`user_id`),
  KEY `FK_tb_code_user_code` (`code_id`),
  CONSTRAINT `FK_tb_code_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `FK_tb_code_user_code` FOREIGN KEY (`code_id`) REFERENCES `tb_code` (`code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `tb_code_user` */

insert  into `tb_code_user`(`id`,`user_id`,`permissions`,`code_id`) values (31,8,10,33),(32,8,10,34),(34,8,10,35);

/*Table structure for table `tb_msg` */

DROP TABLE IF EXISTS `tb_msg`;

CREATE TABLE `tb_msg` (
  `msg_id` int(50) NOT NULL,
  `to_code_id` int(100) NOT NULL,
  `content` varchar(300) NOT NULL,
  `from_user_id` int(100) NOT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `FK_tb_msg` (`from_user_id`),
  KEY `FK_tb_msg1` (`to_code_id`),
  CONSTRAINT `FK_tb_msg` FOREIGN KEY (`from_user_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `FK_tb_msg1` FOREIGN KEY (`to_code_id`) REFERENCES `tb_code` (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_msg` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `permissions` varchar(30) DEFAULT 'normal',
  `user_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`username`,`password`,`phone`,`email`,`permissions`,`user_image`) values (1,'admin','123','123','admin','normal',NULL),(2,'test','123','123','test','normal',NULL),(7,'demo','456','789','test','normal',NULL),(8,'wjwstc123','04c803a245b6629301b8b184ee159b4d','15980272615','269182663@qq.com','normal',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
