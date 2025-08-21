package com.estudos.enquete_online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "enquetes")
@Entity
@Data
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private LocalDateTime data_criacao;

    private LocalDateTime data_encerramento;

    private Boolean ativa;

    @OneToMany(mappedBy = "enquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opcao> opcoes;
}
