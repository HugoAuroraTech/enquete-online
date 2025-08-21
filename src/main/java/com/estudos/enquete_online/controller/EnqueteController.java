package com.estudos.enquete_online.controller;

import com.estudos.enquete_online.dto.enquete.EnqueteRequestDTO;
import com.estudos.enquete_online.dto.enquete.EnqueteResponseDTO;
import com.estudos.enquete_online.dto.enquete.ResultadoEnqueteDTO;
import com.estudos.enquete_online.dto.votos.VotoRequestDTO;
import com.estudos.enquete_online.model.Opcao;
import com.estudos.enquete_online.service.EnqueteService;
import com.estudos.enquete_online.service.OpcaoService;
import com.estudos.enquete_online.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
public class EnqueteController {

    @Autowired
    private EnqueteService enqueteService;

    @Autowired
    private VotoService votoService;

    @Autowired
    private OpcaoService opcaoService;

    @PostMapping
    public ResponseEntity<EnqueteResponseDTO> criarEnquete(@RequestBody @Valid EnqueteRequestDTO requestDTO){
        EnqueteResponseDTO responseDTO = this.enqueteService.criar(requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EnqueteResponseDTO>> buscarEnquetes(){
        List<EnqueteResponseDTO> enqueteResponseDTOS = enqueteService.buscarEnquetes();

        return ResponseEntity.ok().body(enqueteResponseDTOS);
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<?> votar(@PathVariable Long id, @RequestParam Long opcaoId) {
        try {
            String mensagem = votoService.criarVoto(id, opcaoId);
            return ResponseEntity.ok(mensagem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnqueteResponseDTO> buscarEnquetePorId(@PathVariable Long id) {
        EnqueteResponseDTO enquete = enqueteService.buscarEnquetePorId(id);
        return ResponseEntity.ok(enquete);
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<ResultadoEnqueteDTO> getResultados(@PathVariable Long id) {
        return ResponseEntity.ok(enqueteService.getResultados(id));
    }
}
