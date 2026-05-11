# 🏥 Hospital Management System

A full-featured **Hospital Management REST API** built with **Spring Boot**, secured with **JWT authentication** and **role-based access control (RBAC)**. It manages the core operational workflows of a healthcare facility — patients, doctors, appointments, departments, insurance, and more.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.0.5 |
| Security | Spring Security + JWT (JJWT 0.12.5) |
| ORM | Hibernate 7.2 / Spring Data JPA |
| Database | MySQL 8.0 |
| Connection Pool | HikariCP |
| Validation | Hibernate Validator |
| Mapping | ModelMapper |
| Build Tool | Maven |
| Server | Apache Tomcat (Embedded) |

---

## ✨ Features

- **JWT Authentication** — Sign up, sign in, sign out with stateless token-based auth
- **Role-Based Access Control** — `ROLE_ADMIN`, `ROLE_DOCTOR`, `ROLE_PATIENT`, `ROLE_USER` with fine-grained permissions
- **Patient Management** — Register, update, search, paginate, and filter patients
- **Doctor Management** — Onboard doctors, update profiles, manage appointments
- **Appointment System** — Request, update status, and cancel appointments with `AppointmentStatus` lifecycle
- **Department Management** — Create departments, assign head doctors, add/remove doctors
- **Insurance Management** — Create, assign, and remove insurance from patients
- **Admin Panel** — Full CRUD access over users, doctors, patients, appointments, departments, roles & permissions
- **OAuth2 Support** — OAuth2 client integration

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- MySQL 8.0+
- Maven 3.8+

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/hospital-management.git
   cd hospital-management
   ```

2. **Configure the database**

   Create a MySQL database:
   ```sql
   CREATE DATABASE hospitalmanagement;
   ```

   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hospitalmanagement
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   The server starts at `http://localhost:8080`

---

## 🔐 Authentication

All protected endpoints require a **Bearer JWT token** in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```

### Auth Endpoints

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/v1/auth/user/signup` | Register a new user | No |
| POST | `/api/v1/auth/user/signin` | Sign in and receive JWT | No |
| POST | `/api/v1/auth/user/signout` | Sign out | No |

#### Sign Up Request Body
```json
{
  "userName": "rohan@123",
  "password": "Pass@123",
  "emailId": "rohan.kanade@gmail.com"
}
```

#### Sign In Request Body
```json
{
  "userName": "rohan@123",
  "password": "Pass@123"
}
```

---
## 📋 API Reference

### 👤 Patient

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/patients/register` | Register a new patient | No |
| GET | `/api/v1/patients/{patientId}` | Get patient by ID | Yes |
| PUT | `/api/v1/patients/{patientId}` | Update patient profile | Yes |
| GET | `/api/v1/patients/datesbetween?startDate=&endDate=` | Get patients born between dates | No |
| GET | `/api/v1/patients/bornafter?bornAfter=` | Get patients born after a date | No |
| GET | `/api/v1/patients/getpagablepatients?pageno=&noofrecords=&sortBy=` | Get paginated patients | No |
| POST | `/api/v1/patients/{patientId}/requestappointment` | Request a doctor appointment | Yes |
| GET | `/api/v1/patients/{patientId}/appointments` | Get all appointments for a patient | Yes |
| DELETE | `/api/v1/patients/{patientId}/deleteappointment/{appointmentId}` | Cancel a patient appointment | Yes |

#### Register Patient Request Body
```json
{
  "firstName": "Rohit",
  "lastName": "Verma",
  "emailId": "rohit.verma@gmail.com",
  "dateOfBirth": "1997-12-25",
  "gender": "MALE",
  "bloodGroup": "B_POSITIVE"
}
```

#### Request Appointment Body
```json
{
  "appointmentTime": "2026-05-20T16:45:00",
  "reason": "Irregular heartbeat consultation",
  "patientId": "{{patientId}}",
  "doctorId": "{{doctorId}}"
}
```

---

### 🩺 Doctor

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| GET | `/api/v1/doctors/{doctorId}` | Get doctor by ID | Yes |
| PUT | `/api/v1/doctors/{doctorId}` | Update doctor profile | Yes |
| GET | `/api/v1/doctors/{doctorId}/appointments` | Get all appointments for a doctor | Yes |
| PUT | `/api/v1/doctors/{doctorId}/updateappointment/{appointmentId}` | Update appointment status | Yes |
| DELETE | `/api/v1/doctors/{doctorId}/deleteappointment/{appointmentId}` | Delete a doctor appointment | Yes |

#### Update Doctor Profile Body
```json
{
  "firstName": "Rohan",
  "lastName": "Kanade",
  "gender": "MALE",
  "bloodGroup": "O_POSITIVE",
  "dateOfBirth": "1995-08-15",
  "specialization": "CARDIOLOGY"
}
```

#### Update Appointment Status Body
```json
{
  "appointmentStatus": "CONFIRMED"
}
```

**Appointment Statuses:** `PENDING` · `CONFIRMED` · `CANCELLED` · `COMPLETED` · `NO_SHOW` · `RESCHEDULED`

---

### 🏢 Insurance

| Method | Endpoint | Description | Auth |
|---|---|---|---|
| POST | `/api/v1/insurance/` | Create new insurance record | Yes |
| GET | `/api/v1/insurance/` | Get all insurance records | Yes |
| POST | `/api/v1/insurance/addinsurance/{patientId}` | Assign insurance to a patient | Yes |
| DELETE | `/api/v1/insurance/removeinsurance/{patientId}` | Remove insurance from a patient | Yes |

#### Insurance Request Body
```json
{
  "policyNumber": "POL100010",
  "provider": "Oriental Insurance"
}
```

---

### 🔧 Admin

All admin endpoints require `ROLE_ADMIN` authorization.

#### Users

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/admins/users/` | Get all registered users |
| GET | `/api/v1/admins/users/{userId}` | Get user by ID |
| DELETE | `/api/v1/admins/users/{userId}` | Delete a user |
| POST | `/api/v1/admins/users/{userId}/togglestatus` | Toggle user active status |

