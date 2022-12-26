package com.ems.operation.service;

import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;

public interface ProjectService {

	// add project to a department

	// update project

	// delete

	// get all projects

	// get all projects in department

	// add user to a project (check if the dept user belongs to has project and add
	// the project in dept too)
	public ProjectResponse createProject(ProjectRequest projectRequest);

//	public DepartmentResponse updateDepartment(DepartmentUpdateRequest departmentUpdateRequest);
//
//	public boolean deleteDepartment(String departmentId);
//
//	public HashMap<String, Object> getAllDepartments(int pageNumber, int pageSize, String searchQuery);
//
//	public DepartmentResponse getDepartmentById(String departmentId);

}
