package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.GregorianCalendar;
import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;

public interface ExecutedWorkService {
	public void createExecutedWork(ExecutedWork ew);
	public void updateExecutedWork(ExecutedWork ew);
	public void deleteExecutedWork(ExecutedWork ew);
	public List<ExecutedWork> findByEmployeeId(int id);
	public List<ExecutedWork> selectAll();
	public List<ExecutedWork> findByLocationId(int id);
	public List<ExecutedWork> findByLocationIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish);
	public List<ExecutedWork> findByEmployeeIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish);
	public ExecutedWork findById(int id);
	public List<ExecutedWork> searchByParams(String title, String designation,Integer employeeid,
												Integer locationid,GregorianCalendar searchstart,
												GregorianCalendar searchfinish);
}
