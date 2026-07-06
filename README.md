# Game Review API

A REST API that lets users create, view, and delete game reviews. Users are required to register before uploading a review, and each review is tied to an existing game in the system. The project demonstrates how data transfers from the user, through validation in the service layer, to the database.

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Data Access:** Spring Data JPA / Hibernate
- **Database:** H2 (in-memory)
- **Security:** Spring Security (BCrypt password hashing)
- **Build Tool:** Maven
- **Testing:** JUnit & Mockito
- **Utilities:** Lombok
- **API Testing:** Postman

## Architecture

The controller, service, and repository make up the application's three layers.

1. **Controller** — receives user requests as JSON and maps them to a request DTO.
2. **Service** — validates the data against the application's rules. If it detects invalid data, an exception is thrown and the user is returned an error message with a status code.
3. **Repository** — completes the request once the data is approved by the service.

Custom exceptions (`ResourceNotFoundException`, `ValidationException`, `UnauthorizedException`) are handled centrally by a `@RestControllerAdvice` class that maps each one to the correct HTTP status code (404, 400, 403). Response DTOs control exactly what data is returned, ensuring sensitive fields such as password hashes are never exposed.

## Features

- User registration and login with BCrypt-hashed passwords
- Create, view, update, and delete game reviews (full CRUD)
- Reviews are tied to both a registered user and an existing game
- Ownership checks — only the review's author can update or delete it
- Input validation (rating must be 1–5, required fields enforced)
- Centralized error handling with meaningful HTTP status codes
- Response DTOs that hide sensitive data and omit unedited timestamps

## API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Authenticate a user |

### Games
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/games` | Create a new game |
| GET | `/api/games` | Get all games |

### Reviews
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/reviews` | Create a review |
| GET | `/api/reviews` | Get all reviews |
| GET | `/api/reviews/user/{userId}` | Get all reviews by a specific user |
| PUT | `/api/reviews/{reviewId}?userId={userId}` | Update a review (owner only) |
| DELETE | `/api/reviews/{reviewId}?userId={userId}` | Delete a review (owner only) |

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Installation
1. Clone the repository:
   ```
   git clone https://github.com/saustin02/game-review-api.git
   ```
2. Navigate into the project directory:
   ```
   cd game-review-api
   ```
3. Run the application:
   ```
   ./mvnw spring-boot:run
   ```
4. The API will be available at `http://localhost:8080`

No separate database setup is required — the project uses an in-memory H2 database that initializes automatically on startup.

## Future Improvements

- Migrate from H2 to PostgreSQL so review data persists between restarts instead of resetting when the app stops.
- Replace the open `permitAll()` security config with proper authentication (e.g. JWT) so only logged-in users can create, edit, or delete reviews.
- Deploy the API to a cloud platform (e.g. Render or Railway) so it's publicly accessible via a live URL rather than only running locally.

## License

This project is licensed under the MIT License.