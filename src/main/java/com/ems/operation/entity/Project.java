package com.ems.operation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Project {

	@Id
	@GenericGenerator(name = "project_id", strategy = "com.ems.operation.id.generator.ProjectIdGenerator")
	@GeneratedValue(generator = "project_id")
	@Column(name = "project_id")
	private String projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_head")
	private String projectHead;

	private String status;

	@ManyToMany(mappedBy = "projects", cascade = { CascadeType.ALL })
	private List<Department> departments = new ArrayList<>();

	@Embedded
	private Audit audit;

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

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
