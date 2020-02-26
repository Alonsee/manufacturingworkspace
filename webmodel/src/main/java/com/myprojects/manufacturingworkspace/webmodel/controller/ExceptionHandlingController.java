package com.myprojects.manufacturingworkspace.webmodel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	protected String handleException(HttpServletRequest req,Exception e,Model model) {
		    model.addAttribute("exception", e.getMessage());
		    model.addAttribute("url", req.getRequestURL());
		    return "error";
	}
}
