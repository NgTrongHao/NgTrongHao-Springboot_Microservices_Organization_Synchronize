package com.nguyentronghao.employee_service.repository;

import com.nguyentronghao.employee_service.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}