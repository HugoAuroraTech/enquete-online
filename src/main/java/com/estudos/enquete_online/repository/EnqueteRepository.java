package com.estudos.enquete_online.repository;

import com.estudos.enquete_online.model.Enquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqueteRepository extends JpaRepository<Enquete, Long> {

}
