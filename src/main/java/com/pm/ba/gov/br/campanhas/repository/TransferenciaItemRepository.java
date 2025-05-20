package com.pm.ba.gov.br.campanhas.repository;

import com.pm.ba.gov.br.campanhas.models.TransferenciaItem;
import com.pm.ba.gov.br.campanhas.models.TransferenciaItemId;
import com.pm.ba.gov.br.campanhas.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferenciaItemRepository extends JpaRepository<TransferenciaItem, TransferenciaItemId> {
    List<TransferenciaItem> findByTransferencia(Transferencia transferencia);
} 