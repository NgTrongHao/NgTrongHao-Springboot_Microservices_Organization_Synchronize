package com.nguyentronghao.employee_service.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto implements Serializable {
    @NotEmpty(message = "Department's name should not be empty!")
    String name;
    String description;
    String code;
}