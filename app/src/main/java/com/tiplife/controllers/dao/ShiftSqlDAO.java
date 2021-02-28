package com.tiplife.controllers.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.tiplife.models.Job;
import com.tiplife.models.Shift;

@Component
public class ShiftSqlDAO implements ShiftDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JobSqlDAO jobDao;
	
	@Override
	public Shift getById(int id)
	{
		Shift shift = new Shift();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.shift_id = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);
		
		if(row.next())
		{
			shift = mapRowToShift(row);
		}
		return shift;
	}

	@Override
	public List<Shift> findByJob(Job job)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.job_id = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, job.getId());
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public List<Shift> findByDate(LocalDate date)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.date = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, date);
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public List<Shift> findByDayPart(String daypart)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE d.name ILIKE ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, daypart);
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public List<Shift> findByLength(LocalTime low, LocalTime high)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.length BETWEEN ? AND ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, low, high);
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public List<Shift> findByTips(BigDecimal low, BigDecimal high)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.tips BETWEEN ? AND ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, low, high);
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public List<Shift> findByNotes(String notes)
	{
		List<Shift> shifts = new ArrayList<Shift>();
		
		String sql = "SELECT s.shift_id"
					+ ", s.job_id"
					+ ", s.date"
					+ ", d.name AS day_part"
					+ ", s.length"
					+ ", s.tips"
					+ ", s.tipout"
					+ ", s.notes "
					+ "FROM shift AS s "
					+ "JOIN day_part AS d "
					+ "ON s.day_part_id = d.day_part_id "
					+ "WHERE s.notes ILIKE %?%;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, notes);
		
		if(row.next())
		{
			shifts.add(mapRowToShift(row));
		}
		return shifts;
	}

	@Override
	public Shift create(Shift shift)
	{
		int shiftId = getNextId();
		
		String sql = "INSERT INTO shift "
					+ "(shift_id"
					+ ", job_id"
					+ ", date"
					+ ", day_part_id"
					+ ", length"
					+ ", tips"
					+ ", tipout"
					+ ", notes "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?);";
		
		jdbcTemplate.update(sql,
					shiftId,
					shift.getJob().getId(),
					shift.getDate(),
					getDayPartId(shift.getDayPart()),
					shift.getLength(),
					shift.getTips(),
					shift.getTipout(),
					shift.getNotes());
					
		return getById(shiftId);
	}

	@Override
	public Shift update(Shift shift)
	{
		String sql = "UPDATE shift "
					+ "SET job_id = ?"
					+ ", date = ?"
					+ ", day_part_id =?"
					+ ", length = ?"
					+ ", tips = ?"
					+ ", tipout =?"
					+ ", notes = ? "
					+ "WHERE shift_id = ?;";
		
		jdbcTemplate.update(sql,
					shift.getJob().getId(),
					shift.getDate(),
					getDayPartId(shift.getDayPart()),
					shift.getLength(),
					shift.getTips(),
					shift.getTipout(),
					shift.getNotes(),
					shift.getId());
					
		return getById(shift.getId());
	}

	@Override
	public boolean delete(Shift shift)
	{
		String sql = "DELETE FROM shift "
					+ "WHERE shift_id = ?;";
		
		Boolean success = jdbcTemplate.update(sql, shift.getId()) == 1;
		
		return success;
	}

	private Shift mapRowToShift(SqlRowSet row)
	{
		Shift shift = new Shift();
		
		shift.setId(row.getInt("shift_id"));
		shift.setJob(jobDao.getById(row.getInt("job_id")));
		shift.setDate(row.getDate("date").toLocalDate());
		shift.setDayPart(row.getString("day_part"));
		shift.setLength(row.getTime("length").toLocalTime());
		shift.setTips(row.getBigDecimal("tips"));
		shift.setTipout(row.getBigDecimal("tipout"));
		shift.setNotes(row.getString("notes"));
		
		return shift;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('shift_shift_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new shift");
		}
	}
	
	private int getDayPartId(String daypart)
	{
		int dayPartId = -1;
		String sql = "SELECT day_part_id "
					+ "FROM day_part "
					+ "WHERE name ILIKE ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, daypart);
		
		if(row.next())
		{
			dayPartId = row.getInt("day_part_id");
		}
		
		return dayPartId;
	}

}
