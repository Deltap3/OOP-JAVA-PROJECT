create database project;
create table movies
(
	movieId int primary key auto_increment not null,
    title varchar(100) not null,
    genre varchar(50) not null,
    releaseDate date not null,
    runTime int not null,
    image varchar(1000) not null
);
create table employee
(
	personId int primary key auto_increment not null,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    mail varchar(50) not null,
    login varchar(100) not null,
    passw varchar(50) not null
);
create table members
(
	personId int primary key auto_increment not null,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    mail varchar(50) not null,
    login varchar(100) not null,
    passw varchar(50) not null,
    categoryMember int not null
);
create table screening
(
	screeningID int primary key auto_increment not null,
    movieId int not null,
    foreign key (movieId) references movies(movieId),
    tim int not null,
    numberSeat int not null,
    ticketsBoughts int not null,
    discount int not null
);