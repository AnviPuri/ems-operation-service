package com.ems.operation.communication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ems.operation.communication.response.UserResponse;
import com.ems.operation.constant.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SyncCommunication {

	public UserResponse getUserDetails(String userId) {

		WebClient webClient = WebClient.create(Constants.BaseUri.USER_SERVICE_URI);
		UserResponse userResponse = new UserResponse();
		userResponse = webClient.get().uri(Constants.ApiPath.GET_BY_USER_ID + userId).retrieve()
				.bodyToMono(UserResponse.class).block();
		return userResponse;
	}

	public List<UserResponse> getUserDetailsList(List<String> userIdList) {

		WebClient webClient = WebClient.create(Constants.BaseUri.USER_SERVICE_URI);
		Object[] objects = webClient.get().uri(uriBuilder -> uriBuilder.path(Constants.ApiPath.GET_ALL_USERS)
				.queryParam("userIdList", userIdList).build()).retrieve().bodyToMono(Object[].class).block();

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return Arrays.stream(objects).map(object -> mapper.convertValue(object, UserResponse.class))
				.collect(Collectors.toList());
	}

}
