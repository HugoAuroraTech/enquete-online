package com.estudos.enquete_online.repository;

import com.estudos.enquete_online.model.Opcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcaoRepository extends JpaRepository<Opcao, Long> {
}
