package com.ems.operation.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ems.operation.communication.SyncCommunication;
import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;
import com.ems.operation.entity.Project;
import com.ems.operation.mapper.ProjectMapper;
import com.ems.operation.repo.ProjectRepo;
import com.ems.operation.service.ProjectService;

import ems.utility.logger.EmsLogger;
import ems.utility.util.EmsUtility;

public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepo projectRepository;

	@Autowired
	private SyncCommunication syncCommunication;

	private Logger logger = EmsLogger.getLogger(DepartmentServiceImpl.class.getName());

	@Override
	public ProjectResponse createProject(ProjectRequest projectRequest) {

		EmsLogger.log("CREATE PROJECT WITH REQUEST : " + EmsUtility.toJsonString(projectRequest), logger);

		// Project Head added as ROOT user
		// when assigning reporting head only option of users who exist in project/dept
		// given

		// validation - check if user exists

		// ADD user id as project head

		// ADD DEPARTMENT TO PROJECT

		Project project = new Project();
		project = ProjectMapper.projectRequestToEntityMapper(projectRequest);
		project = projectRepository.save(project);

		// TO DO - MAP 
		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse = ProjectMapper.projectEntityToResponseMapper(project);

		return projectResponse;
	}

}
