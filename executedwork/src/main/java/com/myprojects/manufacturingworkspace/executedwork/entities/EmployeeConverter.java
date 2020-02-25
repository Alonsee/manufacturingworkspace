package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;

//converter from employee id to employee
public class EmployeeConverter implements Converter<String,Employee>  {
	
	EmployeeService EmployeeServiceImpl;
	
	public EmployeeConverter(EmployeeService employeeService){
		this.EmployeeServiceImpl=employeeService;
	}
	
	public Employee convert(String id) {
		Employee employee=EmployeeServiceImpl.findById(Integer.parseInt(id));
		return employee;
	}
	
}
