package com.myprojects.manufacturingworkspace.webmodel.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;

@Component
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User searchUserByUsername(String username) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from User where username=:username")
												.setParameter("username", username);
		User user= (User) query.getResultList().get(0);
		session.close();
		return user;
	}
	
}
