package com.example.schedule_system.service;

import com.example.schedule_system.dto.dtorequest.TeacherDTORequest;
import com.example.schedule_system.dto.dtoresponse.TeacherDTO;
import com.example.schedule_system.model.entity.Teacher;
import com.example.schedule_system.repository.TeacherRepository;
import com.example.schedule_system.util.mapper.TeacherMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.schedule_system.util.mapper.TeacherMapper.convertTeacherToTeacherDTO;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherDTO createTeacher(TeacherDTORequest teacherDTORequest){
        final Teacher teacher = new Teacher();
        teacher.setName(teacherDTORequest.getName());
        teacher.setEmail(teacherDTORequest.getEmail());
        teacher.setCreatedAt(teacherDTORequest.getCreatedAt());

        teacherRepository.save(teacher);
        return convertTeacherToTeacherDTO(teacher);
    }

    public TeacherDTO findTeacherById(Long id){
        final Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return convertTeacherToTeacherDTO(teacher);
    }
    public TeacherDTO findTeacherByName(String name){
        final Teacher teacher = teacherRepository.findByName(name)
                .orElseThrow(EntityNotFoundException::new);
        return convertTeacherToTeacherDTO(teacher);
    }

    public List<TeacherDTO> findTeachers(){
        final List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(TeacherMapper::convertTeacherToTeacherDTO).toList();
    }

    public TeacherDTO updateTeacher(TeacherDTORequest teacherDTORequest){
        final Teacher teacher = teacherRepository.findByName(teacherDTORequest.getName())
                .orElseThrow(EntityNotFoundException::new);
        teacher.setName(teacherDTORequest.getName());
        teacher.setEmail(teacherDTORequest.getEmail());
        teacher.setCreatedAt(teacherDTORequest.getCreatedAt());

        teacherRepository.save(teacher);
        return convertTeacherToTeacherDTO(teacher);
    }
    public boolean deleteTeacherById(Long id){
        if(teacherRepository.existsById(id)){
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteTeacherByName(String name){
        if(teacherRepository.existsByName(name)){
            teacherRepository.deleteByName(name);
            return true;
        }
        return false;
    }

}
