package com.myprojects.manufacturingworkspace.webmodel.services;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;

public interface UserService {
	public User searchUserByUsername(String username);
}
