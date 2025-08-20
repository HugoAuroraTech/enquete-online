package com.estudos.enquete_online.dto;

import com.estudos.enquete_online.model.RoleEnum;
import com.estudos.enquete_online.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String senha,
        RoleEnum role
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getRole());
    }
}
