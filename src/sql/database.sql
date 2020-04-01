-- 权限表
CREATE TABLE tb_permission(
pid int(11) not null auto_increment,
pname VARCHAR(255) not null DEFAULT '',
url VARCHAR(255) DEFAULT '',
PRIMARY KEY (pid)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

INSERT INTO tb_permission VALUES (1,'add','');
INSERT INTO tb_permission VALUES (2,'delete','');
INSERT INTO tb_permission VALUES (3,'edit','');
INSERT INTO tb_permission VALUES (4,'query','');
-- 用户表
CREATE TABLE tb_user(
uid int(11) not NULL auto_increment,
username VARCHAR(255) not null DEFAULT '',
pasword VARCHAR(255) not NULL DEFAULT '',
PRIMARY KEY (uid)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

INSERT INTO tb_user VALUES (1,'admin','123');
INSERT INTO tb_user VALUES (2,'demo','123');
-- 角色表
CREATE TABLE tb_role(
rid int(11) not null auto_increment,
rname VARCHAR(255) not NULL DEFAULT '',
PRIMARY KEY (rid)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

INSERT INTO tb_role VALUES (1,'admin');
INSERT INTO tb_role VALUES (2,'customer');

-- 权限角色关系表
CREATE TABLE tb_permission_role(
id INT (11) not null auto_increment,
rid int(11) not null,
pid int(11) not null,
KEY `idx_rid` (rid),
KEY `idx_pid` (pid),
PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET=utf8;

INSERT INTO tb_permission_role VALUES (1,1,'1');
INSERT INTO tb_permission_role VALUES (2,1,'2');
INSERT INTO tb_permission_role VALUES (3,1,'3');
INSERT INTO tb_permission_role VALUES (4,1,'4');
INSERT INTO tb_permission_role VALUES (5,2,'1');
INSERT INTO tb_permission_role VALUES (6,2,'4');

-- 权限角色关系表
CREATE TABLE tb_user_role(
id INT (11) not null auto_increment,
uid int(11) not null,
rid int(11) not null,
KEY `idx_uid` (uid),
KEY `idx_rid` (rid),
PRIMARY KEY (id)
) ENGINE = INNODB DEFAULT CHARSET=utf8;


INSERT INTO tb_user_role VALUES (1,1,'1');
INSERT INTO tb_user_role VALUES (2,2,'2');