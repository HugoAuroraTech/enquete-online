package com.estudos.enquete_online.controller;

import com.estudos.enquete_online.dto.enquete.EnqueteRequestDTO;
import com.estudos.enquete_online.dto.enquete.EnqueteResponseDTO;
import com.estudos.enquete_online.service.EnqueteService;
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
}
