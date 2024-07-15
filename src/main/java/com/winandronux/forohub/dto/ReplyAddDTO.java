package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyAddDTO (
        @NotBlank
        String message,
        @NotNull
        Long userId,
        @NotNull
        Long topicId,
        Long repliesTo
) {}
