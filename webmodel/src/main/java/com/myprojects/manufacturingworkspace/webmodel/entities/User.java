package com.myprojects.manufacturingworkspace.webmodel.entities;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String username;
	private String userpassword;
	private String userrole;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setPassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	
	protected User() {};
	
	public User(String username, String userpassword) {
		this.username=username;
		this.userpassword=userpassword;
	}
	
	public User(String username, String userpassword,String userrole,int id) {
		this.username=username;
		this.userpassword=userpassword;
		this.userrole=userrole;
		this.id=id;
	}
}
