package com.example.schedule_system.dto.dtoresponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@RequiredArgsConstructor
@Getter
public class SubjectDTO {
    private final Long id;
    private final String name;
    private final LocalDate createdAt;
}
