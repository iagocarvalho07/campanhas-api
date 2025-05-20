package com.pm.ba.gov.br.campanhas.repository;

import com.pm.ba.gov.br.campanhas.models.EstoqueCentral;
import com.pm.ba.gov.br.campanhas.models.EstoqueCentralId;
import com.pm.ba.gov.br.campanhas.models.CentralColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstoqueCentralRepository extends JpaRepository<EstoqueCentral, EstoqueCentralId> {
    List<EstoqueCentral> findByCentral(CentralColeta central);
} 