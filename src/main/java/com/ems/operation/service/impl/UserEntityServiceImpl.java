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
import com.ems.operation.dto.request.UserEntityRequest;
import com.ems.operation.dto.response.UserEntityDetailResponse;
import com.ems.operation.dto.response.UserEntityResponse;
import com.ems.operation.entity.UserEntityMapping;
import com.ems.operation.mapper.UserEntityMappingMapper;
import com.ems.operation.repo.UserEntityMappingRepo;
import com.ems.operation.service.UserEntityService;
import com.ems.operation.util.AuditUtility;

import ems.utility.logger.EmsLogger;

@Service
public class UserEntityServiceImpl implements UserEntityService {

	@Autowired
	private UserEntityMappingRepo userEntityRepository;

	@Autowired
	private SyncCommunication syncCommunication;

	private Logger logger = EmsLogger.getLogger(DepartmentServiceImpl.class.getName());

	@Override
	public UserEntityResponse addUserToEntity(UserEntityRequest userEntityRequest, String entityId) {
		EmsLogger.log("ADD USER " + userEntityRequest.getUserId() + " TO ENTITY : " + entityId, logger);

		UserEntityMapping userEntityMapping = new UserEntityMapping();
		userEntityMapping = UserEntityMappingMapper.entityUserMappingRequestToEntityMapper(userEntityRequest, entityId,
				Constants.Status.ACTIVE);

		UserResponse userResponse = new UserResponse();
		userResponse = syncCommunication.getUserDetails(userEntityRequest.getUserId());
		userEntityMapping.setUserFirstName(userResponse.getFirstName());
		userEntityMapping.setUserLastName(userResponse.getLastName());

		userEntityMapping = userEntityRepository.save(userEntityMapping);

		UserEntityResponse userEntityResponse = new UserEntityResponse();
		userEntityResponse = UserEntityMappingMapper.entityUserMappingEntityToResponseMapper(userEntityMapping);
		return userEntityResponse;
	}

	@Override
	public boolean removeUserFromEntity(String userId, String entityId) {

		EmsLogger.log("DELETE USER-ENTITY WITH ENTITY ID : " + entityId + " ADD USER ID : " + userId, logger);

		Optional<UserEntityMapping> optionalUserEntity = userEntityRepository
				.findByEntityIdAndUserIdAndAuditIsActive(entityId, userId, true);
		UserEntityMapping deletedUserEntity = new UserEntityMapping();
		boolean isDeleted = false;
		if (optionalUserEntity.isPresent()) {
			deletedUserEntity = optionalUserEntity.get();
			deletedUserEntity.setAudit(AuditUtility.deleteApiAuditBuild(deletedUserEntity.getAudit()));
			deletedUserEntity = userEntityRepository.save(deletedUserEntity);
			if (!deletedUserEntity.getAudit().isActive())
				isDeleted = true;
		}
		return isDeleted;

	}

	@Override
	public UserEntityResponse updateUserInEntity(UserEntityRequest userEntityRequest, String entityId) {
		EmsLogger.log("UPDATE USER : " + userEntityRequest.getUserId() + " IN ENTITY : " + entityId, logger);

		String userId = userEntityRequest.getUserId();
		Optional<UserEntityMapping> optionalUserEntity = userEntityRepository
				.findByEntityIdAndUserIdAndAuditIsActive(entityId, userId, true);
		UserEntityMapping updatedUserEntity = new UserEntityMapping();
		if (optionalUserEntity.isPresent()) {
			updatedUserEntity = optionalUserEntity.get();
			updatedUserEntity = UserEntityMappingMapper.entityUserMappingUpdateRequestToEntityMapper(userEntityRequest,
					updatedUserEntity);
			updatedUserEntity = userEntityRepository.save(updatedUserEntity);

			UserEntityResponse userEntityResponse = new UserEntityResponse();
			userEntityResponse = UserEntityMappingMapper.entityUserMappingEntityToResponseMapper(updatedUserEntity);
			return userEntityResponse;
		}
		return null;
	}

	@Override
	public HashMap<String, Object> getAllUsersByEntityId(String entityId, int pageNumber, int pageSize,
			String searchQuery) {

		EmsLogger.log("GET ALL USERS IN ENTITY : " + entityId + " WHERE pageNumber:" + pageNumber + " , pageSize:"
				+ pageSize + ", searchQuery:" + searchQuery, logger);

		List<UserEntityMapping> pagedUserEntityList = new ArrayList<>();
		List<UserEntityDetailResponse> pagedUserResponseList = new ArrayList<>();
		HashMap<String, Object> resultBody = new HashMap<>();

		Sort firstNameSort = Sort.by("userFirstName");
		Sort lastNameSort = Sort.by("userLastName");
		Sort groupBySort = firstNameSort.and(lastNameSort);
		Pageable paging = PageRequest.of(pageNumber, pageSize, groupBySort);

		if (searchQuery.isEmpty()) {
			Page<UserEntityMapping> pagedResult = userEntityRepository.findByEntityIdAndAuditIsActive(entityId, true,
					paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());

		} else {
			Page<UserEntityMapping> pagedResult = userEntityRepository
					.findByEntityIdAndAuditIsActiveAndUserFirstNameContaining(entityId, true, searchQuery, paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());
		}

		List<String> userIdList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();
		HashMap<String, UserResponse> userResponseMap = new HashMap<>();

		userIdList = pagedUserEntityList.stream().map(UserEntityMapping::getUserId).collect(Collectors.toList());
		userResponseList = syncCommunication.getUserDetailsList(userIdList);
		userResponseList.stream().forEach(userResponse -> userResponseMap.put(userResponse.getUserId(), userResponse));

		pagedUserResponseList = UserEntityMappingMapper.entityUserDetailsEntityListToResponseListMapper(userResponseMap,
				pagedUserEntityList);

		resultBody.put("pagedUserResponseList", pagedUserResponseList);
		return resultBody;
	}

