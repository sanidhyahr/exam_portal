# Online Exam Portal
 
## Introduction
This document provides the Low-Level Design (LLD) for an Online Exam Portal aimed at streamlining the creation, management, and evaluation of online assessments. This design supports both Java (Spring Boot) for backend development.
 
## Module Overview
### 2.1 Admin Module
- Create and manage exams, questions, and user roles.
 
### 2.2 User Module
- User registration, login, and profile management.
 
### 2.3 Exam Management Module
- Attempt exams, view results, and provide feedback.
 
### 2.4 Question Bank Module
- Manage a repository of categorized questions.
 
### 2.5 Analytics and Reporting Module
- Generate performance reports and insights.
 
## Architecture Overview
### 3.1 Architectural Style
- Frontend: Angular or React
- Backend: REST API-based architecture
- Database: Relational Database (MySQL/PostgreSQL/SQL Server)
 
### 3.2 Component Interaction
- The frontend communicates with the backend through REST APIs for managing exams, users, and reports.
- The backend handles database operations and processes results and analytics.
 
## Module-Wise Design
### 4.1 Admin Module
**Features:**
- Create, update, delete exams and questions.
- Assign roles.

**Endpoints:**
- POST /api/admin/exams – Create exam
- PUT /api/admin/exams/{id} – Update exam
- DELETE /api/admin/exams/{id} – Delete exam
- POST /api/admin/questions – Add question
- PUT /api/admin/questions/{id} – Update question
- DELETE /api/admin/questions/{id} – Delete question
- POST /api/admin/users/assign-role – Assign role to user
 
### 4.2 User Module
**Features:**
- Register, login, and manage profile.

**Endpoints:**
- POST /api/auth/register – Register user
- POST /api/auth/login – Login user
- GET /api/users/profile – Get user profile
- PUT /api/users/profile – Update user profile
 
### 4.3 Exam Management Module
**Features:**
- Attempt exams, submit answers, view results.

**Endpoints:**
- GET /api/exams – List available exams
- GET /api/exams/{id}/questions – Get questions for an exam
- POST /api/exams/{id}/submit – Submit exam responses
- GET /api/results/{userId} – View results

 
### 4.4 Question Bank Module
**Features:**
- Manage categorized questions, import/export.

**Endpoints:**
- GET /api/questions – List all questions
- POST /api/questions/import – Import questions in bulk
- GET /api/questions/export – Export questions
 
### 4.5 Analytics and Reporting Module
**Features:**
- Generate and export performance reports.

**Endpoints:**
- GET /api/reports/user/{userId} – Get user performance report
- GET /api/reports/exam/{examId} – Get exam performance report
- GET /api/reports/export – Export reports

 
## Database Design
### 6.1 Tables and Relationships
1. Exam
  - Primary Key: ExamID
2. User
  - Primary Key: UserID
3. Question
  - Primary Key: QuestionID
4. Response
  - Primary Key: ResponseID
  - Foreign Keys: ExamID, UserID, QuestionID
5. Report
  - Primary Key: ReportID
  - Foreign Keys: ExamID, UserID
 
## User Interface Design
### 7.1 Wireframes:
- Admin Dashboard
- User Registration/Login Page
- Exam Attempt Page
- Results and Analytics Dashboard
 
 
## Project Structure
/online-exam-portal-backend
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── examportal/
│       │           ├── controller/          # REST controllers for handling HTTP requests
│       │           │   ├── AdminController.java
│       │           │   ├── UserController.java
│       │           │   ├── ExamController.java
│       │           │   └── ReportController.java
│       │           │
│       │           ├── service/             # Service interfaces for business logic
│       │           │   ├── AdminService.java
│       │           │   ├── UserService.java
│       │           │   ├── ExamService.java
│       │           │   └── ReportService.java
│       │           │
│       │           ├── service/impl/        # Implementations of service interfaces
│       │           │   ├── AdminServiceImpl.java
│       │           │   ├── UserServiceImpl.java
│       │           │   ├── ExamServiceImpl.java
│       │           │   └── ReportServiceImpl.java
│       │           │
│       │           ├── repository/          # Spring Data JPA repositories
│       │           │   ├── UserRepository.java
│       │           │   ├── ExamRepository.java
│       │           │   ├── QuestionRepository.java
│       │           │   ├── ResponseRepository.java
│       │           │   └── ReportRepository.java
│       │           │
│       │           ├── model/               # Entity classes representing database tables
│       │           │   ├── User.java
│       │           │   ├── Exam.java
│       │           │   ├── Question.java
│       │           │   ├── Response.java
│       │           │   └── Report.java
│       │           │
│       │           ├── config/              # Configuration classes (e.g., security, web)
│       │           │   ├── SecurityConfig.java
│       │           │   └── WebConfig.java
│       │           │
│       │           ├── exception/           # Custom exception handling
│       │           │   ├── GlobalExceptionHandler.java
│       │           │   └── ResourceNotFoundException.java
│       │           │
│       │           └── ExamPortalApplication.java  # Main Spring Boot application class
│       │
│       └── resources/
│           ├── application.properties       # Application configuration
│           └── data.sql                     # Optional seed data for DB initialization
│
└── pom.xml                                  # Maven build configuration
