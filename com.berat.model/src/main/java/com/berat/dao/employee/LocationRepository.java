package com.berat.dao.employee;

import java.util.List;

import com.berat.domain.employee.Location;

public interface LocationRepository {
	
	boolean saveLocation(Location location);
	
	boolean deleteLocation(Location location);
	
	Location updateLocation(Location location);
	
	Location findLocationById(long locationId);
	
	List<Location> findAllLocations();
	

}
