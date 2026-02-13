-- PostgreSQL Database Setup Script for JPA Learning Application
-- Run this script as a PostgreSQL superuser (e.g., postgres)

-- Create the database
CREATE DATABASE web_database;

-- Create the user
CREATE USER web_user WITH PASSWORD 'web_password';

-- Grant all privileges on the database to the user
GRANT ALL PRIVILEGES ON DATABASE web_database TO web_user;

-- Connect to the web_database
\c web_database

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO web_user;

-- Grant default privileges for future tables
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO web_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO web_user;

-- Note: The 'users' table will be automatically created by Hibernate
-- based on the User entity when the application first runs.
--
-- If you want to create it manually, here's the SQL:
--
-- CREATE TABLE users (
--     id BIGSERIAL PRIMARY KEY,
--     username VARCHAR(50) UNIQUE NOT NULL,
--     password VARCHAR(100) NOT NULL,
--     email VARCHAR(100) UNIQUE NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );
--
-- GRANT ALL PRIVILEGES ON TABLE users TO web_user;
-- GRANT USAGE, SELECT ON SEQUENCE users_id_seq TO web_user;

