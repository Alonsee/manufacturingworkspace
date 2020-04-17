package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.List;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.repository.*;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepositoryImpl) {
		this.employeeRepository = employeeRepositoryImpl;
	}
	
	public EmployeeServiceImpl() {};
	
	@Override
	public void createEmployee(Employee employee) {
		employeeRepository.createEmployee(employee);
	}
	
	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.updateEmployee(employee);
	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.deleteEmployee(employee);
	}
	
	@Override
	public Employee findById(int id) {
		return employeeRepository.findById(id);
	}
	
	@Override
	public List<Employee> selectAll() {
		return employeeRepository.selectAll();
	}
	
}
