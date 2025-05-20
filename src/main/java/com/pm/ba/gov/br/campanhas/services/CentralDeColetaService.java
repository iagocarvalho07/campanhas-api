package com.pm.ba.gov.br.campanhas.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pm.ba.gov.br.campanhas.dto.CentralColetaDTO;
import com.pm.ba.gov.br.campanhas.dto.CentralColetaResponseDTO;

public interface CentralDeColetaService {
    public ResponseEntity<List<CentralColetaResponseDTO>> getAllCentralDeColeta();
    public ResponseEntity<?> getCentralDeColetaById(Integer id);
    public ResponseEntity<?> createCentralDeColeta(CentralColetaDTO centralColetaDTO);
    public ResponseEntity<?> updateCentralDeColeta(Integer id, CentralColetaDTO centralColetaDTO);
    public ResponseEntity<Void> deleteCentralDeColeta(Integer id);
    public ResponseEntity<?> getCentralDeColetaByCampanha(Integer id);
}
