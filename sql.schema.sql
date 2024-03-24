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