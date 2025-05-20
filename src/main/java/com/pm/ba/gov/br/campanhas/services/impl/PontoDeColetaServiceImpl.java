package com.pm.ba.gov.br.campanhas.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pm.ba.gov.br.campanhas.dto.PontoColetaDTO;
import com.pm.ba.gov.br.campanhas.models.Campanha;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;
import com.pm.ba.gov.br.campanhas.repository.CampanhaRepository;
import com.pm.ba.gov.br.campanhas.repository.PontoColetaRepository;
import com.pm.ba.gov.br.campanhas.services.PontoDeColetaService;

@Service
public class PontoDeColetaServiceImpl implements PontoDeColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public ResponseEntity<PontoColeta> getPontoDeColetaById(Integer id) {
        try {
            PontoColeta pontoColeta = pontoColetaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(pontoColeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> createPontoDeColeta(PontoColetaDTO pontoColetaDTO) {
        try {
            Campanha campanha = campanhaRepository.findById(pontoColetaDTO.getCampanhaID()).orElse(null);
            if (campanha == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada");
            }
            PontoColeta pontoColeta = new PontoColeta(pontoColetaDTO.getNome(), pontoColetaDTO.getEndereco(), campanha);
            pontoColetaRepository.save(pontoColeta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ponto de coleta criado com sucesso" + pontoColeta.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar ponto de coleta" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<PontoColeta> updatePontoDeColeta(Integer id, PontoColeta pontoDeColeta) {
        try {
            PontoColeta pontoColeta = pontoColetaRepository.save(pontoDeColeta);
            return ResponseEntity.ok(pontoColeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deletePontoDeColeta(Integer id) {
        try {
            pontoColetaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<PontoColeta>> getPontoDeColetaByCampanha(Integer id) {
        try {
            List<PontoColeta> pontoColeta = pontoColetaRepository.findByCampanhaId(id);
            return ResponseEntity.ok(pontoColeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<PontoColeta>> getAllPontoDeColeta() {
        try {
            List<PontoColeta> pontoColeta = pontoColetaRepository.findAll();
            return ResponseEntity.ok(pontoColeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
