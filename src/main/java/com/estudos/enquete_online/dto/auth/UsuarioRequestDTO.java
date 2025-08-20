package com.estudos.enquete_online.dto.auth;

import com.estudos.enquete_online.model.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @NotBlank
        String nome,
        @Email
        String email,
        @NotBlank
        String senha,

        @NotNull
        RoleEnum role

) {
}
