package com.nguyentronghao.department_service.service;

import com.nguyentronghao.department_service.model.dto.DepartmentDto;

public interface DepartmentService {
    /**
     * Retrieves a department by its code.
     * <p>
     * This method retrieves a department from the repository based on the provided code.
     *
     * @param code The code of the department to retrieve.
     * @return The DepartmentDto object representing the retrieved department, or null if not found.
     */
    DepartmentDto getDepartmentByCode(String code);
    
    /**
     * Add a new department.
     *
     * @param departmentDto The DepartmentDto object containing the information of the department to be saved.
     * @return The DepartmentDto object representing the saved department.
     */
    DepartmentDto addDepartment(DepartmentDto departmentDto);
}
