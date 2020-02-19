package com.myprojects.manufacturingworkspace.executedwork.repository;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;

public interface EmployeeRepository {
	void createEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(Employee employee);
}
