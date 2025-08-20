package com.estudos.enquete_online.dto.enquete;

import jakarta.validation.constraints.NotBlank;

public record OpcaoRequestDTO(
        @NotBlank
        String texto
) {
}
