package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;


public class LocationConverter implements Converter<String,Location> {
	
	@Autowired
	LocationService LocationServiceImpl;
	
	public Location convert(String id) {
		Location location=LocationServiceImpl.findById(Integer.parseInt(id));
		return location;
	}
}
