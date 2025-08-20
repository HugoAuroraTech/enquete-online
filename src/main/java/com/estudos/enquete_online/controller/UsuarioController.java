package com.estudos.enquete_online.controller;

import com.estudos.enquete_online.dto.usuario.UsuarioResponseDTO;
import com.estudos.enquete_online.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> buscarInfo(){
        UsuarioResponseDTO usuario = usuarioService.pegarUsuarioAtual();

        return ResponseEntity.ok().body(usuario);
    }
}
