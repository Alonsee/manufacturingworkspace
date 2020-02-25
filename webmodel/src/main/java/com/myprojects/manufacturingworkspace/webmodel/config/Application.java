package com.myprojects.manufacturingworkspace.webmodel.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.myprojects.manufacturingworkspace.webmodel.config",
										"com.myprojects.manufacturingworkspace.webmodel.controller",
										"com.myprojects.manufacturingworkspace.webmodel.repository",
										"com.myprojects.manufacturingworkspace.webmodel.services",
										"com.myprojects.manufacturingworkspace.webmodel.websecurityconfig",
										"com.myprojects.manufacturingworkspace.executedwork.config"})
public class Application {
    public static void main(String[] args) {
       SpringApplication.run(Application.class, args); 	
    }
}