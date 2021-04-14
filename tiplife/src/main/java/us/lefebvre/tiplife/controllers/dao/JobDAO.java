package us.lefebvre.tiplife.controllers.dao;

import java.util.List;

import us.lefebvre.tiplife.models.Job;

public interface JobDAO
{
	public Job getById(int id);
	public List<Job> findByName(String name);
	public List<Job> findAll();
	public Job create(Job job);
	public Job update(Job job);
	public boolean delete(Job job);
}
