package com.estudos.enquete_online.dto.enquete;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ResultadoEnqueteDTO(
        @NotNull Long id,
        String titulo,
        List<ResultadoOpcaoDTO> resultados
) {
}
