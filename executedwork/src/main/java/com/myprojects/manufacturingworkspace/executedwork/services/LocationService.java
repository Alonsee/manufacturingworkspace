package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public interface LocationService {
	public void createLocation(Location location);
	public void updateLocation(Location location);
	public void deleteLocation(Location location);
	public Location findById(int id);
	public List<Location> selectAll();
}
