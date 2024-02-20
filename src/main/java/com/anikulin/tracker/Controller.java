package com.anikulin.tracker;

import com.anikulin.tracker.domain.Employee;
import com.anikulin.tracker.dto.EmployeesRequest;
import com.anikulin.tracker.dto.WorkTimeResponse;
import com.anikulin.tracker.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    private final EmployeeService employeeService;

    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<WorkTimeResponse> getMaxExperienceEmployee(@Valid @RequestBody EmployeesRequest request) {
        List<Employee> employees = Arrays.asList(request.getEmployees());

        return new ResponseEntity<>(employeeService.getMaxExperienceEmployee(employees) ,HttpStatus.OK);
    }
}
