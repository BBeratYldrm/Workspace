package com.berat.domain.employee;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
		@NamedQuery(name = "Employee.findFullById", query = "SELECT e FROM Employee e LEFT OUTER JOIN FETCH e.job LEFT OUTER JOIN FETCH e.department WHERE e.employeeId = :employeeId"),
		@NamedQuery(name = "Employee.count", query = "SELECT count(e) FROM Employee e")

})
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Çalýþan_Kimliði")
	private long employeeId;

	@Column(name = "Adý")
	private String firstName;

	@Column(name = "Soyadý")
	private String lastName;

	@Column(name = "Mail_Adresi")
	private String eMail;

	@Column(name = "Telefon_Numarasý")
	private String phoneNumber;

	@Column(name = "Baþlama_Tarihi")
	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Job.class)
	@JoinColumn(name = "job_Id", foreignKey = @ForeignKey(foreignKeyDefinition = "job_fk"))
	private Job job;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Department.class)
	@JoinColumn(name = "department_Id", foreignKey = @ForeignKey(foreignKeyDefinition = "department_fk"))
	private Department department;

	public Employee(String firstName, String lastName, String eMail, String phoneNumber, Date hireDate, Job job,
			Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.job = job;
		this.department = department;
	}

	public Employee() {
		super();
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
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
		Employee other = (Employee) obj;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}

}
