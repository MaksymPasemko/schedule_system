package com.example.schedule_system.util.mapper;

import com.example.schedule_system.dto.dtoresponse.SubjectDTO;
import com.example.schedule_system.model.entity.Subject;

public class SubjectMapper {
    public static SubjectDTO convertSubjectToSubjectDTO(Subject subject){
        return new SubjectDTO(subject.getId(),subject.getName(),subject.getCreatedAt());
    }
}
