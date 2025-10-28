-- PostgreSQL Database Setup Script
-- Run this script to create the database and user for Hibernate Demo

-- Connect to PostgreSQL as superuser (postgres) and run these commands:

-- 1. Create database
CREATE DATABASE hibernate_demo;

-- 2. Create user (optional - you can use existing postgres user)
CREATE USER hibernate_user WITH PASSWORD 'hibernate_pass';

-- 3. Grant privileges
GRANT ALL PRIVILEGES ON DATABASE hibernate_demo TO hibernate_user;

-- 4. Connect to the new database
\c hibernate_demo;

-- 5. Grant schema privileges (PostgreSQL 15+)
GRANT ALL ON SCHEMA public TO hibernate_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO hibernate_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO hibernate_user;

-- Note: Hibernate will automatically create the tables when you run your application
