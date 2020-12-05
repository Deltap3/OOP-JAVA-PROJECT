create database if not exists project ;
use project;
create table if not exists movies
(
	movieId int primary key auto_increment,
    title varchar(100) not null,
    genre varchar(50) not null,
    releaseDate date not null,
    runTime int not null,
    image varchar(1000) not null
);
create table if not exists employee
(
	personId int primary key auto_increment,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    login varchar(100) not null,
    passw varchar(50) not null
);
create table if not exists members
(
	personId int primary key auto_increment,
	firstName varchar(50) not null,
    lastName varchar(50) not null,
    mail varchar(50),
    login varchar(100),
    passw varchar(50),
    totalPaid double not null,
    categoryMember int not null
);
create table if not exists screening
(
	screeningID int primary key auto_increment,
    movieId int not null,
    foreign key (movieId) references movies(movieId),
    roomNumber int not null,
    datetim datetime not null,
    numberSeat int not null,
    ticketsBoughts int not null,
    discount int not null
);

insert into employee (firstName,lastName,login,passw) values
('Pierre','MAISTERRENA','pierre333','pie3'),
('Juliette','DANIEL','juliette26','jul2'),
('David','ZHONG','david45','dav4'),
('Jean','DUPONT','jean78','jea7'),
('Marie','DUPUIS','marie12','mar1'),
('Capucine','DUBOIS','capucine56','cap5');

insert into members (firstName,lastName,mail,login,passw,totalPaid,categoryMember) values
('Soline','GERMOND','patatedouce@gmail.com','soline789','sol7','100','23'),
('Guillauem','SAURY','nouillefroide@gmail.com','guillaume56','gui5','52','34'),
('Alexis','BOYADJAN','tablebasse@gmail.com','alexis002','alex0','92','45'),
('Alec','PAQUERON','litpose@gmail.com','alec93','ale9','70','56'),
('Valentine','LACOTTE','superfraise@gmail.com','valentine352','val3','15','12'),
('Margaux','GOUJEAU','ultraikea@gmail.com','margaux456','mar4','30','67');


insert into movies (title,genre,releaseDate,runTime,image) values
('Shrek','Animation','2001-07-04','90','shrek.jpg'),
('Pirates des Caraibes : La Malediction du Black Pearl','Action','2003-08-13','142','pirates_des_caraibes.jpg'),
('Avengers Infinity War','Super-heros','2018-04-25','149','avengers.jpg'),
('Star Wars, episode IV : Un nouvel espoir','Science-fiction','1977-07-11','121','star_wars.jpg'),
('Le Seigneur des anneaux : La Communaute de l anneau','Fantasy','2001-12-19','178','seigneur_des_anneaux.jpg'),
('Harry Potter a l ecole des sorciers','Fantastique','2001-12-05','152','harry_potter.jpg');

insert into screening (movieId,datetim,numberSeat,ticketsBoughts,discount,roomNumber) values
('1','2020-12-05 08:30:00','100','70','0','1'),
('2','2020-12-05 12:50:00','400','350','10','2'),
('3','2020-12-05 16:00:00','250','230','15','3'),
('1','2020-12-12 14:15:00','370','120','20','1'),
('4','2020-12-12 13:30:00','60','60','25','2'),
('5','2020-12-22 17:45:00','230','210','30','1');