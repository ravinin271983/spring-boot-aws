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

Sample CURL for testing:
----------------------------------------------------------------------------------------------------------
To register a user:
curl --location 'http://localhost:8080/register' \
--header 'Content-Type: application/json' \
--data-raw '{
	"userName": "abc@test.com", 
    "firstName": "Ravindra", 
    "lastName": "Verma",
    "email": "ravin.in@gmail.com",
    "age": "42",
    "mobile": "8800372326"
}'
Find all users:
curl --location 'http://localhost:8080/users'
Find user by email:
curl --location 'http://localhost:8080/users/email/ravin.in@gmail.com'
Find user by mobile:
curl --location 'http://localhost:8080/users/mobile/8800372326'
Find user by userName:
curl --location 'http://localhost:8080/users/userName/abc@test.com'
Delete user by id:
curl --location --request DELETE 'http://localhost:8080/users/1'
----------------------------------------------------------------------------------------------------------
docker compose yml, if using docker for mysql.
version: '3.1'
services:
  db:
    image: mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 'password@123'
      MYSQL_DATABASE: 'demo'
      MYSQL_USER: 'user'
      MYSQL_PASSWORD: 'password@123'
    ports:
      - 33061:3306
