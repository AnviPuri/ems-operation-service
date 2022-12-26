package com.ems.operation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.operation.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, String> {

}
