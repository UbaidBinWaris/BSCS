# Quick Setup Guide

## Step 1: Set up PostgreSQL Database

### Windows Setup:

1. **Install PostgreSQL** (if not already installed):
   - Download from: https://www.postgresql.org/download/windows/
   - During installation, remember the password you set for the 'postgres' user

2. **Run the database setup script**:
   
   Option A - Using pgAdmin (GUI):
   - Open pgAdmin
   - Connect to your PostgreSQL server
   - Right-click on "Databases" and create a new database called "web_database"
   - Right-click on "Login/Group Roles" and create a new role:
     - Name: web_user
     - Password: web_password
     - Privileges: Can login
   - Right-click on "web_database" → Properties → Security
   - Grant ALL privileges to web_user

   Option B - Using psql command line:
   ```bash
   # Open Command Prompt or PowerShell
   # Navigate to PostgreSQL bin directory (usually C:\Program Files\PostgreSQL\<version>\bin)
   cd "C:\Program Files\PostgreSQL\15\bin"
   
   # Run psql and connect as postgres user
   .\psql.exe -U postgres
   
   # Enter the password you set during installation
   # Then run these commands:
   CREATE DATABASE web_database;
   CREATE USER web_user WITH PASSWORD 'web_password';
   GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;
   \c web_database
   GRANT ALL ON SCHEMA public TO web_user;
   ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO web_user;
   ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO web_user;
   \q
   ```

3. **Verify the setup**:
   ```bash
   # Test connection with the new user
   .\psql.exe -U web_user -d web_database
   # If prompted for password, enter: web_password
   # If successful, you should see the web_database prompt
   ```

## Step 2: Build and Deploy the Application

### Prerequisites:
- Java JDK 17 or higher installed
- Set JAVA_HOME environment variable
- Apache Tomcat 10+ installed

### Build the project:

Using Maven Wrapper (recommended):
```powershell
# Open PowerShell in the project directory
cd "C:\Users\PMLS\Documents\GitHub\BSCS\BSCS 6B\Web Technologies\jpa-learning"

# Build the project
.\mvnw.cmd clean package
```

### Deploy to Tomcat:

1. Copy the WAR file:
   ```powershell
   # The WAR file will be in the target directory
   copy "target\jpa-learning-1.0-SNAPSHOT.war" "C:\path\to\tomcat\webapps\"
   ```

2. Start Tomcat:
   ```powershell
   # Navigate to Tomcat bin directory
   cd "C:\path\to\tomcat\bin"
   
   # Start Tomcat
   .\startup.bat
   ```

3. Access the application:
   - Open browser: http://localhost:8080/jpa-learning-1.0-SNAPSHOT/
   - You should be redirected to the login page

## Step 3: Test the Application

### Create a new account:
1. Click "Sign up" link
2. Fill in the form:
   - Username: testuser
   - Email: test@example.com
   - Password: password123
   - Confirm Password: password123
3. Click "Sign Up"
4. You should be redirected to login

### Login:
1. Enter your username and password
2. Click "Login"
3. You should see your dashboard with user information

## Troubleshooting

### If you get "JAVA_HOME not found":
```powershell
# Set JAVA_HOME temporarily
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# Or set it permanently in System Environment Variables
```

### If you get database connection errors:
1. Verify PostgreSQL is running (check Windows Services)
2. Check if you can connect manually: `psql -U web_user -d web_database`
3. Verify the credentials in `src/main/resources/META-INF/persistence.xml`

### If the users table is not created:
- Check Tomcat logs (catalina.out or localhost logs)
- Look for Hibernate SQL statements
- The table should be created automatically on first run
- Verify the web_user has CREATE TABLE privileges

## Default Credentials

### Database:
- Database: web_database
- Username: web_user
- Password: web_password
- Host: localhost
- Port: 5432

### Application:
No default user - you need to sign up first!

## Files Overview

### Configuration Files:
- `pom.xml` - Maven dependencies and build configuration
- `src/main/resources/META-INF/persistence.xml` - JPA/Database configuration
- `database-setup.sql` - PostgreSQL database setup script

### Java Files:
- `entity/User.java` - JPA Entity (maps to 'users' table)
- `dao/UserDAO.java` - Database operations
- `util/JPAUtil.java` - JPA EntityManager factory
- `servlet/LoginServlet.java` - Handles login requests
- `servlet/SignupServlet.java` - Handles signup requests
- `servlet/LogoutServlet.java` - Handles logout

### Web Files:
- `login.jsp` - Login page
- `signup.jsp` - Registration page
- `dashboard.jsp` - User dashboard (after login)
- `index.jsp` - Entry point (redirects to login)

## Next Steps

After successfully running the application, you can:
1. Add password hashing (BCrypt)
2. Add more user fields (phone, address, etc.)
3. Implement password reset functionality
4. Add user profile editing
5. Implement role-based access control
6. Add email verification

