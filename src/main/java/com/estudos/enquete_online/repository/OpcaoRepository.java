package com.estudos.enquete_online.repository;

import com.estudos.enquete_online.dto.enquete.ResultadoOpcaoDTO;
import com.estudos.enquete_online.model.Opcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpcaoRepository extends JpaRepository<Opcao, Long> {
    List<Opcao> findByEnqueteId(Long id);

    @Query("""
           SELECT new com.estudos.enquete_online.dto.enquete.ResultadoOpcaoDTO(
                o.id, o.texto, COUNT(v)
           )
           FROM Opcao o
           LEFT JOIN Voto v ON v.opcao = o
           WHERE o.enquete.id = :enqueteId
           GROUP BY o.id, o.texto
           """)
    List<ResultadoOpcaoDTO> getResultadosPorEnquete(Long enqueteId);
}
