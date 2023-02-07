package com.ems.operation.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.operation.dto.request.UserEntityRequest;
import com.ems.operation.dto.response.UserEntityResponse;
import com.ems.operation.service.impl.UserEntityServiceImpl;

@RestController
@RequestMapping("/api/operation/user-entity")
public class UserEntityController {

	@Autowired
	UserEntityServiceImpl userEntityServiceImpl;

	@PostMapping(value = "/{entityId}", produces = "application/json")
	public UserEntityResponse addUserToEntity(@Valid @RequestBody UserEntityRequest userEntityRequest,
			@PathVariable String entityId) {

		return userEntityServiceImpl.addUserToEntity(userEntityRequest, entityId);
	}

	@DeleteMapping(value = "/{entityId}/{userId}", produces = "application/json")
	public boolean deleteUserFromEntity(@PathVariable String entityId, @PathVariable String userId) {

		return userEntityServiceImpl.removeUserFromEntity(userId, entityId);
	}

	@PutMapping(value = "/{entityId}", produces = "application/json")
	public UserEntityResponse updateUserInEntity(@Valid @RequestBody UserEntityRequest userEntityRequest,
			@PathVariable String entityId) {

		return userEntityServiceImpl.updateUserInEntity(userEntityRequest, entityId);
	}

	@GetMapping(value = "/{entityId}", produces = "application/json")
	public HashMap<String, Object> getAllUsersInEntity(@PathVariable String entityId,
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery) {

		return userEntityServiceImpl.getAllUsersByEntityId(entityId, pageNumber, pageSize, searchQuery);
	}

	@GetMapping(value = "/{userId}", produces = "application/json")
	public HashMap<String, Object> getAllUsersByReportingHead(@PathVariable String userId,
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery) {

		return userEntityServiceImpl.getAllUsersByReportingHead(userId, pageNumber, pageSize, searchQuery);
	}

	@GetMapping(value = "/{entityId}/{userId}", produces = "application/json")
	public HashMap<String, Object> getAllUsersByReportingHeadAndEntityId(@PathVariable String entityId,
			@PathVariable String userId, @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "searchQuery", defaultValue = "", required = false) String searchQuery) {

		return userEntityServiceImpl.getAllUsersByReportingHeadAndEntityId(userId, entityId, pageNumber, pageSize,
				searchQuery);
	}

}
