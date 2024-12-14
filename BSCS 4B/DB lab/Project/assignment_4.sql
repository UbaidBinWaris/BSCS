use assignment_4;

-- create table countries(
-- countary_id varchar(50),
-- countary_name varchar(50),
-- region_id varchar(50)
-- );


-- create table if not exists countries (
--     country_id int ,
--     country_name varchar(50),
--     region_id int
-- );

-- create table dup_countries as
-- select * from countries where 1 = 0;

-- drop table dup_countries;


-- create table dup_countries as
-- select * from countries;

drop table countries; 


create table countries (
    country_id int null,
    country_name varchar(100) null,
    region_id int null
);


create table jobs (
    job_id int,
    job_title varchar(100),
    min_salary int,
    max_salary int,
    check (max_salary <= 25000)
);

create table countries (
  country_id int,
  country_name varchar(100),
  region_id int,
  check (country_name in ('Italy', 'India', 'China'))  
);

drop table job_history;

create table job_history (
  employee_id int,
  start_date date,
  end_date date,
  job_id int,
  department_id int
);

create table countries (
  country_id int,
  country_name varchar(100),
  region_id int,
  unique (country_id)
);

drop table jobs;

create table jobs (
  job_id int,
  job_title varchar(100) default '',
  min_salary int default 8000,
  max_salary int default null
);


create table countries (
    country_id int primary key,
    country_name varchar(100),
    region_id int
);

create table countries (
    country_id int auto_increment primary key,
    country_name varchar(100),
    region_id int
);

create table countries (
    countary_id int,
    country_name varchar(100),
    region_id int,
    constraint unique_countary_region unique (countary_id, region_id)
);