#### Doctors

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/admins/doctors/` | Get all doctors |
| GET | `/api/v1/admins/doctors/{doctorId}` | Get doctor by ID |
| POST | `/api/v1/admins/doctors/onboard/{doctorId}` | Onboard a doctor |
| DELETE | `/api/v1/admins/doctors/{doctorId}` | Delete a doctor |

#### Patients

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/admins/patients/` | Get all patients |
| GET | `/api/v1/admins/patients/{patientId}` | Get patient by ID |
| POST | `/api/v1/admins/patients/onboard/{patientId}` | Onboard a patient |
| DELETE | `/api/v1/admins/patients/{patientId}` | Delete a patient |

#### Appointments

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/admins/appointments/` | Get all appointments |
| GET | `/api/v1/admins/appointments/{appointmentId}` | Get appointment by ID |
| DELETE | `/api/v1/admins/appointments/{appointmentId}` | Delete an appointment |

#### Departments

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/admins/departments/adddepartment` | Create a new department |
| GET | `/api/v1/admins/departments/` | Get all departments |
| POST | `/api/v1/admins/departments/assigndepartmentheaddoctor` | Assign head doctor to department |
| POST | `/api/v1/admins/departments/adddepartmentdoctors` | Add doctors to a department |
| DELETE | `/api/v1/admins/departments/removedepartmentdoctors` | Remove doctors from a department |

#### Add Department Body
```json
{
  "department": "CARDIOLOGY"
}
```

#### Assign Head Doctor Body
```json
{
  "departmentId": "{{departmentId}}",
  "doctorId": "{{doctorId}}"
}
```

#### Roles & Permissions

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/admins/rolesandpermissions/` | Get all roles and permissions |
| GET | `/api/v1/admins/rolesandpermissions/{roleId}` | Get role by ID |

---

## 🔑 Roles & Permissions

| Role | Key Permissions |
|---|---|
| `ROLE_ADMIN` | Full system access |
| `ROLE_DOCTOR` | Read/manage own profile and appointments |
| `ROLE_PATIENT` | Read own profile, create/read/cancel appointments |
| `ROLE_USER` | Basic read access |

---

## 📁 Project Structure

```
src/main/java/com/hospitalmanagement/
├── controllers/
│   ├── admincontroller/
│   ├── patientcontroller/
│   ├── doctorcontroller/
│   └── authcontroller/
├── services/
│   ├── patientservice/
│   ├── doctorservice/
│   └── adminservice/
├── entities/
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Appointment.java
│   ├── Department.java
│   ├── Insurance.java
│   ├── Role.java
│   └── Permission.java
├── enums/
│   ├── AppointmentStatus.java
│   ├── Gender.java
│   └── BloodGroup.java
├── repositories/
├── securityfilters/
│   └── JWTAuthFilter.java
├── dto/
└── HospitalmanagementApplication.java
```

---

## 📬 Postman Collection

A full Postman collection is included in the repository root: `Hospital_Management_postman_collection.json`

Import it into Postman to test all endpoints. Set the following environment variables:

| Variable | Description |
|---|---|
| `jwtToken` | JWT token received after sign in |
| `patientId` | UUID of a patient |
| `doctorId` | UUID of a doctor |
| `appointmentId` | UUID of an appointment |
| `departmentId` | UUID of a department |
| `roleId` | UUID of a role |

---

## 📄 License

This project is licensed under the MIT License.
