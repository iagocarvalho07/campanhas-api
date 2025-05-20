package com.pm.ba.gov.br.campanhas.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pm.ba.gov.br.campanhas.dto.PontoColetaDTO;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;

public interface PontoDeColetaService {
    public ResponseEntity<PontoColeta> getPontoDeColetaById(Integer id);
    public ResponseEntity<?> createPontoDeColeta(PontoColetaDTO pontoColetaDTO);
    public ResponseEntity<PontoColeta> updatePontoDeColeta(Integer id, PontoColeta pontoDeColeta);
    public ResponseEntity<Void> deletePontoDeColeta(Integer id);
    public ResponseEntity<List<PontoColeta>> getPontoDeColetaByCampanha(Integer id);
    public ResponseEntity<List<PontoColeta>> getAllPontoDeColeta();
}
