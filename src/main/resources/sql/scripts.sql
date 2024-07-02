CREATE DATABASE db_spring_boot_spring_security_umr;
USE db_spring_boot_spring_security_umr;
create table `users` (
	`id` int not null auto_increment,
    `username` varchar(45) not null,
    `password` varchar(45) not null,
    `enabled` int not null,
    primary key (`id`)
);
create table `authorities` (
    `id` int not null auto_increment,
    `username` varchar(45) not null,
    `authority` varchar(45) not null,
    primary key (`id`)
);
insert ignore into `users` values (
	null,
    'happy',
    '12345',
    '1'
);
insert ignore into `authorities` values (
	null,
    'happy',
    'write'
);
create table `customer` (
	`id` int not null auto_increment,
    `email` varchar(45) not null,
    `pwd` varchar(200) not null,
    `role` varchar(45) not null,
    primary key (`id`)
);
insert into `customer`(`email`, `pwd`, `role`) values ('johndoe@gmail.com', '123', 'admin');