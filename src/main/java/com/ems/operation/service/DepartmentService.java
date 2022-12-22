package com.ems.operation.service;

import java.util.HashMap;

import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.request.DepartmentUpdateRequest;
import com.ems.operation.dto.request.UserAddInEntityRequest;
import com.ems.operation.dto.response.DepartmentResponse;
import com.ems.operation.dto.response.UserAddInEntityResponse;

public interface DepartmentService {

	public DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

	public DepartmentResponse updateDepartment(DepartmentUpdateRequest departmentUpdateRequest);

	public boolean deleteDepartment(String departmentId);

	public HashMap<String, Object> getAllDepartments(int pageNumber, int pageSize, String searchQuery);

	public DepartmentResponse getDepartmentById(String departmentId);

	public UserAddInEntityResponse addUserToDept(UserAddInEntityRequest userAddInDepartmentRequest,
			String departmentId);

	public void removeUserFromDept();

	public void updateUserInDept();

	// get all users by dept

	// search all users in dept

	// get all users in dept with hierarchy
}
