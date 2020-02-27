package com.myprojects.manufacturingworkspace.webmodel.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;
import com.myprojects.manufacturingworkspace.executedwork.services.ExecutedWorkService;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;
import com.myprojects.manufacturingworkspace.webmodel.exceptions.EditingRecordException;
import com.myprojects.manufacturingworkspace.webmodel.exceptions.SearchRecordsException;
import com.myprojects.manufacturingworkspace.webmodel.services.UserServiceImpl;

@Controller
public class ExecutedWorkController {
	
	@Autowired
	ExecutedWorkService ExecutedWorkServiceImpl;
	@Autowired
	LocationService LocationServiceImpl;
	@Autowired
	EmployeeService EmployeeServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("/executedwork")
	public String executedwork(Model model) {	
		//show last 20 executed work records
		List<ExecutedWork> executedwork=ExecutedWorkServiceImpl.selectAll();
    	model.addAttribute("executedwork", executedwork);
		return "executedwork";
	}
	
	@GetMapping("/executedworkcreate")
	public String createexecutedwork(Model model) {

		//add locations list for dinamic selection field
		List<Location> locations=LocationServiceImpl.selectAll();
		model.addAttribute("locations", locations);

		//add employees list for dinamic selection field
		List<Employee> employees=EmployeeServiceImpl.selectAll();
		model.addAttribute("employees",employees);

		ExecutedWork ew=new ExecutedWork();
		//add current time to display in fields
		//start and finish time for easy recording in the field
		ew.setDatestart(new GregorianCalendar());
		ew.setDatefinish(new GregorianCalendar());
		//add executedwork object for the form
		model.addAttribute("ExecutedWork", ew);
        return "executedworkcreate";
	}
	
	@PostMapping("/executedworksubmit")
	public RedirectView executedworksubmit(@ModelAttribute ExecutedWork executedwork,
			@ModelAttribute Location Location,
			@ModelAttribute Employee Employee,
			Model model) {
		//entry in the fields converted location and employee onjects
		executedwork.setLocation(Location);
		executedwork.setEmployee(Employee);
		
		//entry user, who created record
		UserDetails loggeduser=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
			
		if(executedwork.getId()==0) {
			executedwork.setCreated_by(userServiceImpl.searchUserByUsername(loggeduser.getUsername()).getId());
			ExecutedWorkServiceImpl.createExecutedWork(executedwork);
		}
		else ExecutedWorkServiceImpl.updateExecutedWork(executedwork);
		
		return new RedirectView("/executedwork");
	}
	
	@PostMapping("/executedworkedit")
	public String executedworkedit(@RequestParam int selectedExecutedWorkId, Model model) 
			throws EditingRecordException {
		
		//search record by id
		ExecutedWork selectedExecutedWork=ExecutedWorkServiceImpl.findById(selectedExecutedWorkId);
		
		//if another user created the record throws exception
		if(userServiceImpl.searchUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId()
				!=selectedExecutedWork.getCreated_by()) 
		{
			throw new EditingRecordException("This record was created by another user");
		}
		
		//add selected record into model
		model.addAttribute("ExecutedWork", selectedExecutedWork);
		
		//add locations and employee list for dinamic selection fields
		List<Location> locations=LocationServiceImpl.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees=EmployeeServiceImpl.selectAll();
		model.addAttribute("employees",employees);
		
		return "executedworkedit";
		}
	
	@GetMapping("/executedworksearch")
	public String executedworksearch(Model model)
		{
		//add locations and employee list for dinamic selection fields in the search form
		List<Location> locations=LocationServiceImpl.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees=EmployeeServiceImpl.selectAll();
		model.addAttribute("employees",employees);

		return "executedworksearch";
		}
	
	@GetMapping("/executedworksearchwithparams")
	public String executedworksearchwithparams(@RequestParam(required=false) Integer employeeid,
									 @RequestParam(required=false) Integer locationid,
									 @RequestParam(required=false) String title,
									 @RequestParam(required=false) String designation,
									 @RequestParam @DateTimeFormat(pattern="yyyy-MM-d") Date searchstart,
									 @RequestParam @DateTimeFormat(pattern="yyyy-MM-d") Date searchfinish,
									 Model model) throws SearchRecordsException
	{
		GregorianCalendar sstart=new GregorianCalendar();
		sstart.setTime(searchstart);

		GregorianCalendar sfinish=new GregorianCalendar();
		sfinish.setTime(searchfinish);
		sfinish.set(Calendar.HOUR_OF_DAY, 23);
		sfinish.set(Calendar.MINUTE,59);
		sfinish.set(Calendar.SECOND,59);
		sfinish.set(Calendar.MILLISECOND,999);

		Employee employee=null; 
		Location location=null;
		if (employeeid!=null) employee=EmployeeServiceImpl.findById(employeeid);
		if(locationid!=null) location=LocationServiceImpl.findById(locationid);

		List<ExecutedWork> executedwork=ExecutedWorkServiceImpl.searchByParams(title,
									designation, employee, location, sstart, sfinish);
		
		if(executedwork.size()==0) throw new SearchRecordsException("There are no results for the given parameters");
		model.addAttribute("executedwork", executedwork);

		return "executedworksearch";
	}
}
