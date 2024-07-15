package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseAddDTO (
        @NotBlank
        String name,
        @NotBlank
        String category
) {}
