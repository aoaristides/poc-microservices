# PoC - Microsserviços em Java com Spring Boot

<!-- TOC -->
- [PoC - Microsserviços em Java com Spring Boot](#poc---microsserviços-em-java-com-spring-boot)
- [Contexto](#contexto)
- [Arquitetura](#arquitetura)
  - [Tecnologias](#tecnologias)
<!-- TOC -->

# Contexto

Nesta PoC vamos abordar a criação de um Sistema EAD, onde vamos aplicar alguns design patterns de Microsserviços utilizando Spring Boot.

# Arquitetura

A principio vamos criar 4 Microsserviços sendo eles (**User Service**, **Course Service**, **Payment Service** e **Notification Service**), como mostrado na imagem abaixo:

![Arquitetura - Sistema EAD](./arquitetura/imagens/arquitetura.png)

## Tecnologias

- Java 21
- Spring Boot 3
  - Spring Data JPA
  - Spring Web
  - Spring Devtools
  - Spring Security
  - Spring RabbitMQ 
  - Spring Validation
  - Spring Actuator
  - Spring Cloud Config
  - Spring Cloud Netflix Eureka
  - Spring Cloud Gateway
- Loombok
- PostegreSQL 15
- MongoDB
- RabbitMQ
- Redis
- Docker Compose


