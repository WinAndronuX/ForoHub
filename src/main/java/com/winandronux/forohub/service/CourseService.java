package com.winandronux.forohub.service;

import com.winandronux.forohub.dto.CourseAddDTO;
import com.winandronux.forohub.dto.CourseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseDTO> getAllCourses(Pageable pageable);
    CourseDTO getCourse(Long id);
    CourseDTO createCourse(CourseAddDTO courseAddDTO);
    CourseDTO updateCourse(Long id, CourseAddDTO courseAddDTO);
    void deleteCourse(Long id);
}
