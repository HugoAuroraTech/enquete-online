package com.estudos.enquete_online.dto.enquete;

import com.estudos.enquete_online.model.Opcao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


public record EnqueteRequestDTO(
        @NotBlank
        String titulo,
        String descricao,
        @NotNull
        List<OpcaoRequestDTO> opcoes
) {
}
