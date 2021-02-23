package com.tiplife.controllers;

import java.util.List;

import com.tiplife.models.Job;

public interface JobDAO
{
	public Job getById(int id);
	public List<Job> findByName(String name);
	public Job create(Job job);
	public Job update(Job job);
}
