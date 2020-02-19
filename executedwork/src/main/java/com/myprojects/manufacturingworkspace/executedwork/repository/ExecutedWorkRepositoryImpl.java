package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;

@Component
public class ExecutedWorkRepositoryImpl implements ExecutedWorkRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createExecutedWork(ExecutedWork executedwork) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(executedwork);
		tr.commit();
		session.close();
		
	}
	@Override
	public void updateExecutedWork(ExecutedWork executedwork) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.update(executedwork);
		tr.commit();
		session.close();
	}
	@Override
	public void deleteExecutedWork(ExecutedWork executedwork) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.delete(executedwork);
		tr.commit();
		session.close();
	}

	public List<ExecutedWork> findByEmployeeId(int id) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where employee_id=:employeeid")
				.setParameter("employeeid", String.valueOf(id));
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew=(List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	
	public List<ExecutedWork> findByLocationId(int id) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where location_id=:location")
				.setParameter("location", String.valueOf(id));
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew=(List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	
	public List<ExecutedWork> findByLocationIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where location_id=:location and datestart>=:datestart and datefinish<=:datefinish")
				.setParameter("location", String.valueOf(id))
				.setParameter("datestart",datestart)
				.setParameter("datefinish",datefinish);
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew=(List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	
	public List<ExecutedWork> findByEmployeeIdAndTime(int id, GregorianCalendar datestart, GregorianCalendar datefinish) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where employee_id=:employee and datestart>=:datestart and datefinish<=:datefinish")
				.setParameter("employee", String.valueOf(id))
				.setParameter("datestart",datestart)
				.setParameter("datefinish",datefinish);
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew=(List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	
	public List<ExecutedWork> selectAll() {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<ExecutedWork> query= (Query<ExecutedWork>) session.createQuery("from ExecutedWork order by id desc");
		query.setMaxResults(20);
		List<ExecutedWork> ew=(List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	
	public ExecutedWork findById(int id) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where id=:id")
				.setParameter("id", id);
		ExecutedWork ew=(ExecutedWork) query.getResultList().get(0);
		session.close();
		return ew;
	}
}
