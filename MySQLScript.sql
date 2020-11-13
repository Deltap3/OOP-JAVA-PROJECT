create database project;
create table movies
(
	movieId int primary key not null,
    title varchar(100) not null,
    genre varchar(50) not null,
    releaseDate date not null,
    runTime int not null
);
create table person
(
	personId int primary key not null,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    mail varchar(50) not null,
    login int not null,
    passw varchar(50) not null,
    categoryMember int not null
);
create table screeningRoom
(
	roomID int primary key not null,
    movieId int,
    foreign key (movieId) references movies(movieId),
    tim int,
    numberSeat int,
    ticketsBoughts int
);