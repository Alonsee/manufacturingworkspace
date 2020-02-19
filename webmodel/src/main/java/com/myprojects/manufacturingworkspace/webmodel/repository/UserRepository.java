package com.myprojects.manufacturingworkspace.webmodel.repository;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;

public interface UserRepository{
	public User searchUserByUsername(String username);
}
