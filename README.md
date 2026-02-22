# JaveMatch: Connect, Swipe & Match

**JaveMatch** is a specialized social platform designed exclusively for the **Pontificia Universidad Javeriana** community. It facilitates meaningful connections through an interest-based matching system, real-time interactions, and integrated video calls within a secure and moderated environment.

## Table of Contents

* [Project Purpose](https://www.google.com/search?q=%23project-purpose)
* [Architecture & Design](https://www.google.com/search?q=%23architecture--design)
* [Core Features](https://www.google.com/search?q=%23core-features)
* [Tech Stack](https://www.google.com/search?q=%23tech-stack)
* [Project Structure](https://www.google.com/search?q=%23project-structure)
* [Setup and Installation](https://www.google.com/search?q=%23setup-and-installation)
* [Development Team](https://www.google.com/search?q=%23development-team)
* [License](https://www.google.com/search?q=%23license)

---

## Project Purpose

JaveMatch addresses the challenge of student integration by providing a private, verified space for social interaction. The platform leverages a dynamic "swiping" mechanism, adding an academic community layer that ensures all users are verified students of the institution. It encourages interaction through shared hobbies, academic interests, and integrated mini-games during video calls to break the ice.

---

## Architecture & Design

The system is built as a **RESTful Web Application** using a layered architecture to ensure scalability and maintainability:

* **Web Layer (Spring MVC Controllers):** Exposes REST controllers that manage requests for users, matches, and video calls.
* **Logic Layer (Service Layer):** Encapsulates business rules, such as the matching algorithm and subscription plan validations.
* **Data Access Layer (JPA Repositories):** Manages persistence using Spring Data JPA with a dual-profile configuration for MySQL (production) and H2 (development/testing).
* **Frontend Layer:** Responsive web interface built with HTML5, CSS3, and Vanilla JavaScript, served as static resources by Spring Boot.

---

## Core Features

* **Interest-Based Matching:** An algorithm that connects users based on mutual hobbies and academic preferences.
* **Dynamic Interaction:**
* **Swipe System:** "Like" or "Reject" profiles to find potential connections.
* **Mutual Match:** A dedicated view for mutual likes ("Your Matches") where contact features are unlocked.


* **Multimedia Connectivity:** Integrated video calling system enabling face-to-face interaction.
* **Security & Moderation:**
* Student status verification (Javeriana credentials).
* Real-time user reporting and blocking system to maintain a safe environment.


* **Subscription Plans:** A tiered system (Bronze, Silver, Gold) with varying daily match limits and additional features.

---

## Tech Stack

| Category | Technologies |
| --- | --- |
| **Backend** | Java 17, Spring Boot, Spring Data JPA |
| **Frontend** | HTML5, CSS3, JavaScript (Vanilla) |
| **Database** | MySQL (Production/Docker), H2 (Testing) |
| **DevOps** | Docker, GitHub Actions (CI/CD), Azure App Service |
| **Build Tool** | Maven |

---

## Project Structure

```text
javematch/
├── .github/workflows/         # CI/CD: Automated deployment to Azure
├── docker-compose.yml         # MySQL container orchestration
├── javematch/
│   ├── src/main/java/gajudama/javematch/
│   │   ├── accesoDatos/       # JPA Repositories (Persistence)
│   │   ├── logic/             # Business Logic & Services
│   │   ├── model/             # Entities (User, Match, VideoCall, Plan)
│   │   └── web/               # REST Controllers (API Endpoints)
│   └── src/main/resources/
│       ├── static/            # User Interface (HTML, CSS, JS)
│       └── data.sql           # Initial database seeding
└── JaveMatch.pdf              # Functional requirements & methodology

```

---

## Setup and Installation

### Prerequisites

* **JDK 17** or higher.
* **Docker & Docker Compose** (for the MySQL database).
* **Maven**.

### Steps

1. **Clone the repository:**
```bash
git clone https://github.com/MapaRuiz/JaveMatch.git
cd JaveMatch

```


2. **Launch the Database:**
```bash
docker-compose up -d

```


3. **Run the Application:**
```bash
./mvnw spring-boot:run

```


4. **Access:** Navigate to `http://localhost:8080` in your web browser.

---

## Development Team (JavEngineering)

| Member | GitHub | Role |
| --- | --- | --- |
| **Daniel Castro** | [@Dani2044](https://github.com/Dani2044) | Backend & Logic |
| Maria Paula Rodríguez | [@MapaRuiz](https://github.com/MapaRuiz) | Backend & DevOps |
| Juan Enrique Rozo | [@JuanR771](https://github.com/JuanR771) | Data Modeling |
| Gabriel Anibal Riaño | [@gabrielrb23](https://github.com/gabrielrb23) | Frontend & Testing |

---

## License

This project is licensed under the **MIT License**.