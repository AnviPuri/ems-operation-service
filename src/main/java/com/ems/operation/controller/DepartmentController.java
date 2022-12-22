package com.ems.operation.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.request.DepartmentUpdateRequest;
import com.ems.operation.dto.request.UserAddInEntityRequest;
import com.ems.operation.dto.response.DepartmentResponse;
import com.ems.operation.dto.response.UserAddInEntityResponse;
import com.ems.operation.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentServiceImpl;

	@PostMapping(produces = "application/json")
	public DepartmentResponse createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest) {

		return departmentServiceImpl.createDepartment(departmentRequest);
	}

	@PutMapping(value = "/{departmentId}", produces = "application/json")
	public DepartmentResponse updateUser(@Valid @RequestBody DepartmentUpdateRequest departmentUpdateRequest,
			@PathVariable String departmentId) {

		return departmentServiceImpl.updateDepartment(departmentUpdateRequest);
	}

	@DeleteMapping(value = "/{departmentId}", produces = "application/json")
	public boolean deleteUser(@PathVariable String departmentId) {

		return departmentServiceImpl.deleteDepartment(departmentId);
	}

	@GetMapping(value = "/all", produces = "application/json")
	public HashMap<String, Object> getAllUsersByUserType(
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery) {

		return departmentServiceImpl.getAllDepartments(pageNumber, pageSize, searchQuery);
	}

	@GetMapping(value = "/{departmentId}", produces = "application/json")
	public DepartmentResponse getByUserId(@PathVariable String departmentId) {

		return departmentServiceImpl.getDepartmentById(departmentId);
	}

	@PostMapping(value = "/{departmentId}", produces = "application/json")
	public UserAddInEntityResponse addUserToDepartment(
			@Valid @RequestBody UserAddInEntityRequest userAddInDepartmentRequest,
			@PathVariable String departmentId) {

		return departmentServiceImpl.addUserToDept(userAddInDepartmentRequest, departmentId);
	}

}
