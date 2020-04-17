package com.myprojects.manufacturingworkspace.webmodel.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User searchUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		org.hibernate.query.Query query=session.createQuery("from User where username = :username")
			.setParameter("username", username);
		if (query.getResultList().isEmpty()) throw new UsernameNotFoundException("User not found.");
		User user = (User) query.getResultList().get(0);
		session.close();
		return user;
	}
	
}
