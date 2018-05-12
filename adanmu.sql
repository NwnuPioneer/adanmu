/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.21-0ubuntu0.16.04.1 : Database - adanmu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`adanmu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `adanmu`;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `dictzh` varchar(50) DEFAULT NULL COMMENT '字段中文名称',
  `dictname` varchar(50) DEFAULT NULL COMMENT '字段显示',
  `dictvalue` varchar(50) DEFAULT NULL COMMENT '字段存储值',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `uid` int(11) NOT NULL COMMENT '操作员id',
  `uptime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统字典';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`dict`,`dictzh`,`dictname`,`dictvalue`,`seq`,`uid`,`uptime`) values (1,'MinZu','民族','汉族','汉族',1,17,'2018-05-11 21:15:31');

/*Table structure for table `sys_privilege` */

DROP TABLE IF EXISTS `sys_privilege`;

CREATE TABLE `sys_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `privilegecode` varchar(4) NOT NULL COMMENT '权限编码',
  `privilegename` varchar(255) NOT NULL COMMENT '权限名称',
  `parentcode` varchar(4) NOT NULL COMMENT '父编码',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `iconfont` varchar(30) DEFAULT NULL,
  `isshow` char(1) DEFAULT 'a' COMMENT '是否显示',
  `sequence` int(11) DEFAULT '0' COMMENT '显示顺序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `uid` int(11) NOT NULL COMMENT '操作员id',
  `uptime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='权限';

/*Data for the table `sys_privilege` */

insert  into `sys_privilege`(`id`,`privilegecode`,`privilegename`,`parentcode`,`url`,`iconfont`,`isshow`,`sequence`,`remark`,`uid`,`uptime`) values (4,'0001','管理员管理','0000','#','62d;','a',1,'请勿删除',4,'2017-09-20 20:56:45'),(5,'0002','角色管理','0001','/SysRole/SysRoleIndex.do','611;','a',2,'请勿删除',4,'2017-10-28 16:40:18'),(6,'0003','菜单管理','0001','/SysPrivilege/SysPrivilegeIndex.do','653;','a',3,'请勿删除',4,'2017-11-19 21:25:49'),(7,'0004','管理员列表','0001','/SysUser/SysUserIndex.do','62d;','a',4,'请勿删除',3,'2017-09-20 20:32:54'),(9,'0005','系统管理','0000','#','62e;','a',2,'请勿删除',4,'2017-11-16 19:21:40'),(14,'0010','主播管理','0000','#','64f;','a',4,'请勿删除',17,'2018-05-12 14:02:58'),(15,'0011','数据字典','0005','/SysDict/SysDictIndex.do','687;','a',1,'请勿删除',4,'2017-10-09 19:15:42'),(34,'0016','系统统计','0000','#','66a;','a',3,'请勿删除',17,'2018-05-12 14:02:52'),(35,'0017','主播列表','0010','/UserAnchor/UserAnchorIndex.do','63c;','a',2,'请勿删除',17,'2018-05-12 16:47:30'),(46,'0028','粉丝列表','0010','/UserFans/UserFansIndex.do','607;','a',1,'请勿删除',17,'2018-05-12 17:43:38'),(47,'0029','实时弹幕发送量','0016','/AnaySys/NowIndex.do','66a;','a',3,'请勿删除',17,'2018-05-12 13:53:12'),(48,'0030','弹幕词频','0016','/AnaySys/CipingIndex.do','70c;','a',4,'请勿删除',17,'2018-05-12 17:46:06'),(57,'0039','真实弹幕用户占比','0016','/AnaySys/RealIndex.do','6b7;','a',5,'请勿删除',17,'2018-05-12 17:33:28'),(58,'0040','词云展示','0016','/AnaySys/WordIndex.do','618;','a',6,'请勿删除',17,'2018-05-12 17:19:33');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rolecode` varchar(4) NOT NULL COMMENT '角色编码',
  `rolename` varchar(255) NOT NULL COMMENT '角色名称',
  `roletype` char(1) DEFAULT 'a' COMMENT '角色类型',
  `sequence` int(11) DEFAULT '0' COMMENT '显示顺序',
  `status` char(1) DEFAULT 'a' COMMENT '是否启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `uid` int(11) NOT NULL COMMENT '操作员id',
  `uptime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`rolecode`,`rolename`,`roletype`,`sequence`,`status`,`remark`,`uid`,`uptime`) values (2,'0001','超级管理员','a',1,'a','请勿删除',17,'2018-05-12 16:57:31'),(3,'0002','管理员','a',2,'a','请勿删除',17,'2018-05-12 16:57:42');

/*Table structure for table `sys_role_privilege` */

DROP TABLE IF EXISTS `sys_role_privilege`;

CREATE TABLE `sys_role_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rolecode` varchar(4) NOT NULL COMMENT '角色编码',
  `privilegecode` varchar(4) NOT NULL COMMENT '权限编码集',
  `uid` int(11) NOT NULL COMMENT '操作员id',
  `uptime` time DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1032 DEFAULT CHARSET=utf8 COMMENT='角色权限';

/*Data for the table `sys_role_privilege` */

