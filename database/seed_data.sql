INSERT INTO employee (first_name, last_name) VALUES ('David', 'LeFebvre');                          -- employee name
INSERT INTO day_part (name) VALUES ('Lunch'), ('Dinner');                                           -- shift names
INSERT INTO job (name) VALUES ('Server, Red DT');
INSERT INTO shift (shift_id, job_id,         date, day_part_id, length,     tips,  tipout,        notes) VALUES
                  (       1,      1, '2018-01-03',           1, '4:30',  '86.00',  '6.00', 'test shift');
INSERT INTO shift (shift_id, job_id,         date, day_part_id, length,     tips,  tipout,        notes) VALUES
                  (       2,      1, '2018-01-04',           2, '6:15', '257.00', '55.00', 'nothing of note');
INSERT INTO shift (shift_id, job_id,         date, day_part_id, length,     tips,  tipout,       notes) VALUES
                  (       3,      1, '2018-01-07',           2, '9:01', '462.00','115.00', 'Cavs game');
INSERT INTO employee_shift (shift_id, employee_id) VALUES (1, 1);
INSERT INTO employee_shift (shift_id, employee_id) VALUES (2, 1);
INSERT INTO employee_shift (shift_id, employee_id) VALUES (3, 1);