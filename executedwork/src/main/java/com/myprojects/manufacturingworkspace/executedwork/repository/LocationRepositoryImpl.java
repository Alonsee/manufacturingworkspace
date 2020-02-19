package com.myprojects.manufacturingworkspace.executedwork.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myprojects.manufacturingworkspace.executedwork.entities.Location;

@Component
public class LocationRepositoryImpl implements LocationRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
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
	
	public Location findById(int id) {
		Session session=sessionFactory.openSession();
		Location loc=session.get(Location.class,id);
		session.close();
		return loc;
	}
	public List<Location> selectAll() {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Location> loc=(List<Location>) session.createQuery("from Location").getResultList();
		session.close();
		return loc;
	}
}
