package com.ems.operation.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit {

//	private String createdBy;
//	private String updatedBy;
//	private String deletedBy;

	@Column(name = "created_at")
	private long createdAt;

	@Column(name = "updated_at")
	private long updatedAt;

	@Column(name = "deleted_at")
	private long deletedAt;

	@Column(name = "is_active")
	private boolean isActive;

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(long deletedAt) {
		this.deletedAt = deletedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
