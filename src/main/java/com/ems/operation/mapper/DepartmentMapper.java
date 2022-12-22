package com.ems.operation.mapper;

import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.request.DepartmentUpdateRequest;
import com.ems.operation.dto.response.DepartmentResponse;
import com.ems.operation.entity.Department;
import com.ems.operation.util.AuditUtility;

public class DepartmentMapper {

	public static Department departmentRequestToEntityMapper(DepartmentRequest deptRequest) {

		Department dept = new Department();
		dept.setDepartmentName(deptRequest.getDepartmentName());
		dept.setDepartmentHead(deptRequest.getDepartmentHead());
		dept.setAudit(AuditUtility.createApiAuditBuild());
		return dept;
	}

	public static Department departmentUpdateRequestToEntityMapper(DepartmentUpdateRequest departmentUpdateRequest,
			Department department) {

		department.setDepartmentName(departmentUpdateRequest.getDepartmentName());
		department.setDepartmentHead(departmentUpdateRequest.getDepartmentHead());
		department.setAudit(AuditUtility.updateApiAuditBuild(department.getAudit()));
		return department;
	}

	public static DepartmentResponse departmentEntityToResponseMapper(Department department) {

		DepartmentResponse deptResponse = new DepartmentResponse();
		deptResponse.setDepartmentId(department.getDepartmentId());
		deptResponse.setDepartmentName(department.getDepartmentName());
		return deptResponse;
	}

}
