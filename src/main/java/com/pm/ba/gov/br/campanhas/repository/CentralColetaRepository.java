package com.pm.ba.gov.br.campanhas.repository;

import com.pm.ba.gov.br.campanhas.models.CentralColeta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralColetaRepository extends JpaRepository<CentralColeta, Integer> {

    @Query("SELECT c FROM CentralColeta c WHERE c.campanha.idCampanha = :id")
    List<CentralColeta> findByCampanhaId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM central_coleta WHERE id_campanha = :id", nativeQuery = true)
    List<CentralColeta> findByCampanhaIdNative(@Param("id") Integer id);
} 