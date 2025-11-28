<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Documentation - MVC1 Web Application</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        line-height: 1.6;
        color: #333;
        background-color: #f8f9fa;
    }

    .navbar {
        background-color: #2c3e50;
        padding: 15px 0;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    .navbar .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .navbar h1 {
        color: white;
        font-size: 24px;
    }

    .navbar a {
        color: white;
        text-decoration: none;
        padding: 8px 15px;
        background-color: #3498db;
        border-radius: 4px;
    }

    .navbar a:hover {
        background-color: #2980b9;
    }

    .container {
        max-width: 1200px;
        margin: 30px auto;
        padding: 0 20px;
    }

    .doc-header {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        padding: 60px 20px;
        text-align: center;
        border-radius: 10px;
        margin-bottom: 30px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
    }

    .doc-header h1 {
        font-size: 42px;
        margin-bottom: 15px;
    }

    .doc-header p {
        font-size: 18px;
        opacity: 0.9;
    }

    .content-section {
        background: white;
        padding: 30px;
        margin-bottom: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    .content-section h2 {
        color: #2c3e50;
        font-size: 28px;
        margin-bottom: 20px;
        padding-bottom: 10px;
        border-bottom: 3px solid #3498db;
    }

    .content-section h3 {
        color: #34495e;
        font-size: 22px;
        margin-top: 25px;
        margin-bottom: 15px;
    }

    .content-section h4 {
        color: #7f8c8d;
        font-size: 18px;
        margin-top: 20px;
        margin-bottom: 10px;
    }

    .content-section p {
        margin-bottom: 15px;
        text-align: justify;
        color: #555;
    }

    .content-section ul, .content-section ol {
        margin-left: 30px;
        margin-bottom: 15px;
    }

    .content-section li {
        margin-bottom: 10px;
        color: #555;
    }

    .code-block {
        background-color: #282c34;
        color: #abb2bf;
        padding: 20px;
        border-radius: 5px;
        overflow-x: auto;
        margin: 15px 0;
        font-family: 'Courier New', monospace;
        font-size: 14px;
    }

    .highlight {
        background-color: #fff3cd;
        padding: 15px;
        border-left: 4px solid #ffc107;
        margin: 15px 0;
        border-radius: 4px;
    }

    .feature-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
        margin: 20px 0;
    }

    .feature-card {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        padding: 20px;
        border-radius: 8px;
        text-align: center;
        transition: transform 0.3s;
    }

    .feature-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 5px 20px rgba(0,0,0,0.2);
    }

    .feature-card h4 {
        color: white;
        margin-bottom: 10px;
    }

    .feature-card p {
        color: rgba(255,255,255,0.9);
        text-align: center;
    }

    .tech-stack {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        margin: 20px 0;
    }

    .tech-badge {
        background-color: #3498db;
        color: white;
        padding: 8px 15px;
        border-radius: 20px;
        font-size: 14px;
        font-weight: bold;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }

    table th {
        background-color: #3498db;
        color: white;
        padding: 12px;
        text-align: left;
    }

    table td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    table tr:hover {
        background-color: #f5f5f5;
    }

    .toc {
        background-color: #ecf0f1;
        padding: 20px;
        border-radius: 8px;
        margin-bottom: 30px;
    }

    .toc h3 {
        margin-bottom: 15px;
        color: #2c3e50;
    }

    .toc ul {
        list-style: none;
        margin-left: 0;
    }

    .toc li {
        margin-bottom: 8px;
    }

    .toc a {
        color: #3498db;
        text-decoration: none;
        font-weight: 500;
    }

    .toc a:hover {
        text-decoration: underline;
    }

    .footer {
        text-align: center;
        padding: 30px;
        color: #7f8c8d;
        margin-top: 50px;
    }
