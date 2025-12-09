package com.example.schedule_system.service;

import com.example.schedule_system.dto.dtorequest.ScheduleDTORequest;
import com.example.schedule_system.dto.dtoresponse.ScheduleDTO;
import com.example.schedule_system.model.entity.Group;
import com.example.schedule_system.model.entity.Schedule;
import com.example.schedule_system.model.entity.Subject;
import com.example.schedule_system.model.entity.Teacher;
import com.example.schedule_system.repository.GroupRepository;
import com.example.schedule_system.repository.ScheduleRepository;
import com.example.schedule_system.repository.SubjectRepository;
import com.example.schedule_system.repository.TeacherRepository;
import com.example.schedule_system.util.mapper.ScheduleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.schedule_system.util.mapper.ScheduleMapper.convertScheduleToScheduleDTO;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    public ScheduleDTO createSchedule(ScheduleDTORequest scheduleDTORequest) {
        final Schedule schedule = new Schedule();
        final Group group = groupRepository.findById(scheduleDTORequest.getGroupId())
                .orElseThrow(EntityNotFoundException::new);
        final Teacher teacher = teacherRepository.findById(scheduleDTORequest.getTeacherId())
                .orElseThrow(EntityNotFoundException::new);
        final Subject subject = subjectRepository.findById(scheduleDTORequest.getSubjectId())
                .orElseThrow(EntityNotFoundException::new);

        schedule.setGroup(group);
        schedule.setSubject(subject);
        schedule.setTeacher(teacher);
        schedule.setPairNumber(scheduleDTORequest.getPairNumber());
        schedule.setShift(scheduleDTORequest.getShift());
        schedule.setWeekday(scheduleDTORequest.getWeekday());

        scheduleRepository.save(schedule);
        return convertScheduleToScheduleDTO(schedule);
    }

    public ScheduleDTO findScheduleById(Long id){
        final Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return convertScheduleToScheduleDTO(schedule);
    }

    public List<ScheduleDTO> findSchedules(){
        final List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(ScheduleMapper::convertScheduleToScheduleDTO).toList();
    }

    public List<ScheduleDTO> findSchedulesByGroupName(String groupName){
        final List<Schedule> schedules = scheduleRepository.findByGroup_Name(groupName);
        return schedules.stream()
                .map(ScheduleMapper::convertScheduleToScheduleDTO).toList();
    }

    public List<ScheduleDTO> findSchedulesBySubjectName(String subjectName){
        final List<Schedule> schedules = scheduleRepository.findBySubject_Name(subjectName);
        return schedules.stream()
                .map(ScheduleMapper::convertScheduleToScheduleDTO).toList();
    }

    public List<ScheduleDTO> findSchedulesByTeacherName(String teacherName){
        final List<Schedule> schedules = scheduleRepository.findByTeacher_Name(teacherName);
        return schedules.stream()
                .map(ScheduleMapper::convertScheduleToScheduleDTO).toList();
    }

    public ScheduleDTO updateSchedule(ScheduleDTORequest scheduleDTORequest){
        final Schedule schedule = scheduleRepository.findById(scheduleDTORequest.getId())
                .orElseThrow(EntityNotFoundException::new);
        final Group group = groupRepository.findById(scheduleDTORequest.getGroupId())
                .orElseThrow(EntityNotFoundException::new);
        final Teacher teacher = teacherRepository.findById(scheduleDTORequest.getTeacherId())
                .orElseThrow(EntityNotFoundException::new);
        final Subject subject = subjectRepository.findById(scheduleDTORequest.getSubjectId())
                .orElseThrow(EntityNotFoundException::new);

        schedule.setGroup(group);
        schedule.setSubject(subject);
        schedule.setTeacher(teacher);
        schedule.setPairNumber(scheduleDTORequest.getPairNumber());
        schedule.setShift(scheduleDTORequest.getShift());
        schedule.setWeekday(scheduleDTORequest.getWeekday());

        scheduleRepository.save(schedule);
        return convertScheduleToScheduleDTO(schedule);
    }

    public boolean deleteScheduleById(Long id){
        if(scheduleRepository.existsById(id)){
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
