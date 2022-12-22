package com.ems.operation.dto.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class DepartmentUpdateRequest {

	@NotNull
	@NotBlank(message = "Department Id cant'be empty")
	private String departmentId;

	@NotNull
	@NotBlank(message = "Department Name cant'be empty")
	private String departmentName;

	@NotNull
	@NotBlank(message = "Department Head cant'be empty")
	private String departmentHead;

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

	public String getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}

}
