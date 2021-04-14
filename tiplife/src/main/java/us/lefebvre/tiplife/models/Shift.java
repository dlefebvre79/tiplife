package us.lefebvre.tiplife.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class Shift
{
	private static final int MINUTES_PER_HOUR = 60;
	
	private int id;
	private Job job;
	private String dayPart;
	private LocalDate date;
	private LocalTime length;
	private BigDecimal tips;
	private BigDecimal tipout;
	private String notes;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Job getJob()
	{
		return job;
	}

	public void setJob(Job job)
	{
		this.job = job;
	}

	public String getDayPart()
	{
		return dayPart;
	}

	public void setDayPart(String dayPart)
	{
		this.dayPart = dayPart;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getLength()
	{
		return length;
	}

	public void setLength(LocalTime length)
	{
		this.length = length;
	}

	public BigDecimal getTips()
	{
		return tips;
	}

	public void setTips(BigDecimal tips)
	{
		this.tips = tips;
	}

	public BigDecimal getTipout()
	{
		return tipout;
	}

	public void setTipout(BigDecimal tipout)
	{
		this.tipout = tipout;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public BigDecimal getHourlyTips()
	{
		BigDecimal totalTips = getTips();
		return totalTips.divide(getDecimalHours(), 2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal getDecimalHours()
	{
		int hours = this.getLength().getHour();
		int minutes = this.getLength().getMinute();
		int totalMinutes = (hours * MINUTES_PER_HOUR) + minutes;
		BigDecimal decimalHours = BigDecimal.valueOf(totalMinutes)
				.divide(BigDecimal.valueOf(MINUTES_PER_HOUR), 2, RoundingMode.HALF_UP);
		return decimalHours;
	}
}
