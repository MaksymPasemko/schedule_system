package com.example.schedule_system.model.entity;

import com.example.schedule_system.model.Weekday;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Column(name = "pair_number",nullable = false)
    @NotNull
    private Integer pairNumber;
    @Column(name = "shift",nullable = false)
    @NotBlank
    private String shift;
    @Column(name = "weekday",nullable = false)
    @NotBlank
    private Weekday weekday;

}
