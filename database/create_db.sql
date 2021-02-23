DROP TABLE IF EXISTS employee_shift;
DROP TABLE IF EXISTS shift;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS day_part;
DROP TABLE IF EXISTS job;

--TABLES

CREATE TABLE employee
(
    employee_id SERIAL NOT NULL PRIMARY KEY
    , first_name VARCHAR(24) NOT NULL
    , last_name VARCHAR(24) NOT NULL
);

CREATE TABLE employee_shift
(
    shift_id INTEGER NOT NULL
    , employee_id INTEGER NOT NULL
    , PRIMARY KEY (shift_id, employee_id)
);

CREATE TABLE shift
(
    shift_id SERIAL NOT NULL PRIMARY KEY
    , job_id INTEGER NOT NULL
    , date DATE
    , day_part_id INTEGER
    , length TIME
    , tips MONEY
    , tipout MONEY
    , notes VARCHAR(255)
);

CREATE TABLE day_part
(
    day_part_id SERIAL NOT NULL PRIMARY KEY
    , name VARCHAR(24) NOT NULL
);

CREATE TABLE job
(
    job_id SERIAL NOT NULL PRIMARY KEY
    , name VARCHAR(64) NOT NULL
);

--RELATIONSHIPS

ALTER TABLE employee_shift
ADD CONSTRAINT fk_employee_shift_employee
FOREIGN KEY (employee_id)
REFERENCES employee (employee_id);

ALTER TABLE employee_shift
ADD CONSTRAINT fk_employee_shift_shift
FOREIGN KEY (shift_id)
REFERENCES shift (shift_id);

ALTER TABLE shift
ALTER COLUMN date
SET DEFAULT CURRENT_DATE;

ALTER TABLE shift
ADD CONSTRAINT fk_shift_job
FOREIGN KEY (job_id)
REFERENCES job (job_id);

ALTER TABLE shift
ADD CONSTRAINT fk_shift_day_part
FOREIGN KEY (day_part_id)
REFERENCES day_part (day_part_id);