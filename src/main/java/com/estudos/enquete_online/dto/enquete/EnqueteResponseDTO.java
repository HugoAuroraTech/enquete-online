package com.estudos.enquete_online.dto.enquete;

import com.estudos.enquete_online.model.Enquete;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record EnqueteResponseDTO(
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        String descricao,
        @NotNull
        LocalDateTime data_criacao,
        LocalDateTime data_encerramento,
        @NotNull
        Boolean ativa,
        List<OpcaoResponseDTO> opcoes
) {
    public static EnqueteResponseDTO fromEntity(Enquete enquete){
        return new EnqueteResponseDTO(
                enquete.getId(),
                enquete.getTitulo(),
                enquete.getDescricao(),
                enquete.getData_criacao(),
                enquete.getData_encerramento(),
                enquete.getAtiva(),
                enquete.getOpcoes().stream()
                        .map(OpcaoResponseDTO::fromEntity)
                        .collect(Collectors.toList()));
    }
}
