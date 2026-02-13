# JPA Learning - User Authentication Application

A Java web application demonstrating JPA/Hibernate integration with PostgreSQL for user authentication (login/signup).

## Database Configuration

The application is configured to connect to PostgreSQL with the following credentials:

- **Database Name:** `web_database`
- **Username:** `web_user`
- **Password:** `web_password`
- **Host:** `localhost`
- **Port:** `5432`

## Prerequisites

1. **Java Development Kit (JDK)** - Version 17 or higher
2. **Maven** - For building the project
3. **PostgreSQL** - Version 12 or higher
4. **Apache Tomcat** or any Jakarta EE compatible server - Version 10 or higher

## Database Setup

### Option 1: Using the SQL Script

1. Make sure PostgreSQL is installed and running
2. Open a terminal/command prompt
3. Run the database setup script as the PostgreSQL superuser:

```bash
psql -U postgres -f database-setup.sql
```

### Option 2: Manual Setup

1. Start PostgreSQL and connect as superuser:
```bash
psql -U postgres
```

2. Create the database and user:
```sql
CREATE DATABASE web_database;
CREATE USER web_user WITH PASSWORD 'web_password';
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;
\c web_database
GRANT ALL ON SCHEMA public TO web_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO web_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO web_user;
```

3. Exit psql:
```
\q
```

**Note:** The `users` table will be automatically created by Hibernate when the application first runs (thanks to `hibernate.hbm2ddl.auto=update` setting).

## Project Structure

```
src/
├── main/
│   ├── java/com/example/jpalearning/
│   │   ├── entity/
│   │   │   └── User.java              # JPA Entity for User
│   │   ├── dao/
│   │   │   └── UserDAO.java           # Data Access Object for User operations
│   │   ├── util/
│   │   │   └── JPAUtil.java           # JPA EntityManager utility
│   │   └── servlet/
│   │       ├── LoginServlet.java      # Login handler
│   │       ├── SignupServlet.java     # Signup handler
│   │       └── LogoutServlet.java     # Logout handler
│   ├── resources/
│   │   └── META-INF/
│   │       └── persistence.xml        # JPA configuration
│   └── webapp/
│       ├── login.jsp                  # Login page
│       ├── signup.jsp                 # Signup page
│       ├── dashboard.jsp              # User dashboard
│       └── index.jsp                  # Entry point (redirects to login)
```

## Building and Running

### 1. Build the project

```bash
mvn clean package
```

### 2. Deploy to Tomcat

- Copy the generated WAR file from `target/jpa-learning-1.0-SNAPSHOT.war` to your Tomcat `webapps` directory
- Start Tomcat

### 3. Access the application

Open your browser and navigate to:
```
http://localhost:8080/jpa-learning-1.0-SNAPSHOT/
```

## Features

### User Registration (Signup)
- Navigate to `/signup`
- Enter username, email, password, and confirm password
- System validates:
  - All fields are required
  - Passwords match
  - Username is unique
  - Email is unique
- Upon successful registration, redirects to login page

### User Login
- Navigate to `/login`
- Enter username and password
- System authenticates against database
- Upon successful login, creates session and redirects to dashboard

### User Dashboard
- Displays user information
- Shows: User ID, Username, Email, Account Creation Date
- Provides logout functionality

### Logout
- Navigate to `/logout`
- Invalidates session
- Redirects to login page

## Database Schema

The `users` table is automatically created with the following structure:

| Column     | Type         | Constraints                |
|------------|--------------|----------------------------|
| id         | BIGSERIAL    | PRIMARY KEY                |
| username   | VARCHAR(50)  | UNIQUE, NOT NULL          |
| password   | VARCHAR(100) | NOT NULL                  |
| email      | VARCHAR(100) | UNIQUE, NOT NULL          |
| created_at | TIMESTAMP    | Default: CURRENT_TIMESTAMP|

## Configuration Files

### persistence.xml
Located at: `src/main/resources/META-INF/persistence.xml`

Key configurations:
- Persistence unit name: `web_database`
- JDBC URL: `jdbc:postgresql://localhost:5432/web_database`
- Hibernate dialect: PostgreSQL
- Auto DDL: `update` (automatically creates/updates tables)

To modify database credentials, edit the following properties:
```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/web_database"/>
<property name="jakarta.persistence.jdbc.user" value="web_user"/>
<property name="jakarta.persistence.jdbc.password" value="web_password"/>
```

## Dependencies

- Jakarta Servlet API 6.1.0
- Jakarta Persistence API 3.1.0
- Hibernate Core 6.4.1.Final
- PostgreSQL JDBC Driver 42.7.1

## Security Notes

**Important:** This is a learning application. For production use, you should:

1. **Hash passwords** - Use BCrypt or similar instead of storing plain text
2. **Add HTTPS** - Encrypt data in transit
3. **Input validation** - Add more robust validation and sanitization
4. **SQL injection protection** - JPA already provides this, but always validate input
5. **Session management** - Implement session timeout and CSRF protection
6. **Error handling** - Don't expose sensitive error details to users

## Troubleshooting

### Connection Issues

If you get database connection errors:
1. Verify PostgreSQL is running: `pg_isready`
2. Check the database exists: `psql -U postgres -l`
3. Verify user credentials: `psql -U web_user -d web_database`
4. Check `persistence.xml` configuration

### Table Not Created

If the `users` table is not created automatically:
1. Check Hibernate logs in the server console
2. Verify `hibernate.hbm2ddl.auto=update` in `persistence.xml`
3. Ensure the user has CREATE TABLE privileges

### Port Conflicts

If port 8080 is already in use:
1. Change Tomcat's port in `conf/server.xml`
2. Or stop the process using port 8080

## License

This is a learning project for educational purposes.

