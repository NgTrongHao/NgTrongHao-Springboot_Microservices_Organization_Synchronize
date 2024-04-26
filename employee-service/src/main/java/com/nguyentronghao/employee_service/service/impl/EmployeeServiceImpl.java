package com.nguyentronghao.employee_service.service.impl;

import com.nguyentronghao.employee_service.exception.ResourceNotFoundException;
import com.nguyentronghao.employee_service.model.dto.ApiResponseDto;
import com.nguyentronghao.employee_service.model.dto.DepartmentDto;
import com.nguyentronghao.employee_service.model.dto.EmployeeDto;
import com.nguyentronghao.employee_service.model.entity.Employee;
import com.nguyentronghao.employee_service.repository.EmployeeRepository;
import com.nguyentronghao.employee_service.service.DepartmentApiClient;
import com.nguyentronghao.employee_service.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    private final RestTemplate restTemplate;
    
    private final WebClient webClient;
    
    private final DepartmentApiClient departmentApiClient;
    
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    
    public EmployeeServiceImpl(RestTemplate restTemplate, WebClient webClient, DepartmentApiClient departmentApiClient, ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.departmentApiClient = departmentApiClient;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }
    
    /**
     * Retrieves an employee by their ID.
     * <p>
     * This method retrieves an employee from the repository based on the provided ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return An ApiResponseDto object containing the retrieved EmployeeDto object representing the employee and the associated DepartmentDto object.
     * @throws ResourceNotFoundException if no employee is found with the provided ID.
     */
    @Override
    public ApiResponseDto getEmployeeById(long id) {
        // Retrieve the employee by their ID from the repository.
        Employee employee = findEmployeeById(id);
        
        // Retrieve the department associated with the employee.
//        DepartmentDto departmentDto = getDepartmentDtoByDepartmentCodeUsingRestTemplate(employee.getDepartmentCode());
//        DepartmentDto departmentDto = getDepartmentDtoByDepartmentCodeUsingWebClient(employee.getDepartmentCode());
        DepartmentDto departmentDto = getDepartmentDtoByDepartmentCodeUsingSpringCloudOpenFeign(employee.getDepartmentCode());
        
        // Return an ApiResponseDto object containing the EmployeeDto object representing the retrieved employee
        // and the DepartmentDto object representing the associated department.
        return new ApiResponseDto(
                convertEmployeeToEmployeeDto(employee),
                departmentDto
        );
    }
    
    /**
     * Retrieves all employees.
     * <p>
     * This method retrieves all employees from the repository.
     *
     * @return A list of EmployeeDto objects representing all employees.
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertEmployeeToEmployeeDto)
                .toList();
    }
    
    /**
     * Add a new employee.
     *
     * @param employeeDto The EmployeeDto object containing the information of the employee to be saved.
     * @return The EmployeeDto object representing the saved employee.
     */
    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        return convertEmployeeToEmployeeDto(
                employeeRepository.save(convertEmployeeDtoToEmployee(employeeDto))
        );
    }
    
    /**
     * Retrieves a department DTO by its department code using RestTemplate.
     * <p>
     * This method retrieves a DepartmentDto object by making a REST API call to another service using RestTemplate.
     *
     * @param departmentCode The code of the department to retrieve.
     * @return The DepartmentDto object representing the retrieved department.
     */
    private DepartmentDto getDepartmentDtoByDepartmentCodeUsingRestTemplate(String departmentCode) {
        // Make a REST API call to retrieve the department DTO by its code using RestTemplate.
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/department/" + departmentCode,
                DepartmentDto.class
        );
        
        // Extract and return the department DTO from the response entity.
        return responseEntity.getBody();
    }
    
    /**
     * Retrieves a department DTO by its department code using WebClient.
     * <p>
     * This method retrieves a DepartmentDto object by making a REST API call to another service using WebClient.
     *
     * @param departmentCode The code of the department to retrieve.
     * @return The DepartmentDto object representing the retrieved department.
     */
    private DepartmentDto getDepartmentDtoByDepartmentCodeUsingWebClient(String departmentCode) {
        // Make a REST API call to retrieve the department DTO by its code using WebClient.
        return webClient.get()
                .uri("http://localhost:8080/api/department/" + departmentCode)
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
    }
    
    private DepartmentDto getDepartmentDtoByDepartmentCodeUsingSpringCloudOpenFeign(String departmentCode) {
        return departmentApiClient.getDepartment(departmentCode);
    }
    
    /**
     * Finds an employee by their ID.
     *
     * @param id The ID of the employee to find.
     * @return The Employee object representing the employee with the provided ID.
     * @throws ResourceNotFoundException if no employee is found with the provided ID.
     */
    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
    }
    
    /**
     * Converts an Employee object to an EmployeeDto object.
     *
     * @param employee The Employee object to be converted.
     * @return The EmployeeDto object converted from the provided Employee object.
     */
    private EmployeeDto convertEmployeeToEmployeeDto(Employee employee) {
        // Use ModelMapper to map the fields from Employee to EmployeeDto and return the result.
        return modelMapper.map(employee, EmployeeDto.class);
    }
    
    /**
     * Converts an EmployeeDto object to an Employee object.
     *
     * @param employeeDto The EmployeeDto object to be converted.
     * @return The Employee object converted from the provided EmployeeDto object.
     */
    private Employee convertEmployeeDtoToEmployee(EmployeeDto employeeDto) {
        // Use ModelMapper to map the fields from EmployeeDto to Employee and return the result.
        return modelMapper.map(employeeDto, Employee.class);
    }
}
