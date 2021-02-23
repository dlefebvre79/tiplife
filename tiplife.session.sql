SELECT e.first_name
    , e.last_name
    , s.date
    , dp.name AS shift
    , s.notes
    , j.name AS job
    , (SELECT amount AS cash_tips FROM tips WHERE tip_type_id = 1)
    , (SELECT amount AS cc_tips FROM tips WHERE tip_type_id = 2)
FROM employee AS e
JOIN employee_shift AS es
    ON e.employee_id = es.employee_id
JOIN shift AS s
    ON es.shift_id = s.shift_id
JOIN job as j
    ON s.job_id = j.job_id
JOIN day_part AS dp
    ON s.day_part_id = dp.day_part_id
JOIN tips as t
    ON s.shift_id = t.shift_id
GROUP BY e.first_name
    , e.last_name
    , s.date
    , shift
    , s.notes
    , job
    , cash_tips
    , cc_tips;