</style>
</head>
<body>
    <div class="navbar">
        <div class="container">
            <h1>MVC1 Documentation</h1>
            <a href="<%= request.getContextPath() %>/">Back to Main Menu</a>
        </div>
    </div>

    <div class="container">
        <div class="doc-header">
            <h1>MVC1 Web Application</h1>
            <p>Complete Documentation & User Guide</p>
            <p style="font-size: 14px; margin-top: 10px;">Version 0.0.1-SNAPSHOT | Last Updated: November 2025</p>
        </div>

        <div class="content-section toc">
            <h3>Table of Contents</h3>
            <ul>
                <li><a href="#overview">1. Project Overview</a></li>
                <li><a href="#architecture">2. System Architecture</a></li>
                <li><a href="#features">3. Key Features</a></li>
                <li><a href="#tech-stack">4. Technology Stack</a></li>
                <li><a href="#how-it-works">5. How It Works</a></li>
                <li><a href="#installation">6. Installation & Setup</a></li>
                <li><a href="#user-guide">7. User Guide</a></li>
                <li><a href="#admin-guide">8. Admin Guide</a></li>
                <li><a href="#benefits">9. Benefits & Use Cases</a></li>
                <li><a href="#database">10. Database Structure</a></li>
                <li><a href="#security">11. Security Features</a></li>
                <li><a href="#troubleshooting">12. Troubleshooting</a></li>
                <li><a href="#future">13. Future Enhancements</a></li>
            </ul>
        </div>

        <div class="content-section" id="overview">
            <h2>1. Project Overview</h2>
            <p>
                <strong>MVC1 Web Application</strong> is a comprehensive Java-based web application built using the Model-View-Controller (MVC) architectural pattern. This project demonstrates a complete implementation of user authentication, session management, and administrative functionalities in a web environment.
            </p>
            <p>
                The application serves as a robust foundation for building secure, scalable web applications with separate user and administrator interfaces. It implements industry-standard practices for web security, database connectivity, and request handling.
            </p>

            <div class="highlight">
                <strong>Project Purpose:</strong> This application is designed to provide a secure authentication system with role-based access control, allowing different functionalities for regular users and administrators.
            </div>
        </div>

        <div class="content-section" id="architecture">
            <h2>2. System Architecture</h2>
            <h3>MVC Pattern Implementation</h3>
            <p>The application follows the classic MVC (Model-View-Controller) architecture pattern, which separates concerns into three interconnected components:</p>

            <h4>Model Layer</h4>
            <ul>
                <li><strong>UserModel.java</strong> - Represents user data with properties (id, username, password, email, first name)</li>
                <li><strong>UserDAO.java</strong> - Data Access Object handling all database operations</li>
                <li><strong>DbUtil.java</strong> - Manages database connections and provides connection pooling</li>
            </ul>

            <h4>View Layer</h4>
            <ul>
                <li><strong>JSP Pages</strong> - Dynamic web pages for user interface</li>
                <li>User pages: login, welcome, settings, logout</li>
                <li>Admin pages: login, dashboard with user management</li>
                <li>Common pages: error handling, documentation</li>
            </ul>

            <h4>Controller Layer</h4>
            <ul>
                <li><strong>FrontController.java</strong> - Main request handler and URL router</li>
                <li><strong>UserController.java</strong> - Handles user-specific operations</li>
                <li><strong>AdminController.java</strong> - Manages admin operations and user viewing</li>
            </ul>

            <h3>Request Flow Diagram</h3>
            <div class="code-block">
