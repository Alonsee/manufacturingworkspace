package com.myprojects.manufacturingworkspace.webmodel.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.myprojects.manufacturingworkspace.webmodel",
										"com.myprojects.manufacturingworkspace"})
public class Application {

    public static void main(String[] args) {
       SpringApplication.run(Application.class, args); 	
    }
}