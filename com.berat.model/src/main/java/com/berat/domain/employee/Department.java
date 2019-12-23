package com.berat.domain.employee;

import java.util.List;

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
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
		@NamedQuery(name = "Department.findByDepartmentName", query = "SELECT d.departmentName FROM Department d"),
		@NamedQuery(name = "Department.findEmployeeAndLocationById", query = "SELECT d FROM Department d LEFT OUTER JOIN FETCH d.location "
				+ "LEFT OUTER JOIN FETCH d.employeeList  WHERE d.departmentId = :departmentId"),
		@NamedQuery(name = "Departments.count", query = "SELECT count(d) FROM Department d")

})
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Departman_Kimliði")
	private long departmentId;

	@Column(name = "Departman_Adý")
	private String departmentName;

	@ManyToOne(targetEntity = Location.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "Lokasyon", foreignKey = @ForeignKey(foreignKeyDefinition = "lokasyon_fk"))
	private Location location;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employeeList;

	// Bir departmanda birden fazla iþçi çalýþabilir.

	public Department() {
	}

	public Department(String departmentName, Location location, List<Employee> employeeList) {
		this.departmentName = departmentName;
		this.location = location;
		this.employeeList = employeeList;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (departmentId ^ (departmentId >>> 32));
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
		Department other = (Department) obj;
		if (departmentId != other.departmentId)
			return false;
		return true;
	}

}
