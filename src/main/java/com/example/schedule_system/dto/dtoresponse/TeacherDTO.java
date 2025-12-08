package com.example.schedule_system.dto.dtoresponse;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class TeacherDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDate createdAt;
}
