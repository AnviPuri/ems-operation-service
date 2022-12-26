package com.ems.operation.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ems.operation.entity.Department;
import com.sun.istack.NotNull;

public class ProjectRequest {

	@NotNull
	@NotBlank(message = "Project Name cant'be empty")
	private String projectName;

	@NotNull
	@NotBlank(message = "Project Head cant'be empty")
	private String projectHead;

	@NotNull
	@NotBlank(message = "Status cant'be empty")
	private String status;

	private List<Department> departments = new ArrayList<>();

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectHead() {
		return projectHead;
	}

	public void setProjectHead(String projectHead) {
		this.projectHead = projectHead;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

}
