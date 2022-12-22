package com.ems.operation.dto.response;

public class ProjectStatusCountResponse {

	private int numberOfActiveProjects = 0;
	private int numberOfCompletedProjects = 0;

	public int getNumberOfActiveProjects() {
		return numberOfActiveProjects;
	}

	public void setNumberOfActiveProjects(int numberOfActiveProjects) {
		this.numberOfActiveProjects = numberOfActiveProjects;
	}

	public int getNumberOfCompletedProjects() {
		return numberOfCompletedProjects;
	}

	public void setNumberOfCompletedProjects(int numberOfCompletedProjects) {
		this.numberOfCompletedProjects = numberOfCompletedProjects;
	}

}
