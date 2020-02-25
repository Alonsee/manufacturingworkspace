package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;

//converter from location id to location
public class LocationConverter implements Converter<String,Location> {
	
	LocationService LocationServiceImpl;
	
	public LocationConverter(LocationService locationService) {
		this.LocationServiceImpl=locationService;
	}
	public Location convert(String id) {
		Location location=LocationServiceImpl.findById(Integer.parseInt(id));
		return location;
	}
}
