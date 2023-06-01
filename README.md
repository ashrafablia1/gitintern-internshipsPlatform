# GitIntern - Internships Platform
I write every line so it needs time to be done.

GitIntern is a comprehensive tech internship platform developed using Java and Spring Boot. The platform aims to connect aspiring interns with companies offering exciting internship opportunities in the tech industry.

Please note that only two pieces of the project code are currently available:

1. Registration System:
   - Company and intern registration with email verification.
   - login system with a single page for both roles.
   - profile page for companies and interns, allowing them to view and edit their information.
   - Interns can upload, reupload, and download their resumes.
   - Etc

2. Internship Management System:
   - Companies can post internship opportunities and easily edit them.
   - Companies and interns can view all available internships.
   - Companies can receive internship applications and accept or reject them.
   - Companies can view their own posted internships in their company profile.
   - Interns can apply for internships by submitting their profile information and resume.
   - Interns can view their internship applications in their profile, including the status (accepted, rejected, or pending).
   - Etc

Technologies and Frameworks Used:
The project utilizes the following technologies and frameworks:

- Java: The code is written in Java, a widely used programming language for developing enterprise applications.
- Spring Framework: The project leverages the Spring Framework, providing a comprehensive platform for building Java applications. It includes modules such as Spring Boot, Spring Data JPA, and Spring MVC.
- Jakarta Persistence (JPA): The code uses Jakarta Persistence annotations for mapping Java objects to relational database tables and performing database operations.
- Hibernate: Hibernate is an Object-Relational Mapping (ORM) framework for Java. It is likely being used by the Spring Data JPA module to interact with the database.
- Lombok: Lombok is a Java library used to reduce boilerplate code by automatically generating common methods based on annotations.
- Spring Boot: Spring Boot simplifies the configuration and deployment of Spring-based applications. It provides defaults for various configurations, allowing developers to focus on writing business logic.
- Spring Data JPA: Spring Data JPA is a part of the Spring Data project that simplifies the implementation of JPA repositories. It provides generic CRUD operations and allows for the creation of custom queries.
- Jakarta Persistence API: Jakarta Persistence is a specification that defines an API for object-relational mapping and database access in Java EE and Jakarta EE applications.
- Etc

Design Pattern:
The project follows the Model-View-Controller (MVC) architectural pattern. The codebase is structured using the following components:

- Models: The models package contains entity classes representing the core data models of the application.
- Repositories: The repositories package provides interfaces for performing CRUD operations and querying the database.
- Services: The services package contains the business logic of the application, handling the processing and manipulation of data.
- Controllers: The controllers package handles HTTP requests and defines the application's endpoints.
- DTOs (Data Transfer Objects): The DTOs package contains classes used for transferring data between layers and external systems.
- Etc

By utilizing the MVC pattern and incorporating the repository layer, service layer, and DTOs, the codebase achieves a clear separation of concerns, making it more maintainable and scalable.

Contact:
- Email: ashraf.ablia.jo@gmail.com
- Linkedin: [Ashraf Ablia](https://www.linkedin.com/in/ashraf-ablia/)

Enjoy coding!


