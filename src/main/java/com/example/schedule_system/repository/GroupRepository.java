package com.example.schedule_system.repository;

import com.example.schedule_system.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    boolean existsByName(String name);

    void deleteByName(String name);

    Optional<Group> findByName(String name);
}
