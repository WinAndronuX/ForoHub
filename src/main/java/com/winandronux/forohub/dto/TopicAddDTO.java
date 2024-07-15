package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicAddDTO(
        @NotBlank
        String title,
        @NotBlank
        String content,
        @NotNull
        Long userId,
        @NotNull
        Long courseId
) {}
