package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;

//converter from employee id to employee
public class EmployeeConverter implements Converter<String, Employee>  {
	
	private EmployeeService employeeService;
	
	public EmployeeConverter(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public Employee convert(String id) {
		Employee employee = employeeService.findById(Integer.parseInt(id));
		return employee;
	}
	
}
