package com.ems.operation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Department {

	@Id
	@GenericGenerator(name = "department_id", strategy = "com.ems.operation.id.generator.DepartmentIdGenerator")
	@GeneratedValue(generator = "department_id")
	@Column(name = "department_id")
	private String departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "department_head")
	private String departmentHead;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "department_project", joinColumns = {
			@JoinColumn(name = "department_id") }, inverseJoinColumns = { @JoinColumn(name = "project_id") })
	List<Project> projects = new ArrayList<>();
	
	@Embedded
	private Audit audit;

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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
