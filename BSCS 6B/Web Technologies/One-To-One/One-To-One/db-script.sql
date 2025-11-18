CREATE SCHEMA one;
CREATE TABLE one.student (
 id SERIAL PRIMARY KEY,
 name VARCHAR(50) NOT NULL,
 email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE one.address (
 id SERIAL PRIMARY KEY,
 street VARCHAR(100),
 city VARCHAR(50),
 zip_code VARCHAR(10),
 student_id INTEGER UNIQUE, -- each student has one address only
 CONSTRAINT fk_student
 FOREIGN KEY (student_id)
 REFERENCES one.student (id)
);


