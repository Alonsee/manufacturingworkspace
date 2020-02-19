package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createEmployee(Employee employee) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(employee);
		tr.commit();
		session.close();
	}
	@Override
	public void updateEmployee(Employee employee) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.update(employee);
		tr.commit();
		session.close();
	}
	@Override
	public void deleteEmployee(Employee employee) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.delete(employee);
		tr.commit();
		session.close();
	}

	public Employee findById(int id) {
		Session session=sessionFactory.openSession();
		Employee em=session.get(Employee.class, id);
		session.close();
		return em;
	}
	
	public List<Employee> findByLocationId(int id) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from Employee where location_id=:location")
				.setParameter("location", id);
		@SuppressWarnings("unchecked")
		List<Employee> em=(List<Employee>) query.getResultList();
		session.close();
		return em;
	}
	
	public List<Employee> selectAll() {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Employee> em=(List<Employee>) session.createQuery("from Employee").getResultList();
		session.close();
		return em;
	}
}
