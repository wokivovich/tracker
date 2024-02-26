package com.anikulin.tracker;

import com.anikulin.tracker.dto.EmployeesRequest;
import com.anikulin.tracker.dto.WorkTimeResponse;
import com.anikulin.tracker.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final EmployeeService employeeService;

    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<WorkTimeResponse> getMaxExperienceEmployee(@Valid @RequestBody EmployeesRequest request) {

        return new ResponseEntity<>(employeeService.getMaxExperienceEmployee(request.getEmployees()) ,HttpStatus.OK);
    }
}
