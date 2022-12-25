package com.ems.operation.service;

import java.util.HashMap;

import com.ems.operation.dto.request.UserEntityRequest;
import com.ems.operation.dto.response.UserEntityResponse;

public interface UserEntityService {

	public UserEntityResponse addUserToEntity(UserEntityRequest userEntityRequest, String entityId);

	public boolean removeUserFromEntity(String userId, String entityId);

	public UserEntityResponse updateUserInEntity(UserEntityRequest userEntityRequest, String entityId);

	public HashMap<String, Object> getAllUsersByEntityId(String entityId, int pageNumber, int pageSize,
			String searchQuery);

	public HashMap<String, Object> getAllUsersByReportingHead(String userId, int pageNumber, int pageSize,
			String searchQuery);

	public HashMap<String, Object> getAllUsersByReportingHeadAndEntityId(String userId, String entityId, int pageNumber,
			int pageSize, String searchQuery);

}
