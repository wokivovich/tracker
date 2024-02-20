package com.anikulin.tracker.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Employee {

    @NotNull(message = "Id is null")
    private Long id;

    @NotNull(message = "Start date is null")
    private LocalDateTime dateStart;

    @NotNull(message = "End date is null")
    private LocalDateTime dateEnd;

}
