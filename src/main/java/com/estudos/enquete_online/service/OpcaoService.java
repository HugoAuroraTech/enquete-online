package com.estudos.enquete_online.service;

import com.estudos.enquete_online.dto.enquete.OpcaoRequestDTO;
import com.estudos.enquete_online.model.Enquete;
import com.estudos.enquete_online.model.Opcao;
import com.estudos.enquete_online.repository.EnqueteRepository;
import com.estudos.enquete_online.repository.OpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcaoService {

    @Autowired
    private OpcaoRepository opcaoRepository;

    public List<Opcao> criarOpcoes(List<OpcaoRequestDTO> opcaoRequests, Enquete enquete) {
        List<Opcao> opcoes = opcaoRequests.stream()
                .map(request -> {
                    Opcao opcao = new Opcao();
                    opcao.setTexto(request.texto());
                    opcao.setEnquete(enquete);
                    return opcao;
                })
                .toList();

        var opcoesSalvas =  opcaoRepository.saveAll(opcoes);
        return opcoesSalvas;
    }

    public List<Opcao> buscarOpcoes(){
        return opcaoRepository.findAll();
    }
}
