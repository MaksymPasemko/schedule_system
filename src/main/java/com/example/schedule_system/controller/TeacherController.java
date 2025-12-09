package com.example.schedule_system.controller;

import com.example.schedule_system.dto.dtorequest.TeacherDTORequest;
import com.example.schedule_system.dto.dtoresponse.TeacherDTO;
import com.example.schedule_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTORequest teacherDTORequest){
        final TeacherDTO teacherDTO = teacherService.createTeacher(teacherDTORequest);
        return ResponseEntity.ok(teacherDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TeacherDTO> findTeacherById(@PathVariable Long id){
        final TeacherDTO teacherDTO = teacherService.findTeacherById(id);
        return ResponseEntity.ok(teacherDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TeacherDTO> findTeacherByName(@PathVariable String name){
        final TeacherDTO teacherDTO = teacherService.findTeacherByName(name);
        return ResponseEntity.ok(teacherDTO);
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findTeachers(){
        final List<TeacherDTO> teacherDTOs = teacherService.findTeachers();
        return ResponseEntity.ok(teacherDTOs);
    }

    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTORequest teacherDTORequest){
        final TeacherDTO teacherDTO = teacherService.updateTeacher(teacherDTORequest);
        return ResponseEntity.ok(teacherDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable Long id){
        final boolean isDeleted = teacherService.deleteTeacherById(id);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteTeacherByName(@PathVariable String name){
        final boolean isDeleted = teacherService.deleteTeacherByName(name);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
