# Assignment Description for WebTech-1 Fall 2025 

Initial Servlet framework working has been introduced to you in the class and some of you have also completed the first assignment.

## Assignment 1: Simulate a `login` functionality

You are required to develop an HTML form that has two fields username & password. You are required to validate this by retrunign a response to the client if user/password matched. 
The exercise introduces the basic idea of request/response. First you get the page and you use the same page (form) to submit your userid/password to get validated. 
Userid/password should be in seperate variables (fields) of the Servlet class. 

## Assignment 2: Simulate `login` functionality for multiple users stored within database.   

You are required to use `JDBC` api to connect to database. You have to store multiple users, in a database table and show the above functionality will run for multiple users. 

## Assignment 3: Write a `DAO` for Student table

You are required to write a `DAO` for a student table that has the following fields:
* studentId: Int
* name: String
* semesterGpa: Double
* cumulativeGpa:Double
* email: String

## Assignment 5: Simulate `login` functionality using `DAO`  

You are required to simulate multiple user login as you have completed in assigment 2 but this time using `DAO`.
Add a method validateUser in the UserDAO class (you have been provided) and try achieving the same in this step as you have previousply accomplished with `JDBC`.  