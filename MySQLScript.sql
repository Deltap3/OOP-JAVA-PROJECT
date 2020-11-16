create database project;
use project;
create table movies
(
	movieId int primary key auto_increment,
    title varchar(100) not null,
    director varchar(50) not null,
    genre varchar(50) not null,
    releaseDate date not null,
    runTime int not null,
    image varchar(1000) not null
);
create table employee
(
	personId int primary key auto_increment,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    login varchar(100) not null,
    passw varchar(50) not null
);
create table members
(
	personId int primary key auto_increment,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    login varchar(100) not null,
    passw varchar(50) not null,
    categoryMember varchar(50) not null
);
create table screening
(
	screeningID int primary key auto_increment,
    movieId int not null,
    foreign key (movieId) references movies(movieId),
    datetim datetime not null,
    numberSeat int not null,
    ticketsBoughts int not null,
    discount int not null
);