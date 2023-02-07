package com.ems.operation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;
import com.ems.operation.service.impl.ProjectServiceImpl;

@RestController
@RequestMapping("/api/operation/project")
public class ProjectController {

	@Autowired
	private ProjectServiceImpl projectServiceImpl;

	@PostMapping(produces = "application/json")
	public ProjectResponse createProject(@RequestBody @Valid ProjectRequest projectRequest) {
		return projectServiceImpl.createProject(projectRequest);
	}

	@PutMapping(value = "/{projectId}", produces = "application/json")
	public ProjectResponse updateProject(@Valid @RequestBody ProjectRequest projectUpdateRequest,
			@PathVariable String projectId) {

		return projectServiceImpl.updateProject(projectUpdateRequest, projectId);
	}
//
//	@DeleteMapping(value = "/{departmentId}", produces = "application/json")
//	public boolean deleteUser(@PathVariable String departmentId) {
//
//		return departmentServiceImpl.deleteDepartment(departmentId);
//	}
//
//	@GetMapping(value = "/all", produces = "application/json")
//	public HashMap<String, Object> getAllUsersByUserType(
//			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
//			@RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery) {
//
//		return departmentServiceImpl.getAllDepartments(pageNumber, pageSize, searchQuery);
//	}
//
//	@GetMapping(value = "/{departmentId}", produces = "application/json")
//	public DepartmentResponse getByUserId(@PathVariable String departmentId) {
//
//		return departmentServiceImpl.getDepartmentById(departmentId);
//	}

}
