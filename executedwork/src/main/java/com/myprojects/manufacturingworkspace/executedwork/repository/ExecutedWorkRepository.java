package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.GregorianCalendar;
import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public interface ExecutedWorkRepository {
	void createExecutedWork(ExecutedWork executedwork);
	void updateExecutedWork(ExecutedWork executedwork);
	void deleteExecutedWork(ExecutedWork executedwork);
	public List<ExecutedWork> findByEmployeeId(int id);
	public List<ExecutedWork> findByLocationId(int id);
	public List<ExecutedWork> findByLocationIdAndTime(Location location, GregorianCalendar datestart, GregorianCalendar datefinish);
	public List<ExecutedWork> findByEmployeeIdAndTime(Employee employee, GregorianCalendar datestart, GregorianCalendar datefinish);
	public List<ExecutedWork> selectAll();
	public ExecutedWork findById(int id);
}
