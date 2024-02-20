package com.anikulin.tracker.dto;

import com.anikulin.tracker.domain.Employee;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class EmployeesRequest {

    @Valid
    private Employee[] employees;
}
