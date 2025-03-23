use db_lab;

-- Table 1: employees


CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    department_id INT,
    salary DECIMAL(10, 2),
    hire_date DATE,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
);
ALTER TABLE employees 
ADD CONSTRAINT fk_department
FOREIGN KEY (department_id) REFERENCES departments(department_id);


-- Table 2: departments
CREATE TABLE departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(50),
    manager_id INT
);

-- Table 3: managers
CREATE TABLE managers (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    manager_name VARCHAR(100)
);

-- Table 4: projects
CREATE TABLE projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_name VARCHAR(100),
    start_date DATE,
    end_date DATE
);

-- Table 5: employee_projects
CREATE TABLE employee_projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

-- Table 6: salaries
CREATE TABLE salaries (
    salary_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    base_salary DECIMAL(10, 2),
    bonus DECIMAL(10, 2),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

-- Insert data into departments
INSERT INTO departments (department_name, manager_id)
VALUES
    ('HR', 1),
    ('Finance', 2),
    ('IT', 3),
    ('Sales', 4),
    ('Marketing', 5),
    ('Operations', 6);

-- Insert data into managers
INSERT INTO managers (manager_name)
VALUES
    ('Megan Carter'),
    ('Steven Clark'),
    ('Rachel Adams'),
    ('Michael Johnson'),
    ('Sarah Taylor'),
    ('James Brown');

-- Insert data into employees
INSERT INTO employees (name, department_id, salary, hire_date)
VALUES
    ('John Doe', 1, 55000.00, '2018-06-23'),
    ('Jane Smith', 2, 70000.00, '2017-04-12'),
    ('Alice Johnson', 3, 90000.00, '2019-10-05'),
    ('Bob Brown', 4, 65000.00, '2020-03-19'),
    ('Charlie Davis', 5, 60000.00, '2021-09-14'),
    ('Emily Wilson', 3, 75000.00, '2016-11-25'),
    ('Frank Thomas', 2, 85000.00, '2015-01-30'),
    ('Grace Lee', 1, 50000.00, '2022-07-11'),
    ('Henry White', 4, 62000.00, '2018-08-22'),
    ('Ivy Green', 5, 58000.00, '2020-12-02');
    
INSERT INTO employees (name, department_id, salary, hire_date)
VALUES
    ('John', 3, 55000.00, '2018-06-23'),
    ('Jane ', 1, 70000.00, '2017-04-12'),
    ('Alice ', 6, 90000.00, '2019-10-05'),
    ('Bob ', 2, 65000.00, '2020-03-19');
-- Insert data into projects
INSERT INTO projects (project_name, start_date, end_date)
VALUES
    ('Project Alpha', '2022-01-01', '2022-06-30'),
    ('Project Beta', '2021-03-01', '2021-12-31'),
    ('Project Gamma', '2020-05-15', '2020-11-30'),
    ('Project Delta', '2019-07-01', '2020-01-15'),
    ('Project Epsilon', '2023-02-01', '2023-08-01'),
    ('Project Zeta', '2022-09-01', '2023-03-01'),
    ('Project Eta', '2021-04-01', '2021-10-01'),
    ('Project Theta', '2020-08-15', '2021-02-15'),
    ('Project Iota', '2023-01-15', '2023-07-15'),
    ('Project Kappa', '2018-02-01', '2018-08-01');

-- Insert data into employee_projects
INSERT INTO employee_projects (employee_id, project_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);
    
INSERT INTO employee_projects (employee_id, project_id)
VALUES
    (1, 5),
    (2, 4);
    
    
    INSERT INTO employee_projects (employee_id, project_id)
VALUES
    (1, 4),
    (1, 7);

-- Insert data into salaries
INSERT INTO salaries (employee_id, base_salary, bonus)
VALUES
    (1, 50000.00, 5000.00),
    (2, 65000.00, 5000.00),
    (3, 85000.00, 5000.00),
    (4, 60000.00, 5000.00),
    (5, 55000.00, 5000.00),
    (6, 70000.00, 5000.00),
    (7, 80000.00, 5000.00),
    (8, 45000.00, 5000.00),
    (9, 58000.00, 4000.00),
    (10, 54000.00, 4000.00);
    
    
Select employees.name, departments.department_name
from employees inner join departments
	on employees.department_id = departments.department_id;

Select employees.name, projects.project_name
from employees inner join employee_projects inner join projects
	on employees.id = employee_projects.employee_id AND employee_projects.project_id = projects.project_id;

select SUM(bonus),departments.department_name
from salaries inner join employees inner join departments
	on salaries.employee_id = employees.id AND employees.department_id = departments.department_id
Group by departments.department_name;

select distinct managers.manager_name, departments.department_name
from managers inner join departments on departments.department_id = managers.manager_id;

select distinct employees.name from employees inner join employee_projects
	on employees.id = employee_projects.employee_id
where employees.id in (select count(employee_id)
						from employee_projects
                        group by project_id 
                        having count(employee_id) > 1);
                        
                        
select employees.name
from employees 
where id in (select Count ())
group by employees.name
having count(employee_id) > 1;


select employees.name, departments.department_name, managers.manager_name
from employees inner join departments inner join managers
	on employees.department_id = departments.department_id AND departments.manager_id = managers.manager_id;


select employees.name, projects.project_name
from projects inner join employee_projects inner join employees
	on projects.project_id = employee_projects.project_id AND employee_projects.employee_id = employees.id;
    

select departments.department_name, SUM(salary)
from employees inner join departments 
	on employees.department_id = departments.department_id
group by departments.department_name;


select employees.name
from employees
where employees.id not in (select employee_id from employee_projects);
    
    

select name, departments.department_name
from employees inner join departments on departments.employees = employees.id
where id in (select AVG(salary) )



