package com.myprojects.manufacturingworkspace.webmodel.controller;

import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

		List<ExecutedWork> executedwork=ExecutedWorkServiceImpl.selectAll();
    	model.addAttribute("executedwork", executedwork);
		return "executedwork";
	}
	
	@GetMapping("/executedworkcreate")
	public String createexecutedwork(Model model) {

		List<Location> locations=LocationServiceImpl.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees=EmployeeServiceImpl.selectAll();
		model.addAttribute("employees",employees);

		model.addAttribute("ExecutedWork", new ExecutedWork());
        return "executedworkcreate";
	}
	
	@PostMapping("/executedworksubmit")
	public RedirectView executedworksubmit(@ModelAttribute ExecutedWork executedwork,
			@ModelAttribute Location Location,
			@ModelAttribute Employee Employee,
			Model model) {
		executedwork.setLocation(Location);
		executedwork.setEmployee(Employee);
		
		UserDetails loggeduser=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		executedwork.setCreated_by(userServiceImpl.searchUserByUsername(loggeduser.getUsername()).getId());
		System.out.println(executedwork);
		
		if(executedwork.getId()==0)ExecutedWorkServiceImpl.createExecutedWork(executedwork);
		else ExecutedWorkServiceImpl.updateExecutedWork(executedwork);
		
		return new RedirectView("/executedwork");
	}
	
	@PostMapping("/executedworkedit")
	public String executedworkedit(@RequestParam int selectedExecutedWorkId, Model model) 
			throws EditingRecordException {
		ExecutedWork selectedExecutedWork=ExecutedWorkServiceImpl.findById(selectedExecutedWorkId);
		if(userServiceImpl.searchUserByUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).getId()
				!=selectedExecutedWork.getCreated_by()) 
		{
			throw new EditingRecordException("This record was created by another user");
		}
		model.addAttribute("ExecutedWork", selectedExecutedWork);
		
		List<Location> locations=LocationServiceImpl.selectAll();
		model.addAttribute("locations", locations);

		List<Employee> employees=EmployeeServiceImpl.selectAll();
		model.addAttribute("employees",employees);
		
		System.out.println(selectedExecutedWork);
		return "executedworkedit";
		}
	
	@GetMapping("/executedworksearch")
	public String executedworksearch(Model model)
		{
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
									 @RequestParam(required=false) GregorianCalendar searchstart,
									 @RequestParam(required=false) GregorianCalendar searchfinish,
									 Model model)
	{
		model.addAttribute("executedwork", ExecutedWorkServiceImpl.searchByParams(title,
							designation, employeeid, locationid, searchstart, searchfinish));
		return "executedworksearch";
	}
}
