package com.tiplife.controllers;

import java.util.List;

import com.tiplife.models.Employee;

public interface EmployeeDAO
{
	public Employee getById(int id);
	public List<Employee> findByName(String firstName, String lastName);
	public Employee create(Employee employee);
	public Employee update(Employee employee);
}
