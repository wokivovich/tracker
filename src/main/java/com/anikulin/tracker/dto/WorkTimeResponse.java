package com.anikulin.tracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class WorkTimeResponse {

    private Long id;
    private LocalTime time;
}
