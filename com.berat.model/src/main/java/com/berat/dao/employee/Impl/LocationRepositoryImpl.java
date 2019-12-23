package com.berat.dao.employee.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.employee.LocationRepository;
import com.berat.domain.employee.Location;

@Repository
@Transactional
public class LocationRepositoryImpl implements LocationRepository {

	private EntityManager entityManager;

	@Override
	public boolean saveLocation(Location location) {
		entityManager.persist(location);
		return true;
	}

	@Override
	public boolean deleteLocation(Location location) {
		if (entityManager.contains(location)) {
			entityManager.remove(location);
		} else {
			Location deleteLocation = findLocationById(location.getLocationId());
			entityManager.remove(deleteLocation);
		}
		return true;
	}

	@Override
	public Location updateLocation(Location location) {
		Location updatedLocation = entityManager.merge(location);
		return updatedLocation;
	}

	@Override
	public Location findLocationById(long locationId) {
		return entityManager.createNamedQuery("Location.findDepartmentById", Location.class)
				.setParameter("locationId", locationId).getSingleResult();
	}

	@Override
	public List<Location> findAllLocations() {
		return entityManager.createNamedQuery("Location.findAll", Location.class).getResultList();
	}

}
