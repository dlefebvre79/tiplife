INSERT INTO employee (first_name, last_name) VALUES ('David', 'LeFebvre');                          -- employee name
INSERT INTO tip_type (name) VALUES ('Cash'), ('Credit Card');                                       -- types of tips earned
INSERT INTO day_part (name) VALUES ('Lunch'), ('Dinner');                                           -- shift names
INSERT INTO job (name, wage, overtime_rate) VALUES ('Server, Red DT', '4.15', 1.5);
INSERT INTO tipout_category (name) VALUES ('food'), ('drinks'), ('door');                           -- categories for calculating tipouts
INSERT INTO tipout (name, job_id, tipout_category_id, rate, flat_rate) VALUES
        ('Busser', 1, 1, 0.015, FALSE)                                                              -- 1.5% to bussers on food sales
        , ('Runner', 1, 1, 0.02, FALSE)                                                             -- 2% to runners on food sales
        , ('Bar', 1, 2, 0.05, FALSE)                                                                -- 5% to bartenders on drink sales
        , ('Door', 1, 3, 3.0, TRUE);                                                               -- $3 to each host, flat rate per shift
INSERT INTO period (first_date, interval_number, interval_type) VALUES ('2018-01-01', 2, 'week');   -- initial pay period starting 1-1-2018, running biweekly

INSERT INTO shift (job_id, date, day_part_id, length, notes) VALUES
        (1, '2018-01-03', 2, '5:30', '');
INSERT INTO tips (tip_type_id, shift_id, amount, declared) VALUES
        (1, 1, '46.00', FALSE), (2, 1, '86.48', TRUE);
INSERT INTO shift_tipout_category (tipout_category_id, shift_id, amount) VALUES
        (1, 1, '720.50'), (2, 1, '60.08');

INSERT INTO employee_shift (shift_id, employee_id) VALUES (1, 1);
INSERT INTO period_shift (period_id, shift_id) VALUES (1, 1);
INSERT INTO employee_tipout (employee_id, tipout_id) VALUES (1, 1);
INSERT INTO shift_tipout (shift_id, tipout_id) VALUES (1, 1), (1, 2);
