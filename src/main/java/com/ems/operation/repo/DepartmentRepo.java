package com.ems.operation.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.operation.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {

	Optional<Department> findByDepartmentIdAndAuditIsActive(String departmentId, boolean isActive);

	Page<Department> findByAuditIsActive(boolean isActive, Pageable pageable);

	Page<Department> findByAuditIsActiveAndDepartmentNameContainingIgnoreCase(boolean isActive, String departmentName,
			Pageable pageable);

}
