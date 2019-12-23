package com.berat.service.employee;

import java.util.List;

import com.berat.domain.employee.Job;

public interface JobService {
	
	boolean saveJob(Job job);
	
	boolean deleteJob(Job job);
	
	Job updateJob(Job job);
	
	Job findJobById(long jobId);
	
	List<Job> findAllJobs();
	
	List<String> findJobTitles();
	

}
