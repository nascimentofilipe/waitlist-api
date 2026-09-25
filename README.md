# Waitlist API

A REST API for managing surgical waiting lists for elective procedures.

## Context

This project is inspired by my experience working on a public healthcare system for surgical management.
Patients waiting for elective surgery are prioritized by their clinical urgency and by how long they have been on the list.
Each priority level has a maximum waiting time, so the system must make sure no patient waits longer than allowed.

## Tech Stack

- **Current**
    - Java 21
    - Spring Boot 4
    - Maven
- **Planned**
    - PostgreSQL and Flyway
    - Docker and Docker Compose
    - Apache Kafka
    - AWS (ECS, RDS)

## How to Run

### Prerequisites

- JDK 21

### Windows

```bash
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

The application starts at `http://localhost:8080`.

### Running the tests

```bash
# Windows
.\mvnw.cmd test

# Linux / macOS
./mvnw test
```

## Roadmap

### Phase 1 — Foundation

- [x] Project setup (Java 21, Spring Boot, Maven)
- [ ] Domain model (patients, procedures, priority levels, status)
- [ ] Containerization with Docker
- [ ] PostgreSQL with Flyway migrations, running via Docker Compose
- [ ] Unit tests with JUnit 5 and Mockito
- [ ] Integration tests with Testcontainers
- [ ] CI pipeline with GitHub Actions

### Phase 2 — Distributed Systems and Cloud

- [ ] Input validation, standardized errors (RFC 9457) and OpenAPI docs
- [ ] Event publishing with Apache Kafka
- [ ] Notification service consuming waitlist events
- [ ] Retries, dead letter topic and the outbox pattern
- [ ] Observability with Actuator, Micrometer and structured logs
- [ ] Deployment on AWS (ECR, ECS Fargate, RDS)

### Phase 3 — Performance and Design

- [ ] Caching with Redis, with before/after latency measurements
- [ ] Architecture diagram and documented trade-offs
- [ ] AI-powered waitlist summary with Spring AI (optional)