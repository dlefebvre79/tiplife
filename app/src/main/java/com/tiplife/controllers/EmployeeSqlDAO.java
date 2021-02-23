package com.tiplife.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.tiplife.models.Employee;

@Component
public class EmployeeSqlDAO implements EmployeeDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee getById(int id)
	{
		Employee employee = new Employee();
		
		String sql = "SELECT employee_id"
					+ ", first_name"
					+ ", last_name "
					+ "FROM employee "
					+ "WHERE employee_id = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);
		
		if(row.next())
		{
			employee = mapRowToEmployee(row);
		}
		
		return employee;
	}

	@Override
	public List<Employee> findByName(String firstName, String lastName)
	{
		List<Employee> employees = new ArrayList<Employee>();
		
		String sql = "SELECT employee_id"
					+ ", first_name"
					+ ", last_name "
					+ "FROM employee "
					+ "WHERE first_name = ? "
						+ "AND last_name = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, firstName, lastName);
		
		while(row.next())
		{
			employees.add(mapRowToEmployee(row));
		}
		
		return employees;
	}

	@Override
	public Employee create(Employee employee)
	{
		int employeeId = getNextId();
		
		String sql = "INSERT INTO employee "
					+ "(employee_id"
					+ ", first_name"
					+ ", last_name) "
					+ "VALUES "
					+ "(?, ?, ?);";
		
		jdbcTemplate.update(sql,
				employeeId,
				employee.getFirstName(),
				employee.getLastName());
		
		return getById(employeeId);
	}

	@Override
	public Employee update(Employee employee)
	{
		String sql = "UPDATE employee "
					+ "SET first_name = ?"
					+ ", last_name = ? "
					+ "WHERE employee_id = ?;";
		
		jdbcTemplate.update(sql,
					employee.getFirstName(),
					employee.getLastName(),
					employee.getId());
		
		return getById(employee.getId());
	}

	
	private Employee mapRowToEmployee(SqlRowSet row)
	{
		Employee employee = new Employee();
		
		employee.setId(row.getInt("employee_id"));
		employee.setFirstName(row.getString("first_name"));
		employee.setLastName(row.getString("last_name"));
		
		return employee;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('employee_employee_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new employee");
		}
	}

}
