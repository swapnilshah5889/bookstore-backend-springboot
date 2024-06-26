create database bookstore;
use bookstore;

create table categories (
	category_id int AUTO_INCREMENT PRIMARY KEY,
    category_name varchar(100)
);

create table authors (
	author_id int AUTO_INCREMENT PRIMARY KEY,
    author_name varchar(100)
);

create table books (
	book_id int AUTO_INCREMENT PRIMARY KEY,
	book_name varchar(255),
    author_id int,
    category_id int,
    isbn int,
    foreign key (author_id) references categories(category_id),
    foreign key (author_id) references authors(author_id)
);

INSERT INTO bookstore.authors(`author_name`) 
VALUES("J.K. Rowling");

INSERT INTO booksauthorstore.categories(`category_name`)
VALUES("Adventure");

INSERT INTO `bookstore`.`books`
(`book_name`,`author_id`,`category_id`,`isbn`)
VALUES("Harry Potter and Goblet of Fire", 1, 1, 1);

INSERT INTO bookstore.categories(`category_name`) VALUES("TEMP");

INSERT INTO `bookstore`.`books`
(`book_name`,`author_id`,`category_id`,`isbn`)
VALUES("Harry Potter TEMP", 1, 4, 1);
