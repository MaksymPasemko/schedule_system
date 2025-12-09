package com.example.schedule_system.repository;

import com.example.schedule_system.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByGroup_Name(String groupName);
    List<Schedule> findBySubject_Name(String subjectName);
    List<Schedule> findByTeacher_Name(String teacherName);
}
