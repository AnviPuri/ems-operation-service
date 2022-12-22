package com.ems.operation.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserEntityMapping {

	@Id
	@GenericGenerator(name = "user_entity_id", strategy = "com.ems.operation.id.generator.UserEntityMappingIdGenerator")
	@GeneratedValue(generator = "user_entity_id")
	@Column(name = "user_entity_id")
	private String userEntityId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "entity_id")
	private String entityId;

	@Column(name = "entity_type")
	private String entityType;

	private String role;

	@Column(name = "reports_to")
	private String reportsTo;

	@Column(name = "entity_level_designation")
	private String entityLevelDesignation;

	private String status;

	@Embedded
	private Audit audit;

	public String getUserEntityId() {
		return userEntityId;
	}

	public void setUserEntityId(String userEntityId) {
		this.userEntityId = userEntityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
