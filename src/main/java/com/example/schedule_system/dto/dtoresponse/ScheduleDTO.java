package com.example.schedule_system.dto.dtoresponse;

import com.example.schedule_system.dto.smalldto.GroupView;
import com.example.schedule_system.dto.smalldto.SubjectView;
import com.example.schedule_system.dto.smalldto.TeacherView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ScheduleDTO {
    private final Long id;
    private final GroupView groupView;
    private final SubjectView subjectView;
    private final TeacherView teacherView;
    private final Integer pairNumber;
    private final String shift;
    private final String weekday;
}
