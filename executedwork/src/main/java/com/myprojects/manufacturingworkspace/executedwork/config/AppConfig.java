package com.myprojects.manufacturingworkspace.executedwork.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myprojects.manufacturingworkspace.executedwork.entities.EmployeeConverter;
import com.myprojects.manufacturingworkspace.executedwork.entities.LocationConverter;
import com.myprojects.manufacturingworkspace.executedwork.repository.EmployeeRepository;
import com.myprojects.manufacturingworkspace.executedwork.repository.EmployeeRepositoryImpl;
import com.myprojects.manufacturingworkspace.executedwork.repository.ExecutedWorkRepository;
import com.myprojects.manufacturingworkspace.executedwork.repository.ExecutedWorkRepositoryImpl;
import com.myprojects.manufacturingworkspace.executedwork.repository.LocationRepository;
import com.myprojects.manufacturingworkspace.executedwork.repository.LocationRepositoryImpl;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeServiceImpl;
import com.myprojects.manufacturingworkspace.executedwork.services.ExecutedWorkService;
import com.myprojects.manufacturingworkspace.executedwork.services.ExecutedWorkServiceImpl;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public EmployeeRepository employeeRepository(SessionFactory sessionFactory) {
		return new EmployeeRepositoryImpl(sessionFactory);
	}
	
	@Bean
	public LocationRepository locationRepository(SessionFactory sessionFactory) {
		return new LocationRepositoryImpl(sessionFactory);
	}
	
	@Bean
	public ExecutedWorkRepository executedWorkRepository(SessionFactory sessionFactory) {
		return new ExecutedWorkRepositoryImpl(sessionFactory);
	}
	
	@Bean
	public EmployeeService employeeService(EmployeeRepository employeeRepository) {
		return new EmployeeServiceImpl(employeeRepository);
	}
	
	@Bean
	public LocationService locationService(LocationRepository locationRepository) {
		return new LocationServiceImpl(locationRepository);
	}
	
	@Bean
	public ExecutedWorkService executedWorkService(ExecutedWorkRepository executedWorkRepository) {
		return new ExecutedWorkServiceImpl(executedWorkRepository);
	}
	
	@Bean
	public EmployeeConverter employeeConverter(EmployeeService employeeService) {
		return new EmployeeConverter(employeeService);
	}
	
	@Bean
	public LocationConverter locationConverter(LocationService locationService) {
		return new LocationConverter(locationService);
	}
	
}
