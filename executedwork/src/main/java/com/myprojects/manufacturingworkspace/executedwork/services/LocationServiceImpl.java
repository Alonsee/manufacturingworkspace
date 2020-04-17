package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.repository.LocationRepository;

public class LocationServiceImpl implements LocationService {
	
	private LocationRepository locationRepository;
	
	public LocationServiceImpl(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	public LocationServiceImpl() {};
	
	@Override
	public void createLocation(Location location) {
		locationRepository.createLocation(location);
	}
	
	@Override
	public void updateLocation(Location location) {
		locationRepository.updateLocation(location);
	}
	
	@Override
	public void deleteLocation(Location location) {
		locationRepository.deleteLocation(location);
	}
	
	@Override
	public Location findById(int id) {
		return locationRepository.findById(id);
	}
	
	@Override
	public List<Location> selectAll() {
		return locationRepository.selectAll();
	}
}
