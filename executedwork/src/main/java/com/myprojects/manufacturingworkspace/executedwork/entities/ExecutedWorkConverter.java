package com.myprojects.manufacturingworkspace.executedwork.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.myprojects.manufacturingworkspace.executedwork.services.ExecutedWorkServiceImpl;

public class ExecutedWorkConverter implements Converter<String,ExecutedWork>{
	
	@Autowired
	ExecutedWorkServiceImpl exwork;

	@Override
	public ExecutedWork convert(String id) {
		return exwork.findById(Integer.valueOf(id));
	}

}
