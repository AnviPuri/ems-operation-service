package com.ems.operation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.operation.constant.Constants;
import com.ems.operation.dto.request.ProjectRequest;
import com.ems.operation.dto.response.ProjectResponse;
import com.ems.operation.entity.Department;
import com.ems.operation.entity.Project;
import com.ems.operation.mapper.ProjectMapper;
import com.ems.operation.repo.DepartmentRepo;
import com.ems.operation.repo.ProjectRepo;
import com.ems.operation.service.ProjectService;

import ems.utility.logger.EmsLogger;
import ems.utility.util.EmsUtility;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepo projectRepository;

	@Autowired
	private DepartmentRepo deptRepository;

//	@Autowired
//	private SyncCommunication syncCommunication;

	private Logger logger = EmsLogger.getLogger(DepartmentServiceImpl.class.getName());

	@Override
	public ProjectResponse createProject(ProjectRequest projectRequest) {

		EmsLogger.log("CREATE PROJECT WITH REQUEST : " + EmsUtility.toJsonString(projectRequest), logger);

		Project project = new Project();
		ProjectMapper.projectRequestToEntityMapper(projectRequest, Constants.RequestOperation.CREATE, project);

		Optional<List<Department>> optionalDepartmentList = deptRepository
				.findByDepartmentIdInAndAuditIsActive(projectRequest.getDepartmentIdList(), true);
		if (optionalDepartmentList.isPresent() && !optionalDepartmentList.get().isEmpty()) {
			project.getDepartments().addAll(optionalDepartmentList.get());
		}
		project = projectRepository.save(project);

		for (Department deptartment : optionalDepartmentList.get()) {
			deptartment.getProjects().add(project);
			deptRepository.save(deptartment);
		}

		ProjectResponse projectResponse = new ProjectResponse();
		projectResponse = ProjectMapper.projectEntityToResponseMapper(project);
		return projectResponse;
	}

	@Override
	public ProjectResponse updateProject(ProjectRequest projectUpdateRequest, String projectId) {

		EmsLogger.log("UPDATE PROJECT WITH REQUEST : " + EmsUtility.toJsonString(projectUpdateRequest), logger);

		Project project = new Project();
		Optional<Project> optionalProject = projectRepository.findByProjectIdAndAuditIsActive(projectId, true);

		// removing relation from project adn dept
		// list of depts
		// new list of depts
		// compare list
		// for the ones not present in new list remove project from those depts

		if (optionalProject.isPresent()) {
			
			List<Department> currentDepartmentsOfProjectList = new ArrayList<>();
			List<Department> updatedDepartmentsOfProjectList = new ArrayList<>();
			
			project = optionalProject.get();
			project = ProjectMapper.projectRequestToEntityMapper(projectUpdateRequest,
					Constants.RequestOperation.UPDATE, project);
			List<Department> currentDepartmentsOfProject = project.getDepartments();

			Optional<List<Department>> optionalDepartmentList = deptRepository
					.findByDepartmentIdInAndAuditIsActive(projectUpdateRequest.getDepartmentIdList(), true);
			if (optionalDepartmentList.isPresent() && !optionalDepartmentList.get().isEmpty()) {
				
				project.setDepartments(optionalDepartmentList.get());
			}
			if (optionalDepartmentList.isPresent() && !optionalDepartmentList.get().isEmpty()) {
				project.setDepartments(optionalDepartmentList.get());
			}

			project = projectRepository.save(project);

			ProjectResponse projectResponse = new ProjectResponse();
			projectResponse = ProjectMapper.projectEntityToResponseMapper(project);
			return projectResponse;
		}
		return null;
	}

	@Override
	public boolean deleteProject(String projectId) {

		// delete relation when project/dept deleted
		return false;
	}

	@Override
	public ProjectResponse getByProjectId(String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getAllProjects(int pageNumber, int pageSize, String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getAllProjectsInDepartment(String departmentId, int pageNumber, int pageSize,
			String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectResponse addProjectToDepartment(String projectId, String departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
