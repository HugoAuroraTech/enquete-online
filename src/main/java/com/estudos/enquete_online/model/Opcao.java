package com.estudos.enquete_online.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "opcoes")
@Entity
@Data
public class Opcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enquete_id", nullable = false)
    private Enquete enquete;

    @OneToMany(mappedBy = "opcao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos;
}
