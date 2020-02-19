package com.myprojects.manufacturingworkspace.webmodel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;
import com.myprojects.manufacturingworkspace.webmodel.repository.UserRepositoryImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepositoryImpl userRepositoryImpl;
	
	public User searchUserByUsername(String username) {
		return userRepositoryImpl.searchUserByUsername(username);
	}
}
