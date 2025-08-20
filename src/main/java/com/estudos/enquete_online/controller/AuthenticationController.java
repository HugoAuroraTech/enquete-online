package com.estudos.enquete_online.controller;


import com.estudos.enquete_online.config.TokenService;
import com.estudos.enquete_online.dto.auth.AuthRequestDTO;
import com.estudos.enquete_online.dto.auth.TokenDTO;
import com.estudos.enquete_online.dto.auth.UsuarioRequestDTO;
import com.estudos.enquete_online.dto.auth.UsuarioResponseDTO;
import com.estudos.enquete_online.model.Usuario;
import com.estudos.enquete_online.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthRequestDTO requestDTO){
        var emailPassword = new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.senha());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = this.tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody @Valid UsuarioRequestDTO requestDTO){
        if(usuarioRepository.findByEmail(requestDTO.email()) != null) return ResponseEntity.badRequest().build();

        String senhaCrypto = new BCryptPasswordEncoder().encode(requestDTO.senha());

        Usuario usuario = new Usuario(requestDTO.nome(), requestDTO.email(), senhaCrypto, requestDTO.role());

        this.usuarioRepository.save(usuario);

        UsuarioResponseDTO responseDTO = UsuarioResponseDTO.fromEntity(usuario);

        return ResponseEntity.ok().body(responseDTO);
    }
}
