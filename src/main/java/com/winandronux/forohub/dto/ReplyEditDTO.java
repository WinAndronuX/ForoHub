package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record ReplyEditDTO(@NotBlank String message) {}
