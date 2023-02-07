package com.ems.operation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.operation.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, String> {

	Optional<Project> findByProjectIdAndAuditIsActive(String projectId, boolean isActive);

}
