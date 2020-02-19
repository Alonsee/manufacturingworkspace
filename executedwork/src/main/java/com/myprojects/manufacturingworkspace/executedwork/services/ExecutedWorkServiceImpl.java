package com.myprojects.manufacturingworkspace.executedwork.services;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.repository.ExecutedWorkRepositoryImpl;

@Service
public class ExecutedWorkServiceImpl implements ExecutedWorkService{
	
	@Autowired
	private ExecutedWorkRepositoryImpl ExecutedWorkRepositoryImpl;
	
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
	public List<ExecutedWork> findByEmployeeId(int id) {
		return ExecutedWorkRepositoryImpl.findByEmployeeId(id);
	}
	@Override
	public List<ExecutedWork> selectAll() {
		return ExecutedWorkRepositoryImpl.selectAll();
	}
	@Override
	public List<ExecutedWork> findByLocationId(int id) {
		return ExecutedWorkRepositoryImpl.findByLocationId(id);
	}
	@Override
	public List<ExecutedWork> findByLocationIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish) {
		return ExecutedWorkRepositoryImpl.findByLocationIdAndTime(id, datestart, datefinish);
	}
	@Override
	public List<ExecutedWork> findByEmployeeIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish) {
		return ExecutedWorkRepositoryImpl.findByEmployeeIdAndTime(id, datestart, datefinish);
	}
	@Override
	public ExecutedWork findById(int id) {
		return ExecutedWorkRepositoryImpl.findById(id);
	}
	@Override
	public List<ExecutedWork> searchByParams(String title, String designation, Integer employeeid, Integer locationid,
			GregorianCalendar searchstart, GregorianCalendar searchfinish) {
			if(employeeid!=null||title==""||designation==""||locationid==null) return findByEmployeeId(employeeid);
			return null;
	}
}
