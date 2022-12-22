package com.ems.operation.mapper;

import com.ems.operation.dto.request.UserAddInEntityRequest;
import com.ems.operation.entity.UserEntityMapping;
import com.ems.operation.util.AuditUtility;

public class UserEntityMappingMapper {

	public static UserEntityMapping entityUserRequestToEntityMapper(UserAddInEntityRequest userAddInEntityRequest,
			String entityId, String entityType, String status) {

		UserEntityMapping userEntityMapping = new UserEntityMapping();
		userEntityMapping.setUserId(userAddInEntityRequest.getUserId());
		userEntityMapping.setEntityId(entityId);
		userEntityMapping.setEntityType(entityType);
		userEntityMapping.setRole(userAddInEntityRequest.getRole());
		userEntityMapping.setReportsTo(userAddInEntityRequest.getReportsTo());
		userEntityMapping.setEntityLevelDesignation(userAddInEntityRequest.getEntityLevelDesignation());
		userEntityMapping.setStatus(status);
		userEntityMapping.setAudit(AuditUtility.createApiAuditBuild());

		return userEntityMapping;
	}

}
