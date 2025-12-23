# AWS EC2 + RDS (PostgreSQL) Complete Deployment Guide
A fully structured, logical, and professionally organized document that allows any reader to recreate your entire setup from scratch and deploy a fully functional production‑ready system.

---
# **Table of Contents**
1. Overview
2. Create AMI From Existing EC2 (Optional)
3. Create Custom VPC
4. Create Subnets (Public + Private)
5. Create & Attach Internet Gateway
6. Configure Route Tables
7. Create Security Groups
8. Launch EC2 Instance (Ubuntu)
9. Install PostgreSQL Client
10. Create RDS Subnet Group
11. Create RDS PostgreSQL Instance
12. Retrieve Endpoint & Connect From EC2
13. Create Database, User & Tables
14. Connect Web Application to RDS
15. Configure Apache/Tomcat Reverse Proxy
16. Enable HTTPS With Certbot (SSL)
17. Final Testing & Verification

---

# **1. Overview**
This guide explains how to set up:
- A secure **AWS VPC** with public & private subnets
- An **EC2 Ubuntu server** for running a web application
- A **PostgreSQL RDS** instance hosted in private subnets
- Secure communication between EC2 → RDS
- Reverse proxy using **Apache/Tomcat**
- **HTTPS encryption** using Certbot

Following these steps will produce a complete and production‑ready deployment.

---

# **2. Create AMI From Existing EC2 (Optional)**
Only do this if you already configured an EC2 and want to clone it.
1. EC2 → Instances → Select instance
2. Actions → Image & Templates → **Create Image**
3. Name: `webtech-base-ami`
4. After creation, launch new instance from this AMI

---

# **3. Create Custom VPC**
1. VPC Dashboard → **Create VPC**
2. VPC Only → Provide:
   - Name: `webtech-vpc`
   - IPv4 CIDR: `10.0.0.0/16`
3. Create VPC

---

# **4. Create Subnets**
## **Public Subnet (EC2)**
- Name: `public-subnet-1`
- AZ: `eu-north-1a`
- CIDR: `10.0.10.0/24`

## **Private Subnets (RDS)**
- `private-subnet-1` → `10.0.20.0/24`
- `private-subnet-2` → `10.0.30.0/24`

---

# **5. Create & Attach Internet Gateway**
1. Create IGW → `webtech-igw`
2. Attach to `webtech-vpc`

---

# **6. Configure Route Tables**
## **Public Route Table**
- Name: `public-rt`
- Associate: `public-subnet-1`
- Add route:
  - Destination: `0.0.0.0/0`
  - Target: **Internet Gateway**

## **Private Route Table**
- Name: `private-rt`
- Associate: `private-subnet-1` & `private-subnet-2`
- No internet routes

---

# **7. Create Security Groups**
## **EC2 Security Group (ec2-rds-2)**
Inbound:
- SSH (22) → Your IP
- HTTP (80) → Anywhere
- HTTPS (443) → Anywhere

## **RDS Security Group (rds-ec2-2)**
Inbound:
- PostgreSQL (5432) → EC2 security group

---

# **8. Launch EC2 Instance**
1. EC2 → Launch Instance
2. AMI: Ubuntu 22.04
3. Instance type: `t2.micro` or `t3.micro`
4. Network:
   - VPC: `webtech-vpc`
   - Subnet: `public-subnet-1`
   - Auto‑assign Public IP: Enabled
5. Attach SG: `ec2-rds-2`
6. Launch

SSH into server:
```sh
ssh -i key.pem ubuntu@<EC2-PUBLIC-IP>
```

---

# **9. Install PostgreSQL Client (EC2)**
```sh
sudo apt update
sudo apt install postgresql-client -y
```

---

# **10. Create RDS Subnet Group**
1. RDS → Subnet Groups → Create
2. Name: `rds-ec2-db-subnet-group-1`
3. Add subnets:
   - `private-subnet-1`
   - `private-subnet-2`

---

# **11. Create RDS PostgreSQL Instance**
- Engine: PostgreSQL
- Template: Free Tier
- DB Identifier: `databas`
- Master username & password: (choose)
- Instance: `db.t4g.micro`
- VPC: `webtech-vpc`
- Subnet group: `rds-ec2-db-subnet-group-1`
- Public Access: NO
- Security Group: `rds-ec2-2`

Wait for status **Available**.

---

# **12. Retrieve RDS Endpoint**
Example:
```
databas.c7u228yiax78.eu-north-1.rds.amazonaws.com
```

---

# **13. Connect to RDS From EC2**
Use the exact command you used:
```sh
psql -h databas.c7u228yiax78.eu-north-1.rds.amazonaws.com -U web_user -d web_database
```

# **14. Connect Web Application to RDS**
Use your environment variables or configuration files to point your web app to the RDS instance.

Example connection variables:
```
DB_HOST=<RDS-ENDPOINT>
DB_PORT=5432
DB_USER=web_user
DB_PASS=yourpassword
DB_NAME=web_database
```
---

# **15. (Removed – Tables/Data Already Present in AMI)**
Since the EC2 image already contained your complete PostgreSQL schema and inserted data, this step is unnecessary.

# **16. Connect Your Web App to RDS**. Connect Your Web App to RDS**
Example environment variables:
```
DB_HOST=<RDS-ENDPOINT>
DB_PORT=5432
DB_USER=web_user
DB_PASS=yourpassword
DB_NAME=web_database
```

---

# **17. Configure Apache/Tomcat Reverse Proxy**
Enable modules:
```sh
sudo a2enmod proxy proxy_http
```

Edit configuration:
```sh
sudo vim /etc/apache2/sites-enabled/000-default.conf
```
Add:
```
ProxyRequests Off
ProxyPreserveHost On
ProxyPass / http://127.0.0.1:8080/
ProxyPassReverse / http://127.0.0.1:8080/
```
Restart:
```sh
sudo systemctl restart apache2
```

---

# **18. Enable HTTPS (SSL Certificate)**
Install Certbot:
```sh
sudo apt install certbot python3-certbot-apache -y
```
Run:
```sh
sudo certbot --apache
```
SSL automatically applies.

---

# **19. Testing & Verification**
Verify SSL:
```sh
curl -I https://yourdomain.com
```
Verify Reverse Proxy:
```sh
curl -I http://127.0.0.1:8080
```
Verify Database:
```sh
psql -h <RDS-ENDPOINT> -U web_user -d web_database -c "SELECT * FROM users";
```

---
# **Setup Complete**
This improved documentation is now fully structured and ready for production use. Anyone reading it can recreate the entire setup without confusion.

