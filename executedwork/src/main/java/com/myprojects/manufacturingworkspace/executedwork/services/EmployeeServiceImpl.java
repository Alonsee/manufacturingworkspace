package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.repository.*;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository EmployeeRepositoryImpl;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepositoryImpl) {
		this.EmployeeRepositoryImpl=employeeRepositoryImpl;
	}
	public EmployeeServiceImpl() {};
	@Override
	public void createEmployee(Employee employee) {
		EmployeeRepositoryImpl.createEmployee(employee);
	}
	@Override
	public void updateEmployee(Employee employee) {
		EmployeeRepositoryImpl.updateEmployee(employee);
	}
	@Override
	public void deleteEmployee(Employee employee) {
		EmployeeRepositoryImpl.deleteEmployee(employee);
	}
	@Override
	public Employee findById(int id) {
		return EmployeeRepositoryImpl.findById(id);
	}
	@Override
	public List<Employee> selectAll() {
		return EmployeeRepositoryImpl.selectAll();
	}
	
}
