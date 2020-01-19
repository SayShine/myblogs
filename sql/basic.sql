#创建权限表
drop table if exists tpg_authority;
CREATE TABLE `tpg_authority` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `NAME` varchar(255) DEFAULT NULL COMMENT '权限名',
  `PID` bigint(255) DEFAULT NULL COMMENT '父权限id',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATER` bigint(20) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `STATUS` tinyint(2) DEFAULT NULL COMMENT '状态【0：已删除；1：可使用；2：禁用】',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
INSERT INTO tpg_user VALUES('','超级管理员','admin','',now(),'',now(),'',
                            1,'超级超级管理员','1','952505116@qq.com','cxk');

#创建用户表
drop table if exists tpg_user;
CREATE TABLE `tpg_user` (
                            `ID` bigint(20) NOT NULL AUTO_INCREMENT,
                            `NAME` varchar(100) NOT NULL COMMENT '姓名',
                            `ACCOUNT` varchar(100) NOT NULL COMMENT '账号',
                            `PASSWORD` varchar(100) NOT NULL COMMENT '密码',
                            `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                            `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建人',
                            `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                            `UPDATOR` bigint(20) DEFAULT NULL COMMENT '更新人',
                            `STATUS` tinyint(2) DEFAULT NULL COMMENT '状态【0：已删除；1：可使用；2：禁用】',
                            `COMMENTS` varchar(100) DEFAULT NULL COMMENT '备注',
                            `GENDER` varchar(10) DEFAULT NULL COMMENT '性别【1：女；2，男】',
                            `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
                            `REALNAME` varchar(100) DEFAULT NULL COMMENT '真实姓名',
                            PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
INSERT INTO tpg_authority VALUES('','祖安权限','','祖安所有组件都可以使用',1,now(),
                                 1,now(),1);

#创建用户-权限关联表
drop table if exists tpg_user_auth_relation;
CREATE TABLE `tpg_user_auth_relation` (
                                          `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户权限id',
                                          `AUTHORITY_ID` bigint(20) NOT NULL COMMENT '权限id',
                                          `USER_ID` bigint(20) NOT NULL COMMENT '用户id',
                                          `CREATOR` bigint(20) DEFAULT NULL COMMENT '创建人',
                                          `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                          `UPDATER` bigint(20) DEFAULT NULL COMMENT '修改人',
                                          `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
                                          `STATUS` tinyint(2) DEFAULT NULL COMMENT '状态【0：已删除；1：可使用；2：禁用】',
                                          PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
INSERT INTO tpg_user_auth_relation VALUES('',1,1,1,now(),1,now(),1);