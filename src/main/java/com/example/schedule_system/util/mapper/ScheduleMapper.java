package com.example.schedule_system.util.mapper;

import com.example.schedule_system.dto.dtoresponse.ScheduleDTO;
import com.example.schedule_system.dto.smalldto.GroupView;
import com.example.schedule_system.dto.smalldto.SubjectView;
import com.example.schedule_system.dto.smalldto.TeacherView;
import com.example.schedule_system.model.entity.Group;
import com.example.schedule_system.model.entity.Schedule;
import com.example.schedule_system.model.entity.Subject;
import com.example.schedule_system.model.entity.Teacher;

public class ScheduleMapper {
    public static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        final Teacher teacher = schedule.getTeacher();
        final TeacherView teacherView = new TeacherView(teacher.getId(), teacher.getName());

        final Group group = schedule.getGroup();
        final GroupView groupView = new GroupView(group.getId(), group.getName());

        final Subject subject = schedule.getSubject();
        final SubjectView subjectView = new SubjectView(subject.getId(), subject.getName());

        return new ScheduleDTO(schedule.getId(), groupView, subjectView, teacherView, schedule.getPairNumber(),
         schedule.getShift(), schedule.getWeekday());
    }

}
