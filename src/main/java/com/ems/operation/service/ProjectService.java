package com.ems.operation.service;

import java.util.HashMap;

import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;

public interface ProjectService {

	// add user to a project (check if the dept user belongs to has project and add
	// the project in dept too)
	public ProjectResponse createProject(ProjectRequest projectRequest);

	public ProjectResponse updateProject(ProjectRequest projectUpdateRequest, String projectId);

	public boolean deleteProject(String projectId);

	public ProjectResponse getByProjectId(String projectId);

	public HashMap<String, Object> getAllProjects(int pageNumber, int pageSize, String searchQuery);

	public HashMap<String, Object> getAllProjectsInDepartment(String departmentId, int pageNumber, int pageSize,
			String searchQuery);

	public ProjectResponse addProjectToDepartment(String projectId, String departmentId);

}
