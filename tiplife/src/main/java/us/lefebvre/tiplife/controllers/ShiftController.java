package us.lefebvre.tiplife.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tiplife.controllers.dao.EmployeeDAO;
import com.tiplife.controllers.dao.JobDAO;
import com.tiplife.controllers.dao.ShiftDAO;
import com.tiplife.models.Job;
import com.tiplife.models.Shift;

@RestController
@RequestMapping(path="/shift")
public class ShiftController
{
	@Autowired
	ShiftDAO shiftDao;
	@Autowired
	EmployeeDAO employeeDao;
	@Autowired
	JobDAO jobDao;
	
	@GetMapping("/{id}")
	public Shift getById(@PathVariable int id)
	{
		return shiftDao.getById(id);
	}
	
	@GetMapping()
	public List<Shift> getByQuery(@RequestParam Map<String, String> queryParams)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		if (queryParams.containsKey("job"))
		{
			Job job = jobDao.getById(Integer.parseInt(queryParams.get("job")));
			shifts = shiftDao.findByJob(job);
		}
		else if (queryParams.containsKey("date"))
		{
			LocalDate date = LocalDate.parse(queryParams.get("date"));
			shifts = shiftDao.findByDate(date);
		}
		else if (queryParams.containsKey("daypart"))
		{
			shifts = shiftDao.findByDayPart(queryParams.get("daypart"));
		}
		else if (queryParams.containsKey("length-low"))
		{
			LocalTime low = LocalTime.parse(queryParams.get("length-low"));
			LocalTime high = LocalTime.parse(queryParams.get("length-low"));
			if(queryParams.containsKey("length-high"))
			{
				high = LocalTime.parse(queryParams.get("length-high"));
			}
			
			shifts = shiftDao.findByLength(low, high);
		}
		else if (queryParams.containsKey("tips-low"))
		{
			BigDecimal low = new BigDecimal(queryParams.get("tips-low"));
			BigDecimal high = new BigDecimal(queryParams.get("tips-low"));
			if(queryParams.containsKey("tips-high"))
			{
				high = new BigDecimal(queryParams.get("tips-high"));
			}
			
			shifts = shiftDao.findByTips(low, high);
		}
		else if (queryParams.containsKey("notes"))
		{
			shifts = shiftDao.findByNotes(queryParams.get("notes"));
		}
		
		return shifts;
	}
	
	@PostMapping()
	public Shift createShift(@RequestBody Shift shift)
	{
		return shiftDao.create(shift);
	}
	
	@PutMapping()
	public Shift updateShift(@RequestBody Shift shift)
	{
		return shiftDao.update(shift);
	}
	
	@DeleteMapping()
	public boolean deleteShift(@RequestBody Shift shift)
	{
		return shiftDao.delete(shift);
	}
	
}
