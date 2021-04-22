package us.lefebvre.tiplife;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import us.lefebvre.tiplife.controllers.dao.EmployeeDAO;
import us.lefebvre.tiplife.controllers.dao.JobDAO;
import us.lefebvre.tiplife.controllers.dao.ShiftDAO;
import us.lefebvre.tiplife.models.Job;
import us.lefebvre.tiplife.models.Shift;

@SpringBootTest
class TipLifeApplicationTests {

	@Autowired
	ShiftDAO shiftDao;
	@Autowired
	EmployeeDAO employeeDao;
	@Autowired
	JobDAO jobDao;
		
	@Test
	void contextLoads() {
	}

	@Test
	void testGetShiftById()
	{
		Job job = new Job();
		job.setId(1);
		job.setName("Server, Red DT");
		
		Shift expected = new Shift();
		expected.setId(1);
		expected.setJob(job);
		expected.setDayPart("Lunch");
		expected.setDate(LocalDate.parse("2018-01-03"));
		expected.setLength(LocalTime.parse("04:30:00"));
		expected.setTips(BigDecimal.valueOf(86.0));
		expected.setTipout(BigDecimal.valueOf(6.0));
		expected.setNotes("test shift");
		
		Shift actual = shiftDao.getById(expected.getId());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getJob().getId(), actual.getJob().getId());
		assertEquals(expected.getJob().getName(), actual.getJob().getName());
		assertEquals(expected.getDayPart(), actual.getDayPart());
		assertEquals(expected.getDate(), actual.getDate());
		assertEquals(expected.getLength(), actual.getLength());
		assertEquals(expected.getTips(), actual.getTips());
		assertEquals(expected.getTipout(), actual.getTipout());
		assertEquals(expected.getNotes(), actual.getNotes());
	}
	
	
}
