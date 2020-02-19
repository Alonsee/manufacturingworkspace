package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;

public class EmployeeConverter implements Converter<String,Employee>  {
	
	@Autowired
	EmployeeService EmployeeServiceImpl;
	
	public Employee convert(String id) {
		Employee employee=EmployeeServiceImpl.findById(Integer.parseInt(id));
		return employee;
	}
	
}
