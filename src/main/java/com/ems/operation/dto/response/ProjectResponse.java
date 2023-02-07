package com.ems.operation.dto.response;

public class ProjectResponse {

	private String projectId;

	private String projectName;

	private String projectHeadId;

	private String projectHeadFirstName;

	private String projectHeadLastName;

	private String status;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectHeadId() {
		return projectHeadId;
	}

	public void setProjectHeadId(String projectHeadId) {
		this.projectHeadId = projectHeadId;
	}

	public String getProjectHeadFirstName() {
		return projectHeadFirstName;
	}

	public void setProjectHeadFirstName(String projectHeadFirstName) {
		this.projectHeadFirstName = projectHeadFirstName;
	}

	public String getProjectHeadLastName() {
		return projectHeadLastName;
	}

	public void setProjectHeadLastName(String projectHeadLastName) {
		this.projectHeadLastName = projectHeadLastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
