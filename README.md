# tiplife

Simple application for tracking tipped income, using Java, Spring, and PostgreSQL


API Endpoints:


**EMPLOYEE**

/employee/{id} (GET)
-get single employee by id number (int)
-returns Employee object if found, Null if not

/employee?firstName (GET)
-get list of employees where first name contains search string (case insensitive, wildcards added before and after in SQL query)
-returns List<Employee>

/employee?lastName (GET)
-same as above, but last name

(firstName and lastName requestParams can be combined or used separately)

/employee (POST)
-JSON employee object in body, creates new entry in DB
-returns new Employee object

/employee (PUT)
-JSON employee object in body, updates existing entry with JSON data (send all data, whether changed or unchanged)
-returns updated Employee object

/employee (DELETE)
-JSON employee object in body (minimum of just ID attribute), deletes matching entry in DB
-returns boolean: True if successful, False if unsuccessful

**JOB**

/job/{id} (GET)
-get single job by id number (int)
-returns Job object if found, Null if not

/job?name (GET)
-get list of jobs where name contains search string (case insensitive, wildcards added before and after in SQL query)
-returns List<Job>

/job (POST)
-JSON job object in body, creates new entry in DB
-returns new Job object

/job (PUT)
-JSON job object in body, updates existing entry with JSON data (send all data, whether changed or unchanged)
-returns updated Job

/job (DELETE)
-JSON job object in body (minimum of just ID attribute), deletes matching entry in DB
-returns boolean: True if successful, False if unsuccessful

**SHIFT**

/shift/{id} (GET)
-get single shift by id number (int)
-returns Shift object if found, Null if not

/shift?queryParams (GET)
-search for shifts matching search terms:
	"job=" search by jobID (int)
	
	"date=" search by date, yyy-MM-dd format; colon-separated range, else single term used for exact match
	
	"daypart=" search by daypart (breakfast, lunch, dinner, etc - as defined in DB)
	
	"length=" search by length of shift, HH:MM format; dash-separated range, else single term used as floor value

	"tips=" search by tip amount, 0.00 format; dash-separated range, else single term used as floor value

	"notes=" find shifts where notes field contains search term (case-insensitive, wildcards applied in SQL query)

	all parameters optional, can be combined

-returns List<Shift>

/shift (POST)
-JSON shift object in body, creates new entry in DB
-returns new Shift object

/shift (PUT)
-JSON shift object in body, updates existing entry with JSON data (send all data, whether changed or unchanged)
-returns updated Shift object

/shift (DELETE)
-JSON shift object in body (minimum of just ID attribute), deletes matching entry in DB
-returns boolean: True if successful, False if unsuccessful
