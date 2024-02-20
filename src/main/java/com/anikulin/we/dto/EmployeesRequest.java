package com.anikulin.we.dto;

import com.anikulin.we.domain.Employee;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class EmployeesRequest {

    @Valid
    private Employee[] employees;
}