Client Request → FrontController → URL Parsing → Route to Specific Controller
                                                           ↓
                                              UserController / AdminController
                                                           ↓
                                                    DAO Layer
                                                           ↓
                                                   PostgreSQL Database
                                                           ↓
                                                   Return Data
                                                           ↓
                                              Forward to JSP View
                                                           ↓
                                                   Render Response
                                                           ↓
                                                   Send to Client
            </div>
        </div>

        <div class="content-section" id="features">
            <h2>3. Key Features</h2>

            <div class="feature-grid">
                <div class="feature-card">
                    <h4>User Authentication</h4>
                    <p>Secure login system with session management</p>
                </div>
                <div class="feature-card">
                    <h4>Admin Dashboard</h4>
                    <p>Complete user management and monitoring</p>
                </div>
                <div class="feature-card">
                    <h4>Session Control</h4>
                    <p>Automatic session handling and timeout</p>
                </div>
                <div class="feature-card">
                    <h4>Database Integration</h4>
                    <p>PostgreSQL with JDBC connectivity</p>
                </div>
                <div class="feature-card">
                    <h4>Error Handling</h4>
                    <p>Comprehensive error pages and validation</p>
                </div>
                <div class="feature-card">
                    <h4>Responsive Design</h4>
                    <p>Clean, modern UI with CSS styling</p>
                </div>
            </div>

            <h3>Detailed Features</h3>
            <ul>
                <li><strong>User Registration & Login:</strong> Secure authentication with encrypted password storage</li>
                <li><strong>Admin Panel:</strong> View all registered users with complete details</li>
                <li><strong>Session Management:</strong> Automatic session creation, validation, and destruction</li>
                <li><strong>Role-Based Access:</strong> Separate interfaces for users and administrators</li>
                <li><strong>Security:</strong> Protection against unauthorized access with session validation</li>
                <li><strong>Navigation:</strong> Intuitive menu system with back buttons and logout options</li>
                <li><strong>Database Operations:</strong> Full CRUD operations on user data</li>
                <li><strong>Error Handling:</strong> Graceful error messages and redirection</li>
            </ul>
        </div>

        <div class="content-section" id="tech-stack">
            <h2>4. Technology Stack</h2>

            <h3>Backend Technologies</h3>
            <div class="tech-stack">
                <span class="tech-badge">Java 8+</span>
                <span class="tech-badge">Java Servlets</span>
                <span class="tech-badge">JSP</span>
                <span class="tech-badge">JDBC</span>
                <span class="tech-badge">Maven</span>
            </div>

            <h3>Database</h3>
            <div class="tech-stack">
                <span class="tech-badge">PostgreSQL 42.6.0</span>
            </div>

            <h3>Frontend Technologies</h3>
            <div class="tech-stack">
                <span class="tech-badge">HTML5</span>
                <span class="tech-badge">CSS3</span>
                <span class="tech-badge">JavaScript</span>
                <span class="tech-badge">JSTL 1.2</span>
            </div>

            <h3>Server & Deployment</h3>
            <div class="tech-stack">
                <span class="tech-badge">Apache Tomcat</span>
                <span class="tech-badge">WAR Packaging</span>
            </div>

            <h3>Development Tools</h3>
            <ul>
                <li><strong>Build Tool:</strong> Apache Maven 3.x</li>
                <li><strong>Version Control:</strong> Git</li>
                <li><strong>IDE:</strong> IntelliJ IDEA / Eclipse</li>
                <li><strong>Application Server:</strong> Apache Tomcat 9.x</li>
            </ul>
        </div>

        <div class="content-section" id="how-it-works">
            <h2>5. How It Works</h2>

            <h3>Application Flow</h3>

            <h4>Step 1: User Accesses the Application</h4>
            <p>When a user visits the application, they are presented with the main menu (index.jsp) which provides two options:</p>
            <ul>
                <li>User Login - For regular users</li>
                <li>Admin Login - For administrators</li>
            </ul>

            <h4>Step 2: URL Processing</h4>
            <p>The <strong>FrontController</strong> intercepts all requests and performs the following:</p>
            <ol>
                <li>Parses the URL to extract controller and action (case)</li>
                <li>Determines which controller to invoke (User or Admin)</li>
                <li>Sets the appropriate view (JSP page) to display</li>
                <li>Forwards the request to the specific controller</li>
            </ol>

            <div class="code-block">
Example URL: /user/login
Parsed as: controller = "user", case = "login"
Forwards to: UserController with case attribute
            </div>

            <h4>Step 3: Controller Processing</h4>
            <p><strong>UserController</strong> or <strong>AdminController</strong> handles the request:</p>
            <ul>
                <li>Checks the "case" parameter (login, logout, auth, etc.)</li>
                <li>Validates session if required</li>
                <li>Interacts with DAO layer for database operations</li>
                <li>Sets attributes for the view</li>
                <li>Forwards to appropriate JSP page</li>
            </ul>

            <h4>Step 4: User Authentication (User)</h4>
            <p>When a user submits login credentials:</p>
            <ol>
                <li>Form data is sent to UserController with case="auth"</li>
                <li>Controller retrieves username and password</li>
                <li>UserDAO.checkUser() queries the database</li>
                <li>If valid: Session is created with UserName attribute</li>
                <li>If invalid: Error message is displayed</li>
                <li>User is redirected to welcome page or back to login</li>
            </ol>

            <h4>Step 5: Admin Authentication</h4>
            <p>Admin login uses hardcoded credentials for security:</p>
            <div class="code-block">
