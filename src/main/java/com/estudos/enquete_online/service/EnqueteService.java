package com.estudos.enquete_online.service;


import com.estudos.enquete_online.dto.enquete.*;
import com.estudos.enquete_online.model.Enquete;
import com.estudos.enquete_online.model.Opcao;
import com.estudos.enquete_online.repository.EnqueteRepository;
import com.estudos.enquete_online.repository.OpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnqueteService {

    @Autowired
    private EnqueteRepository enqueteRepository;
    @Autowired
    private OpcaoService opcaoService;
    @Autowired
    private OpcaoRepository opcaoRepository;

    @Transactional
    public EnqueteResponseDTO criar(EnqueteRequestDTO requestDTO){
        Enquete enquete = new Enquete();
        if (requestDTO.opcoes().isEmpty()) {
            throw new IllegalArgumentException("Enquete deve ter pelo menos uma opção");
        }
        List<OpcaoRequestDTO> opcaos = new ArrayList<>(requestDTO.opcoes());


        enquete.setTitulo(requestDTO.titulo());
        enquete.setDescricao(requestDTO.descricao());
        enquete.setData_criacao(LocalDateTime.now());
        enquete.setData_encerramento(LocalDateTime.now().plusWeeks(7));
        enquete.setAtiva(true);
        Enquete enqueteSalva = enqueteRepository.save(enquete);
        opcaoService.criarOpcoes(opcaos, enqueteSalva);


        return EnqueteResponseDTO.fromEntity(enqueteSalva);
    }

    @Transactional(readOnly = true)
    public List<EnqueteResponseDTO> buscarEnquetes(){
        List<Enquete> enquetes = enqueteRepository.findAll();
        List<EnqueteResponseDTO> enqueteResponseDTOS = enquetes.stream().map(EnqueteResponseDTO::fromEntity).toList();

        return enqueteResponseDTOS;
    }

    public EnqueteResponseDTO buscarEnquetePorId(Long id) {
        Enquete enquete = enqueteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquete não encontrada"));

        return new EnqueteResponseDTO(
                enquete.getId(),
                enquete.getTitulo(),
                enquete.getDescricao(),
                enquete.getData_criacao(),
                enquete.getData_encerramento(),
                enquete.getAtiva(),
                enquete.getOpcoes().stream()
                        .map(OpcaoResponseDTO::fromEntity)
                        .collect(Collectors.toList())
        );

    }


    public ResultadoEnqueteDTO getResultados(Long enqueteId) {
        var enquete = enqueteRepository.findById(enqueteId)
                .orElseThrow(() -> new RuntimeException("Enquete não encontrada"));

        var resultados = opcaoRepository.getResultadosPorEnquete(enqueteId);

        return new ResultadoEnqueteDTO(
                enquete.getId(),
                enquete.getTitulo(),
                resultados
        );
    }
}
