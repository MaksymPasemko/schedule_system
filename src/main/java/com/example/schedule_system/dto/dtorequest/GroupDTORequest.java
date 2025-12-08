package com.example.schedule_system.dto.dtorequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class GroupDTORequest {
    private final String name;
    private final LocalDate createdAt;
    private final Integer groupSize;
    private final String groupLeader;
}
