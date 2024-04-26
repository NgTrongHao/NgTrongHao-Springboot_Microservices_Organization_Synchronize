package com.nguyentronghao.department_service.repository;

import com.nguyentronghao.department_service.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByCode(String code);
}