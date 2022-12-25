package com.ems.operation.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.operation.entity.UserEntityMapping;

@Repository
public interface UserEntityMappingRepo extends JpaRepository<UserEntityMapping, String> {

	Optional<UserEntityMapping> findByEntityIdAndUserIdAndAuditIsActive(String entityId, String userId,
			boolean isActive);

	Page<UserEntityMapping> findByEntityIdAndAuditIsActive(String entityId, boolean isActive, Pageable pageable);

	Page<UserEntityMapping> findByEntityIdAndAuditIsActiveAndUserFirstNameContaining(String entityId, boolean isActive,
			String searchQuery, Pageable pageable);

	Page<UserEntityMapping> findByReportsToAndAuditIsActive(String userId, boolean isActive, Pageable pageable);

	Page<UserEntityMapping> findByReportsToAndAuditIsActiveAndUserFirstNameContaining(String userId, boolean isActive,
			String searchQuery, Pageable pageable);

	Page<UserEntityMapping> findByReportsToAndEntityIdAndAuditIsActive(String userId, String entityId, boolean isActive,
			Pageable pageable);

	Page<UserEntityMapping> findByReportsToAndEntityIdAndAuditIsActiveAndUserFirstNameContaining(String userId,
			String entityId, boolean isActive, String searchQuery, Pageable pageable);

}