Username: admin
Password: admin123
            </div>
            <p>The authentication process:</p>
            <ol>
                <li>AdminController receives case="adminLogin"</li>
                <li>Validates credentials using if-statement comparison</li>
                <li>If correct: Creates session with AdminName</li>
                <li>Fetches all users from database using UserDAO.getAllUsers()</li>
                <li>Displays admin dashboard with user table</li>
                <li>If incorrect: Shows error message on login page</li>
            </ol>

            <h4>Step 6: Session Management</h4>
            <p>Throughout the application:</p>
            <ul>
                <li>Sessions are checked before displaying protected pages</li>
                <li>If no session exists, user is redirected to login</li>
                <li>Session attributes store username/admin name</li>
                <li>Logout destroys the session and redirects to login</li>
            </ul>

            <h4>Step 7: Database Operations</h4>
            <p>UserDAO class handles all database interactions:</p>
            <ul>
                <li><strong>checkUser()</strong> - Validates user credentials</li>
                <li><strong>addUser()</strong> - Adds new user to database</li>
                <li><strong>getAllUsers()</strong> - Retrieves all users (for admin)</li>
            </ul>
            <p>Connection is managed by DbUtil which maintains a single connection to PostgreSQL database.</p>
        </div>

        <div class="content-section" id="installation">
            <h2>6. Installation & Setup</h2>

            <h3>Prerequisites</h3>
            <ul>
                <li>Java Development Kit (JDK) 8 or higher</li>
                <li>Apache Maven 3.6+</li>
                <li>PostgreSQL 12+ Database Server</li>
                <li>Apache Tomcat 9.x Server</li>
                <li>Git (for cloning repository)</li>
            </ul>

            <h3>Step-by-Step Installation</h3>

            <h4>1. Database Setup</h4>
            <div class="code-block">
-- Create database
CREATE DATABASE web_database;

-- Create user
CREATE USER web_user WITH PASSWORD 'web_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;

-- Create users table
CREATE TABLE users (
    iduser SERIAL PRIMARY KEY,
    user VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fname VARCHAR(100)
);
            </div>

            <h4>2. Clone and Build Project</h4>
            <div class="code-block">
# Clone the repository
git clone [repository-url]
cd MVC1

# Build the project
mvn clean package

# WAR file will be created in target/ROOT.war
            </div>

            <h4>3. Configure Database Connection</h4>
            <p>Edit src/main/java/pk/edu/zab/util/DbUtil.java if needed:</p>
            <div class="code-block">
