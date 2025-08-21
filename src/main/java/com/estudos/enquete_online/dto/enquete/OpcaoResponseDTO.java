package com.estudos.enquete_online.dto.enquete;

import com.estudos.enquete_online.model.Opcao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OpcaoResponseDTO(
        @NotNull Long id,
        @NotBlank String texto,
        @NotNull Long totalVotos
) {
    public static OpcaoResponseDTO fromEntity(Opcao opcao){
        return new OpcaoResponseDTO(
                opcao.getId(),
                opcao.getTexto(),
                opcao.getVotos() != null ? (long) opcao.getVotos().size() : 0L
        );
    }
}