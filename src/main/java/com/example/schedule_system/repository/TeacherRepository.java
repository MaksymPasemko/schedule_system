package com.example.schedule_system.repository;

import com.example.schedule_system.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    boolean existsByName(String name);

    void deleteByName(String name);

    Optional<Teacher> findByName(String name);
}
