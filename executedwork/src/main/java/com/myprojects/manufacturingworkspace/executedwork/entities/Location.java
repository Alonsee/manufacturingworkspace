package com.myprojects.manufacturingworkspace.executedwork.entities;

import java.util.List;

import javax.persistence.*;

//entity objest for location
//several people can work on one location
@Entity
@Table(name="locations")
public class Location {
	@Column(name="name")
	private String name;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int location_id;
	
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Employee> employees;
	
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ExecutedWork> executedwork;
	
	public Location() {};
	
	public Location(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public List<ExecutedWork> getExecutedwork() {
		return executedwork;
	}
	
	public void setExecutedwork(List<ExecutedWork> executedwork) {
		this.executedwork = executedwork;
	}
	
	@Override
	public String toString() {
		return "location id: "+location_id+" location name: "+name;
	}
}
