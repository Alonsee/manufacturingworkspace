package com.myprojects.manufacturingworkspace.webmodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.myprojects.manufacturingworkspace.executedwork.entities.Employee;
import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;
import com.myprojects.manufacturingworkspace.executedwork.entities.Location;
import com.myprojects.manufacturingworkspace.executedwork.services.EmployeeService;
import com.myprojects.manufacturingworkspace.executedwork.services.ExecutedWorkService;
import com.myprojects.manufacturingworkspace.executedwork.services.LocationService;
import com.myprojects.manufacturingworkspace.webmodel.exceptions.EditingRecordException;
import com.myprojects.manufacturingworkspace.webmodel.exceptions.SearchRecordsException;
import com.myprojects.manufacturingworkspace.webmodel.services.UserService;


@Controller
@RequestMapping("/executedwork")
public class ExecutedWorkController {
	
	@Autowired
	ExecutedWorkService executedWorkService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserService userServiceImpl;
	
	@GetMapping
	public String executedwork(Model model) {	
		//show last 15 executed work records
		List<ExecutedWork> executedwork = executedWorkService.selectAll();
    	model.addAttribute("executedwork", executedwork);
		return "executedwork";
	}
	
	@GetMapping("/search")
	public String executedworksearch(Model model)
		{
		//add locations and employee list for dinamic selection fields in the search form
		List<Location> locations=locationService.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees = employeeService.selectAll();
		model.addAttribute("employees", employees);
		return "executedworksearch";
		}
	
	@GetMapping("/searchparams")
	public String executedworksearchwithparams(@RequestParam(required = false, defaultValue = "0") int employeeid,
											   @RequestParam(required = false, defaultValue = "0") int locationid,
											   @RequestParam(required = false, defaultValue = "") String title,
											   @RequestParam(required = false, defaultValue = "") String designation,
											   @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-d") Date searchstart,
											   @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-d") Date searchfinish,
											   Model model) throws SearchRecordsException
	{

		GregorianCalendar sstart = new GregorianCalendar();
		GregorianCalendar sfinish = new GregorianCalendar();
		if (searchstart != null) {
			sstart.setTime(searchstart);
		}
		if (searchfinish != null) {
			sfinish.setTime(searchfinish);
		}

		Employee employee = null; 
		Location location = null;
		if (employeeid != 0) employee = employeeService.findById(employeeid);
		if (locationid != 0) location = locationService.findById(locationid);

		List<ExecutedWork> executedwork = executedWorkService.searchByParams(title,
				designation, employee, location, sstart, sfinish);

		if ( (executedwork.size() == 0) | (executedwork == null)) {
			throw new SearchRecordsException("There are no results for the given parameters");
		}
		
		model.addAttribute("executedwork", executedwork);

		return "executedworksearch";
	}
	
	@GetMapping("/create")
	public String createexecutedwork(Model model) {

		//add locations list for dinamic selection field
		List<Location> locations = locationService.selectAll();
		model.addAttribute("locations", locations);

		//add employees list for dinamic selection field
		List<Employee> employees = employeeService.selectAll();
		model.addAttribute("employees",employees);

		ExecutedWork ew = new ExecutedWork();
		//add current time to display in fields
		//start and finish time for easy recording in the field
		ew.setDatestart(new GregorianCalendar());
		ew.setDatefinish(new GregorianCalendar());
		//add executedwork object for the form
		model.addAttribute("ExecutedWork", ew);
        return "executedworkcreate";
	}
	
	@PostMapping("/edit")
	public String executedworkedit(@RequestParam int selectedExecutedWorkId, Model model) 
			throws EditingRecordException {
		
		//search record by id
		ExecutedWork selectedExecutedWork = executedWorkService.findById(selectedExecutedWorkId);
		
		//if another user created the record throws exception
		if (userServiceImpl.searchUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal()).getUsername()).getId() != selectedExecutedWork.getCreated_by()) 
		{
			throw new EditingRecordException("This record was created by another user");
		}
		
		//add selected record into model
		model.addAttribute("ExecutedWork", selectedExecutedWork);
		
		//add locations and employee list for dinamic selection fields
		List<Location> locations = locationService.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees = employeeService.selectAll();
		model.addAttribute("employees", employees);
		
		return "executedworkedit";
		}
	
	@PostMapping("/submit")
	public String executedworksubmit(@Valid @ModelAttribute ExecutedWork executedwork,
											Errors errors,
											@ModelAttribute Location Location,
											@ModelAttribute Employee Employee,
											Model model, HttpServletRequest request) 
													throws EditingRecordException {
		if (errors.hasErrors()) {
			String er = "";
			for (FieldError e : errors.getFieldErrors()) {
				er += "Field: " + e.getField() + " error: " + e.getDefaultMessage() + "\n";		
			}
			throw new EditingRecordException("invalid data \n" + er);
		}
		
		//entry in the fields converted location and employee onjects
		executedwork.setLocation(Location);
		executedwork.setEmployee(Employee);

		if (executedwork.getId() == 0) {
			//entry user, who created record
			UserDetails loggeduser = (UserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();	
			executedwork.setCreated_by(userServiceImpl.searchUserByUsername(loggeduser.getUsername()).getId());
			executedWorkService.createExecutedWork(executedwork);
		}
		else {
			executedWorkService.updateExecutedWork(executedwork);
		}
		
		return "redirect:/executedwork";
	}
	
	@PostMapping("/delete")
	public RedirectView executedworkdelete(@RequestParam(name = "selectedExecutedWorkId") Integer executedworkid) {	
		executedWorkService.deleteExecutedWork(executedWorkService.findById(executedworkid));
		return new RedirectView("/executedwork");
	}
}
