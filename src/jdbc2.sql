-- drop database jdbc;
create database jdbc;
use jdbc;

CREATE TABLE author(
                       id INT AUTO_INCREMENT NOT NULL,
                       FName VARCHAR(30),
                       LName VARCHAR(60),
                       PRIMARY KEY (id),
                       UNIQUE (FName, LName)
);

create table genre(
                      id INT AUTO_INCREMENT NOT NULL,
                      genre VARCHAR(30) UNIQUE NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE book (
                      id INT AUTO_INCREMENT NOT NULL,
                      title VARCHAR(30),
                      year VARCHAR(10),
                      author_id INT,
                      foreign key (author_id) references author(id),
                      genre_id int,
                      foreign key (genre_id) references genre(id),
                      PRIMARY KEY (id),
                      UNIQUE (title, author_id)
);