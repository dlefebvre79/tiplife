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

import us.lefebvre.tiplife.controllers.dao.JobDAO;
import us.lefebvre.tiplife.models.Job;

@RestController
@RequestMapping(path="/job")
public class JobController
{
//	@Autowired
//	ShiftDAO shiftDao;
//	@Autowired
//	EmployeeDAO employeeDao;
	@Autowired
	JobDAO jobDao;
	
	@GetMapping("/{id}")
	public Job getById(@PathVariable int id)
	{
		return jobDao.getById(id);
	}
	
	@GetMapping()
	public List<Job> findByName(@RequestParam(required=false) String name)
	{
		if(name != null)
		{
			return jobDao.findByName(name);
		}
		else
		{
			return jobDao.findAll();
		}
	}
	
	@PostMapping()
	public Job createJob(@RequestBody Job job)
	{
		return jobDao.create(job);
	}
	
	@PutMapping()
	public Job updateJob(@RequestBody Job job)
	{
		return jobDao.update(job);
	}
	
	@DeleteMapping()
	public boolean deleteJob(@RequestBody Job job)
	{
		return jobDao.delete(job);
	}
	
}
