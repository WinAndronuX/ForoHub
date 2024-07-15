package com.winandronux.forohub.service.impl;

import com.winandronux.forohub.dto.CourseAddDTO;
import com.winandronux.forohub.dto.CourseDTO;
import com.winandronux.forohub.entity.Course;
import com.winandronux.forohub.repository.CourseRepository;
import com.winandronux.forohub.service.CourseService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(CourseDTO::new);
    }

    @Override
    public CourseDTO getCourse(Long id) {
        var course = courseRepository.findById(id);
        return course.map(CourseDTO::new).orElse(null);
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CourseAddDTO courseAddDTO) {
        if (courseRepository.existsByName(courseAddDTO.name())) throw new EntityExistsException("A courseId with this name already exists");

        var course = new Course(courseAddDTO);
        courseRepository.save(course);
        return new CourseDTO(course);
    }

    @Override
    @Transactional
    public CourseDTO updateCourse(Long id, CourseAddDTO courseAddDTO) {
        var optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) return null;

        var course = optionalCourse.get();
        course.updateInfo(courseAddDTO);

        return new CourseDTO(course);
    }


    @Override
    @Transactional
    public void deleteCourse(Long id) {
        var optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent())
            courseRepository.delete(optionalCourse.get());
        else
            throw new EntityNotFoundException("Course whit id " + id + " does not exist");
    }
}
