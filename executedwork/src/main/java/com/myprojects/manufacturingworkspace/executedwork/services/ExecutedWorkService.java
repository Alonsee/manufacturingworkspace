package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.GregorianCalendar;
import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public interface ExecutedWorkService {
	public void createExecutedWork(ExecutedWork ew);
	public void updateExecutedWork(ExecutedWork ew);
	public void deleteExecutedWork(ExecutedWork ew);
	public List<ExecutedWork> selectAll();
	public ExecutedWork findById(int id);
	public List<ExecutedWork> searchByParams(String title, String designation, Employee employee, 
			Location location, GregorianCalendar searchstart, GregorianCalendar searchfinish);
}
