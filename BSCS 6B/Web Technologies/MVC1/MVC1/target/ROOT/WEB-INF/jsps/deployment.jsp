<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deployment Guide - MVC1 Web Application</title>
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
        position: sticky;
        top: 0;
        z-index: 1000;
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
        margin-left: 10px;
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
        background: linear-gradient(135deg, #e74c3c 0%, #c0392b 100%);
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
        border-bottom: 3px solid #e74c3c;
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
        position: relative;
    }

    .code-block::before {
        content: 'Terminal';
        position: absolute;
        top: 5px;
        right: 10px;
        font-size: 11px;
        color: #6c757d;
    }

    .highlight {
        background-color: #fff3cd;
        padding: 15px;
        border-left: 4px solid #ffc107;
        margin: 15px 0;
        border-radius: 4px;
    }

    .warning {
        background-color: #f8d7da;
        padding: 15px;
        border-left: 4px solid #dc3545;
        margin: 15px 0;
        border-radius: 4px;
        color: #721c24;
    }

    .success {
        background-color: #d4edda;
        padding: 15px;
        border-left: 4px solid #28a745;
        margin: 15px 0;
        border-radius: 4px;
        color: #155724;
    }

    .info {
        background-color: #d1ecf1;
        padding: 15px;
        border-left: 4px solid #17a2b8;
        margin: 15px 0;
        border-radius: 4px;
        color: #0c5460;
    }

    .step-card {
        background: #f8f9fa;
        border-left: 4px solid #3498db;
        padding: 20px;
        margin: 15px 0;
        border-radius: 4px;
    }

    .step-card h4 {
        color: #2c3e50;
        margin-top: 0;
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

    table {
        width: 100%;
        border-collapse: collapse;
        margin: 20px 0;
    }

    table th {
        background-color: #e74c3c;
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

    .command-label {
        color: #27ae60;
        font-weight: bold;
        font-size: 12px;
        display: block;
        margin-bottom: 5px;
    }

    .footer {
        text-align: center;
        padding: 30px;
        color: #7f8c8d;
        margin-top: 50px;
    }

    .checklist {
        list-style: none;
        margin-left: 0;
    }

    .checklist li::before {
        content: "✓ ";
        color: #28a745;
        font-weight: bold;
        margin-right: 10px;
    }
</style>
</head>
<body>
    <div class="navbar">
        <div class="container">
            <h1>Deployment Guide</h1>
            <div>
                <a href="<%= request.getContextPath() %>/public/documentation">Documentation</a>
                <a href="<%= request.getContextPath() %>/">Main Menu</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="doc-header">
            <h1>Complete Deployment Guide</h1>
            <p>Step-by-Step Instructions for Local, Server, and AWS Deployment</p>
            <p style="font-size: 14px; margin-top: 10px;">MVC1 Web Application | Production-Ready Deployment</p>
        </div>

        <div class="content-section toc">
            <h3>Table of Contents</h3>
            <ul>
                <li><a href="#prerequisites">1. Prerequisites & Requirements</a></li>
                <li><a href="#local">2. Local Development Deployment</a></li>
                <li><a href="#build">3. Building the Application</a></li>
                <li><a href="#tomcat">4. Apache Tomcat Deployment</a></li>
                <li><a href="#linux-server">5. Linux Server Deployment</a></li>
                <li><a href="#aws">6. AWS EC2 Deployment</a></li>
                <li><a href="#aws-rds">7. AWS RDS Database Setup</a></li>
                <li><a href="#domain">8. Domain & SSL Configuration</a></li>
                <li><a href="#monitoring">9. Monitoring & Maintenance</a></li>
                <li><a href="#troubleshooting">10. Deployment Troubleshooting</a></li>
                <li><a href="#security">11. Security Best Practices</a></li>
                <li><a href="#backup">12. Backup & Recovery</a></li>
            </ul>
        </div>

        <div class="content-section" id="prerequisites">
            <h2>1. Prerequisites & Requirements</h2>

            <h3>Software Requirements</h3>
            <table>
                <thead>
                    <tr>
                        <th>Software</th>
                        <th>Minimum Version</th>
                        <th>Recommended Version</th>
                        <th>Purpose</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Java JDK</td>
                        <td>8</td>
                        <td>11 or 17 LTS</td>
                        <td>Application Runtime</td>
                    </tr>
                    <tr>
                        <td>Apache Maven</td>
                        <td>3.6.0</td>
                        <td>3.8.x</td>
                        <td>Build Tool</td>
                    </tr>
                    <tr>
                        <td>Apache Tomcat</td>
                        <td>9.0</td>
                        <td>9.0.x or 10.x</td>
                        <td>Application Server</td>
                    </tr>
                    <tr>
                        <td>PostgreSQL</td>
                        <td>12.0</td>
                        <td>14.x or 15.x</td>
                        <td>Database Server</td>
                    </tr>
                    <tr>
                        <td>Git</td>
                        <td>2.x</td>
                        <td>Latest</td>
                        <td>Version Control</td>
                    </tr>
                </tbody>
            </table>

            <h3>System Requirements</h3>
            <div class="info">
                <strong>Minimum Server Specifications:</strong>
                <ul>
                    <li><strong>CPU:</strong> 2 vCPUs</li>
                    <li><strong>RAM:</strong> 2 GB (4 GB recommended)</li>
                    <li><strong>Storage:</strong> 10 GB SSD</li>
                    <li><strong>OS:</strong> Linux (Ubuntu 20.04/22.04, CentOS 7/8, Amazon Linux 2)</li>
                    <li><strong>Network:</strong> Stable internet connection, Port 80/443/8080 open</li>
                </ul>
            </div>

            <h3>Access Requirements</h3>
            <ul class="checklist">
                <li>Root or sudo access to the server</li>
                <li>SSH key pair for secure access</li>
                <li>AWS Account (for AWS deployment)</li>
                <li>Domain name (optional, for production)</li>
                <li>SSL certificate (Let's Encrypt or commercial)</li>
            </ul>
        </div>

        <div class="content-section" id="local">
            <h2>2. Local Development Deployment</h2>

            <h3>Step 1: Install Java JDK</h3>
            <div class="step-card">
                <h4>For Ubuntu/Debian:</h4>
                <div class="code-block">
sudo apt update
sudo apt install openjdk-11-jdk -y
java -version
                </div>
            </div>

            <div class="step-card">
                <h4>For CentOS/RHEL:</h4>
                <div class="code-block">
sudo yum install java-11-openjdk-devel -y
java -version
                </div>
            </div>

            <div class="step-card">
                <h4>For Windows:</h4>
                <ol>
                    <li>Download JDK from Oracle or AdoptOpenJDK</li>
                    <li>Run the installer</li>
                    <li>Set JAVA_HOME environment variable</li>
                    <li>Add %JAVA_HOME%\bin to PATH</li>
                </ol>
            </div>

            <h3>Step 2: Install Maven</h3>
            <div class="step-card">
                <h4>For Linux:</h4>
                <div class="code-block">
# Ubuntu/Debian
sudo apt install maven -y

# CentOS/RHEL
sudo yum install maven -y

# Verify installation
mvn -version
                </div>
            </div>

            <h3>Step 3: Install PostgreSQL</h3>
            <div class="step-card">
                <h4>For Ubuntu:</h4>
                <div class="code-block">
# Install PostgreSQL
sudo apt install postgresql postgresql-contrib -y

# Start PostgreSQL service
sudo systemctl start postgresql
sudo systemctl enable postgresql

# Check status
sudo systemctl status postgresql
                </div>
            </div>

            <h3>Step 4: Setup Database</h3>
            <div class="code-block">
# Switch to postgres user
sudo -i -u postgres

# Access PostgreSQL prompt
psql

# Create database and user
CREATE DATABASE web_database;
CREATE USER web_user WITH PASSWORD 'web_password';
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;

# Exit
\q
exit
            </div>

            <h3>Step 5: Create Database Table</h3>
            <div class="code-block">
# Connect to database
psql -U web_user -d web_database

# Create users table
CREATE TABLE users (
    iduser SERIAL PRIMARY KEY,
    user VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fname VARCHAR(100)
);

# Insert sample data (optional)
INSERT INTO users (user, pass, email, fname)
VALUES ('testuser', 'test123', 'test@example.com', 'Test User');

# Verify
SELECT * FROM users;

# Exit
\q
            </div>

            <div class="warning">
                <strong>Security Warning:</strong> Change default passwords before production deployment!
            </div>

            <h3>Step 6: Install Apache Tomcat</h3>
            <div class="code-block">
# Download Tomcat 9
cd /opt
sudo wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.83/bin/apache-tomcat-9.0.83.tar.gz

# Extract
sudo tar -xzf apache-tomcat-9.0.83.tar.gz
sudo mv apache-tomcat-9.0.83 tomcat9

# Set permissions
sudo chmod +x /opt/tomcat9/bin/*.sh

# Create systemd service
sudo nano /etc/systemd/system/tomcat.service
            </div>

            <h4>Tomcat Service File Content:</h4>
            <div class="code-block">
[Unit]
Description=Apache Tomcat Web Application Container
After=network.target

[Service]
Type=forking

Environment="JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64"
Environment="CATALINA_PID=/opt/tomcat9/temp/tomcat.pid"
Environment="CATALINA_HOME=/opt/tomcat9"
Environment="CATALINA_BASE=/opt/tomcat9"

ExecStart=/opt/tomcat9/bin/startup.sh
ExecStop=/opt/tomcat9/bin/shutdown.sh

User=root
Group=root
UMask=0007
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
            </div>

            <div class="code-block">
# Reload systemd
sudo systemctl daemon-reload

# Start Tomcat
sudo systemctl start tomcat
sudo systemctl enable tomcat

# Check status
sudo systemctl status tomcat
            </div>

            <div class="success">
                ✓ Tomcat should now be running on http://localhost:8080
            </div>
        </div>

        <div class="content-section" id="build">
            <h2>3. Building the Application</h2>

            <h3>Clone the Repository</h3>
            <div class="code-block">
# Clone from Git
git clone [your-repository-url]
cd MVC1

# Or if you have the project locally
cd /path/to/MVC1/project
            </div>

            <h3>Configure Database Connection</h3>
            <div class="step-card">
                <h4>Edit DbUtil.java:</h4>
                <div class="code-block">
# Open the file
nano src/main/java/pk/edu/zab/util/DbUtil.java

# Update these lines:
String url = "jdbc:postgresql://localhost:5432/web_database";
String user = "web_user";
String password = "web_password";
                </div>
                <div class="info">
                    <strong>Note:</strong> For production, use environment variables or external configuration files instead of hardcoding credentials.
                </div>
            </div>

            <h3>Build the WAR File</h3>
            <div class="code-block">
# Clean and build
mvn clean package

# The WAR file will be created at:
# target/ROOT.war
            </div>

            <h3>Verify Build</h3>
            <div class="code-block">
# Check if WAR file exists
ls -lh target/ROOT.war

# Output should show file size (e.g., 5.2M)
            </div>

            <div class="success">
                <strong>Build Successful!</strong> You should see "BUILD SUCCESS" message.
            </div>
        </div>

        <div class="content-section" id="tomcat">
            <h2>4. Apache Tomcat Deployment</h2>

            <h3>Method 1: Manual Deployment</h3>
            <div class="step-card">
                <h4>Deploy WAR File:</h4>
                <div class="code-block">
# Copy WAR to Tomcat webapps directory
sudo cp target/ROOT.war /opt/tomcat9/webapps/

# Tomcat will automatically extract it
# Wait a few seconds for deployment

# Check deployment
ls -la /opt/tomcat9/webapps/ROOT/
                </div>
            </div>

            <h3>Method 2: Using Tomcat Manager</h3>
            <ol>
                <li>Access Tomcat Manager: http://localhost:8080/manager</li>
                <li>Configure manager credentials in tomcat-users.xml</li>
                <li>Upload ROOT.war through the web interface</li>
            </ol>

            <h4>Configure Tomcat Manager:</h4>
            <div class="code-block">
# Edit tomcat-users.xml
sudo nano /opt/tomcat9/conf/tomcat-users.xml

# Add these lines before &lt;/tomcat-users&gt;:
&lt;role rolename="manager-gui"/&gt;
&lt;role rolename="admin-gui"/&gt;
&lt;user username="admin" password="admin123" roles="manager-gui,admin-gui"/&gt;

# Restart Tomcat
sudo systemctl restart tomcat
            </div>

            <h3>Verify Deployment</h3>
            <div class="code-block">
# Check Tomcat logs
tail -f /opt/tomcat9/logs/catalina.out

# Look for deployment success messages
            </div>

            <h3>Access the Application</h3>
            <div class="success">
                <strong>Application URL:</strong> http://localhost:8080/
                <br><br>
                <strong>Test Pages:</strong>
                <ul>
                    <li>Main Menu: http://localhost:8080/</li>
                    <li>User Login: http://localhost:8080/user/login</li>
                    <li>Admin Login: http://localhost:8080/admin/login</li>
                    <li>Documentation: http://localhost:8080/public/documentation</li>
                </ul>
            </div>

            <h3>Common Tomcat Commands</h3>
            <div class="code-block">
# Start Tomcat
sudo systemctl start tomcat

# Stop Tomcat
sudo systemctl stop tomcat

# Restart Tomcat
sudo systemctl restart tomcat

# Check status
sudo systemctl status tomcat

# View logs
tail -f /opt/tomcat9/logs/catalina.out

# View access logs
tail -f /opt/tomcat9/logs/localhost_access_log.*.txt
            </div>
        </div>

        <div class="content-section" id="linux-server">
            <h2>5. Linux Server Deployment</h2>

            <h3>Server Preparation</h3>
            <div class="step-card">
                <h4>Update System:</h4>
                <div class="code-block">
# For Ubuntu/Debian
sudo apt update && sudo apt upgrade -y

# For CentOS/RHEL
sudo yum update -y

# Install essential tools
sudo apt install curl wget vim net-tools -y
                </div>
            </div>

            <h3>Configure Firewall</h3>
            <div class="code-block">
# For Ubuntu (UFW)
sudo ufw allow 22/tcp      # SSH
sudo ufw allow 80/tcp      # HTTP
sudo ufw allow 443/tcp     # HTTPS
sudo ufw allow 8080/tcp    # Tomcat
sudo ufw allow 5432/tcp    # PostgreSQL (if remote access needed)
sudo ufw enable
sudo ufw status

# For CentOS (firewalld)
sudo firewall-cmd --permanent --add-port=22/tcp
sudo firewall-cmd --permanent --add-port=80/tcp
sudo firewall-cmd --permanent --add-port=443/tcp
sudo firewall-cmd --permanent --add-port=8080/tcp
sudo firewall-cmd --reload
            </div>

            <h3>Setup Reverse Proxy with Nginx</h3>
            <div class="step-card">
                <h4>Install Nginx:</h4>
                <div class="code-block">
# Install Nginx
sudo apt install nginx -y

# Start and enable Nginx
sudo systemctl start nginx
sudo systemctl enable nginx
                </div>
            </div>

            <h4>Configure Nginx as Reverse Proxy:</h4>
            <div class="code-block">
# Create configuration file
sudo nano /etc/nginx/sites-available/mvc1

# Add this configuration:
            </div>

            <div class="code-block">
server {
    listen 80;
    server_name your-domain.com www.your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    access_log /var/log/nginx/mvc1_access.log;
    error_log /var/log/nginx/mvc1_error.log;
}
            </div>

            <div class="code-block">
# Enable the site
sudo ln -s /etc/nginx/sites-available/mvc1 /etc/nginx/sites-enabled/

# Test configuration
sudo nginx -t

# Reload Nginx
sudo systemctl reload nginx
            </div>

            <div class="success">
                ✓ Your application should now be accessible on port 80 (http://your-domain.com)
            </div>

            <h3>Configure PostgreSQL for Remote Access (if needed)</h3>
            <div class="code-block">
# Edit PostgreSQL configuration
sudo nano /etc/postgresql/14/main/postgresql.conf

# Find and update:
listen_addresses = '*'

# Edit pg_hba.conf
sudo nano /etc/postgresql/14/main/pg_hba.conf

# Add this line:
host    all             all             0.0.0.0/0               md5

# Restart PostgreSQL
sudo systemctl restart postgresql
            </div>

            <div class="warning">
                <strong>Security Warning:</strong> Only allow remote database access if absolutely necessary. Use firewall rules to restrict access to specific IP addresses.
            </div>
        </div>

        <div class="content-section" id="aws">
            <h2>6. AWS EC2 Deployment</h2>

            <h3>Step 1: Launch EC2 Instance</h3>
            <div class="step-card">
                <h4>Instance Configuration:</h4>
                <ol>
                    <li>Log in to AWS Console</li>
                    <li>Navigate to EC2 Dashboard</li>
                    <li>Click "Launch Instance"</li>
                    <li>Choose AMI: <strong>Ubuntu Server 22.04 LTS</strong></li>
                    <li>Instance Type: <strong>t2.micro</strong> (free tier) or <strong>t2.small</strong> (recommended)</li>
                    <li>Configure Security Group:
                        <ul>
                            <li>SSH (22) - Your IP</li>
                            <li>HTTP (80) - Anywhere</li>
                            <li>HTTPS (443) - Anywhere</li>
                            <li>Custom TCP (8080) - Anywhere (for testing)</li>
                        </ul>
                    </li>
                    <li>Create or select key pair (.pem file)</li>
                    <li>Launch instance</li>
                </ol>
            </div>

            <h3>Step 2: Connect to EC2 Instance</h3>
            <div class="code-block">
# Set key permissions
chmod 400 your-key.pem

# Connect via SSH
ssh -i your-key.pem ubuntu@your-ec2-public-ip

# Or use EC2 Instance Connect from AWS Console
            </div>

            <h3>Step 3: Setup EC2 Instance</h3>
            <div class="code-block">
# Update system
sudo apt update && sudo apt upgrade -y

# Install Java
sudo apt install openjdk-11-jdk -y

# Install Maven
sudo apt install maven -y

# Install PostgreSQL
sudo apt install postgresql postgresql-contrib -y

# Install Git
sudo apt install git -y

# Verify installations
java -version
mvn -version
psql --version
            </div>

            <h3>Step 4: Setup Database on EC2</h3>
            <div class="code-block">
# Configure PostgreSQL
sudo -i -u postgres
psql

CREATE DATABASE web_database;
CREATE USER web_user WITH PASSWORD 'SecurePassword123!';
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;
\q
exit

# Create users table
sudo -u postgres psql -d web_database -c "
CREATE TABLE users (
    iduser SERIAL PRIMARY KEY,
    user VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fname VARCHAR(100)
);"
            </div>

            <h3>Step 5: Install and Configure Tomcat</h3>
            <div class="code-block">
# Download Tomcat
cd /opt
sudo wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.83/bin/apache-tomcat-9.0.83.tar.gz
sudo tar -xzf apache-tomcat-9.0.83.tar.gz
sudo mv apache-tomcat-9.0.83 tomcat9
sudo chmod +x /opt/tomcat9/bin/*.sh

# Create systemd service (same as local deployment)
# See section 2 for complete service file

# Start Tomcat
sudo systemctl daemon-reload
sudo systemctl start tomcat
sudo systemctl enable tomcat
            </div>

            <h3>Step 6: Deploy Application</h3>
            <div class="code-block">
# Clone your repository
cd /home/ubuntu
git clone [your-repository-url]
cd MVC1

# Update database connection for localhost
nano src/main/java/pk/edu/zab/util/DbUtil.java

# Build application
mvn clean package

# Deploy to Tomcat
sudo cp target/ROOT.war /opt/tomcat9/webapps/

# Check logs
tail -f /opt/tomcat9/logs/catalina.out
            </div>

            <h3>Step 7: Configure Domain (Optional)</h3>
            <div class="code-block">
# Install Nginx
sudo apt install nginx -y

# Configure Nginx (see section 5 for complete config)
sudo nano /etc/nginx/sites-available/mvc1

# Enable site
sudo ln -s /etc/nginx/sites-available/mvc1 /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
            </div>

            <div class="success">
                <strong>✓ AWS Deployment Complete!</strong>
                <br>Access your app at: http://your-ec2-public-ip
            </div>

            <h3>Elastic IP Configuration</h3>
            <div class="info">
                <strong>Allocate Elastic IP:</strong>
                <ol>
                    <li>Go to EC2 Dashboard → Elastic IPs</li>
                    <li>Click "Allocate Elastic IP address"</li>
                    <li>Associate it with your EC2 instance</li>
                    <li>Update DNS records to point to Elastic IP</li>
                </ol>
            </div>
        </div>

        <div class="content-section" id="aws-rds">
            <h2>7. AWS RDS Database Setup</h2>

            <h3>Why Use RDS?</h3>
            <ul>
                <li>Automated backups and snapshots</li>
                <li>High availability and automatic failover</li>
                <li>Easy scaling and performance optimization</li>
                <li>Managed security and updates</li>
                <li>Better separation of concerns</li>
            </ul>

            <h3>Step 1: Create RDS Instance</h3>
            <div class="step-card">
                <h4>RDS Configuration:</h4>
                <ol>
                    <li>Go to RDS Dashboard</li>
                    <li>Click "Create database"</li>
                    <li>Choose PostgreSQL</li>
                    <li>Template: Free tier or Production</li>
                    <li>DB Instance Identifier: <strong>mvc1-db</strong></li>
                    <li>Master Username: <strong>web_user</strong></li>
                    <li>Master Password: <strong>[Strong Password]</strong></li>
                    <li>Instance Class: db.t3.micro (free tier)</li>
                    <li>Storage: 20 GB</li>
                    <li>VPC: Same as EC2 instance</li>
                    <li>Public Access: No (for security)</li>
                    <li>VPC Security Group: Create new or use existing</li>
                    <li>Initial Database Name: <strong>web_database</strong></li>
                    <li>Create database</li>
                </ol>
            </div>

            <h3>Step 2: Configure Security Group</h3>
            <div class="code-block">
# Add inbound rule to RDS security group:
Type: PostgreSQL
Protocol: TCP
Port: 5432
Source: [EC2 Security Group ID]
            </div>

            <h3>Step 3: Connect to RDS from EC2</h3>
            <div class="code-block">
# Install PostgreSQL client on EC2
sudo apt install postgresql-client -y

# Connect to RDS
psql -h your-rds-endpoint.amazonaws.com -U web_user -d web_database

# Create table
CREATE TABLE users (
    iduser SERIAL PRIMARY KEY,
    user VARCHAR(100) NOT NULL,
    pass VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fname VARCHAR(100)
);

\q
            </div>

            <h3>Step 4: Update Application Configuration</h3>
            <div class="code-block">
# Update DbUtil.java on EC2
cd /home/ubuntu/MVC1
nano src/main/java/pk/edu/zab/util/DbUtil.java

# Update connection URL:
String url = "jdbc:postgresql://your-rds-endpoint.amazonaws.com:5432/web_database";
String user = "web_user";
String password = "your-rds-password";

# Rebuild and redeploy
mvn clean package
sudo cp target/ROOT.war /opt/tomcat9/webapps/
            </div>

            <div class="warning">
                <strong>Important:</strong> Never commit database credentials to Git. Use environment variables or AWS Secrets Manager.
            </div>

            <h3>Using Environment Variables (Recommended)</h3>
            <div class="code-block">
# Set environment variables
export DB_HOST="your-rds-endpoint.amazonaws.com"
export DB_NAME="web_database"
export DB_USER="web_user"
export DB_PASSWORD="your-password"

# Add to Tomcat service file
sudo nano /etc/systemd/system/tomcat.service

# Add these lines under [Service]:
Environment="DB_HOST=your-rds-endpoint.amazonaws.com"
Environment="DB_NAME=web_database"
Environment="DB_USER=web_user"
Environment="DB_PASSWORD=your-password"

# Reload and restart
sudo systemctl daemon-reload
sudo systemctl restart tomcat
            </div>
        </div>

        <div class="content-section" id="domain">
            <h2>8. Domain & SSL Configuration</h2>

            <h3>Step 1: Configure Domain DNS</h3>
            <div class="step-card">
                <h4>Add DNS Records:</h4>
                <table>
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Name</th>
                            <th>Value</th>
                            <th>TTL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>A</td>
                            <td>@</td>
                            <td>[Your EC2 Elastic IP]</td>
                            <td>300</td>
                        </tr>
                        <tr>
                            <td>A</td>
                            <td>www</td>
                            <td>[Your EC2 Elastic IP]</td>
                            <td>300</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <h3>Step 2: Install Certbot for SSL</h3>
            <div class="code-block">
# Install Certbot
sudo apt install certbot python3-certbot-nginx -y

# Obtain SSL certificate
sudo certbot --nginx -d your-domain.com -d www.your-domain.com

# Follow prompts:
# - Enter email address
# - Agree to terms
# - Choose redirect option (2)
            </div>

            <h3>Step 3: Update Nginx Configuration</h3>
            <div class="code-block">
# Certbot automatically updates Nginx config
# Verify configuration
sudo nginx -t

# Reload Nginx
sudo systemctl reload nginx
            </div>

            <h3>Auto-Renewal Setup</h3>
            <div class="code-block">
# Test auto-renewal
sudo certbot renew --dry-run

# Certbot automatically sets up cron job
# Check renewal timer
sudo systemctl status certbot.timer
            </div>

            <div class="success">
                ✓ SSL Certificate installed! Your site is now accessible via HTTPS
            </div>

            <h3>Force HTTPS Redirect</h3>
            <div class="code-block">
# Nginx configuration with SSL
server {
    listen 80;
    server_name your-domain.com www.your-domain.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name your-domain.com www.your-domain.com;

    ssl_certificate /etc/letsencrypt/live/your-domain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/your-domain.com/privkey.pem;
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
            </div>
        </div>

        <div class="content-section" id="monitoring">
            <h2>9. Monitoring & Maintenance</h2>

            <h3>Application Monitoring</h3>
            <div class="code-block">
# Monitor Tomcat logs
tail -f /opt/tomcat9/logs/catalina.out

# Monitor access logs
tail -f /opt/tomcat9/logs/localhost_access_log.*.txt

# Monitor Nginx logs
tail -f /var/log/nginx/mvc1_access.log
tail -f /var/log/nginx/mvc1_error.log

# Monitor system resources
htop  # Install with: sudo apt install htop

# Check disk usage
df -h

# Check memory usage
free -m
            </div>

            <h3>Database Monitoring</h3>
            <div class="code-block">
# Check PostgreSQL status
sudo systemctl status postgresql

# Connect and check database
sudo -u postgres psql -d web_database

# List connections
SELECT * FROM pg_stat_activity;

# Check database size
SELECT pg_size_pretty(pg_database_size('web_database'));

# Check table size
SELECT pg_size_pretty(pg_total_relation_size('users'));

\q
            </div>

            <h3>Performance Monitoring</h3>
            <div class="code-block">
# Install monitoring tools
sudo apt install sysstat iotop -y

# CPU usage
mpstat 1

# I/O usage
iotop

# Network usage
iftop
            </div>

            <h3>Log Rotation</h3>
            <div class="code-block">
# Configure log rotation for Tomcat
sudo nano /etc/logrotate.d/tomcat

# Add this configuration:
/opt/tomcat9/logs/catalina.out {
    copytruncate
    daily
    rotate 7
    compress
    missingok
    size 100M
}
            </div>

            <h3>Automated Health Checks</h3>
            <div class="code-block">
# Create health check script
sudo nano /usr/local/bin/health-check.sh

#!/bin/bash
# Check if Tomcat is running
if ! systemctl is-active --quiet tomcat; then
    echo "Tomcat is down! Restarting..."
    systemctl restart tomcat
fi

# Check if Nginx is running
if ! systemctl is-active --quiet nginx; then
    echo "Nginx is down! Restarting..."
    systemctl restart nginx
fi

# Make executable
sudo chmod +x /usr/local/bin/health-check.sh

# Add to crontab (run every 5 minutes)
sudo crontab -e
*/5 * * * * /usr/local/bin/health-check.sh
            </div>
        </div>

        <div class="content-section" id="troubleshooting">
            <h2>10. Deployment Troubleshooting</h2>

            <h3>Application Not Starting</h3>
            <div class="step-card">
                <h4>Problem: Tomcat won't start</h4>
                <div class="code-block">
# Check Tomcat status
sudo systemctl status tomcat

# Check logs for errors
tail -100 /opt/tomcat9/logs/catalina.out

# Common causes:
# 1. Port 8080 already in use
sudo netstat -tulpn | grep 8080

# 2. Java not found
which java
echo $JAVA_HOME

# 3. Permission issues
sudo chown -R tomcat:tomcat /opt/tomcat9
                </div>
            </div>

            <h3>Database Connection Errors</h3>
            <div class="step-card">
                <h4>Problem: Cannot connect to database</h4>
                <div class="code-block">
# Test database connection
psql -h localhost -U web_user -d web_database

# Check PostgreSQL is running
sudo systemctl status postgresql

# Check PostgreSQL logs
sudo tail -f /var/log/postgresql/postgresql-*-main.log

# Verify credentials in DbUtil.java
cat src/main/java/pk/edu/zab/util/DbUtil.java

# Test connection from Java
telnet localhost 5432
                </div>
            </div>

            <h3>404 Errors on Pages</h3>
            <div class="step-card">
                <h4>Problem: Pages return 404</h4>
                <div class="code-block">
# Check WAR deployment
ls -la /opt/tomcat9/webapps/ROOT/

# Check Tomcat logs
tail -f /opt/tomcat9/logs/catalina.out

# Verify URL patterns
# Should be: /user/login not /user.login

# Check FrontController mapping in web.xml
                </div>
            </div>

            <h3>Session Issues</h3>
            <div class="step-card">
                <h4>Problem: Sessions not persisting</h4>
                <ul>
                    <li>Clear browser cookies and cache</li>
                    <li>Check if cookies are enabled</li>
                    <li>Verify session timeout in web.xml</li>
                    <li>Check Tomcat session configuration</li>
                </ul>
            </div>

            <h3>AWS EC2 Connection Issues</h3>
            <div class="step-card">
                <h4>Problem: Cannot SSH to EC2</h4>
                <div class="code-block">
# Check security group allows SSH from your IP
# Verify key permissions
chmod 400 your-key.pem

# Try verbose SSH
ssh -v -i your-key.pem ubuntu@ec2-ip

# Check EC2 instance state
# Make sure it's "running" in AWS Console
                </div>
            </div>

            <h3>SSL Certificate Issues</h3>
            <div class="step-card">
                <h4>Problem: SSL not working</h4>
                <div class="code-block">
# Check Certbot status
sudo certbot certificates

# Test SSL configuration
openssl s_client -connect your-domain.com:443

# Check Nginx SSL config
sudo nginx -t

# Renew certificate manually
sudo certbot renew
                </div>
            </div>
        </div>

        <div class="content-section" id="security">
            <h2>11. Security Best Practices</h2>

            <h3>Server Security</h3>
            <ul class="checklist">
                <li>Keep system and packages updated</li>
                <li>Use strong passwords for all accounts</li>
                <li>Disable root login via SSH</li>
                <li>Use SSH keys instead of passwords</li>
                <li>Configure firewall (UFW or firewalld)</li>
                <li>Install fail2ban for brute force protection</li>
                <li>Enable automatic security updates</li>
                <li>Use non-standard SSH port (optional)</li>
            </ul>

            <h3>Install Fail2ban</h3>
            <div class="code-block">
# Install fail2ban
sudo apt install fail2ban -y

# Configure for SSH
sudo cp /etc/fail2ban/jail.conf /etc/fail2ban/jail.local
sudo nano /etc/fail2ban/jail.local

# Add this configuration:
[sshd]
enabled = true
port = 22
filter = sshd
logpath = /var/log/auth.log
maxretry = 3
bantime = 3600

# Start fail2ban
sudo systemctl start fail2ban
sudo systemctl enable fail2ban
            </div>

            <h3>Application Security</h3>
            <ul class="checklist">
                <li>Use HTTPS for all connections</li>
                <li>Implement password hashing (BCrypt)</li>
                <li>Use PreparedStatements for SQL queries</li>
                <li>Validate and sanitize all user inputs</li>
                <li>Implement CSRF protection</li>
                <li>Set secure session cookies</li>
                <li>Implement rate limiting</li>
                <li>Hide server version information</li>
            </ul>

            <h3>Database Security</h3>
            <ul class="checklist">
                <li>Use strong database passwords</li>
                <li>Limit database user permissions</li>
                <li>Disable remote root access</li>
                <li>Use SSL for database connections</li>
                <li>Regular database backups</li>
                <li>Encrypt sensitive data</li>
            </ul>

            <h3>AWS Security</h3>
            <ul class="checklist">
                <li>Use IAM roles instead of access keys</li>
                <li>Enable MFA for AWS account</li>
                <li>Restrict security group rules</li>
                <li>Use VPC for network isolation</li>
                <li>Enable CloudWatch monitoring</li>
                <li>Use AWS Systems Manager for management</li>
                <li>Enable AWS Config for compliance</li>
            </ul>

            <h3>Hide Server Information</h3>
            <div class="code-block">
# Hide Tomcat version
sudo nano /opt/tomcat9/conf/server.xml

# Find and update:
&lt;Connector port="8080" ... server="Apache" /&gt;

# Hide Nginx version
sudo nano /etc/nginx/nginx.conf

# Add in http block:
server_tokens off;

# Reload services
sudo systemctl restart tomcat
sudo systemctl reload nginx
            </div>
        </div>

        <div class="content-section" id="backup">
            <h2>12. Backup & Recovery</h2>

            <h3>Database Backup</h3>
            <div class="code-block">
# Manual backup
pg_dump -U web_user -d web_database > backup_$(date +%Y%m%d_%H%M%S).sql

# Restore from backup
psql -U web_user -d web_database < backup_20241128_120000.sql
            </div>

            <h3>Automated Database Backups</h3>
            <div class="code-block">
# Create backup script
sudo nano /usr/local/bin/db-backup.sh

#!/bin/bash
BACKUP_DIR="/home/ubuntu/backups"
mkdir -p $BACKUP_DIR
DATE=$(date +%Y%m%d_%H%M%S)
FILENAME="mvc1_backup_$DATE.sql"

pg_dump -U web_user -d web_database > $BACKUP_DIR/$FILENAME

# Compress backup
gzip $BACKUP_DIR/$FILENAME

# Keep only last 7 days
find $BACKUP_DIR -name "*.sql.gz" -mtime +7 -delete

echo "Backup completed: $FILENAME.gz"

# Make executable
sudo chmod +x /usr/local/bin/db-backup.sh

# Add to crontab (daily at 2 AM)
sudo crontab -e
0 2 * * * /usr/local/bin/db-backup.sh
            </div>

            <h3>Application Backup</h3>
            <div class="code-block">
# Backup WAR file
sudo cp /opt/tomcat9/webapps/ROOT.war /home/ubuntu/backups/

# Backup configuration files
tar -czf config_backup.tar.gz \
    /opt/tomcat9/conf/ \
    /etc/nginx/sites-available/ \
    /etc/systemd/system/tomcat.service
            </div>

            <h3>AWS RDS Automated Backups</h3>
            <div class="info">
                <strong>RDS Backup Configuration:</strong>
                <ul>
                    <li>Go to RDS Console</li>
                    <li>Select your database instance</li>
                    <li>Click "Modify"</li>
                    <li>Set "Backup retention period": 7 days</li>
                    <li>Set "Backup window": Preferred time</li>
                    <li>Enable "Copy tags to snapshots"</li>
                </ul>
            </div>

            <h3>EC2 Snapshot</h3>
            <div class="code-block">
# From AWS Console:
# 1. Go to EC2 → Instances
# 2. Select your instance
# 3. Actions → Image and templates → Create image
# 4. Name: mvc1-backup-[date]
# 5. Create image

# Automated with AWS CLI:
aws ec2 create-image \
    --instance-id i-1234567890abcdef0 \
    --name "mvc1-backup-$(date +%Y%m%d)" \
    --description "Automated backup of MVC1 application"
            </div>

            <h3>Disaster Recovery Plan</h3>
            <ol>
                <li><strong>Regular Backups:</strong> Daily database backups, weekly snapshots</li>
                <li><strong>Off-site Storage:</strong> Store backups in S3 or different region</li>
                <li><strong>Test Restores:</strong> Monthly test restore procedures</li>
                <li><strong>Documentation:</strong> Keep updated recovery procedures</li>
                <li><strong>Contact List:</strong> Maintain emergency contact information</li>
                <li><strong>Monitoring:</strong> Set up alerts for backup failures</li>
            </ol>
        </div>

        <div class="content-section">
            <h2>Conclusion</h2>
            <p>
                This deployment guide covers everything from local development to production deployment on AWS. Following these instructions and best practices will ensure your MVC1 application is deployed securely, reliably, and efficiently.
            </p>

            <div class="success">
                <strong>Deployment Checklist:</strong>
                <ul class="checklist">
                    <li>Java and Maven installed</li>
                    <li>PostgreSQL database configured</li>
                    <li>Tomcat server running</li>
                    <li>Application WAR deployed</li>
                    <li>Firewall configured</li>
                    <li>SSL certificate installed</li>
                    <li>Monitoring setup</li>
                    <li>Backups automated</li>
                    <li>Security measures implemented</li>
                </ul>
            </div>

            <div class="warning">
                <strong>⚠️ Remember:</strong>
                <ul>
                    <li>Always test in development before production</li>
                    <li>Keep all passwords and keys secure</li>
                    <li>Regularly update software and dependencies</li>
                    <li>Monitor application and server health</li>
                    <li>Maintain regular backups</li>
                    <li>Document all changes and configurations</li>
                </ul>
            </div>
        </div>

        <div class="footer">
            <p>&copy; 2025 MVC1 Web Application | Deployment Documentation</p>
            <p>For questions and support, refer to the main documentation</p>
            <p style="margin-top: 10px;">
                <a href="<%= request.getContextPath() %>/public/documentation" style="color: #3498db; text-decoration: none;">Main Documentation</a> |
                <a href="<%= request.getContextPath() %>/" style="color: #3498db; text-decoration: none;">Main Menu</a>
            </p>
        </div>
    </div>
</body>
</html>
