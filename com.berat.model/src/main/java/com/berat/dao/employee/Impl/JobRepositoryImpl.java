package com.berat.dao.employee.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.employee.JobRepository;
import com.berat.domain.employee.Job;

@Repository
@Transactional
public class JobRepositoryImpl implements JobRepository {

	private EntityManager entityManager;

	@Override
	public boolean saveJob(Job job) {
		entityManager.persist(job);
		return true;
	}

	@Override
	public boolean deleteJob(Job job) {
		if (entityManager.contains(job)) {
			entityManager.remove(job);
		} else {
			Job deleteJob = findJobById(job.getJobId());
			entityManager.remove(deleteJob);
		}
		return true;
	}

	@Override
	public Job updateJob(Job job) {
		Job updatedJob = entityManager.merge(job);
		return updatedJob;
	}

	@Override
	public Job findJobById(long jobId) {
		return entityManager.createNamedQuery("Job.findEmployeesById", Job.class).setParameter("jobId", jobId)
				.getSingleResult();
	}

	@Override
	public List<Job> findAllJobs() {
		return entityManager.createNamedQuery("Job.findAll", Job.class).getResultList();
	}

	@Override
	public List<String> findJobTitles() {
		return entityManager.createNamedQuery("Job.findAllByJobTitle", String.class).getResultList();
	}

}
