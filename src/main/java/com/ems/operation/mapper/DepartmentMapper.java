package com.ems.operation.mapper;

import com.ems.operation.constant.Constants;
import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.response.DepartmentResponse;
import com.ems.operation.entity.Department;
import com.ems.operation.util.AuditUtility;

public class DepartmentMapper {

	public static Department departmentRequestToEntityMapper(DepartmentRequest deptRequest, String requestOperation,
			Department department) {

		department.setDepartmentName(deptRequest.getDepartmentName());
		department.setDepartmentHead(deptRequest.getDepartmentHead());
		if (requestOperation.equals(Constants.RequestOperation.CREATE)) {
			department.setAudit(AuditUtility.createApiAuditBuild());
		} else if (requestOperation.equals(Constants.RequestOperation.UPDATE)) {
			department.setAudit(AuditUtility.updateApiAuditBuild(department.getAudit()));
		}
		return department;
	}

	public static DepartmentResponse departmentEntityToResponseMapper(Department department) {

		DepartmentResponse deptResponse = new DepartmentResponse();
		deptResponse.setDepartmentId(department.getDepartmentId());
		deptResponse.setDepartmentName(department.getDepartmentName());
		return deptResponse;
	}

}
