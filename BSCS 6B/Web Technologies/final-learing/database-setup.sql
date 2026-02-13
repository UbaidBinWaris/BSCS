-- PostgreSQL Database Setup Script
-- Run this script in your PostgreSQL database to create the required database

-- Create the database (if not exists)
CREATE DATABASE webdb;

-- Connect to the database
\c webdb;

-- The 'users' table will be automatically created by Hibernate when the application starts
-- This is handled by spring.jpa.hibernate.ddl-auto=update in application.properties

-- Optional: Create the users table manually if you prefer
-- CREATE TABLE IF NOT EXISTS users (
--     id BIGSERIAL PRIMARY KEY,
--     username VARCHAR(255) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(255) NOT NULL,
--     full_name VARCHAR(255) NOT NULL,
--     role VARCHAR(255) NOT NULL DEFAULT 'USER',
--     enabled BOOLEAN NOT NULL DEFAULT true
-- );

-- Grant permissions (if needed)
-- GRANT ALL PRIVILEGES ON DATABASE webdb TO postgres;
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;
-- GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO postgres;
p