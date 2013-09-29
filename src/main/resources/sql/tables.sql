CREATE TABLE access
(
  id INT NOT NULL AUTO_INCREMENT,
  accessname VARCHAR(45) NOT NULL,
  PRIMARY KEY ( id, accessname )
);
CREATE UNIQUE INDEX unique_accessname ON access ( accessname );
CREATE UNIQUE INDEX unique_id ON access ( id );

CREATE TABLE employee
(
  id INT PRIMARY KEY NOT NULL,
  activation VARCHAR(50),
  email VARCHAR(64),
  identification VARCHAR(50),
  lastResetDate DATE,
  password VARCHAR(200),
  registerDate DATE,
  resetCount INT,
  sendemail INT,
  username VARCHAR(50) NOT NULL,
  access VARCHAR(100) NOT NULL
);

CREATE TABLE employee_group
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(45),
  access VARCHAR(100) NOT NULL,
  groupdesc VARCHAR(100) NOT NULL
);

CREATE TABLE employee_group_map
(
  id INT PRIMARY KEY NOT NULL,
  employeeid INT NOT NULL,
  groupid INT NOT NULL
);
CREATE UNIQUE INDEX unique_id ON employee_group_map ( id );


// create view of employee_group_view
CREATE
ALGORITHM = UNDEFINED
DEFINER = `root`@`localhost`
SQL SECURITY DEFINER
VIEW `employee_group_view` AS
select
  `employee`.`username` AS `username`,
  `employee`.`password` AS `password`,
  `employee_group`.`group_name` AS `group_name`
from
    ((`employee_group_map`
    join `employee` ON ((`employee_group_map`.`employeeid` = `employee`.`id`)))
join `employee_group` ON ((`employee_group_map`.`groupid` = `employee_group`.`id`)));

CREATE TABLE patient
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  identification VARCHAR(45),
  CIN VARCHAR(45),
  PIN VARCHAR(45)
);
