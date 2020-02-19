package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.repository.LocationRepositoryImpl;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepositoryImpl LocationRepositoryImpl;
	
	public LocationServiceImpl() {};
	@Override
	public void createLocation(Location location) {
		LocationRepositoryImpl.createLocation(location);
	}
	@Override
	public void updateLocation(Location location) {
		LocationRepositoryImpl.updateLocation(location);
	}
	@Override
	public void deleteLocation(Location location) {
		LocationRepositoryImpl.deleteLocation(location);
	}
	@Override
	public Location findById(int id) {
		return LocationRepositoryImpl.findById(id);
	}
	@Override
	public List<Location> selectAll() {
		return LocationRepositoryImpl.selectAll();
	}
}
