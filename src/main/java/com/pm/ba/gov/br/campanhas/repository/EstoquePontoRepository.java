package com.pm.ba.gov.br.campanhas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pm.ba.gov.br.campanhas.models.EstoquePonto;
import com.pm.ba.gov.br.campanhas.models.EstoquePontoId;
import com.pm.ba.gov.br.campanhas.models.Item;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;

@Repository
public interface EstoquePontoRepository extends JpaRepository<EstoquePonto, EstoquePontoId> {

    @Query("SELECT e FROM EstoquePonto e WHERE e.ponto = :ponto")
    List<EstoquePonto> findByPonto(@Param("ponto") PontoColeta ponto);

    @Query("SELECT e FROM EstoquePonto e WHERE e.ponto.id = :idPonto")
    List<EstoquePonto> findByPontoId(@Param("idPonto") Integer idPonto);

    @Query("SELECT e FROM EstoquePonto e WHERE e.ponto = :ponto AND e.item = :item")
    Optional<EstoquePonto> findByPontoAndItem(@Param("ponto") PontoColeta ponto, @Param("item") Item item);

    @Query("SELECT DISTINCT e FROM EstoquePonto e LEFT JOIN FETCH e.ponto LEFT JOIN FETCH e.item WHERE e.ponto.id = :idPonto")
    List<EstoquePonto> findByPontoIdWithDetails(@Param("idPonto") Integer idPonto);

    

}