package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDTO (
        @NotBlank String username,
        @NotBlank String password
) {}
