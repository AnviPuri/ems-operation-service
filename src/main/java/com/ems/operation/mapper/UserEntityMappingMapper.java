package com.ems.operation.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ems.operation.communication.response.UserResponse;
import com.ems.operation.dto.request.UserEntityRequest;
import com.ems.operation.dto.response.UserEntityDetailResponse;
import com.ems.operation.dto.response.UserEntityResponse;
import com.ems.operation.entity.UserEntityMapping;
import com.ems.operation.util.AuditUtility;

public class UserEntityMappingMapper {

	public static UserEntityMapping entityUserMappingRequestToEntityMapper(UserEntityRequest userEntityRequest,
			String entityId, String status) {

		UserEntityMapping userEntityMapping = new UserEntityMapping();
		userEntityMapping.setUserId(userEntityRequest.getUserId());
		userEntityMapping.setEntityId(entityId);
		userEntityMapping.setEntityType(userEntityRequest.getEntityType());
		userEntityMapping.setRole(userEntityRequest.getRole());
		userEntityMapping.setReportsTo(userEntityRequest.getReportsTo());
		userEntityMapping.setEntityLevelDesignation(userEntityRequest.getEntityLevelDesignation());
		userEntityMapping.setStatus(status);
		userEntityMapping.setAudit(AuditUtility.createApiAuditBuild());

		return userEntityMapping;
	}

	public static UserEntityMapping entityUserMappingUpdateRequestToEntityMapper(UserEntityRequest userEntityRequest,
			UserEntityMapping updatedUserEntity) {

		updatedUserEntity.setRole(userEntityRequest.getRole());
		updatedUserEntity.setReportsTo(userEntityRequest.getReportsTo());
		updatedUserEntity.setEntityLevelDesignation(userEntityRequest.getEntityLevelDesignation());
		updatedUserEntity.setStatus(userEntityRequest.getStatus());
		updatedUserEntity.setAudit(AuditUtility.updateApiAuditBuild(updatedUserEntity.getAudit()));

		return updatedUserEntity;
	}

	public static UserEntityResponse entityUserMappingEntityToResponseMapper(UserEntityMapping userEntityMapping) {

		UserEntityResponse userEntityResponse = new UserEntityResponse();
		userEntityResponse.setUserEntityId(userEntityMapping.getUserEntityId());
		userEntityResponse.setUserId(userEntityMapping.getEntityId());
		userEntityResponse.setEntityId(userEntityMapping.getEntityId());
		userEntityResponse.setEntityType(userEntityMapping.getEntityType());
		userEntityResponse.setRole(userEntityMapping.getRole());
		userEntityResponse.setReportsTo(userEntityMapping.getReportsTo());
		userEntityResponse.setEntityLevelDesignation(userEntityMapping.getEntityLevelDesignation());
		userEntityResponse.setStatus(userEntityMapping.getStatus());
		userEntityResponse.setUserFirstName(userEntityMapping.getUserFirstName());
		userEntityResponse.setUserLastName(userEntityMapping.getUserLastName());
		return userEntityResponse;
	}

	public static List<UserEntityDetailResponse> entityUserDetailsEntityListToResponseListMapper(
			HashMap<String, UserResponse> userResponseMap, List<UserEntityMapping> userEntityList) {

		List<UserEntityDetailResponse> userEntityDetailResponseList = new ArrayList<>();

		userEntityList.stream().forEach(userEntity -> {
			UserEntityDetailResponse userEntityDetailResponse = new UserEntityDetailResponse();
			UserResponse userResponse = new UserResponse();
			userResponse = userResponseMap.get(userEntity.getUserId());

			userEntityDetailResponse.setUserId(userEntity.getUserId());
			userEntityDetailResponse.setEntityId(userEntity.getEntityId());
			userEntityDetailResponse.setFirstName(userEntity.getUserFirstName());
			userEntityDetailResponse.setMiddleName(userResponse.getMiddleName());
			userEntityDetailResponse.setLastName(userResponse.getLastName());
			userEntityDetailResponse.setGender(userResponse.getGender());
			userEntityDetailResponse.setOrgEmail(userResponse.getOrgEmail());
			userEntityDetailResponse.setCountryCode(userResponse.getCountryCode());
			userEntityDetailResponse.setPhoneNumber(userResponse.getPhoneNumber());
			userEntityDetailResponse.setOfficialDesignation(userResponse.getOfficialDesignation());
			userEntityDetailResponse.setRole(userEntity.getRole());
			userEntityDetailResponse.setReportsTo(userEntity.getReportsTo());
			userEntityDetailResponse.setEntityLevelDesignation(userEntity.getEntityLevelDesignation());
			userEntityDetailResponse.setStatus(userEntity.getStatus());

			userEntityDetailResponseList.add(userEntityDetailResponse);

		});

		return userEntityDetailResponseList;
	}

}
