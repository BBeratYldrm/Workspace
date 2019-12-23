package com.berat.service.employee.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.employee.JobRepository;
import com.berat.domain.employee.Job;
import com.berat.service.employee.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Override
	public boolean saveJob(Job job) {
		return jobRepository.saveJob(job);
	}

	@Override
	public boolean deleteJob(Job job) {
		return jobRepository.deleteJob(job);
	}

	@Override
	public Job updateJob(Job job) {
		return jobRepository.updateJob(job);
	}

	@Override
	public Job findJobById(long jobId) {
		return jobRepository.findJobById(jobId);
	}

	@Override
	public List<Job> findAllJobs() {
		return jobRepository.findAllJobs();
	}

	@Override
	public List<String> findJobTitles() {
		return jobRepository.findJobTitles();
	}

}
