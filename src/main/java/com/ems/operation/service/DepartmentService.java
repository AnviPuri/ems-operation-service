package com.ems.operation.service;

import java.util.HashMap;

import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.response.DepartmentResponse;

public interface DepartmentService {

	public DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

	public DepartmentResponse updateDepartment(DepartmentRequest departmentUpdateRequest, String departmentId);

	public boolean deleteDepartment(String departmentId);

	public HashMap<String, Object> getAllDepartments(int pageNumber, int pageSize, String searchQuery);

	public DepartmentResponse getDepartmentById(String departmentId);

}
