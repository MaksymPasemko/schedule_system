package com.example.schedule_system.dto.dtorequest;

import com.example.schedule_system.model.Weekday;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ScheduleDTORequest {
    private final Long id;
    private final Long groupId;
    private final Long subjectId;
    private final Long teacherId;
    private final Integer pairNumber;
    private final String shift;
    private final Weekday weekday;
}
