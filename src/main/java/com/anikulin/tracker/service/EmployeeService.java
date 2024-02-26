package com.anikulin.tracker.service;

import com.anikulin.tracker.domain.Employee;
import com.anikulin.tracker.dto.WorkTimeResponse;
import com.anikulin.tracker.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final String DATE_VALIDATION_EXCEPTION_MESSAGE = "Start date must be less than end date";

    public WorkTimeResponse getMaxExperienceEmployee(List<Employee> employees) {
        validateEmployeeDates(employees);

        Map<Long, LocalTime> summarizedResponses = mergeResponseByLocalTime(employees.stream()
                .map(employee -> convertToResponse(employee))
                .collect(Collectors.groupingBy(WorkTimeResponse::getId)));

        Long maxExperienceId = summarizedResponses
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

        return WorkTimeResponse.builder()
                .id(maxExperienceId)
                .time(summarizedResponses.get(maxExperienceId))
                .build();
    }

    private void validateEmployeeDates(List<Employee> employees) {

        employees.forEach(employee -> {
            if (Duration.between(employee.getDateStart(), employee.getDateEnd()).isNegative()) {
                throw new ValidationException(DATE_VALIDATION_EXCEPTION_MESSAGE);
            }
        });
    }

    private WorkTimeResponse convertToResponse(Employee employee) {

        return WorkTimeResponse.builder()
                .id(employee.getId())
                .time(LocalTime.ofSecondOfDay(Duration.between(employee.getDateStart(), employee.getDateEnd()).getSeconds()))
                .build();
    }

    private Map<Long, LocalTime>  mergeResponseByLocalTime(Map<Long, List<WorkTimeResponse>> workExperienceResponses) {
        Map<Long, LocalTime> responses = workExperienceResponses.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        response -> response.getKey(),
                        response -> response.getValue()
                                .stream()
                                .map(WorkTimeResponse::getTime).reduce((baseTime, addedTime) ->
                                        sumTime(baseTime, addedTime))
                                .get()));

        return responses;
    }

    private LocalTime sumTime(LocalTime baseTime, LocalTime addedTime) {
        return baseTime
                .plusHours(addedTime.getHour())
                .plusMinutes(addedTime.getMinute())
                .plusSeconds(addedTime.getSecond());
    }

}
