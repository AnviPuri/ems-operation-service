package com.ems.operation.dto.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

public class UserEntityRequest {

	@NotNull
	@NotBlank(message = "User ID cant'be empty")
	private String userId;

	// not sure if role needed or not
	private String role = "";

	// WILL BE ROOT IN CASE TOP OF HIERARCHY IN AN ENTITY
	@NotNull
	@NotBlank(message = "Reporting Head cant'be empty")
	private String reportsTo;

	@NotNull
	@NotBlank(message = "Entity Level Designation cant'be empty")
	private String entityLevelDesignation;

	@NotNull
	@NotBlank(message = "Entity Type cant'be empty")
	private String entityType;

	private String status;

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

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
