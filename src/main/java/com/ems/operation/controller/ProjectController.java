package com.ems.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.operation.communication.SyncCommunication;

@RestController
@RequestMapping("/api/operation/project")
public class ProjectController {

	@Autowired
	private SyncCommunication syncCommunication;

}
