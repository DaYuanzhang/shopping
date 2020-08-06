drop database if exists mall; -- 数据库名不能用中划线-
create database mall;
use mall;
CREATE TABLE users (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  `tel` varchar(20) DEFAULT NULL,
  `Role` enum('客户','商家','管理员') DEFAULT NULL,
  `email`    varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
insert into users(loginName,password,remark,Role,email,tel) values ('admin','admin','测试数据:管理员用户','管理员','1236523652@qq.com','15326323589');
insert into users(loginName,password,remark,Role,email,tel) values ('chen','123456','测试数据:客户用户','客户','1523652489@qq.com','12356956848');
insert into users(loginName,password,remark,Role,email,tel) values ('lin','123456','测试数据:商家用户','商家','1368945685@qq.com','17856359465');
insert into users(loginName,password,remark,Role,email,tel) values ('jiang','123456','测试数据:客户用户','客户','1546258965@qq.com','13658974586');
insert into users(loginName,password,remark,Role,email,tel) values ('he','123456','测试数据:客户用户','客户','1321454651@qq.com','16359845682');
insert into users(loginName,password,remark,Role,email,tel) values ('zheng','123456','测试数据:客户用户','客户','1547586954@qq.com','13625123652');
insert into users(loginName,password,remark,Role,email,tel) values ('kang','123456','测试数据:客户用户','客户','1523654758@qq.com','19953625248');
insert into users(loginName,password,remark,Role,email,tel) values ('jun','123456','测试数据:客户用户','客户','15236958458@qq.com','19568958745');
insert into users(loginName,password,remark,Role,email,tel) values ('zhao','123456','测试数据:商家用户','商家','1698546523@qq.com','11365889544');
insert into users(loginName,password,remark,Role,email,tel) values ('qian','123456','测试数据:商家用户','商家','1475684547@qq.com','18455563236');

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
#订单表
CREATE TABLE orders(
oid	INT(11)	AUTO_INCREMENT,
con_id	INT(11) ,
pro_id	VARCHAR(32),
dat 	DATE ,
mer_id INT(11),
addr  VARCHAR(50),
PRIMARY KEY(oid),
CONSTRAINT oc_fk FOREIGN KEY(con_id) REFERENCES users(id),
CONSTRAINT op_fk FOREIGN KEY(pro_id) REFERENCES products(pid),
CONSTRAINT om_fk FOREIGN KEY(mer_id) REFERENCES users(id)
);

CREATE TABLE request (
  `req_number` int(11) NOT NULL AUTO_INCREMENT,
    `id`    int(11)   DEFAULT NULL,
  `loginName` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`req_number`)
);
INSERT INTO request(id,loginName,remark) VALUES('2','chen','我要赚钱');
INSERT INTO request(id,loginName,remark) VALUES('4','jiang','我有梦想');
INSERT INTO request(id,loginName,remark) VALUES('5','he','我要当老板');


INSERT INTO category(cid,cname) VALUES('c001','家电');
INSERT INTO category(cid,cname) VALUES('c002','服饰');
INSERT INTO category(cid,cname) VALUES('c003','化妆品');
#商品

INSERT INTO products(pid, pname,price,flag,category_id,s_id,inventory) VALUES('p1','联想',5000,'1','c001',3,60);
INSERT INTO products(pid, pname,price,flag,category_id,s_id,inventory) VALUES('p2','海尔',3000,'1','c001',3,35);
INSERT INTO products(pid, pname,price,flag,category_id,s_id,inventory) VALUES('p3','雷神',5000,'1','c001',3,21);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p4','JACKJONES',800,'1','c002',9,13);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p5','真维斯',200,'1','c002',9,125);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p6','花花公子',440,'1','c002',9,365);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p7','劲霸',2000,'1','c002',9,1000);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p8','香奈儿',800,'1','c003',10,536);
INSERT INTO products (pid, pname,price,flag,category_id,s_id,inventory) VALUES('p9','相宜本草',200,'1','c003',10,152);

#订单
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(2,'p1','2020-12-13',3,'ddasdasdas');
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(2,'p2','2020-09-01',3,'fgvkjhbk.noih');
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(2,'p3','2020-10-04',3,'asfasgassdasda');
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(5,'p6','2019-12-05',9,'45345655465466fsa');
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(5,'p9','2019-11-02',10,'345dsfas');
INSERT INTO orders(con_id,pro_id,dat,mer_id,addr) VALUES(6,'p4','2019-07-05',9,'湖南省');

