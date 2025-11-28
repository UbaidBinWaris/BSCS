# Tomcat, Apache, PostgreSQL, and Maven Deployment Guide

## Table of Contents
1. System Preparation
2. Java Installation
3. Tomcat Installation
   3.1 Create Directory
   3.2 Extract Tomcat
   3.3 Set Permissions
   3.4 Run Tomcat
   3.5 Set Environment Variables for Multiple Tomcat Versions
4. Tomcat Configuration
   4.1 Configure Users
   4.2 Edit Context Files
5. PostgreSQL Setup
   5.1 Install PostgreSQL
   5.2 Create Database and User
   5.3 Create Tables and Insert Data
6. Apache HTTP Server Setup
   6.1 Install Apache
   6.2 Configure Modules
7. Reverse Proxy Setup
   7.1 Configure VirtualHost
   7.2 Restart Apache
   7.3 Verify Proxy Operation
8. Maven Project Deployment
   8.1 Build Project
   8.2 Deploy WAR File
   8.3 Restart Tomcat

---

## 1. System Preparation
Update your system for best performance.

### Arch Linux:
```
sudo pacman -Syu
```
### Ubuntu/Debian:
```
sudo apt update && sudo apt upgrade
```

## 2. Java Installation
Install JDK 17 and verify:
```
sudo pacman -S jdk17-openjdk
java -version
```

## 3. Tomcat Installation

### 3.1 Create Directory
```
mkdir -p ~/tomcat9
```

### 3.2 Extract Tomcat
```
cd /tmp
wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.tar.gz
sudo tar -xzf apache-tomcat-10.1.33.tar.gz -C ~/tomcat9 --strip-components=1
```

### 3.3 Set Permissions
```
sudo chown -R $USER:$USER ~/tomcat9
chmod +x ~/tomcat9/bin/*.sh
```

### 3.4 Run Tomcat
Start and stop Tomcat:
```
~/tomcat9/bin/startup.sh
~/tomcat9/bin/shutdown.sh
```
Access via browser:
```
http://localhost:8080
```

### 3.5 Set Environment Variables for Multiple Tomcat Versions
If you plan to manage multiple Tomcat versions on the same system, set the following environment variables in your shell or `~/.bashrc` / `~/.zshrc`:
```
export CATALINA_HOME=~/tomcat9
export CATALINA_BASE=~/tomcat9
```
- `CATALINA_HOME` points to the Tomcat installation directory.
- `CATALINA_BASE` points to the Tomcat instance directory (allows running multiple instances with different configurations).

Reload shell configuration:
```
source ~/.bashrc   # or source ~/.zshrc
```

This setup allows you to maintain multiple Tomcat versions and easily switch between them by changing these environment variables.

## 4. Tomcat Configuration

### 4.1 Configure Users
Edit `tomcat-users.xml` in `~/tomcat9/conf` and add your user and roles.

### 4.2 Edit Context Files
Edit `webapps/manager/META-INF/context.xml` and `webapps/host-manager/META-INF/context.xml`, commenting out the RemoteAddrValve to allow remote access.

## 5. PostgreSQL Setup

### 5.1 Install PostgreSQL
```
sudo pacman -S postgresql
sudo systemctl enable postgresql
sudo systemctl start postgresql
```

### 5.2 Create Database and User
```
sudo -i -u postgres
CREATE DATABASE web_database;
CREATE USER web_user WITH PASSWORD 'web_password';
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;
```

### 5.3 Create Tables and Insert Data
```
\c web_database
CREATE TABLE users (iduser SERIAL PRIMARY KEY, user_name VARCHAR(50), pass VARCHAR(50), email VARCHAR(100), fname VARCHAR(50));
INSERT INTO users (user_name, pass, email, fname) VALUES ('ubaid', 'ubaid123', 'ubaid@don.bigdon', 'Ubaid');
```

## 6. Apache HTTP Server Setup

### 6.1 Install Apache
```
sudo pacman -S apache
```

### 6.2 Configure Modules
Edit `/etc/httpd/conf/httpd.conf` and ensure all proxy modules are uncommented.

## 7. Reverse Proxy Setup

### 7.1 Configure VirtualHost
Edit `/etc/httpd/conf/extra/httpd-vhosts.conf` and add:
```
<VirtualHost *:80>
    ServerName localhost
    ProxyRequests Off
    ProxyPreserveHost On
    ProxyPass / http://127.0.0.1:8080/
    ProxyPassReverse / http://127.0.0.1:8080/
    ErrorLog "/var/log/httpd/error.log"
    CustomLog "/var/log/httpd/access.log" combined
</VirtualHost>
```

### 7.2 Restart Apache
```
sudo systemctl restart httpd
```

### 7.3 Verify Proxy Operation
Visit `http://localhost` and ensure Tomcat pages load correctly via port 80.

## 8. Maven Project Deployment

### 8.1 Build Project
Use IntelliJ IDEA Maven panel or CLI:
```
mvn clean install
```

### 8.2 Deploy WAR File
Remove old ROOT folder and copy new WAR:
```
rm -rf ~/tomcat9/webapps/ROOT
cp target/ROOT.war ~/tomcat9/webapps/
```

### 8.3 Restart Tomcat
```
~/tomcat9/bin/shutdown.sh
~/tomcat9/bin/startup.sh
```
Visit `http://localhost` to see the deployed web application.

