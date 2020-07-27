drop database if exists mall; -- 数据库名不能用中划线-
create database mall;
use mall;
CREATE TABLE users (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `Role` enum('客户','商家','管理员') DEFAULT NULL,
  emai    varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
insert into users(loginName,password,remark,Role) values ('admin','admin','测试数据:管理员用户','管理员');


# 分类表
CREATE TABLE category (
cid VARCHAR(32) PRIMARY KEY ,
cname VARCHAR(50)
);
#商品表
CREATE TABLE products(
pid VARCHAR(32) PRIMARY KEY ,
pname VARCHAR(50),
price INT,
flag VARCHAR(2),
category_id VARCHAR(32),
s_id  int(11),
inventory int DEFAULT '0',
CONSTRAINT products_fk FOREIGN KEY (category_id) REFERENCES category (cid),
CONSTRAINT ps_fk FOREIGN KEY (s_id) REFERENCES users(id)
);


INSERT INTO category(cid,cname) VALUES('c001','家电');
INSERT INTO category(cid,cname) VALUES('c002','服饰');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
#商品
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p001','联想',5000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p002','海尔',3000,'1','c001');
INSERT INTO products(pid, pname,price,flag,category_id) VALUES('p003','雷神',5000,'1','c001');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p004','JACKJONES',800,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p005','真维斯',200,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p006','花花公子',440,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p007','劲霸',2000,'1','c002');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p008','香奈儿',800,'1','c003');
INSERT INTO products (pid, pname,price,flag,category_id) VALUES('p009','相宜本草',200,'1','c003');
