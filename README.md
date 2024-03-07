Description

This is a sample application built using Spring Tools Suite 4, WampServer and Gradle. It utilizes Spring Boot 3 with Spring Security 6 for JWT authentication and connects to a MySQL database. The project was initialized using the Spring Initializer.

Installation


To install the project in Eclipse STS, follow these steps:


Import the project by selecting File -> Import -> Existing Gradle Project.
Choose the extracted project directory and click Import.


Database Configuration


Create the database configuration in the application.properties file. Make sure to configure the MySQL database connection properties such as spring.datasource.url, spring.datasource.username, and spring.datasource.password.properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password


Spring Security JWT Authentication

This project uses Spring Security with JWT authentication. You can configure the security settings in the project's security configuration class. Make sure to set up JWT token generation, validation, and authentication logic according to your requirements.



Running the Application

You can run the application by executing the main application class or using the Spring Boot Maven plugin. Make sure to build the project before running it.



That's it! You should now have the project set up and ready to run in your development environment.
