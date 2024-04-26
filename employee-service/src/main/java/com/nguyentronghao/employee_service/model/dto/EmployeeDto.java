package com.nguyentronghao.employee_service.model.dto;

import com.nguyentronghao.employee_service.model.entity.Employee;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Employee}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {
    Long id;
    @NotEmpty
    String firstName;
    @NotEmpty
    String lastName;
    @Email
    String email;
    String phone;
}