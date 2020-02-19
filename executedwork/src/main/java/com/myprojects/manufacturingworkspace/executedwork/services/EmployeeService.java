package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;

public interface EmployeeService {
	public void createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
	public Employee findById(int id);
	public List<Employee> selectAll();
}
