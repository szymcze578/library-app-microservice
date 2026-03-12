# 📚 Library Microservices Application

A microservices-based library management system designed to manage publications, users, and library operations in a scalable and modular architecture.

---

📖 Table of Contents
About The Project

- [About The Project](#about-the-project)
- [Architecture](#architecture)
- [Microservices](#microservices)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Running the Project](#running-the-project)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Future Improvements](#future-improvements)
- [Author](#author)

# 📌 About The Project

The **Library Microservices Application** is a distributed system that manages typical library operations such as:

- Managing publications
- Managing users and librarians
- Borrowing and returning books
- Searching publications

The application follows **microservice architecture principles**, where each service is responsible for a specific domain and communicates with other services through APIs.

Benefits of this architecture:

- Scalability
- Maintainability
- Independent service deployment
- Clear domain separation

---

# 🏗 Architecture

The system follows a **microservice architecture** where services communicate via REST APIs.

<p align="center">
  <img src="Diagrams/LibraryMicroservice.drawio.svg" alt="Library Microservices Architecture"/>
</p>