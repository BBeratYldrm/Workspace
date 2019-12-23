package com.berat.domain.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
		@NamedQuery(name = "Job.findAllByJobTitle", query = "SELECT j FROM Job j WHERE j.jobTitle = :jobTitle"),
		@NamedQuery(name = "Job.findEmployeesById", query = "SELECT j FROM Job j LEFT OUTER JOIN FETCH j.employees WHERE j.jobId = :jobId")

})
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_Id")
	private long jobId;

	@Column(name = "Meslek_Adý")
	private String jobTitle;

	@Column(name = "Minimum_Maaþ")
	private double minSalary;

	@Column(name = "Maksimum_Maaþ")
	private double maxSalary;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
	private List<Employee> employees;

	public Job() {

	}

	public Job(String jobTitle, double minSalary, double maxSalary, List<Employee> employees) {
		this.jobTitle = jobTitle;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employees = employees;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (jobId ^ (jobId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (jobId != other.jobId)
			return false;
		return true;
	}
}
