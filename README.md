Distributed Real-Time Quiz Application

A microservices-based real-time quiz application built using Java 21, Spring Boot, Spring Cloud, Apache Kafka, PostgreSQL, and Docker.

Features
Microservices architecture
Service discovery using Eureka
API Gateway for centralized routing
OpenFeign for inter-service communication
PostgreSQL for data persistence
Apache Kafka for asynchronous event processing
Real-time leaderboard updates
Docker and Docker Compose for containerization
Microservices
Eureka Server – Service discovery
API Gateway – Centralized API routing
User Service – User management
Question Service – Quiz question management
Quiz Service – Quiz creation, submission, and scoring
Leaderboard Service – Score and leaderboard management
Tech Stack
Java 21
Spring Boot
Spring Cloud
Spring Data JPA
PostgreSQL
Apache Kafka
Docker
Maven
Architecture
Client
   |
   v
API Gateway
   |
   +---- User Service
   |
   +---- Question Service
   |
   +---- Quiz Service
              |
              v
            Kafka
              |
              v
      Leaderboard Service
              |
              v
         PostgreSQL
Author

Sohail Shaik
