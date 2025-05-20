package com.pm.ba.gov.br.campanhas.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pm.ba.gov.br.campanhas.dto.CentralColetaDTO;
import com.pm.ba.gov.br.campanhas.dto.CentralColetaResponseDTO;
import com.pm.ba.gov.br.campanhas.models.Campanha;
import com.pm.ba.gov.br.campanhas.models.CentralColeta;
import com.pm.ba.gov.br.campanhas.repository.CampanhaRepository;
import com.pm.ba.gov.br.campanhas.repository.CentralColetaRepository;
import com.pm.ba.gov.br.campanhas.services.CentralDeColetaService;

@Service
public class CentralDeColetaServiceImpl implements CentralDeColetaService {

    @Autowired
    private CentralColetaRepository centralColetaRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public ResponseEntity<List<CentralColetaResponseDTO>> getAllCentralDeColeta() {
        try {
            List<CentralColeta> centrais = centralColetaRepository.findAll();
            List<CentralColetaResponseDTO> response = centrais.stream()
                .map(c -> new CentralColetaResponseDTO(
                    c.getIdCentral(),
                    c.getNome(),
                    c.getEndereco(),
                    c.getCampanha().getIdCampanha()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> getCentralDeColetaById(Integer id) {
        try {
            CentralColeta centralColeta = centralColetaRepository.findById(id).orElse(null);
            if (centralColeta == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            CentralColetaResponseDTO response = new CentralColetaResponseDTO(
                centralColeta.getIdCentral(),
                centralColeta.getNome(),
                centralColeta.getEndereco(),
                centralColeta.getCampanha().getIdCampanha()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> createCentralDeColeta(CentralColetaDTO centralColetaDTO) {
        try {

            Campanha campanha = campanhaRepository.findById(centralColetaDTO.getCampanhaID()).orElse(null);
            if (campanha == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campanha não encontrada");
            }
            CentralColeta centralColetas = new CentralColeta(centralColetaDTO.getNome(), centralColetaDTO.getEndereco(), campanha);
            centralColetaRepository.save(centralColetas);
            return ResponseEntity.status(HttpStatus.CREATED).body("Central de coleta criada com sucesso" + centralColetas.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar central de coleta" + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateCentralDeColeta(Integer id, CentralColetaDTO centralColetaDTO) {
        try {
            CentralColeta centralColeta = centralColetaRepository.findById(id).orElse(null);
            if (centralColeta == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Central de coleta não encontrada");
            }
            centralColeta.setNome(centralColetaDTO.getNome());
            centralColeta.setEndereco(centralColetaDTO.getEndereco());
            centralColetaRepository.save(centralColeta);
            return ResponseEntity.ok(centralColeta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCentralDeColeta(Integer id) {
        try {
            if (centralColetaRepository.existsById(id)) {
                centralColetaRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<?> getCentralDeColetaByCampanha(Integer id) {
        try {
            List<CentralColeta> centrais = centralColetaRepository.findByCampanhaId(id);
            if (centrais.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma central de coleta encontrada para a campanha com ID: " + id);
            }
            List<CentralColetaResponseDTO> response = centrais.stream()
                .map(c -> new CentralColetaResponseDTO(
                    c.getIdCentral(),
                    c.getNome(),
                    c.getEndereco(),
                    c.getCampanha().getIdCampanha()))
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
