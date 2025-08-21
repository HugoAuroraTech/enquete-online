package com.estudos.enquete_online.service;

import com.estudos.enquete_online.model.Enquete;
import com.estudos.enquete_online.model.Opcao;
import com.estudos.enquete_online.model.Usuario;
import com.estudos.enquete_online.model.Voto;
import com.estudos.enquete_online.repository.EnqueteRepository;
import com.estudos.enquete_online.repository.OpcaoRepository;
import com.estudos.enquete_online.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private EnqueteRepository enqueteRepository;

    @Autowired
    private OpcaoRepository opcaoRepository;

    public String criarVoto(Long enqueteId, Long opcaoId){
        var enquete = enqueteRepository.findById(enqueteId).orElseThrow(() -> new RuntimeException("Enquete não encontrada com ID: " + enqueteId));
        var opcao = opcaoRepository.findById(opcaoId).orElseThrow(() -> new RuntimeException("Opcao não encontrada com ID: " + opcaoId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Voto voto = new Voto();
        voto.setUsuario(usuario);
        voto.setEnquete(enquete);
        voto.setOpcao(opcao);
        voto.setData_voto(LocalDateTime.now());
        Voto votoSalvo = votoRepository.save(voto);
        return "Obrigado por votar na enquete: " + enquete.getId();
    }
}
