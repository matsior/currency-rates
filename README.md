# Currency Rates App

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Functionalities](#Functionalities)
* [ToDo](#ToDo)
* [Setup](#setup)

## General info
This application allows you to follow the current exchange rates.
	
## Technologies
Project is created with:
* Java 17
* Jakarta EE
* Jakarta Servlet
* JSP
* Tomcat 10
* Maven 
* MySql
* JDBC
* Lombok
* json.org

## Functionalities
* downloading up-to-date exchange rates from [http://api.nbp.pl](http://api.nbp.pl)
* currency conversion
* archival currencies
* user registration
* user authorization and authentication
* saving users and "favourite currencies" in MySQL database
* password encryption using SHA-256 algorithm
* admin panel for users management

## ToDo
- [ ] switch double variables to BigDecimal
- [ ] use Hibernate
- [ ] rewrite project using Spring framework
	
## Setup
To run this project:
1. create MySQL database using database.sql file
2. overwrite database properties in config.properties and context.xml
3. build .war file with maven
```
$ mvn clean install
```
4. Deploy on Tomcat 10 server
5. You can log in using (username: test, password: test) or (username: admin, password: admin)
