package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;

public interface EmployeeRepository {
	void createEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
	public Employee findById(int id);
	public List<Employee> selectAll();
	public List<Employee> findByLocationId(int id);
}
