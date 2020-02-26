package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.GregorianCalendar;
import java.util.List;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.repository.ExecutedWorkRepository;

public class ExecutedWorkServiceImpl implements ExecutedWorkService{
	
	private ExecutedWorkRepository ExecutedWorkRepositoryImpl;
	
	public ExecutedWorkServiceImpl(ExecutedWorkRepository executedWorkRepositoryImpl) {
		this.ExecutedWorkRepositoryImpl=executedWorkRepositoryImpl;
	}
	
	public ExecutedWorkServiceImpl() {};
	
	@Override
	public void createExecutedWork(ExecutedWork ew) {
		ExecutedWorkRepositoryImpl.createExecutedWork(ew);
	}
	@Override
	public void updateExecutedWork(ExecutedWork ew) {
		ExecutedWorkRepositoryImpl.updateExecutedWork(ew);
	}
	@Override
	public void deleteExecutedWork(ExecutedWork ew) {
		ExecutedWorkRepositoryImpl.deleteExecutedWork(ew);
	}
	@Override
	public List<ExecutedWork> selectAll() {
		return ExecutedWorkRepositoryImpl.selectAll();
	}
	@Override
	public List<ExecutedWork> findByLocationIdAndTime(Location location, GregorianCalendar datestart, GregorianCalendar datefinish) {
		return ExecutedWorkRepositoryImpl.findByLocationIdAndTime(location, datestart, datefinish);
	}
	@Override
	public List<ExecutedWork> findByEmployeeIdAndTime(Employee employee, GregorianCalendar datestart, GregorianCalendar datefinish) {
		return ExecutedWorkRepositoryImpl.findByEmployeeIdAndTime(employee, datestart, datefinish);
	}
	@Override
	public ExecutedWork findById(int id) {
		return ExecutedWorkRepositoryImpl.findById(id);
	}
	@Override
	public List<ExecutedWork> searchByParams(String title, String designation, Employee employee, Location location,
			GregorianCalendar searchstart, GregorianCalendar searchfinish) {
			if(employee!=null&title==""&designation==""&location==null&searchstart!=null&searchfinish!=null)
							return findByEmployeeIdAndTime(employee,searchstart,searchfinish);
			if(location!=null&title==""&designation==""&employee==null&searchstart!=null&searchfinish!=null)
				return findByLocationIdAndTime(location,searchstart,searchfinish);
			return null;
	}
}
