# MicroSave - SHG Savings and Loan Management System

##  Project Overview

MicroSave is a web-based community finance management system developed using Java Spring Boot and MySQL.

The system is designed to help Self-Help Groups (SHGs) and community groups manage their members, savings contributions, loans, and repayments in a centralized system.

Instead of maintaining financial records manually, MicroSave provides a simple dashboard to track group-level and member-level financial information.
The system allows users to:

- Create and manage SHG groups
- Add members to groups
- Record member contributions
- Track member savings
- Request loans
- Track outstanding loans
- Make loan repayments
- View group financial summaries
- View individual member financial summaries

---

##  Objectives

The main objectives of MicroSave are:

1. To maintain SHG member information.
2. To track regular member contributions.
3. To manage loans provided to members.
4. To track loan repayments and outstanding amounts.
5. To calculate total group savings.
6. To provide a simple dashboard for monitoring group finances.

---

##  Technologies Used

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- Lombok

### Database
- MySQL

### Frontend
- HTML
- CSS
- JavaScript

---

##  Project Architecture

The project follows a layered architecture:

    Frontend
       ↓
    Controller
       ↓
    Service
       ↓
    Repository
       ↓
    Database

##  Project Structure


      microsave/
      │
      ├── src/
      │   ├── main/
      │   │   ├── java/
      │   │   │   └── com/example/microsave/
      │   │   │       │
      │   │   │       ├── controller/
      │   │   │       │   ├── GroupController.java
      │   │   │       │   ├── MemberController.java
      │   │   │       │   ├── ContributionController.java
      │   │   │       │   ├── LoanController.java
      │   │   │       │   └── RepaymentController.java
      │   │   │       │
      │   │   │       ├── service/
      │   │   │       │   ├── GroupService.java
      │   │   │       │   ├── MemberService.java
      │   │   │       │   ├── ContributionService.java
      │   │   │       │   ├── LoanService.java
      │   │   │       │   └── RepaymentService.java
      │   │   │       │
      │   │   │       ├── repository/
      │   │   │       │   ├── GroupRepository.java
      │   │   │       │   ├── MemberRepository.java
      │   │   │       │   ├── ContributionRepository.java
      │   │   │       │   ├── LoanRepository.java
      │   │   │       │   └── RepaymentRepository.java
      │   │   │       │
      │   │   │       ├── entity/
      │   │   │       │   ├── Group.java
      │   │   │       │   ├── Member.java
      │   │   │       │   ├── Contribution.java
      │   │   │       │   ├── Loan.java
      │   │   │       │   └── Repayment.java
      │   │   │       │
      │   │   │       └── dto/
      │   │   │           ├── GroupSummary.java
      │   │   │           └── MemberSummary.java
      │   │   │
      │   │   └── resources/
      │   │       ├── static/
      │   │       │   └── index.html
      │   │       └── application.properties
      │   │
      │   └── test/
      │
      ├── pom.xml
      └── README.md
