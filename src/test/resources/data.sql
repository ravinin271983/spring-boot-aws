create table if not exists users_seq(next_val integer);
truncate table users_seq;
insert into users_seq values(1);

create table if not exists users (
	id int PRIMARY KEY, 
	age int, 
	email varchar(255), 
	first_name varchar(255), 
	last_name varchar(255), 
	mobile varchar(255), 
	user_name varchar(255)); 
