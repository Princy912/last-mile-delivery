# Last Mile Delivery Route Optimisation Platform

A Spring Boot REST API for managing a Last Mile Delivery Route Optimisation Platform. The system enables customers to place delivery orders, manages delivery agents, and records Proof of Delivery (POD).

## Tech Stack

### Backend
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Web
- Maven
- Lombok

### Database
- MySQL

### API Testing
- Swagger UI

---

# Features Implemented

## User Management
- Create User
- Get All Users
- Get User by ID
- Update User
- Delete User

Supports different user roles:
- CUSTOMER
- DELIVERY_AGENT
- DISPATCHER
- ADMIN

---

## Delivery Agent Management

- Register Delivery Agent
- View Delivery Agents
- Update Delivery Agent
- Delete Delivery Agent

Each delivery agent includes:
- Vehicle Type
- Current Location
- Availability Status
- Rating

---

## Delivery Order Management

Customers can:

- Create Delivery Orders
- Assign Delivery Agents
- Update Delivery Status
- Track Orders
- Delete Orders

Order Details:
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

## Proof of Delivery (POD)

Supports:

- Photo Proof
- OTP Verification
- Digital Signature

Operations:
- Create POD Record
- View POD Records
- Update POD Record
- Delete POD Record

---

# Database Relationships

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

Relationships:

- User → DeliveryAgent (One-to-One)
- User → DeliveryOrder (One-to-Many)
- DeliveryAgent → DeliveryOrder (One-to-Many)
- DeliveryOrder → PODRecord (One-to-One)

---

# REST API Modules

- User API
- Delivery Agent API
- Delivery Order API
- POD Record API

All modules support full CRUD operations.

---

# Project Structure

```
src
 ├── controller
 ├── model
 ├── repository
 ├── service
 │      └── impl
 ├── enums
 └── BackendApplication.java
```

---

# Running the Project

Clone the repository

```bash
git clone <repository-url>
```

Navigate to the backend folder

```bash
cd backend
```

Build the project

```bash
mvn clean compile
```

Run the application

```bash
mvn spring-boot:run
```

---

# Swagger

After starting the application:

```
http://localhost:8080/swagger-ui/index.html
```

---

# Current Progress

- ✅ User CRUD
- ✅ Delivery Agent CRUD
- ✅ Delivery Order CRUD
- ✅ POD Record CRUD

---

# Upcoming Features

- DTO Layer
- Validation
- Global Exception Handling
- JWT Authentication
- Role-Based Authorization
- Pagination & Filtering
- Search APIs
- Route Optimisation
- Delivery Tracking
- Dashboard & Analytics

---

# Developed Using

- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Swagger OpenAPI
