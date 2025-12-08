package com.example.schedule_system.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private Long id;
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;
    @Column(name = "email",unique = true,nullable = false)
    @NotBlank
    private String email;
    @Column(name = "created_at",nullable = false)
    @NotNull
    private LocalDate createdAt;
}
