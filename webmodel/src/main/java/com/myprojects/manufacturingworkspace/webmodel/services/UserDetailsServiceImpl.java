package com.myprojects.manufacturingworkspace.webmodel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myprojects.manufacturingworkspace.webmodel.entities.User;
import com.myprojects.manufacturingworkspace.webmodel.repository.UserRepositoryImpl;

//userdetails service for spring security
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepositoryImpl userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.searchUserByUsername(username);
		UserBuilder builder = null;
		 if (user != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(user.getUserpassword());
		      builder.roles(user.getUserrole());
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }
		return builder.build();
	}
}
