package com.example.schedule_system.dto.dtorequest;

import com.example.schedule_system.model.Weekday;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleDTORequest {
    private Long id;
    private Long groupId;
    private Long subjectId;
    private Long teacherId;
    private Integer pairNumber;
    private String shift;
    private Weekday weekday;
}
