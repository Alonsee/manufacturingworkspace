package com.myprojects.manufacturingworkspace.executedwork.repository;

import com.myprojects.manufacturingworkspace.executedwork.entities.ExecutedWork;

public interface ExecutedWorkRepository {
	void createExecutedWork(ExecutedWork executedwork);
	void updateExecutedWork(ExecutedWork executedwork);
	void deleteExecutedWork(ExecutedWork executedwork);
}
