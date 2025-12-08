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
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    @NotBlank
    private String name;
    @Column(name = "created_at",nullable = false)
    @NotNull
    private LocalDate createdAt;
    @Column(name = "group_size",nullable = false)
    @NotNull
    private Integer groupSize;
    @Column(name = "group_leader",nullable = false)
    @NotBlank
    private String groupLeader;
}
