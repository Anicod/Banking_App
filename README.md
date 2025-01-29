# 🏦 Banking API - Spring Boot & Spring Data JPA  

## 📌 Overview  
This project is a **RESTful Banking API** built using **Spring Boot** and **Spring Data JPA**.  
It provides essential banking functionalities such as **account creation, fund transfers, deposits, and withdrawals**.  

---

## 🚀 Features  
✅ Create a new bank account  
✅ Retrieve all bank accounts  
✅ Delete an account  
✅ Deposit money into an account  
✅ Withdraw money from an account  
✅ Transfer funds between accounts  

---

## 🛠️ Tech Stack  
- **Spring Boot** - Framework for building RESTful services  
- **Spring Data JPA** - ORM for database interaction  
- **H2/MySQL/PostgreSQL** - Database options  
- **Lombok** - Simplifies Java code  
- **DTO Pattern** - For efficient data transfer  
- **Service Layer** - Contains business logic  
- **Controller Layer** - Exposes REST endpoints  

## API Endpoints
---POST	/accounts	Create a new account
GET	/accounts	Retrieve all accounts
GET	/accounts/{id}	Retrieve an account by ID
DELETE	/accounts/{id}	Delete an account
PUT	/accounts/deposit	Deposit money
PUT	/accounts/withdraw	Withdraw money
PUT	/accounts/transfer	Transfer funds


## 📂 Project Structure  
