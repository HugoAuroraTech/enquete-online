package com.estudos.enquete_online.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

public record UsuarioResponseDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email
) {

}
