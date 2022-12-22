package com.ems.operation.dto.response;

public class DepartmentResponse {

	private String departmentId;

	private String departmentName;

	private String departmentHeadFirstName;

	private String departmentHeadLastName;

	private ProjectStatusCountResponse projectStatusCount;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentHeadFirstName() {
		return departmentHeadFirstName;
	}

	public void setDepartmentHeadFirstName(String departmentHeadFirstName) {
		this.departmentHeadFirstName = departmentHeadFirstName;
	}

	public String getDepartmentHeadLastName() {
		return departmentHeadLastName;
	}

	public void setDepartmentHeadLastName(String departmentHeadLastName) {
		this.departmentHeadLastName = departmentHeadLastName;
	}

	public ProjectStatusCountResponse getProjectStatusCount() {
		return projectStatusCount;
	}

	public void setProjectStatusCount(ProjectStatusCountResponse projectStatusCount) {
		this.projectStatusCount = projectStatusCount;
	}

}
