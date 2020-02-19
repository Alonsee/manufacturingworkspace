package com.myprojects.manufacturingworkspace.executedwork.repository;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public interface LocationRepository {
	void createLocation(Location location);
	void updateLocation(Location location);
	void deleteLocation(Location location);
}
