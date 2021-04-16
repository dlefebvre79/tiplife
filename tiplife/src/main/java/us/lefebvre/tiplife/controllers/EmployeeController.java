package us.lefebvre.tiplife.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.lefebvre.tiplife.controllers.dao.EmployeeDAO;
import us.lefebvre.tiplife.controllers.dao.JobDAO;
import us.lefebvre.tiplife.models.Employee;
import us.lefebvre.tiplife.models.Job;

@RestController
@RequestMapping(path="/employee")
public class EmployeeController
{
	@Autowired
	EmployeeDAO employeeDao;
	
	@GetMapping("/{id}")
	public Employee getById(@PathVariable int id)
	{
		return employeeDao.getById(id);
	}
	
	@GetMapping()
	public List<Employee> findByName(@RequestParam(required=false) String firstName, @RequestParam(required=false) String lastName)
	{
		String first = "";
		String last = "";
		
		if(firstName == null && lastName == null)
		{
			return employeeDao.findAll();
		}
		else
		{
			if(firstName != null)
			{
				first = firstName;
			}
			if(lastName != null)
			{
				last = lastName;
			}
			return employeeDao.findByName(first, last);
		}
	}
	
	@PostMapping()
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeDao.create(employee);
	}
	
	@PutMapping()
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		return employeeDao.update(employee);
	}
	
	@DeleteMapping()
	public boolean deleteEmployee(@RequestBody Employee employee)
	{
		return employeeDao.delete(employee);
	}
	
}
