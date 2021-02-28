package com.tiplife.controllers.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.tiplife.models.Job;
import com.tiplife.models.Shift;

public interface ShiftDAO
{
	public Shift getById(int id);
	public List<Shift> findByJob(Job job);
	public List<Shift> findByDate(LocalDate date);
	public List<Shift> findByDayPart(String daypart);
	public List<Shift> findByLength(LocalTime low, LocalTime high);
	public List<Shift> findByTips(BigDecimal low, BigDecimal high);
	public List<Shift> findByNotes(String notes);
	public Shift create(Shift shift);
	public Shift update(Shift shift);
	public boolean delete(Shift shift);
}
