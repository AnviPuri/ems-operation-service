package com.ems.operation.dto.response;

public class UserEntityResponse {

	private String userEntityId;

	private String entityId;

	private String entityType;

	private String userId;

	private String role;

	private String reportsTo;

	private String entityLevelDesignation;

	private String status;

	private String userFirstName;

	private String userLastName;

	public String getUserEntityId() {
		return userEntityId;
	}

	public void setUserEntityId(String userEntityId) {
		this.userEntityId = userEntityId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

}
