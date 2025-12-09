package com.example.schedule_system.util.mapper;

import com.example.schedule_system.dto.dtoresponse.TeacherDTO;
import com.example.schedule_system.model.entity.Teacher;

public class TeacherMapper {
    public static TeacherDTO convertTeacherToTeacherDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(),teacher.getName(),teacher.getEmail(),
                teacher.getCreatedAt());
    }
}
