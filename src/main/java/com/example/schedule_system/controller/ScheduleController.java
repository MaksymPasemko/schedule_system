package com.example.schedule_system.controller;

import com.example.schedule_system.dto.dtorequest.ScheduleDTORequest;
import com.example.schedule_system.dto.dtoresponse.ScheduleDTO;
import com.example.schedule_system.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTORequest scheduleDTORequest){
        final ScheduleDTO scheduleDTO = scheduleService.createSchedule(scheduleDTORequest);
        return ResponseEntity.ok(scheduleDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ScheduleDTO> findScheduleById(@PathVariable Long id){
        final ScheduleDTO scheduleDTO = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(scheduleDTO);
    }

    @GetMapping("/group/{groupName}")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByGroupName(@PathVariable String groupName){
        final List<ScheduleDTO> scheduleDTOs = scheduleService.findSchedulesByGroupName(groupName);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping("/subject/{subjectName}")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesBySubjectName(@PathVariable String subjectName){
        final List<ScheduleDTO> scheduleDTOs = scheduleService.findSchedulesBySubjectName(subjectName);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping("/teacher/{teacherName}")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByTeacherName(@PathVariable String teacherName){
        final List<ScheduleDTO> scheduleDTOs = scheduleService.findSchedulesByTeacherName(teacherName);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> findSchedules(){
        final List<ScheduleDTO> scheduleDTOs = scheduleService.findSchedules();
        return ResponseEntity.ok(scheduleDTOs);
    }

    @PutMapping
    public ResponseEntity<ScheduleDTO> updateSchedule(@RequestBody ScheduleDTORequest scheduleDTORequest){
        final ScheduleDTO scheduleDTO = scheduleService.updateSchedule(scheduleDTORequest);
        return ResponseEntity.ok(scheduleDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id){
        final boolean isDeleted = scheduleService.deleteScheduleById(id);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
