package com.ems.operation.dto.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class DepartmentRequest {

	@NotNull
	@NotBlank(message = "Department Name cant'be empty")
	private String departmentName;

	@NotNull
	@NotBlank(message = "Department Head cant'be empty")
	private String departmentHead;

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
