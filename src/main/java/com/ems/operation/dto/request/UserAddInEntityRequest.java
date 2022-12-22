package com.ems.operation.dto.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class UserAddInEntityRequest {

	@NotNull
	@NotBlank(message = "User ID cant'be empty")
	private String userId;

	// not sure if role needed or not
	private String role = "";

	@NotNull
	@NotBlank(message = "Reporting Head cant'be empty")
	private String reportsTo;

	@NotNull
	@NotBlank(message = "Entity Level Designation cant'be empty")
	private String entityLevelDesignation;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getEntityLevelDesignation() {
		return entityLevelDesignation;
	}

	public void setEntityLevelDesignation(String entityLevelDesignation) {
		this.entityLevelDesignation = entityLevelDesignation;
	}

}
