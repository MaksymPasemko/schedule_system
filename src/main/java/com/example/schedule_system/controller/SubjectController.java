package com.example.schedule_system.controller;

import com.example.schedule_system.dto.dtorequest.SubjectDTORequest;
import com.example.schedule_system.dto.dtoresponse.SubjectDTO;
import com.example.schedule_system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTORequest subjectDTORequest){
        final SubjectDTO subjectDTO = subjectService.createSubject(subjectDTORequest);
        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id){
        final SubjectDTO subjectDTO = subjectService.findSubjectById(id);
        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SubjectDTO> findSubjectByName(@PathVariable String name){
        final SubjectDTO subjectDTO = subjectService.findSubjectByName(name);
        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findSubject(){
        final List<SubjectDTO> subjectDTOS = subjectService.findSubjects();
        return ResponseEntity.ok(subjectDTOS);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTORequest subjectDTORequest){
        final SubjectDTO subjectDTO = subjectService.updateSubject(subjectDTORequest);
        return ResponseEntity.ok(subjectDTO);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteSubjectById(@PathVariable Long id){
        final boolean isDeleted = subjectService.deleteSubjectById(id);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteSubjectByName(@PathVariable String name){
        final boolean isDeleted = subjectService.deleteSubjectByName(name);
        return isDeleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
