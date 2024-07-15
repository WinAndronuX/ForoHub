package com.winandronux.forohub.dto;

import com.winandronux.forohub.entity.Course;

public record CourseDTO(Long Id, String name, String category) {

    public CourseDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }

}
