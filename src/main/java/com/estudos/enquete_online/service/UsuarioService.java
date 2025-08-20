package com.estudos.enquete_online.service;

import com.estudos.enquete_online.dto.usuario.UsuarioResponseDTO;
import com.estudos.enquete_online.model.Usuario;
import com.estudos.enquete_online.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO pegarUsuarioAtual(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        var usuario = usuarioRepository.findByEmail(email);

        return new UsuarioResponseDTO(usuario.getNome(), email);
    }
}
