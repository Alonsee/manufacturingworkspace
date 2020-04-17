package com.myprojects.manufacturingworkspace.executedwork.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public class ExecutedWorkRepositoryImpl implements ExecutedWorkRepository {

	private SessionFactory sessionFactory;
	
	public ExecutedWorkRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public ExecutedWorkRepositoryImpl() {};
	
	@Override
	public void createExecutedWork(ExecutedWork executedwork) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(executedwork);
		tr.commit();
		session.close();
		
	}
	@Override
	public void updateExecutedWork(ExecutedWork executedwork) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(executedwork);
		tr.commit();
		session.close();
	}
	@Override
	public void deleteExecutedWork(ExecutedWork executedwork) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(executedwork);
		tr.commit();
		session.close();
	}
	@Override
	public List<ExecutedWork> findByLocationIdAndTime(Location location, 
			GregorianCalendar datestart, GregorianCalendar datefinish) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where location = :location and datestart >= :datestart and datefinish <= :datefinish")
				.setParameter("location", location)
				.setParameter("datestart",datestart)
				.setParameter("datefinish",datefinish);
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew = (List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	@Override
	public List<ExecutedWork> findByEmployeeIdAndTime(Employee employee, 
			GregorianCalendar datestart, GregorianCalendar datefinish) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where employee = :employee and datestart >= :datestart and datefinish <= :datefinish")
				.setParameter("employee", employee)
				.setParameter("datestart",datestart)
				.setParameter("datefinish",datefinish);
		@SuppressWarnings("unchecked")
		List<ExecutedWork> ew = (List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	@Override
	public List<ExecutedWork> selectAll() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<ExecutedWork> query = (Query<ExecutedWork>) session.createQuery("from ExecutedWork order by id desc");
		query.setMaxResults(15);
		List<ExecutedWork> ew = (List<ExecutedWork>) query.getResultList();
		session.close();
		return ew;
	}
	@Override
	public ExecutedWork findById(int id) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from ExecutedWork where id = :id")
				.setParameter("id", id);
		ExecutedWork ew = (ExecutedWork) query.getResultList().get(0);
		session.close();
		return ew;
	}
	
	@Override
	public List<ExecutedWork> findByTitle(String title) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ExecutedWork> cq = cb.createQuery(ExecutedWork.class);
		Root<ExecutedWork> root = cq.from(ExecutedWork.class);
		cq.select(root).where(cb.like(root.get("title"), '%'+title+'%'));
		Query<ExecutedWork> query = session.createQuery(cq);
		List<ExecutedWork> ew = query.getResultList();
		session.close();
		return ew;
	}
	
	@Override
	public List<ExecutedWork> findByDesignation(String designation) {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<ExecutedWork> cq = cb.createQuery(ExecutedWork.class);
		Root<ExecutedWork> root = cq.from(ExecutedWork.class);
		cq.select(root).where(cb.like(root.get("designation"), '%'+designation+'%'));
		Query<ExecutedWork> query = session.createQuery(cq);
		List<ExecutedWork> ew = query.getResultList();
		session.close();
		return ew;
	}
}
