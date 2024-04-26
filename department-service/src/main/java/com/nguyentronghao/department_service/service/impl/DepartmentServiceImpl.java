package com.nguyentronghao.department_service.service.impl;

import com.nguyentronghao.department_service.model.dto.DepartmentDto;
import com.nguyentronghao.department_service.model.entity.Department;
import com.nguyentronghao.department_service.repository.DepartmentRepository;
import com.nguyentronghao.department_service.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;
    
    public DepartmentServiceImpl(ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }
    
    /**
     * Retrieves a department by its code.
     * <p>
     * This method retrieves a department from the repository based on the provided code.
     *
     * @param code The code of the department to retrieve.
     * @return The DepartmentDto object representing the retrieved department, or null if not found.
     */
    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        // Retrieve the department from the repository by its code,
        // then convert it to DepartmentDto and return it.
        return convertDepartmentToDepartmentDto(
                departmentRepository.findByCode(code)
        );
    }
    
    /**
     * Create a new department.
     *
     * @param departmentDto The DepartmentDto object containing the information of the department to be saved.
     * @return The DepartmentDto object representing the saved department.
     */
    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        return convertDepartmentToDepartmentDto(
                departmentRepository.save(
                        convertDepartmentDtoToDepartment(departmentDto)
                )
        );
    }
    
    /**
     * Converts a DepartmentDto object to a Department object.
     *
     * @param departmentDto The DepartmentDto object to be converted.
     * @return The Department object converted from the provided DepartmentDto object.
     */
    private Department convertDepartmentDtoToDepartment(DepartmentDto departmentDto) {
        // Use ModelMapper to map the fields from DepartmentDto to Department and return the result
        return modelMapper.map(departmentDto, Department.class);
    }
    
    /**
     * Converts a Department object to a DepartmentDto object.
     *
     * @param department The Department object to be converted.
     * @return The DepartmentDto object converted from the provided Department object.
     */
    private DepartmentDto convertDepartmentToDepartmentDto(Department department) {
        // Use ModelMapper to map the fields from Department to DepartmentDto and return the result
        return modelMapper.map(department, DepartmentDto.class);
    }
}
