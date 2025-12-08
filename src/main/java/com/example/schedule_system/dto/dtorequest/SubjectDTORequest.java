package com.example.schedule_system.dto.dtorequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class SubjectDTORequest{
    private final String name;
    private final LocalDate createdAt;
}