insert  into `sys_role_privilege`(`id`,`rolecode`,`privilegecode`,`uid`,`uptime`) values (839,'0008','0001',1,'19:00:03'),(840,'0008','0002',1,'19:00:03'),(842,'0008','0004',1,'19:00:03'),(843,'0008','0005',1,'19:00:03'),(844,'0008','0011',1,'19:00:03'),(845,'0008','0010',1,'19:00:03'),(846,'0008','0017',1,'19:00:03'),(847,'0008','0028',1,'19:00:03'),(848,'0008','0016',1,'19:00:03'),(849,'0008','0029',1,'19:00:03'),(850,'0008','0030',1,'19:00:03'),(851,'0008','0039',1,'19:00:03'),(852,'0008','0040',1,'19:00:03'),(867,'0008','0047',1,'19:00:03'),(868,'0008','0036',1,'19:00:03'),(869,'0008','0037',1,'19:00:03'),(878,'0016','0016',1,'10:39:57'),(879,'0016','0029',1,'10:39:57'),(880,'0016','0030',1,'10:39:57'),(881,'0016','0039',1,'10:39:57'),(882,'0016','0040',1,'10:39:57'),(883,'0011','0001',1,'17:39:02'),(884,'0011','0002',1,'17:39:02'),(886,'0011','0004',1,'17:39:02'),(888,'0011','0005',1,'17:39:02'),(889,'0011','0011',1,'17:39:02'),(890,'0011','0010',1,'17:39:02'),(891,'0011','0017',1,'17:39:02'),(892,'0011','0028',1,'17:39:02'),(893,'0011','0016',1,'17:39:02'),(894,'0011','0029',1,'17:39:02'),(895,'0011','0030',1,'17:39:02'),(896,'0011','0039',1,'17:39:02'),(897,'0011','0040',1,'17:39:02'),(913,'0011','0047',1,'17:39:02'),(914,'0011','0036',1,'17:39:02'),(915,'0011','0037',1,'17:39:02'),(916,'0011','012',1,'17:39:02'),(918,'0026','0001',1,'14:04:01'),(919,'0026','0002',1,'14:04:01'),(921,'0026','0004',1,'14:04:01'),(957,'0004','0047',1,'21:37:12'),(958,'0004','0036',1,'21:37:12'),(959,'0004','0037',1,'21:37:12'),(973,'0005','0047',1,'21:37:41'),(974,'0005','0036',1,'21:37:41'),(975,'0005','0037',1,'21:37:41'),(1005,'0001','0001',1,'20:57:36'),(1006,'0001','0002',1,'20:57:36'),(1007,'0001','0003',1,'20:57:36'),(1008,'0001','0004',1,'20:57:36'),(1009,'0001','0005',1,'20:57:36'),(1010,'0001','0011',1,'20:57:36'),(1011,'0001','0010',1,'20:57:36'),(1012,'0001','0017',1,'20:57:36'),(1013,'0001','0028',1,'20:57:36'),(1014,'0001','0016',1,'20:57:36'),(1015,'0001','0029',1,'20:57:36'),(1016,'0001','0030',1,'20:57:36'),(1017,'0001','0039',1,'20:57:36'),(1018,'0001','0040',1,'20:57:36'),(1019,'0002','0001',1,'21:10:09'),(1020,'0002','0002',1,'21:10:09'),(1021,'0002','0004',1,'21:10:09'),(1022,'0002','0005',1,'21:10:09'),(1023,'0002','0011',1,'21:10:09'),(1024,'0002','0010',1,'21:10:09'),(1025,'0002','0017',1,'21:10:09'),(1026,'0002','0028',1,'21:10:09'),(1027,'0002','0016',1,'21:10:09'),(1028,'0002','0029',1,'21:10:09'),(1029,'0002','0030',1,'21:10:09'),(1030,'0002','0039',1,'21:10:09'),(1031,'0002','0040',1,'21:10:09');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tel` varchar(50) NOT NULL COMMENT '手机号码',
  `pwd` varchar(200) NOT NULL COMMENT '密码',
  `rolecode` varchar(4) NOT NULL COMMENT '角色编码',
  `name` varchar(50) NOT NULL COMMENT '真实姓名',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号码',
  `uid` int(11) NOT NULL COMMENT '操作员id',
  `uptime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='管理员';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`tel`,`pwd`,`rolecode`,`name`,`qq`,`uid`,`uptime`) values (4,'18298325268','a851eadd447a19e7','0002','dsk','1103146395',17,'2018-05-11 21:09:42'),(17,'13321224698','ae0e071c8a9902c3','0001','杜世康','25455652',4,'2018-05-11 20:51:00'),(18,'15393543655','a851eadd447a19e7','0001','小杜','332332',17,'2018-05-12 17:03:15');

/*Table structure for table `user_anchor` */

DROP TABLE IF EXISTS `user_anchor`;

CREATE TABLE `user_anchor` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` int(8) unsigned NOT NULL COMMENT '房间号',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user_anchor` */

/*Table structure for table `user_fans` */

DROP TABLE IF EXISTS `user_fans`;

CREATE TABLE `user_fans` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `namme` varchar(20) DEFAULT NULL COMMENT '昵称',
  `bnn` varchar(20) DEFAULT NULL COMMENT '徽章昵称',
  `level` int(3) DEFAULT NULL COMMENT '用户等级',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user_fans` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
