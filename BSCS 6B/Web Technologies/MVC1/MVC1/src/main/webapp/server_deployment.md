# Tomcat, Apache, PostgreSQL, and Maven Deployment Guide

## Table of Contents

1. System Preparation
2. Java Installation
3. Tomcat Installation
    - 3.1 Create Directory
    - 3.2 Extract Tomcat
    - 3.3 Set Permissions
    - 3.4 Run Tomcat
4. Tomcat Configuration
    - 4.1 Configure Users
    - 4.2 Edit Context Files
5. PostgreSQL Setup
    - 5.1 Install PostgreSQL
    - 5.2 Create Database
    - 5.3 Create User
    - 5.4 Grant Privileges
    - 5.5 Create Tables
    - 5.6 Insert Data
6. Apache HTTP Server Setup
7. Reverse Proxy Setup
8. Maven Project Deployment
9. Deployment on AWS Ubuntu Instance

---

## 1. System Preparation

Update your system:
```
sudo apt update
sudo apt upgrade -y
```

## 2. Java Installation

Install JDK 17 and verify:
```
sudo apt install openjdk-17-jdk -y
java -version
```

## 3. Tomcat Installation

### 3.1 Create Directory
```
sudo mkdir -p /opt/tomcat
```

### 3.2 Extract Tomcat
```
cd /tmp
wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.tar.gz
sudo tar -xzf apache-tomcat-10.1.33.tar.gz -C /opt/tomcat --strip-components=1
```

### 3.3 Set Permissions
```
sudo chown -R ubuntu:ubuntu /opt/tomcat
chmod +x /opt/tomcat/bin/*.sh
```

### 3.4 Run Tomcat
```
/opt/tomcat/bin/startup.sh
/opt/tomcat/bin/shutdown.sh
```

## 4. Tomcat Configuration

### 4.1 Configure Users
Edit `/opt/tomcat/conf/tomcat-users.xml` to add roles and users.

### 4.2 Edit Context Files
Edit `webapps/manager/META-INF/context.xml` and `webapps/host-manager/META-INF/context.xml` to comment out RemoteAddrValve:
```
<!--
  <Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127\\.\d+\.\d+\.\d+|::1"/>
-->
```

## 5. PostgreSQL Setup

### 5.1 Install PostgreSQL
```
sudo apt install postgresql -y
sudo apt install postgresql-contrib -y
sudo systemctl enable postgresql
sudo systemctl start postgresql
```

### 5.2 Create Database
```
sudo -i -u postgres
CREATE DATABASE web_database;
```

### 5.3 Create User
```
CREATE USER web_user WITH PASSWORD 'web_password';
```

### 5.4 Grant Privileges
```
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;
```

### 5.5 Create Tables
```
psql -d web_database
CREATE TABLE users (
    iduser SERIAL PRIMARY KEY,
    user_name VARCHAR(50),
    pass VARCHAR(50),
    email VARCHAR(100),
    fname VARCHAR(50)
);
```

### 5.6 Insert Data
```
INSERT INTO users (user_name, pass, email, fname) VALUES ('ubaid', 'ubaid123', 'ubaid@don.bigdon', 'Ubaid');
```

## 6. Apache HTTP Server Setup

### 6.1 Install Apache
```
sudo apt install apache2 -y
```

### 6.2 Configure Modules

Enable the Apache modules required for proxying, rewrites and SSL. Each command below enables a single module so a beginner can run them one by one and understand what they do.

```
# Core proxy module – provides proxy support
sudo a2enmod proxy

# HTTP proxy – forwards HTTP requests to backend (Tomcat)
sudo a2enmod proxy_http

# AJP proxy – alternative protocol used by some Tomcat setups (optional)
sudo a2enmod proxy_ajp

# Balancer – required if you plan to load-balance across multiple backends
sudo a2enmod proxy_balancer

# CONNECT – allows tunneling (used by some proxy features)
sudo a2enmod proxy_connect

# proxy_html – helps rewrite HTML returned from proxied servers (optional)
sudo a2enmod proxy_html

# URL rewriting – required for HTTP→HTTPS redirect and friendly URLs
sudo a2enmod rewrite

# SSL – enables HTTPS support
sudo a2enmod ssl

# After enabling modules, restart Apache to apply changes
sudo systemctl restart apache2
```

**What each module does (short):**
- `proxy`: base proxy framework for Apache.
- `proxy_http`: forwards regular HTTP requests to another server (Tomcat).
- `proxy_ajp`: forwards AJP protocol to Tomcat (only if you use AJP).
- `proxy_balancer`: lets Apache distribute requests across multiple backend servers.
- `proxy_connect`: allows proxying of CONNECT requests (used by tunneling).
- `proxy_html`: rewrites links inside HTML responses from proxied servers.
- `rewrite`: performs URL rewriting and redirects (used for forcing HTTPS).
- `ssl`: enables TLS/SSL support for secure HTTPS connections.

### 6.3 Apache Configuration Files
Below are the full files (path + copy–paste content). Put these exact contents into the files on your server.

#### 6.3.1 `/etc/apache2/sites-available/000-default.conf`
This is the default site — it proxies all requests to Tomcat on localhost:8080 and blocks access to `/manager`.

