SPRING-TIME_TRACKER

## Documentation
### This project is about employee accounting when he came in, when he left with registration on microservices


This payment project in microservices:

### 1.First module API_GATEWAY:
Spring Boot is an open-source Java-based framework used to create stand-alone, production-grade web applications and services. ...
An API gateway is a component that sits between your backend services and your API clients.

### 2.Second module is discovery-server:
Eureka Server is an application that holds the information about all client-service applications. 
Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address. Eureka Server is also known as Discovery Server.


### 3.Third module user-service:

```bash
Add configuration in src/main/resources/application.properties
server.port=8000
server.servlet.context-path=/api/v1/

eureka.client.service-url.default-zone=your_path_to_eureka

spring.cloud.compatibility-verifier.enabled=false

spring.application.name=user-service
spring.zipkin.base-url=your_path_to_zipkin
spring.sleuth.sampler.probability=1.0

spring.datasource.url=your_path_to_db
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.cloud.config.enabled=false

management.health.circuitbreakers.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always

#Resilinece4j Properties
resilience4j.circuitbreaker.instances.inventory.registerHealthIndicator=true
resilience4j.circuitbreaker.instances.inventory.event-consumer-buffer-size=10
resilience4j.circuitbreaker.instances.inventory.slidingWindowType=COUNT_BASED
resilience4j.circuitbreaker.instances.inventory.slidingWindowSize=5
resilience4j.circuitbreaker.instances.inventory.failureRateThreshold=50
resilience4j.circuitbreaker.instances.inventory.waitDurationInOpenState=5s
resilience4j.circuitbreaker.instances.inventory.permittedNumberOfCallsInHalfOpenState=3
resilience4j.circuitbreaker.instances.inventory.automaticTransitionFromOpenToHalfOpenEnabled=true

#Resilience4J Timeout Properties
resilience4j.timelimiter.instances.inventory.timeout-duration=3s

#Resilience4J Retry Properties
resilience4j.retry.instances.inventory.max-attempts=3
resilience4j.retry.instances.inventory.wait-duration=5s

```

```bash
    Endpoints:
      http://localhost:8000/api/v1/auth/register:
        Post-method: in is API register user and send to request to confirmation-token module
    
      http://localhost:8000/api/v1/auth/authenticate:
        Post-method: is this API authenticate user
    
      http://localhost:8000/api/v1/auth/parse:
        Post-method: This is API parse JWT-TOKEN
  
      http://localhost:8000/api/v1/user/{id}
        Get-method: Result: Get By Id User
        Put-method:Result: Update User  
        Delete-method: Result: Delete user  
        
      http://localhost:8000/api/v1/user/getByEmail
        Get-method: Result: Get By Email(@Param email)

      http://localhost:8000/api/v1/user/add-admin/{id}
        Put-method: Result: Update role on ADMIN and send request to organization-service
        
      http://localhost:8000/api/v1/user//add-user-for-organization/{id}
        Put-method: Result: Send dato to user-organization-service
```

### 4.Fourth module is conformation-token:

```bash
  spring application-properties:
    spring.mail.host=localhost
    spring.mail.port=your_port
    spring.mail.username=your_email
    spring.mail.password=your_password
    
    spring.datasource.url=your_path_to_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    
    spring.cloud.config.enabled=false
    
    server.port=0
    server.servlet.context-path=/api/v1/
    
    eureka.client.service-url.default-zone=your_path_to_eureka
    
    spring.cloud.compatibility-verifier.enabled=false
    
    spring.application.name=user-service
    spring.zipkin.base-url=your_path_to_zipkin
    spring.sleuth.sampler.probability=1.0
    
Endpoints:
    And update and delete parent category:
    http://localhost:8000/api/v1/token
      Post: Post-method for generation token email Param(userId, email)
      
    http://localhost:8000/api/v1/token/confirm
      Get: confirm token Param(token)
    
```


### 5.Organization-service:

```bash
spring-application.properties:
    server.port=0
    server.servlet.context-path=/api/v1/
    
    eureka.client.service-url.default-zone=http://localhost:8761/eureka
    
    spring.cloud.compatibility-verifier.enabled=false
    
    spring.application.name=organization-service
    spring.zipkin.base-url=http://localhost:9411
    spring.sleuth.sampler.probability=1.0
    
    spring.datasource.url=your_db
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    
    spring.cloud.config.enabled=false

Endpoints:
    http://localhost:8000/api/v1/organization
      CRUD-method
    http://localhost:8000/api/v1/organization/check-child/{id}
      Get-method: Check have organization child organization

    http://localhost:8000/api/v1/organization/exists-is-department
      Get-method: Check have department organization

    http://localhost:8000/api/v1/organization/update-parent-in-organization/{id}
      Put: Add parent to organization
      
    http://localhost:8000/api/v1/organization/delete-parent-in-organization/{id}
      Delete: Delete parent to child organization

    http://localhost:8000/api/v1/organization/add-organization-for-user-organization/{id}
      Put: add organization for user organization
    
```

### 6.User-organization-service:

```bash
spring-application.properties:
    spring.datasource.url=your_db
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    
    spring.cloud.config.enabled=false

    server.port=0
    server.servlet.context-path=/api/v1/
    
    eureka.client.service-url.default-zone=http://localhost:8761/eureka
    
    spring.cloud.compatibility-verifier.enabled=false
    
    spring.application.name=user-organization-service
    spring.zipkin.base-url=http://localhost:9411
    spring.sleuth.sampler.probability=1.0
    
Endpoints:
    http://localhost:8000/api/v1/work-times
      CRUD-method
      
    http://localhost:8000/api/v1/user-organization/add-organization/{id}
    Put: Add organization for user organization

    http://localhost:8000/api/v1/user-organization/add-user/{id}
    Put: Add user for user organization

    http://localhost:8000/api/v1/user-organization/add-qr-code/{id}
    Put: Add qrCode for user organization
    
    http://localhost:8000/api/v1/user-organization/save-start-time/{id}
    Put: Save startTime for user organization
    
    http://localhost:8000/api/v1/user-organization/save-end-time/{id}
    Put: Save endTime for user organization
   
```