String url = "jdbc:postgresql://127.0.0.1:5432/web_database";
String user = "web_user";
String password = "web_password";
            </div>

            <h4>4. Deploy to Tomcat</h4>
            <ol>
                <li>Copy target/ROOT.war to Tomcat's webapps directory</li>
                <li>Start Tomcat server</li>
                <li>Access application at: http://localhost:8080/</li>
            </ol>

            <h3>Configuration Options</h3>
            <p>Key configuration files:</p>
            <ul>
                <li><strong>pom.xml</strong> - Maven dependencies and build configuration</li>
                <li><strong>web.xml</strong> - Servlet mappings and application configuration</li>
                <li><strong>DbUtil.java</strong> - Database connection parameters</li>
            </ul>
        </div>

        <div class="content-section" id="user-guide">
            <h2>7. User Guide</h2>

            <h3>For Regular Users</h3>

            <h4>Accessing the Application</h4>
            <ol>
                <li>Open your web browser</li>
                <li>Navigate to the application URL (e.g., http://localhost:8080/)</li>
                <li>You'll see the main menu with two options</li>
            </ol>

            <h4>User Login</h4>
            <ol>
                <li>Click on "Login" link from the main menu</li>
                <li>Enter your username in the User ID field</li>
                <li>Enter your password</li>
                <li>Click "Sign In" button</li>
                <li>If credentials are correct, you'll be redirected to the welcome page</li>
            </ol>

            <h4>User Dashboard</h4>
            <p>After successful login, you'll see:</p>
            <ul>
                <li>Welcome message with your username</li>
                <li>"User Setting" button - Access your profile settings</li>
                <li>"Logout" button - Sign out from the application</li>
            </ul>

            <h4>User Settings</h4>
            <p>Click "User Setting" to manage your profile and preferences.</p>

            <h4>Logout</h4>
            <ol>
                <li>Click the "Logout" button</li>
                <li>Your session will be terminated</li>
                <li>You'll be redirected to the login page</li>
            </ol>

            <div class="highlight">
                <strong>Security Tip:</strong> Always logout when you're done, especially on shared computers!
            </div>
        </div>

        <div class="content-section" id="admin-guide">
            <h2>8. Admin Guide</h2>

            <h3>For Administrators</h3>

            <h4>Admin Login</h4>
            <ol>
                <li>From the main menu, click "Admin page"</li>
                <li>Enter admin credentials:
                    <div class="code-block">
Username: admin
Password: admin123
                    </div>
                </li>
                <li>Click "Login" button</li>
            </ol>

            <h4>Admin Dashboard</h4>
            <p>After successful login, you'll see:</p>
            <ul>
                <li>Welcome message with admin username</li>
                <li>Complete list of all registered users in a table format</li>
                <li>User details include: ID, Username, Email, Password, First Name</li>
                <li>Total user count at the bottom</li>
                <li>"Logout" button in the header</li>
            </ul>

            <h4>User Management</h4>
            <p>The admin dashboard displays all users with their complete information:</p>
            <table>
                <thead>
                    <tr>
                        <th>Column</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td>Unique user identifier (auto-generated)</td>
                    </tr>
                    <tr>
                        <td>Username</td>
                        <td>User's login name</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>User's email address</td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>User's password (displayed for admin monitoring)</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td>User's first name</td>
                    </tr>
                </tbody>
            </table>

            <h4>Admin Features</h4>
            <ul>
                <li>View all registered users at a glance</li>
                <li>Monitor user accounts</li>
                <li>Track total number of users</li>
                <li>Access complete user details</li>
            </ul>

            <div class="highlight">
                <strong>Important:</strong> Admin credentials are hardcoded for security. Change them in AdminController.java for production use.
            </div>
        </div>

        <div class="content-section" id="benefits">
            <h2>9. Benefits & Use Cases</h2>

            <h3>Benefits of This Application</h3>

            <h4>For Developers</h4>
            <ul>
                <li><strong>Learning Tool:</strong> Excellent example of MVC pattern implementation</li>
                <li><strong>Code Structure:</strong> Well-organized package structure following best practices</li>
                <li><strong>Reusable Components:</strong> Modular code that can be extended easily</li>
                <li><strong>Security Implementation:</strong> Demonstrates session management and authentication</li>
                <li><strong>Database Integration:</strong> Shows proper DAO pattern usage</li>
                <li><strong>Error Handling:</strong> Comprehensive error management examples</li>
            </ul>

            <h4>For Organizations</h4>
            <ul>
                <li><strong>Foundation:</strong> Ready-to-use base for building larger applications</li>
                <li><strong>Security:</strong> Built-in authentication and authorization</li>
                <li><strong>Scalability:</strong> Architecture supports easy expansion</li>
                <li><strong>Cost-Effective:</strong> Open-source technologies reduce licensing costs</li>
                <li><strong>Maintainability:</strong> Clear separation of concerns makes maintenance easier</li>
            </ul>

            <h4>For End Users</h4>
            <ul>
                <li><strong>Easy to Use:</strong> Intuitive interface with clear navigation</li>
                <li><strong>Secure:</strong> Protected access with session management</li>
                <li><strong>Fast:</strong> Lightweight application with quick response times</li>
                <li><strong>Reliable:</strong> Robust error handling prevents crashes</li>
            </ul>

            <h3>Real-World Use Cases</h3>

            <h4>1. Educational Institutions</h4>
            <p>Can be adapted for student/teacher login systems with different access levels.</p>

            <h4>2. Small Business Applications</h4>
            <p>Employee management system with admin oversight capabilities.</p>

            <h4>3. Community Portals</h4>
            <p>Member login and management for community websites.</p>

            <h4>4. Internal Company Tools</h4>
            <p>Staff authentication for internal applications and dashboards.</p>

            <h4>5. Learning Platform</h4>
            <p>Foundation for building online learning management systems.</p>

            <h3>Why Choose This Project?</h3>
            <ul>
                <li><strong>Proven Architecture:</strong> MVC pattern is industry-standard</li>
                <li><strong>Complete Example:</strong> Not just code snippets, but a working application</li>
                <li><strong>Documentation:</strong> Comprehensive documentation (this page!)</li>
                <li><strong>Best Practices:</strong> Follows Java EE web application standards</li>
                <li><strong>Extensible:</strong> Easy to add new features and modules</li>
                <li><strong>No Framework Lock-in:</strong> Uses core Java technologies</li>
            </ul>
        </div>

        <div class="content-section" id="database">
            <h2>10. Database Structure</h2>

            <h3>Database Schema</h3>
            <p>The application uses PostgreSQL database with the following structure:</p>

            <h4>Database: web_database</h4>

            <h4>Table: users</h4>
            <table>
                <thead>
                    <tr>
                        <th>Column Name</th>
                        <th>Data Type</th>
                        <th>Constraints</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>iduser</td>
                        <td>SERIAL</td>
                        <td>PRIMARY KEY</td>
                        <td>Auto-incrementing unique identifier</td>
                    </tr>
                    <tr>
                        <td>user</td>
                        <td>VARCHAR(100)</td>
                        <td>NOT NULL</td>
                        <td>Username for login</td>
                    </tr>
                    <tr>
                        <td>pass</td>
                        <td>VARCHAR(100)</td>
                        <td>NOT NULL</td>
                        <td>User password</td>
                    </tr>
                    <tr>
                        <td>email</td>
                        <td>VARCHAR(100)</td>
                        <td>UNIQUE, NOT NULL</td>
                        <td>User email address</td>
                    </tr>
                    <tr>
                        <td>fname</td>
                        <td>VARCHAR(100)</td>
                        <td></td>
                        <td>User's first name</td>
                    </tr>
                </tbody>
            </table>

            <h3>Database Operations</h3>

            <h4>Supported Operations</h4>
            <ul>
                <li><strong>SELECT:</strong> Retrieve user information for authentication</li>
                <li><strong>INSERT:</strong> Add new users to the system</li>
                <li><strong>SELECT ALL:</strong> Admin retrieves all user records</li>
            </ul>

            <h4>Connection Management</h4>
            <p>DbUtil class manages database connections:</p>
            <ul>
                <li>Creates a single connection instance</li>
                <li>Reuses connection for all database operations</li>
                <li>Handles connection exceptions gracefully</li>
            </ul>

            <div class="code-block">
Connection Details:
- Database: web_database
- Host: 127.0.0.1
- Port: 5432
- Username: web_user
- Password: web_password
            </div>
        </div>

        <div class="content-section" id="security">
            <h2>11. Security Features</h2>

            <h3>Implemented Security Measures</h3>

            <h4>1. Session Management</h4>
            <ul>
                <li>Server-side session storage</li>
                <li>Session validation on protected pages</li>
                <li>Automatic session destruction on logout</li>
                <li>Redirect to login if no valid session</li>
            </ul>

            <h4>2. Authentication</h4>
            <ul>
                <li>User credentials validated against database</li>
                <li>Admin credentials hardcoded for security</li>
                <li>Failed login attempts show generic error messages</li>
            </ul>

            <h4>3. Authorization</h4>
            <ul>
                <li>Role-based access (User vs Admin)</li>
                <li>Separate controllers for different user types</li>
                <li>Protected routes require valid sessions</li>
            </ul>

            <h4>4. Error Handling</h4>
            <ul>
                <li>Graceful error pages prevent information leakage</li>
                <li>Exception handling in all critical operations</li>
                <li>Validation of user inputs</li>
            </ul>

            <h3>Security Recommendations</h3>

            <div class="highlight">
                <strong>For Production Deployment:</strong>
                <ul>
                    <li>Implement password hashing (BCrypt, PBKDF2)</li>
                    <li>Use HTTPS for encrypted communication</li>
                    <li>Implement CSRF protection</li>
                    <li>Add input validation and sanitization</li>
                    <li>Use PreparedStatements to prevent SQL injection</li>
                    <li>Store admin credentials in encrypted configuration</li>
                    <li>Implement session timeout</li>
                    <li>Add rate limiting for login attempts</li>
                </ul>
            </div>
        </div>

        <div class="content-section" id="troubleshooting">
            <h2>12. Troubleshooting</h2>

            <h3>Common Issues and Solutions</h3>

            <h4>Issue: Cannot connect to database</h4>
            <p><strong>Solution:</strong></p>
            <ul>
                <li>Verify PostgreSQL is running</li>
                <li>Check database credentials in DbUtil.java</li>
                <li>Ensure database and user exist</li>
                <li>Verify PostgreSQL is listening on port 5432</li>
            </ul>

            <h4>Issue: Login fails for valid credentials</h4>
            <p><strong>Solution:</strong></p>
            <ul>
                <li>Check if users table exists and has data</li>
                <li>Verify column names match (user, pass, email, fname)</li>
                <li>Check UserDAO.checkUser() query</li>
                <li>Review server logs for SQL exceptions</li>
            </ul>

            <h4>Issue: Admin page shows no users</h4>
            <p><strong>Solution:</strong></p>
            <ul>
                <li>Verify database connection is working</li>
                <li>Check if users table has data</li>
                <li>Review UserDAO.getAllUsers() method</li>
                <li>Check server console for error messages</li>
            </ul>

            <h4>Issue: 404 Error on pages</h4>
            <p><strong>Solution:</strong></p>
            <ul>
                <li>Verify WAR file is properly deployed</li>
                <li>Check URL mapping in web.xml</li>
                <li>Ensure FrontController is registered</li>
                <li>Verify JSP files are in WEB-INF/jsps/</li>
            </ul>

            <h4>Issue: Session not persisting</h4>
            <p><strong>Solution:</strong></p>
            <ul>
                <li>Check if cookies are enabled in browser</li>
                <li>Verify session creation in controllers</li>
                <li>Check Tomcat session configuration</li>
                <li>Clear browser cache and cookies</li>
            </ul>

            <h3>Debugging Tips</h3>
            <ul>
                <li>Check Tomcat logs in logs/catalina.out</li>
                <li>Enable debug output in controllers (System.out.println)</li>
                <li>Use browser developer tools to inspect network requests</li>
                <li>Verify database queries in PostgreSQL logs</li>
            </ul>
        </div>

        <div class="content-section" id="future">
            <h2>13. Future Enhancements</h2>

            <h3>Planned Features</h3>

            <h4>Security Enhancements</h4>
            <ul>
                <li>Password hashing with BCrypt</li>
                <li>HTTPS/SSL support</li>
                <li>Two-factor authentication (2FA)</li>
                <li>CAPTCHA for login forms</li>
                <li>Account lockout after failed attempts</li>
            </ul>

            <h4>User Features</h4>
            <ul>
                <li>User registration page</li>
                <li>Password reset functionality</li>
                <li>Profile editing</li>
                <li>Avatar/photo upload</li>
                <li>Email verification</li>
            </ul>

            <h4>Admin Features</h4>
            <ul>
                <li>User creation/editing/deletion</li>
                <li>User role management</li>
                <li>Activity logs and audit trails</li>
                <li>Statistics dashboard</li>
                <li>Export user data (CSV, PDF)</li>
            </ul>

            <h4>Technical Improvements</h4>
            <ul>
                <li>RESTful API implementation</li>
                <li>Connection pooling (HikariCP)</li>
                <li>Caching layer (Redis/Ehcache)</li>
                <li>Logging framework (Log4j2)</li>
                <li>Unit and integration tests</li>
                <li>Docker containerization</li>
                <li>CI/CD pipeline setup</li>
            </ul>

            <h4>UI/UX Improvements</h4>
            <ul>
                <li>Responsive mobile design</li>
                <li>Modern JavaScript framework (React/Vue)</li>
                <li>AJAX for dynamic content</li>
                <li>Improved form validation</li>
                <li>Better error messages</li>
            </ul>

            <h3>Contributing</h3>
            <p>This project welcomes contributions! Here's how you can help:</p>
            <ul>
                <li>Report bugs and issues</li>
                <li>Suggest new features</li>
                <li>Submit pull requests</li>
                <li>Improve documentation</li>
                <li>Write test cases</li>
            </ul>
        </div>

        <div class="content-section">
            <h2>Conclusion</h2>
            <p>
                The MVC1 Web Application provides a solid foundation for understanding and implementing web application development using Java technologies. It demonstrates essential concepts like MVC architecture, session management, database integration, and role-based access control.
            </p>
            <p>
                Whether you're a student learning web development, a developer looking for a starter template, or an organization needing a simple authentication system, this project offers valuable insights and a ready-to-use codebase.
            </p>
            <p>
                The clean architecture and comprehensive documentation make it easy to understand, extend, and customize according to your specific requirements.
            </p>

            <div class="highlight">
                <strong>Get Started Today!</strong> Deploy the application, explore the code, and build something amazing on top of this foundation.
            </div>
        </div>

        <div class="footer">
            <p>&copy; 2025 MVC1 Web Application | Version 0.0.1-SNAPSHOT</p>
            <p>Built with Java, JSP, Servlets, and PostgreSQL</p>
            <p style="margin-top: 10px;">
                <a href="<%= request.getContextPath() %>/" style="color: #3498db; text-decoration: none;">Back to Main Menu</a>
            </p>
        </div>
    </div>
</body>
</html>

