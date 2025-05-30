# 🛡️ Spring Security Sample

This is a sample project built with **Spring Boot**, demonstrating common authentication methods for securing RESTful APIs:

- ✅ Basic Authentication  
- 🔐 Bearer Token using JWT (JSON Web Token)

---

## 🎯 Project Goals

- Provide a basic security foundation for RESTful applications.
- Demonstrate user authentication via `username/password`.
- Integrate JWT for securing protected endpoints.
- Apply a **stateless security model** (no session management).

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Security
- JWT (JJWT Library)
- Maven
- PostgreSQL

---

## 🔐 Key Features

- User registration and login with secure password encoding.
- JWT generation and validation after successful login.
- Token-based protection for secured APIs.
- Route-level authorization for public and private access.
- Middleware filter to validate JWT on each request.

---

## 🚀 How to Use

1. **Register** a new user via the API.
2. **Log in** to receive a JWT token.
3. **Include the token** in the `Authorization` header to access protected endpoints.

---

## 👨‍💻 Author

> Developed by [q1xuanx](https://github.com/q1xuanx) as a practical example to learn about Spring Security.

⚠️ For security reasons, the `application.properties` (or `application.yml`) file is not included in this repository.
