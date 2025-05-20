package com.pm.ba.gov.br.campanhas.repository;

import com.pm.ba.gov.br.campanhas.models.Transferencia;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;
import com.pm.ba.gov.br.campanhas.models.CentralColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    List<Transferencia> findByPonto(PontoColeta ponto);
    List<Transferencia> findByCentral(CentralColeta central);
} 