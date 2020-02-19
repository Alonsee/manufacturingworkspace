package com.myprojects.manufacturingworkspace.executedwork.entities;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
	private String firstname;
	private String lastname;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int employee_id;

	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ExecutedWork> executedwork;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable = true, updatable = true)
	private Location location;

	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public Employee() {}
	public Employee(String firstname,String lastname) {
	this.firstname=firstname;
	this.lastname=lastname;	
	}

	public String getFirstname() {
	return firstname;
	}
	
	public void setFirstname(String firstname) {
	this.firstname = firstname;
	}
	
	public String getLastname() {
	return lastname;
	}
	
	public void setLastname(String lastname) {
	this.lastname = lastname;
	}
	
	public List<ExecutedWork> getExecutedwork() {
	return executedwork;
	}
	
	public void setExecutedwork(List<ExecutedWork> executedwork) {
	this.executedwork = executedwork;
	}
	
	public Location getLocation() {
	return location;
	}
	
	public void setLocation(Location location) {
	this.location = location;
	}
	
	@Override
	public String toString() {
		return "id: "+employee_id+" firstname: "+firstname+" lastname: "+lastname+" location id: "+location.getLocation_id()+" location name "+location.getName();
	}
}
