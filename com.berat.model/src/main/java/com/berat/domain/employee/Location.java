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
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
	@NamedQuery(name = "Location.findDepartmentById", query = "SELECT l FROM Location l LEFT OUTER JOIN FETCH l.departmentList WHERE l.locationId = :locationId"),
	
})

public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_Id")
	private long locationId;

	@Column(name = "Sokak_Adresi")
	private String streetAddress;

	@Column(name = "Posta_Kodu")
	private int postalCode;

	@Column(name = "Þehir")
	private String city;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> departmentList;

	public Location() {

	}

	public Location(String streetAddress, int postalCode, String city, List<Department> departmentList) {
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.departmentList = departmentList;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (locationId ^ (locationId >>> 32));
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
		Location other = (Location) obj;
		if (locationId != other.locationId)
			return false;
		return true;
	}

}
