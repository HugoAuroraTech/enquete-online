package com.estudos.enquete_online.dto.enquete;

import jakarta.validation.constraints.NotNull;

public record ResultadoOpcaoDTO(
        @NotNull Long id,
        String texto,
        Long totalVotos
) {
}
