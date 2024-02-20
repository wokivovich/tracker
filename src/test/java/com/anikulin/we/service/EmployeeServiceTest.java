package com.anikulin.we.service;

import com.anikulin.we.domain.Employee;
import com.anikulin.we.dto.WorkTimeResponse;
import com.anikulin.we.exception.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

class EmployeeServiceTest {

    private EmployeeService employeeService = new EmployeeService();

    @Test
    void getMaxExperienceEmployee_twoEmployees_returnsMaxExperienceEmployee() {

        //Given
        Employee employee1 = Employee.builder()
                .id(1L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 12, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 12, 35))
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 12, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 12, 36))
                .build();
        List<Employee> employees = List.of(employee1, employee2);

        //When
        WorkTimeResponse actual = employeeService.getMaxExperienceEmployee(employees);


        //Then
        WorkTimeResponse expected = WorkTimeResponse.builder()
                .id(2L)
                .time(LocalTime.of(0, 3))
                .build();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getMaxExperienceEmployee_oneEmployeeWorksTwice_employeeWithSumExperience() {

        //Given
        Employee employee1 = Employee.builder()
                .id(1L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 9, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 18, 35))
                .build();

        Employee employee2 = Employee.builder()
                .id(2L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 12, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 18, 36))
                .build();

        Employee employee3 = Employee.builder()
                .id(1L)
                .dateStart(LocalDateTime.of(2024, 12, 7, 9, 33))
                .dateEnd(LocalDateTime.of(2024, 12, 7, 18, 20))
                .build();
        List<Employee> employees = List.of(employee1, employee2, employee3);

        //When
        WorkTimeResponse actual = employeeService.getMaxExperienceEmployee(employees);


        //Then
        WorkTimeResponse expected = WorkTimeResponse.builder()
                .id(1L)
                .time(LocalTime.of(17, 49))
                .build();

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getMaxExperienceEmployee_invalidStartAndEndDates_throwsValidationException() {

        //Given
        Employee employee1 = Employee.builder()
                .id(1L)
                .dateStart(LocalDateTime.of(2024, 12, 6, 18, 20))
                .dateEnd(LocalDateTime.of(2024, 12, 6, 9, 25))
                .build();
        List<Employee> employees = List.of(employee1);

        //When-Then
        Assertions.assertThatThrownBy(() -> employeeService.getMaxExperienceEmployee(employees))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Start date must be less than end date");

    }
}