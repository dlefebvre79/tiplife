package us.lefebvre.tiplife.controllers.dao;

import java.util.List;

import us.lefebvre.tiplife.models.Employee;

public interface EmployeeDAO
{
	public Employee getById(int id);
	public List<Employee> findByName(String firstName, String lastName);
	public List<Employee> findAll();
	public Employee create(Employee employee);
	public Employee update(Employee employee);
	public boolean delete(Employee employee);
}
