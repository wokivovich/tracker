package com.anikulin.tracker.dto;

import com.anikulin.tracker.domain.Employee;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class EmployeesRequest {

    @Valid
    private List<Employee> employees;
}
