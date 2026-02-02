# Hospital Management System

This project is a **Hospital Management System** developed as part of a **software internship**.
The application is built using **Java, Spring Boot, Vaadin**, and **Oracle Database**.

The system provides a web-based interface for managing **patients** and **personnel**, and for
assigning patients to responsible personnel.

---

## 🚀 Technologies Used

- Java 21  
- Spring Boot 3  
- Spring Data JPA  
- Vaadin 24  
- Oracle Database  
- Maven  

---

## 📌 Features

### 👨‍⚕️ Admin Panel
- Add, list and delete personnel  
- Add, list and delete patients  
- Assign patients to personnel  

### 🧍 Patient Panel
- Query patient information using **TC Identity Number**
- View assigned personnel information  

### 🧑‍💼 Personnel Panel
- Query personnel information using **TC Identity Number**
- View assigned patients  

---

## 🗂️ Project Structure

```text
src
└── main
    └── java
        └── com
            └── example
                ├── domain
                │   ├── hasta
                │   │   ├── Hasta.java
                │   │   ├── HastaRepository.java
                │   │   └── HastaService.java
                │   └── personel
                │       ├── Personel.java
                │       ├── PersonelRepository.java
                │       └── PersonelService.java
                ├── dto
                │   ├── HastaDTO.java
                │   └── PersonelDTO.java
                ├── ui
                │   ├── HomeView.java
                │   ├── HastaView.java
                │   ├── HastaPanelView.java
                │   ├── PersonelView.java
                │   ├── PersonelPanelView.java
                │   ├── AdminHomeView.java
                │   └── AdminLoginView.java
                │   └── components
                │       └── NavigationCard.java
                └── util
                    └── TcKimlikValidator.java
```


---

## ▶️ Running the Application (Development Mode)

1️⃣ Configure the database connection in `application.properties`.

2️⃣ Start the application using Maven Wrapper:

```bash
./mvnw spring-boot:run

3️⃣ Open the application in your browser:
http://localhost:8080

🏗️ Building the Application

To build the project:
./mvnw package

👩‍💻 Author
Tuana Ay


---
> **Note:** DTO classes are included for future REST API or external client integrations.  
> Currently, the Vaadin UI layer works directly with JPA entities.

⚠️ Database credentials are not included in this repository for security reasons.  
Please create a local `application.properties` file based on `application-example.properties`.

