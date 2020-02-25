package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.List;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public interface LocationRepository {
	void createLocation(Location location);
	void updateLocation(Location location);
	void deleteLocation(Location location);
	public Location findById(int id);
	public List<Location> selectAll();
}
