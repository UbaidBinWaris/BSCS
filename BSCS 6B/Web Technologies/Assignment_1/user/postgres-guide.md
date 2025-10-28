# Guide to Creating Roles in PostgreSQL with psql

This guide explains how to create and manage roles in a PostgreSQL database using the `psql` command-line interface and the `createuser` utility. Roles are a fundamental concept in PostgreSQL for managing user accounts and privileges.

## Method 1: Using the `psql` command-line interface

This method offers fine-grained control over a role's attributes using SQL commands.

### Step 1: Connect to PostgreSQL

Open your terminal and connect to your PostgreSQL server. You must connect as a user with `CREATEROLE` privileges, such as the default `postgres` superuser.

```sh
  psql -U postgres
   
```
We can list databases by using the command: \l 
```sh
  \l
                                      List of databases
   Name    | Owner  | Encoding | Locale Provider | Collate | Ctype | ICU Locale | ICU Rules | Access privileges 
-----------+--------+----------+-----------------+---------+-------+------------+-----------+-------------------
 postgres  | wizard | UTF8     | libc            | C       | C     |            |           | 
 template0 | wizard | UTF8     | libc            | C       | C     |            |           | =c/wizard        +
           |        |          |                 |         |       |            |           | wizard=CTc/wizard
 template1 | wizard | UTF8     | libc            | C       | C     |            |           | =c/wizard        +
           |        |          |                 |         |       |            |           | wizard=CTc/wizard
(3 rows)

```
Postgres installation results in the creation of a role which is also called postgres. Postgres role is the default superuser account. Installation also creates a database named postgres. So the first command we use does both logs us into postgres with default database selected for our ease.  

### Step 2: Create a USER with LOGIN attribute
After connecting with your postgres server, you should now create a role, associated with roles are different 
* attributes and 
* privileges.

In the below command you are asking postgres to create a ROLE with an attribute LOGIN & PASSWORD. If a user is created with LOGIN attribute that user can login to postgres as shown in the Step 1:
```sh
  CREATE ROLE web_user WITH LOGIN PASSWORD ‘web_password';
   
```
### Step 3: Create a USER with SUPERUSER privileges
You can also create a user with SUPER USER privileges using the below command. 
```sh
  CREATE ROLE admin_user WITH SUPERUSER LOGIN PASSWORD 'another_secure_password';
   
```
### Step 4: Create a database with owner (option 1) 
As a next step you can create a database with owner mentioned as your newsly created user i.e web_user   
```sh
  CREATE DATABASE web_database OWNER web_user;
   
```

### Step 5: Disconnect from the session to login as new user  (option 2)
Since we have created a user that we can login into postgres using its passwprd, why not create database after logging in with tat user! 
```sh
  \q
   
```
### Step 6: Connect using username & password of web_user
We try starting a psql session but it would not be possible with the below command:   
```sh
  psql -U web_user
   
```
Its because it assumes we have created database with the same name as web_user but the database we have created has a different name i.e web_database.
Since its a different database we have to specify its name with the -d option. 
```sh
  psql -U web_user -d web_database
   
```
if you run the command \l again on psql command-line you will get the following output
```
                                                  List of databases
     Name     |  Owner   | Encoding | Locale Provider | Collate | Ctype | ICU Locale | ICU Rules | Access privileges 
--------------+----------+----------+-----------------+---------+-------+------------+-----------+-------------------
 postgres     | wizard   | UTF8     | libc            | C       | C     |            |           | 
 template0    | wizard   | UTF8     | libc            | C       | C     |            |           | =c/wizard        +
              |          |          |                 |         |       |            |           | wizard=CTc/wizard
 template1    | wizard   | UTF8     | libc            | C       | C     |            |           | =c/wizard        +
              |          |          |                 |         |       |            |           | wizard=CTc/wizard
 web_database | web_user | UTF8     | libc            | C       | C     |            |           | 
(4 rows)
```
This validates that the database has been created, now you can create a table. To solve this exercise, we have selected the following info:

* id: Int  (postgres Serial Type)
* username: String (VARCHAR)
* password: String (VARCHAR)

### Step 7: Create table users within the web_database 
```sql
CREATE TABLE users (
 id SERIAL PRIMARY KEY,
 username VARCHAR(50) UNIQUE NOT NULL,
 password VARCHAR(255) NOT NULL
);
```
When you declare a column as SERIAL, PostgreSQL performs several actions behind the scenes: 
* <b>Creates a sequence:</b> A sequence is a special database object that generates a sequence of numbers. PostgreSQL creates a sequence object with a default name, typically `<table_name>_<column_name>_seq`
* <b>Sets a default value:</b> The column's default value is set to draw the next value from the newly created sequence, using the `nextval()` function.
* <b>Applies a `NOT NULL` constraint:</b> A `NOT NULL` constraint is added to the column to ensure that a value is always present for each new row.
* <b>Manages ownership:</b> The sequence is automatically "owned by" the table column. This means that if the column or the table is dropped, the sequence is automatically dropped as well.

