package com.estudos.enquete_online.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @Email
        String email,
        @NotBlank
        String senha
) {

}
