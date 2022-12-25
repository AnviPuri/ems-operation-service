package com.ems.operation.communication;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ems.operation.communication.response.UserResponse;
import com.ems.operation.constant.Constants;

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

//		WebClient webClient = WebClient.create(Constants.BaseUri.USER_SERVICE_URI);
//		UserResponse userResponse = new UserResponse();
//		userResponse = webClient.get().uri(Constants.ApiPath.GET_BY_USER_ID + userId).retrieve()
//				.bodyToMono(UserResponse.class).block();
//		return userResponse;
		return null;
	}

}
