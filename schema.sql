
CREATE DATABASE fyy DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

show variables like'%char%';
set character_set_database=utf8;
set character_set_server=utf8;
set character_set_client=utf8;
set character_set_connection=utf8;

use fyy;
create table Employee(
 id int(10) not null primary key auto_increment,
 name varchar(20) not null,
 createDate DATE,
 updateDate DATE,
 email varchar(100) not null,
 phone varchar(40) not null,
 departmentId int(1) not null,
 positionId int(1) not null,
 statusId int(1) not null

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Customer(
 id int(10) not null primary key auto_increment,
 name varchar(20) not null,
 password varchar(256) not null,
 createDate DATE,
 updateDate DATE,
 employeeId int(10),
 roleId int(10)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into Customer (name,password,createDate,updateDate,employeeId) values('admin','admin',now(),now(),null);

create table Material(
 id int(10) not null primary key auto_increment,
 name varchar(20) not null,
 categoryId int(1) not null,
 unitId int(1) not null,
 note varchar(256),
 createDate DATE,
 updateDate DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Inventory(
 id int(10) not null primary key auto_increment,
  createDate DATE,
 updateDate DATE,
 typeId int(1) not null,
 customerId  int(10) not null,
 materialId int(10) not null,
  num int(10) not null,
 note varchar(256),
 CONSTRAINT FOREIGN KEY (customerId) REFERENCES Customer(id),
 CONSTRAINT FOREIGN KEY (materialId) REFERENCES Material(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table Role(
 id int(10) not null primary key auto_increment,
 name varchar(20) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table RolePermission(
 id int(10) not null primary key auto_increment,
 modelId int(10) not null,
 roleId int(10) not null,
 isPerm char(1) default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

