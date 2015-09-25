## 创建系统用户表
DROP TABLE IF EXISTS `channel_user`;
CREATE TABLE `channel_user` (
    `user_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(64) NOT NULL COMMENT '用户名',
    `user_password` varchar(64) default '' COMMENT '密码',
    `user_update_time` TIMESTAMP default CURRENT_TIMESTAMP comment '记录更新时间',
    `user_remark` varchar(128) default '' COMMENT '备注',
    PRIMARY KEY (`user_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '存储可以访问渠道管理系统的登录用户信息';

insert into channel_user(user_id,user_name,user_password,user_update_time,user_remark) values(null,'channel','channel2',current_timestamp,'管理员账户');
#############################################################################################

## 创建白名单表 
DROP TABLE IF EXISTS `channel_whitelists`;
CREATE TABLE `channel_whitelists` (
    `wl_id` int(11) NOT NULL AUTO_INCREMENT,
    `wl_ip` varchar(15) NOT NULL COMMENT 'ipv4地址',
    `wl_hostname` varchar(64) default '' COMMENT '主机名称',
    `wl_update_time` TIMESTAMP default CURRENT_TIMESTAMP comment '记录更新时间',
    `wl_remark` varchar(128) default '' COMMENT '备注',
    PRIMARY KEY (`wl_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '存储可以访问渠道系统的IP白名单信息';

insert into channel_whitelists(wl_id,wl_ip,wl_hostname,wl_update_time,wl_remark) values(null,'127.0.0.1','本机',current_timestamp,'测试机器');
insert into channel_whitelists(wl_id,wl_ip,wl_hostname,wl_update_time,wl_remark) values(null,'172.16.104.147','本机',current_timestamp,'测试机器');
insert into channel_whitelists(wl_id,wl_ip,wl_hostname,wl_update_time,wl_remark) values(null,'192.168.23.3','本机',current_timestamp,'测试机器');
select 
    *
from
    channel_whitelists;
#############################################################################################

## 创建终端秘钥表 
DROP TABLE IF EXISTS `channel_security`;
CREATE TABLE `channel_security` (
    `sec_id` int(11) NOT NULL AUTO_INCREMENT,
    `sec_appno` varchar(16) NOT NULL COMMENT '终端号',
    `sec_key` varchar(64) NOT NULL COMMENT '32/64位秘钥',
    `sec_name` VARCHAR(64) default '' COMMENT '使用者名称',
    `sec_update_time` TIMESTAMP default CURRENT_TIMESTAMP comment '记录更新时间',
    `sec_remark` varchar(128) default '' COMMENT '备注',
    primary key (`sec_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '存储终端及秘钥信息';


#############################################################################################

## 创建服务列表
DROP TABLE IF EXISTS `channel_service`;
CREATE TABLE `channel_service` (
    `svc_id` int(11) NOT NULL AUTO_INCREMENT,
    `svc_owner` varchar(64) default '' comment '负责人',
    `svc_organization` varchar(64) default '' comment '组织名称(部门)',
    `svc_bean_id` varchar(32) not null comment '服务引用的bean id',
    `svc_fully_qualified_name` varchar(128) not null comment '服务接口类的全限定名称',
    `svc_name` varchar(64) not null comment '服务名称，供调用方使用，服务名称+服务版本必须保证唯一',
    `svc_version` varchar(16) not null comment '服务版本，供调用方使用用，服务名称+服务版本必须保证唯一',
    `svc_status` char(2) default '00' comment '服务状态，用于访问的服务的开关，00-停用 01-启用',
    `svc_update_time` TIMESTAMP default CURRENT_TIMESTAMP comment '记录更新时间',
    `svc_remark` varchar(128) default '' COMMENT '备注',
    PRIMARY KEY (`svc_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '存储服务配置信息';

#############################################################################################

## 创建权限表
DROP TABLE IF EXISTS `channel_permission`;
CREATE TABLE `channel_permission` (
    `per_id` int(11) NOT NULL AUTO_INCREMENT,
    `per_appno` varchar(16) not null comment 'channel_security中的id',
    `per_service_name` varchar(64) not null comment 'channel_service中的svc_name',
    `per_service_version` varchar(16) not null comment 'channel_service中的svc_version',
    `per_update_time` TIMESTAMP default CURRENT_TIMESTAMP comment '记录更新时间',
    `per_remark` varchar(128) default '' COMMENT '备注',
    PRIMARY KEY (`per_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '存储访问权限信息';




