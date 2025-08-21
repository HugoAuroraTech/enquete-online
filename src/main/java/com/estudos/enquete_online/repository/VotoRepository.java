package com.estudos.enquete_online.repository;

import com.estudos.enquete_online.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByEnqueteId(Long id);
    boolean existsByUsuarioIdAndEnqueteId(Long usuarioId, Long enqueteId);
}
