create database labtask_3;

use labtask_3;


create table Persons (
    EMPLOYEE_ID int,
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    EMAIL varchar(255),
    PHONE_NUMBER varchar(255),
    HIRE_DATE varchar(255),
    JOB_ID varchar(255),
    SALARY float,
    COMMISSION_PCT float,
    MANAGER_ID int,
    DEPARTMENT_ID int
);
insert into Persons (EMPLOYEE_ID,FIRST_NAME,LAST_NAME,
EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,
DEPARTMENT_ID)
values
(100, 'Steven', 'King', 'SKING', '515.123', '1987-06-17', 'AD-PRESS', 24000, 0.00, 90, 0),
(101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123', '1989-05-18', 'VP', 17000, 0.00, 100, 90),
(102, 'Lex', 'De Haan', 'LDEHAAN', '515.123', '1993-01-13', 'VP', 17000, 0.00, 100, 90),
(103, 'Alexander', 'Hunold', 'AHUNOLD', '590.423', '1990-01-03', 'IT_PROG', 9000, 0.00, 102, 60),
(104, 'Bruce', 'Ernst', 'BERNST', '590.423', '1991-05-21', 'IT_PROG', 6000, 0.00, 103, 60),
(105, 'David', 'Austin', 'DAUSTIN', '590.423', '1997-06-25', 'IT_PROG', 4800, 0.00, 103, 60),
(106, 'Valli', 'Pataballa', 'VPATABALLA', '590.423', '1998-02-07', 'IT_PROG', 4800, 0.00, 103, 60),
(107, 'Diana', 'Loventz', 'DLOVENTZ', '590.423', '1998-02-07', 'IT_PROG', 4200, 0.00, 101, 60),
(108, 'Nancy', 'Greenberg', 'NGREENBERG', '515.124', '1999-08-17', 'FI_MGR', 12000, 0.00, 100, 100),
(109, 'Daniel', 'Faviet', 'DFAVIET', '515.124', '1999-08-17', 'FI_ACCOUNT', 9000, 0.00, 108, 100),
(110, 'John', 'Chen', 'JCHEN', '515.124', '1999-08-17', 'FI_ACCOUNT', 8000, 0.00, 108, 100);


SET SQL_SAFE_UPDATES = 0;


update Persons
set SALARY = 8000
where EMPLOYEE_ID = 105 and SALARY < 5000;


update Persons
set JOB_ID = 'SH_CLERK'
where EMPLOYEE_ID = 118 and DEPARTMENT_ID = 30 and JOB_ID not like 'SH%';


drop table location;
 
create table locations (
    LOCATION_ID decimal(4,0) null,
    STREET_ADDRESS varchar(40) null,
    POSTAL_CODE varchar(12) null,
    CITY varchar(30) null,
    STATE_PROVINCE varchar(25) null,
    COUNTRY_ID varchar(2) null
);  




alter table locations
add column region_id int null;

alter table locations
add column ID int not null first;

alter table locations
modify column region_id int null after state_province;

alter table locations
modify column country_id int;

alter table locations
drop column city;



alter table locations
modify column LOCATION_ID int not null,
modify column region_id int default 0;

alter table locations
add primary key (ID);

alter table locations
add constraint unique_street unique (STREET_ADDRESS);





