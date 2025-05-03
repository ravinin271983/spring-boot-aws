This is a simple microservice project to demonstrate basic spring boot features e.g. Rest, Jpa, Exception Handling. JUnit test cases included, jacoco plugin for code coverage.
Using MYSQL as database, use below sql script to create table.

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `age` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

To run this project:
1. Import project into IDE (eclipse, IntelliJ etc)
2. update below properties in application.properties
spring.datasource.url=jdbc:mysql://${AWS_MYSQL_ENDPOINT}:${AWS_MYSQL_PORT}/${AWS_MYSQL_DB}
spring.datasource.username=${AWS_MYSQL_USER}
spring.datasource.password=${AWS_MYSQL_PASS}
3. Find SpringApplicationLauncher and run as Java Application.
