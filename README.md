# ⚖️ Advodraft

> **AI-Powered Legal Document Drafting Platform**

Advodraft is a full-stack web application designed to simplify the creation and management of legal documents through a modern web interface and AI-powered assistance.

The project combines a responsive frontend with a Spring Boot backend, REST APIs, and database integration to provide a foundation for automated legal document drafting.

---

## 🚀 Project Overview

Advodraft aims to make legal document preparation faster, structured, and easier to manage.

### Key Features

* 🤖 AI-assisted legal document drafting
* 📄 Legal document creation and management
* 👤 User registration and management
* 🔐 Backend REST APIs
* 🗄️ Database integration using H2
* 🔄 CRUD operations using Spring Data JPA
* 🌐 Responsive web interface
* ⚡ Spring Boot REST architecture

---

## 🖥️ Project Preview

### Sign In
<img width="1535" height="812" alt="sign-in" src="https://github.com/user-attachments/assets/91fb3aef-f05a-420c-a5d2-46def8853c3a" />

---
## 📄 Sample Generated Document

A real document generated end-to-end by Advodraft — a **Rental Agreement** between two parties, created by filling in Party A, Party B, and a short instruction on the Draft Document screen, then clicking **Generate Draft**.

https://1drv.ms/b/c/ce345e18b57cc5c7/IQAsLT08pGs9RLSHB6P_D77sAaehtPYrK1nFON1FMHbInDc?e=FuNHZC


## 🏗️ Project Architecture

```text
Advodraft
│
├── Frontend
│   └── Web Interface
│
├── Backend
│   ├── Spring Boot
│   ├── REST APIs
│   ├── Controllers
│   ├── Services
│   └── Repositories
│
└── Database
    └── H2 + Spring Data JPA
```

---

## 🛠️ Tech Stack

| Technology              | Purpose                 |
| ----------------------- | ----------------------- |
| Java 17                 | Backend development     |
| Spring Boot             | Backend framework       |
| Spring Web              | REST APIs               |
| Spring Data JPA         | Database access         |
| Hibernate               | ORM                     |
| H2 Database             | Development database    |
| Lombok                  | Reduce boilerplate code |
| HTML / CSS / JavaScript | Web interface           |
| Maven                   | Dependency management   |

---

## 📂 Project Structure

```text
advodraft-backend/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── advodraft/
│       │           ├── controller/
│       │           ├── model/
│       │           ├── repository/
│       │           └── AdvodraftApplication.java
│       │
│       └── resources/
│           ├── static/
│           └── application.yml
│
├── pom.xml
├── README.md
└── .gitignore
```

---

## ⚙️ Getting Started

### Prerequisites

Make sure you have installed:

* Java 17
* Maven
* Git

### Clone the Repository

```bash
git clone https://github.com/yashowardhan0124/Advodraft.git
```

```bash
cd Advodraft
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

## 🗄️ H2 Database

Advodraft currently uses an H2 in-memory database for development and testing.

### H2 Console

Open:

```text
http://localhost:8080/h2-console
```

Database URL:

```text
jdbc:h2:mem:advodraft_dev
```

Username:

```text
sa
```

Password:

```text
```

The database allows developers to inspect users and other application data during development.

---

## 🔌 REST API

### User API

#### Create User

```http
POST /api/users
```

Example request:

```json
{
  "email": "test@example.com",
  "fullName": "Test User"
}
```

#### Get All Users

```http
GET /api/users
```

#### Example Response

```json
[
  {
    "id": 1,
    "email": "test@example.com",
    "fullName": "Test User"
  }
]
```

---

## 🧩 Backend Concepts

This project demonstrates practical implementation of:

* REST API development
* Spring Boot architecture
* Entity mapping with JPA
* Hibernate ORM
* Repository pattern
* CRUD operations
* Dependency Injection
* Database integration
* Maven dependency management

---

## 🔄 Future Enhancements

Planned improvements include:

* [ ] User authentication
* [ ] Password hashing
* [ ] Input validation
* [ ] DTO and service layers
* [ ] PostgreSQL integration
* [ ] AI-powered document generation
* [ ] Document templates
* [ ] PDF export
* [ ] User dashboard
* [ ] Cloud deployment

---

## 📈 Development Progress

```text
Step 1  ████████████████████  Backend Setup
Step 2  ████████████████████  H2 Database + CRUD
Step 3  ███████████░░░░░░░░░  Authentication
Step 4  ░░░░░░░░░░░░░░░░░░░░  AI Integration
Step 5  ░░░░░░░░░░░░░░░░░░░░  Document Generation
Step 6  ░░░░░░░░░░░░░░░░░░░░  Deployment
```

---

## 👨‍💻 Developer

**Yashowardhan Shete**

B.Tech CSE – Artificial Intelligence & Analytics

GitHub: [@yashowardhan0124](https://github.com/yashowardhan0124)

---

## 📄 License

This project is developed for educational and portfolio purposes.

---

⭐ If you find this project interesting, consider giving the repository a star!
