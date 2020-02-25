package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

public class LocationRepositoryImpl implements LocationRepository {
	
	SessionFactory sessionFactory;
	
	public LocationRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	public LocationRepositoryImpl() {};
	
	@Override
	public void createLocation(Location location) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.save(location);
		tr.commit();
		session.close();
		
	}
	@Override
	public void updateLocation(Location location) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.update(location);
		tr.commit();
		session.close();
	}
	@Override
	public void deleteLocation(Location location) {
		Session session=sessionFactory.openSession();
		Transaction tr=session.beginTransaction();
		session.delete(location);
		tr.commit();
		session.close();
		
	}
	@Override
	public Location findById(int id) {
		Session session=sessionFactory.openSession();
		Location loc=session.get(Location.class,id);
		session.close();
		return loc;
	}
	@Override
	public List<Location> selectAll() {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Location> loc=(List<Location>) session.createQuery("from Location").getResultList();
		session.close();
		return loc;
	}
}
