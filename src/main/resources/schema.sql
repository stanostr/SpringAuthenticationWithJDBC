CREATE TABLE IF NOT EXISTS Users(
	username varchar(16) PRIMARY KEY,
	password varchar(64) NOT NULL,
	enabled int(1) NOT NULL DEFAULT 1,
	firstname varchar(16),
	lastname varchar(24)
);

create table UserAuthorities (
	username varchar(16) NOT NULL,
	authority varchar(20) NOT NULL
);

alter table UserAuthorities
    add foreign key (username) references Users(username);