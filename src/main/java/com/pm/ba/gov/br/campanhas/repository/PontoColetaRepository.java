package com.pm.ba.gov.br.campanhas.repository;

import com.pm.ba.gov.br.campanhas.models.PontoColeta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {

    @Query("SELECT p FROM PontoColeta p WHERE p.campanha.id = :id")
    List<PontoColeta> findByCampanhaId(@Param("id") Integer id);
} 