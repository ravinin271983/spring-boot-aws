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

truncate table users;
insert into users values (11111, 30, 'user1-email@junit.test', 'JUnit1', 'Test', '1111111111', 'user-name1@junit.test');
insert into users values (11112, 31, 'user2-email@junit.test', 'JUnit2', 'Test', '2222222222', 'user-name2@junit.test');
insert into users values (11113, 32, 'user3-email@junit.test', 'JUnit3', 'Test', '3333333333', 'user-name3@junit.test');
insert into users values (11114, 34, 'user4-email@junit.test', 'JUnit4', 'Test', '4444444444', 'user-name4@junit.test');
insert into users values (11115, 35, 'user5-email@junit.test', 'JUnit5', 'Test', '5555555555', 'user-name5@junit.test');
insert into users values (11116, 36, 'user6-email@junit.test', 'JUnit6', 'Test', '6666666666', 'user-name6@junit.test');