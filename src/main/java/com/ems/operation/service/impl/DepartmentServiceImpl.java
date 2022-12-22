package com.ems.operation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ems.operation.communication.SyncCommunication;
import com.ems.operation.communication.response.UserResponse;
import com.ems.operation.constant.Constants;
import com.ems.operation.dto.request.DepartmentRequest;
import com.ems.operation.dto.request.DepartmentUpdateRequest;
import com.ems.operation.dto.request.UserAddInEntityRequest;
import com.ems.operation.dto.response.DepartmentResponse;
import com.ems.operation.dto.response.UserAddInEntityResponse;
import com.ems.operation.entity.Department;
import com.ems.operation.mapper.DepartmentMapper;
import com.ems.operation.mapper.UserEntityMappingMapper;
import com.ems.operation.repo.DepartmentRepo;
import com.ems.operation.repo.UserEntityRepo;
import com.ems.operation.service.DepartmentService;
import com.ems.operation.util.AuditUtility;

import ems.utility.logger.EmsLogger;
import ems.utility.util.EmsUtility;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	// kafka to be used when user is deleted - so would be have to deleted from all
	// departments and prjects too
	// before deleting user check if user id is department head or project head of
	// any department/ project to resassign before deleting

	@Autowired
	private DepartmentRepo deptRepository;

	@Autowired
	private UserEntityRepo userEntityRepository;

	@Autowired
	private SyncCommunication syncCommunication;

	private Logger logger = EmsLogger.getLogger(DepartmentServiceImpl.class.getName());

	@Override
	public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {

		EmsLogger.log("CREATE DEPARTMENT WITH REQUEST : " + EmsUtility.toJsonString(departmentRequest), logger);

		// validation - check if user exists

		Department department = new Department();
		department = DepartmentMapper.departmentRequestToEntityMapper(departmentRequest);
		department = deptRepository.save(department);

		DepartmentResponse deptResponse = new DepartmentResponse();
		deptResponse = DepartmentMapper.departmentEntityToResponseMapper(department);

		return deptResponse;
	}

	@Override
	public DepartmentResponse updateDepartment(DepartmentUpdateRequest departmentUpdateRequest) {

		EmsLogger.log("UPDATE DEPARTMENT WITH REQUEST : " + EmsUtility.toJsonString(departmentUpdateRequest), logger);

		// validation - IF NEEDED

		String departmentId = departmentUpdateRequest.getDepartmentId();

		Department department = new Department();
		Optional<Department> optionalDepartment = deptRepository.findByDepartmentIdAndAuditIsActive(departmentId, true);
		if (optionalDepartment.isPresent()) {
			department = optionalDepartment.get();
			department = DepartmentMapper.departmentUpdateRequestToEntityMapper(departmentUpdateRequest, department);
			department = deptRepository.save(department);

			DepartmentResponse departmentResponse = new DepartmentResponse();
			departmentResponse = DepartmentMapper.departmentEntityToResponseMapper(department);

			return departmentResponse;
		}
		return null;
	}

	@Override
	public boolean deleteDepartment(String departmentId) {

		EmsLogger.log("DELETE DEPARTMENT : " + departmentId, logger);

		Optional<Department> optionalDepartment = deptRepository.findByDepartmentIdAndAuditIsActive(departmentId, true);
		Department deletedDepartment = new Department();
		boolean isDeleted = false;
		if (optionalDepartment.isPresent()) {
			deletedDepartment = optionalDepartment.get();
			deletedDepartment.setAudit(AuditUtility.deleteApiAuditBuild(deletedDepartment.getAudit()));
			deletedDepartment = deptRepository.save(deletedDepartment);
			if (deletedDepartment.getAudit().isActive())
				isDeleted = false;
			else {
				isDeleted = true;
			}
		}
		return isDeleted;
	}

	@Override
	public HashMap<String, Object> getAllDepartments(int pageNumber, int pageSize, String searchQuery) {

		EmsLogger.log("GET ALL DEPARTMENTS WHERE pageNumber:" + pageNumber + " , pageSize:" + pageSize
				+ ", searchQuery:" + searchQuery, logger);

		List<Department> pagedDepartmentList = new ArrayList<>();
		List<DepartmentResponse> pagedDepartmentResponseList = new ArrayList<>();
		HashMap<String, Object> resultBody = new HashMap<>();

		Sort departmenteNameSort = Sort.by("departmentName");
		Pageable paging = PageRequest.of(pageNumber, pageSize, departmenteNameSort);

		if (searchQuery.isEmpty()) {
			Page<Department> pagedResult = deptRepository.findByAuditIsActive(true, paging);
			if (pagedResult.hasContent()) {
				pagedDepartmentList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());

		} else {
			Page<Department> pagedResult = deptRepository.findByAuditIsActiveAndDepartmentNameContainingIgnoreCase(true,
					searchQuery, paging);
			if (pagedResult.hasContent()) {
				pagedDepartmentList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());
		}
		pagedDepartmentResponseList = pagedDepartmentList.stream()
				.map(pagedDepartment -> DepartmentMapper.departmentEntityToResponseMapper(pagedDepartment))
				.collect(Collectors.toList());
		// TO DO - add project details and department head details
		resultBody.put("pagedUserResponseList", pagedDepartmentResponseList);

		return resultBody;
	}

	@Override
	public DepartmentResponse getDepartmentById(String departmentId) {

		EmsLogger.log("GET DEPARTMENT : " + departmentId, logger);

		Optional<Department> optionalDepartment = deptRepository.findByDepartmentIdAndAuditIsActive(departmentId, true);
		Department department = new Department();
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (optionalDepartment.isPresent()) {
			department = optionalDepartment.get();
			departmentResponse = DepartmentMapper.departmentEntityToResponseMapper(department);
			UserResponse userResponse = new UserResponse();
			userResponse = syncCommunication.getUserDetails(department.getDepartmentHead());
			departmentResponse.setDepartmentHeadFirstName(userResponse.getFirstName());
			departmentResponse.setDepartmentHeadLastName(userResponse.getLastName());
			// TO DO - add project details
		}
		return departmentResponse;
	}

	@Override
	public UserAddInEntityResponse addUserToDept(UserAddInEntityRequest userAddInDepartmentRequest,
			String departmentId) {

		// TO DO - Validation check if user and department exists

		// create request object
		UserEntityMappingMapper.entityUserRequestToEntityMapper(userAddInDepartmentRequest, departmentId,
				Constants.EntityType.DEPARTMENT, Constants.Status.ACTIVE);
		// save to repo
		// convert to response
		return null;

	}

	@Override
	public void removeUserFromDept() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUserInDept() {
		// TODO Auto-generated method stub

	}

}
