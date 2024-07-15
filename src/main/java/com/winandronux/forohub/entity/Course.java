package com.winandronux.forohub.entity;

import com.winandronux.forohub.dto.CourseAddDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> topics;

    public Course(CourseAddDTO courseAddDTO) {
        this.name = courseAddDTO.name();
        this.category = courseAddDTO.category();
        this.topics = new HashSet<>();
    }

    public void updateInfo(CourseAddDTO courseAddDTO) {
        if (courseAddDTO.name() != null) this.name = courseAddDTO.name();
        if (courseAddDTO.category() != null) this.category = courseAddDTO.category();
    }
}
