#数据库版本：mysql 5.5.44

#1. root登陆mysql

#2. 创建channel用户
CREATE USER channel IDENTIFIED BY 'com.zjht.channel';

#3. 创建数据库
CREATE DATABASE `channel` CHARACTER SET utf8 COLLATE utf8_general_ci;
 
#4. 给用户chanle赋予数据款权限
grant all privileges on channel.* to channel@localhost identified by 'com.zjht.channel';

#5. 创建表结构
CREATE TABLE whiltelists(
	id int(16) not null 
)