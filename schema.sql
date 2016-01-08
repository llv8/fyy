create database fyy;

use fyy;

create table User(
 id int(10) not null primary key auto_increment,
 userName varchar(20) not null,
 loginName varchar(20) not null,
 password varchar(256) not null,
 createDate DATE,
 updateDate DATE,
 email varchar(100) not null,
 phone varchar(40) not null,
 department int(1) not null

);


create table Material(
 id int(10) not null primary key auto_increment,
 name varchar(20) not null,
 categoryId int(1) not null,
 unitId int(1) not null,
 num int(10) not null,
 note varchar(256),
 createDate DATE,
 updateDate DATE
);

create table Inventory(
 id int(10) not null primary key auto_increment,
  createDate DATE,
 updateDate DATE,
 typeId int(1) not null,
 userId  int(10) not null,
 materialId int(10) not null,
  num int(10) not null,
 note varchar(256),
 CONSTRAINT FOREIGN KEY (userId) REFERENCES User(id),
 CONSTRAINT FOREIGN KEY (materialId) REFERENCES Material(id)
);
