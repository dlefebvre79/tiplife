package com.tiplife.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.tiplife.models.Job;

@Component
public class JobSqlDAO implements JobDAO
{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Job getById(int id)
	{
		Job job = new Job();
		
		String sql = "SELECT job_id"
					+ ", name "
					+ "FROM job "
					+ "WHERE job_id = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, id);
		
		if(row.next())
		{
			job = mapRowToJob(row);
		}
		
		return job;
	}

	@Override
	public List<Job> findByName(String name)
	{
		List<Job> jobs = new ArrayList<Job>();
		
		String sql = "SELECT job_id"
					+ ", name "
					+ "FROM job "
					+ "WHERE name = ?;";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, name);
		
		while(row.next())
		{
			jobs.add(mapRowToJob(row));
		}
		
		return jobs;
	}

	@Override
	public Job create(Job job)
	{
		int jobId = getNextId();
		
		String sql = "INSERT INTO job "
					+ "(job_id"
					+ ", name) "
					+ "VALUES "
					+ "(?, ?);";
		
		jdbcTemplate.update(sql,
				jobId,
				job.getName());
		
		return getById(jobId);

	}

	@Override
	public Job update(Job job)
	{
		String sql = "UPDATE job "
				+ "SET name = ? "
				+ "WHERE job_id = ?;";
	
		jdbcTemplate.update(sql, job.getId());
		
		return getById(job.getId());
	}

	private Job mapRowToJob(SqlRowSet row)
	{
		Job job = new Job();
		
		job.setId(row.getInt("job_id"));
		job.setName(row.getString("name"));
		
		return job;
	}
	
	private int getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('job_job_id_seq') AS next_id");
		if(nextIdResult.next()) {
			return nextIdResult.getInt("next_id");
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new job");
		}
	}

}
