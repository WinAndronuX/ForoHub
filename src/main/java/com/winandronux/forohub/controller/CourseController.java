package com.winandronux.forohub.controller;

import com.winandronux.forohub.dto.CourseAddDTO;
import com.winandronux.forohub.dto.CourseDTO;
import com.winandronux.forohub.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @Operation(summary = "Obtener el listado de los cursos")
    public ResponseEntity<Page<CourseDTO>> getCourses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles sobre un curso a partir del Id")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        var course = courseService.getCourse(id);
        if (course == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(course);
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo curso")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody @Valid CourseAddDTO courseAddDTO) {
        var course = courseService.createCourse(courseAddDTO);
        var uri = UriComponentsBuilder.fromPath("/courses/{id}").buildAndExpand(course.Id()).toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar informacion de un curso. Campos editables: name, category")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseAddDTO courseAddDTO, @PathVariable Long id) {
        var course = courseService.updateCourse(id, courseAddDTO);
        if (course == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso a partir del Id")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
