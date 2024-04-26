package com.nguyentronghao.employee_service.service;

import com.nguyentronghao.employee_service.exception.ResourceNotFoundException;
import com.nguyentronghao.employee_service.model.dto.ApiResponseDto;
import com.nguyentronghao.employee_service.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    /**
     * Retrieves an employee by their ID.
     * <p>
     * This method retrieves an employee from the repository based on the provided ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The ApiResponseDto object representing the retrieved employee.
     * @throws ResourceNotFoundException if no employee is found with the provided ID.
     */
    ApiResponseDto getEmployeeById(long id);
    
    /**
     * Retrieves all employees.
     * <p>
     * This method retrieves all employees from the repository.
     *
     * @return A list of EmployeeDto objects representing all employees.
     */
    List<EmployeeDto> getAllEmployees();
    
    /**
     * Add a new employee.
     *
     * @param employeeDto The EmployeeDto object containing the information of the employee to be saved.
     * @return The EmployeeDto object representing the saved employee.
     */
    EmployeeDto addEmployee(EmployeeDto employeeDto);
}
