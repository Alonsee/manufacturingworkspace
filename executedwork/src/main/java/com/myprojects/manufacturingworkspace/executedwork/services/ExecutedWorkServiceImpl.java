package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.repository.ExecutedWorkRepository;

public class ExecutedWorkServiceImpl implements ExecutedWorkService{
	
	private ExecutedWorkRepository executedWorkRepository;
	
	public ExecutedWorkServiceImpl(ExecutedWorkRepository executedWorkRepositoryImpl) {
		this.executedWorkRepository = executedWorkRepositoryImpl;
	}
	
	public ExecutedWorkServiceImpl() {};
	
	@Override
	public void createExecutedWork(ExecutedWork ew) {
		executedWorkRepository.createExecutedWork(ew);
	}
	
	@Override
	public void updateExecutedWork(ExecutedWork ew) {
		executedWorkRepository.updateExecutedWork(ew);
	}
	
	@Override
	public void deleteExecutedWork(ExecutedWork ew) {
		executedWorkRepository.deleteExecutedWork(ew);
	}
	
	@Override
	public List<ExecutedWork> selectAll() {
		return executedWorkRepository.selectAll();
	}
	
	@Override
	public ExecutedWork findById(int id) {
		return executedWorkRepository.findById(id);
	}
	
	@Override
	public List<ExecutedWork> searchByParams(String title, String designation, 
			Employee employee, Location location, GregorianCalendar searchstart, 
			GregorianCalendar searchfinish) {
		
		searchfinish.set(Calendar.HOUR_OF_DAY, 23);
		searchfinish.set(Calendar.MINUTE,59);
		searchfinish.set(Calendar.SECOND,59);
		searchfinish.set(Calendar.MILLISECOND,999);

		if (!title.equals("") & designation.equals("")) {
			return executedWorkRepository.findByTitle(title);
		}
			
		if (!designation.equals("") & title.equals("")) {
			return executedWorkRepository.findByDesignation(designation);
		}
			
		if ((employee != null) & title.equals("") & designation.equals("") & (location == null)) {
			return executedWorkRepository.findByEmployeeIdAndTime(employee,searchstart,searchfinish);
		}
			
		if ((location != null) & title.equals("") & designation.equals("") & (employee == null)){
			return executedWorkRepository.findByLocationIdAndTime(location,searchstart,searchfinish);
		}
			
		return null;
	}
}
