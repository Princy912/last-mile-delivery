# 🚚 Last Mile Delivery Route Optimization Platform

A Spring Boot REST API for managing and optimizing last-mile delivery operations. The platform enables customers to place delivery orders, assign delivery agents, track deliveries, and maintain Proof of Delivery (POD) records.

---

# ✨ Features

## 👤 User Management
- Register User
- Login User (JWT Authentication)
- Create User
- Get All Users
- Get User by ID
- Update User
- Delete User

Supported Roles:
- CUSTOMER
- DELIVERY_AGENT
- DISPATCHER
- ADMIN

---

## 🚴 Delivery Agent Management
- Register Delivery Agent
- View Delivery Agents
- Update Delivery Agent
- Delete Delivery Agent

Agent Details:
- Vehicle Type
- Current Location
- Status
- Rating

---

## 📦 Delivery Order Management
- Create Delivery Order
- View Orders
- Update Order
- Delete Order

Order Information:
- Customer
- Delivery Agent
- Tracking Number
- Pickup Address
- Delivery Address
- Priority
- Estimated Delivery Time
- Actual Delivery Time
- Order Status

---

## 📷 Proof of Delivery (POD)
Supports:
- Photo
- OTP
- Digital Signature

Operations:
- Create POD Record
- View POD Records
- Update POD Record
- Delete POD Record

---

# 🔐 Authentication

Implemented using:

- Spring Security
- JWT (JSON Web Token)
- BCrypt Password Encoding

Authentication Flow:

```
Register
      ↓
Login
      ↓
Receive JWT Token
      ↓
Authorize in Swagger
      ↓
Access Protected APIs
```

Public Endpoints

```
/api/auth/register

/api/auth/login

/swagger-ui/**

/v3/api-docs/**
```

Protected Endpoints

```
/api/users/**

/api/delivery-agents/**

/api/delivery-orders/**

/api/pod-records/**
```

---

# 🏗️ Project Architecture

```
User
│
├── Customer
│      │
│      ▼
│   DeliveryOrder
│         │
│         ▼
│    PODRecord
│
└── DeliveryAgent
       │
       ▼
 DeliveryOrder
```

---

# 🔗 Entity Relationships

| Relationship | Type |
|--------------|------|
| User → DeliveryAgent | One-to-One |
| User → DeliveryOrder | One-to-Many |
| DeliveryAgent → DeliveryOrder | One-to-Many |
| DeliveryOrder → PODRecord | One-to-One |

---

# 🛠️ Technology Stack

### Backend
- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Spring Web
- Maven
- Lombok

### Database
- MySQL

### Authentication
- JWT
- BCrypt

### API Documentation
- Swagger OpenAPI

### Testing
- Postman
- Swagger UI

---

# 📂 Project Structure

```
src
└── main
    └── java
        └── com.example.backend
            ├── auth
            ├── config
            ├── controller
            ├── dto
            ├── entity
            ├── enums
            ├── exception
            ├── mapper
            ├── repository
            ├── security
            ├── service
            │     └── serviceImpl
            └── BackendApplication.java
```

---

# 🚀 Running the Project

Clone Repository

```bash
git clone <repository-url>
```

Navigate

```bash
cd backend
```

Compile

```bash
mvn clean compile
```

Run

```bash
mvn spring-boot:run
```

---

# 📖 Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# ✅ Completed Features

### Project Setup
- ✅ Spring Boot
- ✅ Maven
- ✅ MySQL
- ✅ Swagger

### Database
- ✅ Entity Design
- ✅ Relationships

### CRUD APIs
- ✅ User
- ✅ Delivery Agent
- ✅ Delivery Order
- ✅ POD Record

### DTO Layer
- ✅ DTO Classes
- ✅ Mapper Classes
- ✅ DTO Integration

### Validation
- ✅ Bean Validation
- ✅ @Valid
- ✅ Validation Messages

### Exception Handling
- ✅ Global Exception Handler
- ✅ Custom Exceptions

### Authentication
- ✅ Spring Security
- ✅ JWT Authentication
- ✅ User Registration
- ✅ User Login
- ✅ BCrypt Password Encoding
- ✅ Protected APIs
- ✅ Swagger Authorization

---

# 🚧 Upcoming Features

- Role-Based Authorization (ADMIN, CUSTOMER, DELIVERY_AGENT, DISPATCHER)
- Automatic Tracking Number Generation
- Order Status Workflow
- Auto Assign Delivery Agent
- Delivery Time Calculation
- Search APIs
- Pagination
- Sorting
- File Upload (POD Images)
- Dashboard & Analytics
- React Frontend

---

# 📌 API Modules

- Authentication API
- User API
- Delivery Agent API
- Delivery Order API
- POD Record API

---

# 👨‍💻 Built With

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Swagger OpenAPI

---

## ⭐ Current Progress

**Backend Completion:** ~85%

The project now includes a secure REST API with JWT authentication, CRUD operations, DTO architecture, validation, exception handling, and API documentation. The remaining work focuses on role-based authorization, business logic, advanced APIs, and the React frontend.
