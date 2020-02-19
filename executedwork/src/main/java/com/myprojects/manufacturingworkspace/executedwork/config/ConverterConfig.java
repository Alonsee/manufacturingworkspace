package com.myprojects.manufacturingworkspace.executedwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myprojects.manufacturingworkspace.executedwork.entities.EmployeeConverter;
import com.myprojects.manufacturingworkspace.executedwork.entities.LocationConverter;

@Configuration
public class ConverterConfig {

	@Bean
	public EmployeeConverter employeeConverter() {
		return new EmployeeConverter();
	}
	
	@Bean
	public LocationConverter locationConverter() {
		return new LocationConverter();
	}
	
}
