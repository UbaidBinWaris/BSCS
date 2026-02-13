# Spring Boot Login Application

This is a Spring Boot application with database connection, user authentication, and Thymeleaf templates.

## Features

- ✅ **Database Connection**: H2 in-memory database configured and ready
- ✅ **Auto Table Creation**: Database tables are created automatically on startup
- ✅ **Sample Data**: Random users are inserted automatically if the database is empty
- ✅ **User Authentication**: Login functionality with username/password validation
- ✅ **Thymeleaf Templates**: Modern, responsive HTML pages using Thymeleaf
- ✅ **Spring Security**: Security configuration with custom login handling
- ✅ **Session Management**: User sessions are managed properly
- ✅ **MVC Architecture**: Proper separation with Controllers, Services, and Repositories

## Project Structure

```
src/main/java/com/example/finallearing/
├── config/
│   ├── DatabaseInitializer.java    - Initializes database with sample users
│   └── SecurityConfig.java         - Spring Security configuration
├── controller/
│   ├── FrontController.java        - Handles page navigation
│   └── UserController.java         - Handles user login/logout
├── entity/
│   └── User.java                   - User entity/model
├── repository/
│   └── UserRepository.java         - Database repository for users
├── service/
│   └── UserService.java            - Business logic for user operations
└── FinalLearingApplication.java    - Main application class

src/main/resources/
├── templates/
│   ├── login.html                  - Login page (Thymeleaf)
│   ├── dashboard.html              - Dashboard page (Thymeleaf)
│   └── home.html                   - Home page (Thymeleaf)
└── application.properties          - Application configuration
```

## Technologies Used

- **Spring Boot 4.0.1** - Main framework
- **Spring Data JPA** - Database access
- **Spring Security** - Authentication and security
- **Thymeleaf** - Template engine for HTML views
- **H2 Database** - In-memory database
- **Lombok** - Reduce boilerplate code
- **Gradle** - Build tool

## Database Configuration

The application uses an **H2 in-memory database** configured in `application.properties`:

- **Database URL**: `jdbc:h2:mem:webdb`
- **Username**: `sa`
- **Password**: (empty)
- **H2 Console**: Enabled at `/h2-console`

### Sample Users

The application automatically creates the following users on startup:

| Username | Password    | Email              | Full Name       | Role  |
|----------|-------------|-------------------|-----------------|-------|
| admin    | admin123    | admin@example.com | Admin User      | ADMIN |
| john     | password123 | john@example.com  | John Doe        | USER  |
| jane     | password123 | jane@example.com  | Jane Smith      | USER  |
| bob      | password123 | bob@example.com   | Bob Johnson     | USER  |
| alice    | password123 | alice@example.com | Alice Williams  | USER  |

## How to Run

### 1. Build the project:
```bash
.\gradlew clean build
```

### 2. Run the application:
```bash
.\gradlew bootRun
```

### 3. Access the application:
Open your browser and navigate to:
- **Login Page**: http://localhost:8080/
- **H2 Console**: http://localhost:8080/h2-console

## Application Flow

1. **Startup**: 
   - Application starts
   - Database tables are created automatically
   - Sample users are inserted (if database is empty)

2. **Login**:
   - User navigates to http://localhost:8080/
   - Redirected to login page
   - Enter username and password
   - Credentials are validated through `UserController`
   - If valid, user is redirected to dashboard
   - If invalid, error message is displayed

3. **Dashboard**:
   - Shows user information from session
   - Displays welcome message
   - Provides logout functionality

4. **Logout**:
   - User clicks logout
   - Session is invalidated
   - Redirected back to login page

## API Endpoints

### Frontend Routes (FrontController)
- `GET /` - Redirects to login page
- `GET /login` - Displays login page
- `GET /dashboard` - Displays dashboard (requires authentication)
- `GET /home` - Displays home page

### User Routes (UserController)
- `POST /user/login` - Handles user login
- `POST /user/logout` - Handles user logout

## Security Configuration

- CSRF is disabled for simplicity
- Custom form login is disabled
- Public access to: `/`, `/login`, `/user/login`, static resources, and H2 console
- All other routes require authentication
- Passwords are encrypted using BCrypt

## Thymeleaf Features

The HTML templates use Thymeleaf for:
- Dynamic content rendering
- Session attribute access
- Conditional rendering
- Form handling
- Flash messages (success/error)

## Testing the Application

1. Open http://localhost:8080/
2. You'll be redirected to the login page
3. Try logging in with:
   - **Username**: `admin`
   - **Password**: `admin123`
4. Upon successful login, you'll see the dashboard with your user information
5. Click logout to return to the login page

## Build Status

✅ **BUILD SUCCESSFUL** - All compilation errors have been fixed!

## Notes

- The database is in-memory, so data is lost when the application stops
- To use PostgreSQL instead, uncomment the PostgreSQL configuration in `application.properties` and ensure PostgreSQL is running
- The application uses Spring Security but with custom form handling for educational purposes

