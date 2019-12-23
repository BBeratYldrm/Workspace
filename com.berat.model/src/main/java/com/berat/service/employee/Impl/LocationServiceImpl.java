package com.berat.service.employee.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.employee.LocationRepository;
import com.berat.domain.employee.Location;
import com.berat.service.employee.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public boolean saveLocation(Location location) {
		return locationRepository.saveLocation(location);
	}

	@Override
	public boolean deleteLocation(Location location) {
		return locationRepository.deleteLocation(location);
	}

	@Override
	public Location updateLocation(Location location) {
		return locationRepository.updateLocation(location);
	}

	@Override
	public Location findLocationById(long locationId) {
		return locationRepository.findLocationById(locationId);
	}

	@Override
	public List<Location> findAllLocations() {
		return locationRepository.findAllLocations();
	}

}
