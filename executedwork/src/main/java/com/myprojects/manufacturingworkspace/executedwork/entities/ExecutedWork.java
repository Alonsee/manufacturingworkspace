package com.myprojects.manufacturingworkspace.executedwork.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="executedwork")
public class ExecutedWork {
	
	private String title;
	private String designation;
	
	@DateTimeFormat(pattern="yyyy-MM-d'T'H:mm")
	private Calendar datestart;
	@DateTimeFormat(pattern="yyyy-MM-d'T'H:mm")
	private Calendar datefinish;
	private int count;
	private double piecetime;
	private String comment;
	private double adjustmenttime;
	private int created_by;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", insertable = true, updatable = true)
	Employee employee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="location_id", insertable = true, updatable = true)
	Location location;

	public ExecutedWork() {};
	
	public ExecutedWork(String title, String designation, GregorianCalendar datestart, GregorianCalendar datefinish,
			int count, double piecetime, String comment) {
		super();
		this.title = title;
		this.designation = designation;
		this.datestart = datestart;
		this.datefinish = datefinish;
		this.count = count;
		this.piecetime = piecetime;
		this.comment = comment;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Calendar getDatestart() {
		return datestart;
	}

	public void setDatestart(Calendar datestart) {
		this.datestart = datestart;
	}

	public Calendar getDatefinish() {
		return datefinish;
	}

	public void setDatefinish(Calendar datefinish) {
		this.datefinish = datefinish;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPiecetime() {
		return piecetime;
	}

	public void setPiecetime(double piecetime) {
		this.piecetime = piecetime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getAdjustmenttime() {
		return adjustmenttime;
	}

	public void setAdjustmenttime(double adjustmenttime) {
		this.adjustmenttime = adjustmenttime;
	}
	
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
		return "executedwork id: "+id+" title: "+title+" designation: "+designation
				+" count: "+count+" piecetime: "+piecetime+" start: "+sdf.format(new Date(datestart.getTimeInMillis()))
				+" finish: "+sdf.format(new Date(datefinish.getTimeInMillis()))+" comment: "+comment
				+" isAdjustment: "+ adjustmenttime
				+" location: "+location.toString()
				+" employee: "+employee.toString()
				+" created by: "+created_by;
	}

}
