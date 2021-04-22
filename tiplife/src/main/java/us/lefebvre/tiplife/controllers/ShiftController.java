package us.lefebvre.tiplife.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

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
import us.lefebvre.tiplife.controllers.dao.ShiftDAO;
import us.lefebvre.tiplife.models.Job;
import us.lefebvre.tiplife.models.Shift;

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
		
		if (queryParams.containsKey("date"))
		{
			LocalDate start;
			LocalDate end;
			if(queryParams.get("date").contains(":"))
			{
				String[] range = queryParams.get("date").split(":");
				start = LocalDate.parse(range[0]);
				end = LocalDate.parse(range[1]);
			}
			else
			{
				start = LocalDate.parse(queryParams.get("date"));				
				end = LocalDate.parse(queryParams.get("date"));
			}

			if(shifts.size() > 0)
			{
				for (Shift shift : shifts)
				{
					if(shift.getDate().compareTo(start) < 0 || shift.getDate().compareTo(end) > 0 )
					{
						shifts.remove(shift);
					}
				}
			}
			else
			{
				shifts = shiftDao.findByDate(start, end);
			}
		}

		if (queryParams.containsKey("daypart"))
		{
			String daypart = queryParams.get("daypart");
			
			if(shifts.size() > 0)
			{
				Predicate<Shift> condition = shift -> !shift.getDayPart().toLowerCase().contains(daypart);
		        shifts.removeIf(condition);
			}
			else
			{
				shifts = shiftDao.findByDayPart(daypart);
			}
		}
		
		if (queryParams.containsKey("length"))
		{
			LocalTime low;
			LocalTime high;
			
			if(queryParams.get("length").contains("-"))
			{
				String[] range = queryParams.get("length").split("-");
				low = LocalTime.parse(range[0], DateTimeFormatter.ISO_LOCAL_TIME);
				high = LocalTime.parse(range[1], DateTimeFormatter.ISO_LOCAL_TIME);
			}
			else
			{
				low = LocalTime.parse(queryParams.get("length"), DateTimeFormatter.ISO_LOCAL_TIME);
				high = LocalTime.parse(queryParams.get("length"), DateTimeFormatter.ISO_LOCAL_TIME);
			}
			
			if(shifts.size() > 0)
			{
				if(low.equals(high))
				{
					Predicate<Shift> condition = shift -> shift.getLength().compareTo(low) < 0;
			        shifts.removeIf(condition);
				}
				else
				{
					Predicate<Shift> condition = shift -> (shift.getLength().compareTo(low) < 0 || shift.getLength().compareTo(high) > 0);
			        shifts.removeIf(condition);
				}
			}
			else
			{
				shifts = shiftDao.findByLength(low, high);
			}
		}

		if (queryParams.containsKey("tips"))
		{
			BigDecimal low;
			BigDecimal high;
			
			if(queryParams.get("tips").contains("-"))
			{
				String[] range = queryParams.get("tips").split("-");
				low = new BigDecimal(range[0]);
				high = new BigDecimal(range[1]);
			}
			else
			{
				low = new BigDecimal(queryParams.get("tips"));
				high = new BigDecimal(queryParams.get("tips"));
			}
			
			if(shifts.size() > 0)
			{
				if(low.equals(high))
				{
					Predicate<Shift> condition = shift -> shift.getTips().compareTo(low) < 0;
			        shifts.removeIf(condition);
				}
				else
				{
					Predicate<Shift> condition = shift -> (shift.getTips().compareTo(low) < 0 || shift.getTips().compareTo(high) > 0);
			        shifts.removeIf(condition);
				}
			}
			else
			{
				shifts = shiftDao.findByTips(low, high);
			}
		}

		if (queryParams.containsKey("notes"))
		{
			String notes = queryParams.get("notes");
			
			if(shifts.size() > 0)
			{
				Predicate<Shift> condition = shift -> !shift.getNotes().toLowerCase().contains(notes);
		        shifts.removeIf(condition);
			}
			else
			{
				shifts = shiftDao.findByNotes(notes);
			}
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
