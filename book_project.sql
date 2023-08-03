DROP DATABASE IF EXISTS `book`;

CREATE DATABASE `book`;

USE `book`;

# 建表
CREATE TABLE `books`(
	`isbn` varchar(10) PRIMARY KEY,
	`name` varchar(30) NOT NULL DEFAULT "***",
	`author` varchar(20) NOT NULL DEFAULT "***",
	`version` varchar(10) default "第1版",
	`price` float DEFAULT 0,
	`stock` int UNSIGNED,
	`sale` int UNSIGNED DEFAULT 0,
	`img` varchar(100)
);

CREATE TABLE `user`(
	`id` int UNSIGNED primary KEY AUTO_INCREMENT,
	`name` varchar(20) NOT NULL UNIQUE,
	`password` varchar(20) NOT NULL DEFAULT "123456",
	`email` varchar(40)
);

CREATE TABLE `order`(
	`id` varchar(15) PRIMARY KEY,
	`name` varchar(30) NOT NULL,
	`time` varchar(15) NOT NULL,
	`total` float,
	FOREIGN KEY (`name`) REFERENCES `user`(`name`) ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE `goods`(
	`isbn` varchar(10),
	`num` int,
	`price` float,
	`id` varchar(15),
	primary KEY (`isbn`, `id`),
	FOREIGN KEY (`isbn`) REFERENCES `books`(`isbn`) ON DELETE CASCADE ON UPDATE CASCADE ,
	FOREIGN KEY (`id`) REFERENCES `order`(`id`) ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE `cart`(
	`name` varchar(30),
	`isbn` varchar(15),
	primary key(`name`, `isbn`),
	FOREIGN KEY (`isbn`) REFERENCES `books`(`isbn`) ON DELETE CASCADE ON UPDATE CASCADE ,
	FOREIGN KEY (`name`) REFERENCES `user`(`name`) ON DELETE CASCADE ON UPDATE CASCADE 
);

# 插入初始数据
INSERT INTO `user` values (1, "admin", "admin", "admin@qq,com");

INSERT INTO `books` values("0000000000", "时间简史", "霍金", "第一版", "78.88", 10, 2, "/WEB-INF/static/img/book/0000000000.png");
INSERT INTO `books` values("1111111111", "Go", "Sary", "第一版", "72.98", 15, 2, "/WEB-INF/static/img/book/1111111111.png");
INSERT INTO `books` values("2222222222", "人月神话", "Bob", "第一版", "50.45", 15, 4, "/WEB-INF/static/img/book/2222222222.png");
INSERT INTO `books` values("3333333333", "Java编程思想", "Chae", "第一版", "58.88", 30, 10, "/WEB-INF/static/img/book/3333333333.png");
INSERT INTO `books` values("4444444444", "python爬虫", "Eric", "第一版", "89.87", 45, 22, "/WEB-INF/static/img/book/4444444444.png");
INSERT INTO `books` values("5555555555", "深度学习入门", "France", "第一版", "23.90", 18, 4, "/WEB-INF/static/img/book/5555555555.png");
INSERT INTO `books` values("6666666666", "JavaScript速成", "Emi", "第一版", "23.08", 30, 15, "/WEB-INF/static/img/book/6666666666.png");
INSERT INTO `books` values("7777777777", "高等数学", "Onhyr", "第一版", "45.99", 80, 40, "/WEB-INF/static/img/book/7777777777.png");

INSERT INTO `cart` values("admin", "1111111111");
INSERT INTO `cart` values("admin", "2222222222");
INSERT INTO `cart` values("admin", "3333333333");

