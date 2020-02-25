package com.myprojects.manufacturingworkspace.webmodel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.myprojects.manufacturingworkspace.executedwork.entities.EmployeeConverter;
import com.myprojects.manufacturingworkspace.executedwork.entities.LocationConverter;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{

	@Autowired
	LocationConverter LocationConverter;
	@Autowired
	EmployeeConverter EmployeeConverter;

	//path for css and js source
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 		registry.addResourceHandler("/**")
	            .addResourceLocations("classpath:/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	//add converters for employees and locations
    @Override
    public void addFormatters(FormatterRegistry registry) {
    	registry.addConverter(EmployeeConverter);
    	registry.addConverter(LocationConverter); 
    }

}