```
<VirtualHost *:80>
    # Proxy all requests to Tomcat
    ProxyPreserveHost On
    ProxyPass / http://127.0.0.1:8080/
    ProxyPassReverse / http://127.0.0.1:8080/

    # Optional: prevent direct access to Tomcat manager via Apache
    <Location /manager>
        Require all denied
    </Location>

    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

Save the file and enable the site (if not already):

```
sudo a2ensite 000-default.conf
sudo systemctl reload apache2
```

#### 6.3.2 `/etc/apache2/sites-available/webtech.conf`
This virtual host handles HTTP for your domain and redirects to HTTPS.

```
<VirtualHost *:80>
    ServerName webtech.uabidbinwaris.dev
    ServerAlias www.webtech.uabidbinwaris.dev

    ProxyRequests Off
    ProxyPreserveHost On
    ProxyPass / http://127.0.0.1:8080/
    ProxyPassReverse / http://127.0.0.1:8080/

    ErrorLog ${APACHE_LOG_DIR}/webtech-error.log
    CustomLog ${APACHE_LOG_DIR}/webtech-access.log combined

    RewriteEngine on
    RewriteCond %{SERVER_NAME} =webtech.uabidbinwaris.dev [OR]
    RewriteCond %{SERVER_NAME} =www.webtech.uabidbinwaris.dev
    RewriteRule ^ https://%{SERVER_NAME}%{REQUEST_URI} [END,NE,R=permanent]
</VirtualHost>
```

Enable and reload:

```
sudo a2ensite webtech.conf
sudo systemctl reload apache2
```

#### 6.3.3 `/etc/apache2/sites-available/webtech-le-ssl.conf`
This virtual host serves HTTPS and points Apache to the Let's Encrypt certificates.

```
<IfModule mod_ssl.c>
<VirtualHost *:443>
    ServerName webtech.uabidbinwaris.dev
    ServerAlias www.webtech.uabidbinwaris.dev

    ProxyRequests Off
    ProxyPreserveHost On
    ProxyPass / http://127.0.0.1:8080/
    ProxyPassReverse / http://127.0.0.1:8080/

    ErrorLog ${APACHE_LOG_DIR}/webtech-error.log
    CustomLog ${APACHE_LOG_DIR}/webtech-access.log combined

    Include /etc/letsencrypt/options-ssl-apache.conf
    SSLCertificateFile /etc/letsencrypt/live/webtech.uabidbinwaris.dev/fullchain.pem
    SSLCertificateKeyFile /etc/letsencrypt/live/webtech.uabidbinwaris.dev/privkey.pem
</VirtualHost>
</IfModule>
```

Enable the SSL site and reload Apache:

```
sudo a2ensite webtech-le-ssl.conf
sudo systemctl reload apache2
```

Restart Apache:
```
sudo apachectl configtest
sudo systemctl restart apache2
```

## 6.4 SSL Certificate Installation (Certbot)

Install Certbot and generate your SSL certificate:
```
sudo apt install certbot python3-certbot-apache -y
sudo certbot --apache -d webtech.uabidbinwaris.dev -d www.webtech.uabidbinwaris.dev
```

After SSL is successfully issued, verify the HTTPS setup:
```
curl -I https://webtech.uabidbinwaris.dev
curl -I https://www.webtech.uabidbinwaris.dev
```

Ensure EC2 security group allows inbound HTTP(80), HTTPS(443), and SSH(22).

## 7. Reverse Proxy Setup
 SSL Certificate Installation (Certbot)

Install Certbot and configure SSL:
```
sudo apt install certbot python3-certbot-apache -y
sudo certbot --apache -d webtech.uabidbinwaris.dev -d www.webtech.uabidbinwaris.dev
```

## 8. Maven Project Deployment

Build Project:
```
mvn clean install
```

Deploy WAR File:
```
sudo rm -rf /opt/tomcat/webapps/ROOT
sudo mv ~/ROOT.war /opt/tomcat/webapps/
sudo chown -R ubuntu:ubuntu /opt/tomcat/webapps/ROOT.war
```

Restart Tomcat:
```
sudo /opt/tomcat/bin/shutdown.sh
sudo /opt/tomcat/bin/startup.sh
```

## 9. Deployment on AWS Ubuntu Instance

Copy WAR from local machine to server:
```
scp -i ~/Downloads/web-key.pem ~/tomcat9/webapps/ROOT.war ubuntu@13.62.238.161:~/
```

SSH to server:
```
ssh -i ~/Downloads/web-key.pem ubuntu@13.62.238.161
```

Move WAR to Tomcat webapps:
```
sudo mv ~/ROOT.war /opt/tomcat/webapps/
```

Set ownership:
```
sudo chown -R ubuntu:ubuntu /opt/tomcat/webapps/ROOT.war
```

Stop Tomcat:
```
sudo /opt/tomcat/bin/shutdown.sh
```

Start Tomcat:
```
sudo /opt/tomcat/bin/startup.sh
```

Verify deployment in browser:
```
https://webtech.uabidbinwaris.dev
```

