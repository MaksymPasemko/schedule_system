package com.example.schedule_system.service;

import com.example.schedule_system.dto.dtorequest.SubjectDTORequest;
import com.example.schedule_system.dto.dtoresponse.SubjectDTO;
import com.example.schedule_system.model.entity.Subject;
import com.example.schedule_system.repository.SubjectRepository;
import com.example.schedule_system.util.mapper.SubjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.schedule_system.util.mapper.SubjectMapper.convertSubjectToSubjectDTO;


@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectDTO createSubject(SubjectDTORequest subjectDTORequest){
        final Subject subject = new Subject();
        subject.setName(subjectDTORequest.getName());
        subject.setCreatedAt(subjectDTORequest.getCreatedAt());

        subjectRepository.save(subject);
        return convertSubjectToSubjectDTO(subject);
    }

    public SubjectDTO findSubjectById(Long id){
        final Subject subject = subjectRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return convertSubjectToSubjectDTO(subject);
    }

    public SubjectDTO findSubjectByName(String name){
        final Subject subject = subjectRepository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
        return convertSubjectToSubjectDTO(subject);
    }

    public List<SubjectDTO> findSubjects(){
        final List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(SubjectMapper::convertSubjectToSubjectDTO).toList();
    }

    public SubjectDTO updateSubject(SubjectDTORequest subjectDTORequest){
        final Subject subject = subjectRepository.findByName(subjectDTORequest.getName())
                .orElseThrow(EntityNotFoundException::new);
        subject.setName(subjectDTORequest.getName());
        subject.setCreatedAt(subjectDTORequest.getCreatedAt());

        subjectRepository.save(subject);
        return convertSubjectToSubjectDTO(subject);
    }
    public boolean deleteSubjectById(Long id){
        if(subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteSubjectByName(String name){
        if(subjectRepository.existsByName(name)){
            subjectRepository.deleteByName(name);
            return true;
        }
        return false;
    }
}
