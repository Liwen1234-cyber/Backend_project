create database if not exists test;
use test;
drop table if exists user;
drop table if exists product;
create table user
(
    id       int primary key auto_increment,
    username varchar(20),
    password varchar(20)
);

create table product
(
    id    int primary key auto_increment,
    name  varchar(20),
    price decimal(10, 2)
);

insert into user (username, password)
values ('zhangsan', '123'),
       ('lisi', '123');

insert into product (name, price)
values ('商品1', 1000.0),
       ('商品2',500),
       ('商品3',800),
       ('商品4',600),
       ('商品5',500);