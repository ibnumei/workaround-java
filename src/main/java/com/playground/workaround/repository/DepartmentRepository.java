package com.playground.workaround.repository;

import com.playground.workaround.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository

// Interface extending CrudRepository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}