	@Override
	public HashMap<String, Object> getAllUsersByReportingHead(String userId, int pageNumber, int pageSize,
			String searchQuery) {
		EmsLogger.log("GET ALL USERS WHO REPORT TO : " + userId + " WHERE pageNumber:" + pageNumber + " , pageSize:"
				+ pageSize + ", searchQuery:" + searchQuery, logger);

		List<UserEntityMapping> pagedUserEntityList = new ArrayList<>();
		List<UserEntityDetailResponse> pagedUserResponseList = new ArrayList<>();
		HashMap<String, Object> resultBody = new HashMap<>();

		Sort firstNameSort = Sort.by("userFirstName");
		Sort lastNameSort = Sort.by("userLastName");
		Sort groupBySort = firstNameSort.and(lastNameSort);
		Pageable paging = PageRequest.of(pageNumber, pageSize, groupBySort);

		if (searchQuery.isEmpty()) {
			Page<UserEntityMapping> pagedResult = userEntityRepository.findByReportsToAndAuditIsActive(userId, true,
					paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());

		} else {
			Page<UserEntityMapping> pagedResult = userEntityRepository
					.findByReportsToAndAuditIsActiveAndUserFirstNameContaining(userId, true, searchQuery, paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());
		}

		List<String> userIdList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();
		HashMap<String, UserResponse> userResponseMap = new HashMap<>();

		userIdList = pagedUserEntityList.stream().map(UserEntityMapping::getUserId).collect(Collectors.toList());
		userResponseList = syncCommunication.getUserDetailsList(userIdList);
		userResponseList.stream().forEach(userResponse -> userResponseMap.put(userResponse.getUserId(), userResponse));

		pagedUserResponseList = UserEntityMappingMapper.entityUserDetailsEntityListToResponseListMapper(userResponseMap,
				pagedUserEntityList);

		resultBody.put("pagedUserResponseList", pagedUserResponseList);
		return resultBody;
	}

	@Override
	public HashMap<String, Object> getAllUsersByReportingHeadAndEntityId(String userId, String entityId, int pageNumber,
			int pageSize, String searchQuery) {
		EmsLogger.log("GET ALL USERS WHO REPORT TO : " + userId + " IN ENITY : " + entityId + " WHERE pageNumber:"
				+ pageNumber + " , pageSize:" + pageSize + ", searchQuery:" + searchQuery, logger);

		List<UserEntityMapping> pagedUserEntityList = new ArrayList<>();
		List<UserEntityDetailResponse> pagedUserResponseList = new ArrayList<>();
		HashMap<String, Object> resultBody = new HashMap<>();

		Sort firstNameSort = Sort.by("userFirstName");
		Sort lastNameSort = Sort.by("userLastName");
		Sort groupBySort = firstNameSort.and(lastNameSort);
		Pageable paging = PageRequest.of(pageNumber, pageSize, groupBySort);

		if (searchQuery.isEmpty()) {
			Page<UserEntityMapping> pagedResult = userEntityRepository
					.findByReportsToAndEntityIdAndAuditIsActive(userId, entityId, true, paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());

		} else {
			Page<UserEntityMapping> pagedResult = userEntityRepository
					.findByReportsToAndEntityIdAndAuditIsActiveAndUserFirstNameContaining(userId, entityId, true,
							searchQuery, paging);
			if (pagedResult.hasContent()) {
				pagedUserEntityList = pagedResult.getContent();
			}
			resultBody.put("totalPages", pagedResult.getTotalPages());
			resultBody.put("totalUsers", pagedResult.getTotalElements());
		}

		List<String> userIdList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();
		HashMap<String, UserResponse> userResponseMap = new HashMap<>();

		userIdList = pagedUserEntityList.stream().map(UserEntityMapping::getUserId).collect(Collectors.toList());
		userResponseList = syncCommunication.getUserDetailsList(userIdList);
		userResponseList.stream().forEach(userResponse -> userResponseMap.put(userResponse.getUserId(), userResponse));

		pagedUserResponseList = UserEntityMappingMapper.entityUserDetailsEntityListToResponseListMapper(userResponseMap,
				pagedUserEntityList);

		resultBody.put("pagedUserResponseList", pagedUserResponseList);
		return resultBody;
	}

}
