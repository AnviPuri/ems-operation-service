package com.ems.operation.mapper;

import com.ems.operation.constant.Constants;
import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;
import com.ems.operation.entity.Project;
import com.ems.operation.util.AuditUtility;

public class ProjectMapper {

	public static Project projectRequestToEntityMapper(ProjectRequest projectRequest, String requestOperation,
			Project project) {

		project.setProjectName(projectRequest.getProjectName());
		project.setProjectHead(projectRequest.getProjectHead());
		project.setStatus(projectRequest.getStatus());
		if (requestOperation.equals(Constants.RequestOperation.CREATE)) {
			project.setAudit(AuditUtility.createApiAuditBuild());
		} else if (requestOperation.equals(Constants.RequestOperation.UPDATE)) {
			project.setAudit(AuditUtility.updateApiAuditBuild(project.getAudit()));
		}
		project.setAudit(AuditUtility.createApiAuditBuild());
		return project;

	}

	public static ProjectResponse projectEntityToResponseMapper(Project project) {

		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse.setProjectId(project.getProjectId());
		projectResponse.setProjectName(project.getProjectName());
		projectResponse.setProjectHeadId(project.getProjectHead());
		projectResponse.setStatus(project.getStatus());
		return projectResponse;
	}

}
