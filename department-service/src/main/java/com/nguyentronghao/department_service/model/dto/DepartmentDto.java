package com.nguyentronghao.department_service.model.dto;

import com.nguyentronghao.department_service.model.entity.Department;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Department}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto implements Serializable {
    @NotEmpty(message = "Department's name should not be empty!")
    String name;
    String description;
    String code;
}