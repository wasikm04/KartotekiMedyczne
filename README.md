# Medical Files

## 1. Description

Application created in Spring Boot that works as a REST Api. It is responsible of communicating with MongoDB database and after passing data throught services layer returns it to client side appliaction. Authorization and authentication was implemented using Spring Security. 

This app is based on MongoDb which means that in this database are stored not only medical files but also binary files in chunks and user sessions. It was achieved by using Spring Data Mongo, GridFs and Spring Session Mongo.

Client-side application that consume this api is here: https://github.com/wasikm04/medfiles

## 2. Results
Documentation in OpenApi specification:
<img src="https://github.com/wasikm04/MedicalFiles/blob/master/img/swagger.png" width="800"/>

Results of tests prepared in Postman for one of controllers:
<img src="https://github.com/wasikm04/MedicalFiles/blob/master/img/commandRunner.png" width="800"/>