package com.winandronux.forohub.dto;

import jakarta.validation.constraints.NotBlank;


public record UserUpdatePasswordDTO(
        @NotBlank
        Long Id,
        @NotBlank
        String password
) {}
