package com.ems.operation.mapper;

import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;
import com.ems.operation.entity.Project;
import com.ems.operation.util.AuditUtility;

public class ProjectMapper {

	public static Project projectRequestToEntityMapper(ProjectRequest projectRequest) {

		Project project = new Project();
		project.setProjectName(projectRequest.getProjectName());
		project.setProjectHead(projectRequest.getProjectHead());
		project.setStatus(projectRequest.getStatus());
		project.setAudit(AuditUtility.createApiAuditBuild());
		return project;

	}

//	public static Department departmentUpdateRequestToEntityMapper(DepartmentUpdateRequest departmentUpdateRequest,
//			Department department) {
//
//		department.setDepartmentName(departmentUpdateRequest.getDepartmentName());
//		department.setDepartmentHead(departmentUpdateRequest.getDepartmentHead());
//		department.setAudit(AuditUtility.updateApiAuditBuild(department.getAudit()));
//		return department;
//	}
//
	public static ProjectResponse projectEntityToResponseMapper(Project project) {

		ProjectResponse projectResponse = new ProjectResponse();
//		deptResponse.setDepartmentId(department.getDepartmentId());
//		deptResponse.setDepartmentName(department.getDepartmentName());
		return projectResponse;
	}

}
