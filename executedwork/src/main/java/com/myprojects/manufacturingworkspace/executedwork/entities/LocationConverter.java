package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;

//converter from location id to location
public class LocationConverter implements Converter<String,Location> {
	
	private LocationService locationService;
	
	public LocationConverter(LocationService locationService) {
		this.locationService = locationService;
	}
	public Location convert(String id) {
		Location location = locationService.findById(Integer.parseInt(id));
		return location;
	}
}
