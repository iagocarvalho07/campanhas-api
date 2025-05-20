package com.pm.ba.gov.br.campanhas.services.impl;

import com.pm.ba.gov.br.campanhas.models.Campanha;
import com.pm.ba.gov.br.campanhas.repository.CampanhaRepository;
import com.pm.ba.gov.br.campanhas.services.CampanhasServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampanhasServiceImpl implements CampanhasServices {
    
    @Autowired
    private CampanhaRepository campanhaRepository;

    @Override
    public ResponseEntity<Campanha> createCampanha(Campanha campanha) {
        try {
            campanhaRepository.save(campanha);
            return ResponseEntity.status(HttpStatus.CREATED).body(campanha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(campanha);
        }
        
        
    }

    @Override
    public ResponseEntity<Campanha> updateCampanha(Campanha campanha) {
        try {
            campanhaRepository.save(campanha);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(campanha);
        }
        return ResponseEntity.ok(campanha);
    }

    @Override
    public ResponseEntity<Campanha> deleteCampanha(int id) {
        try {
            campanhaRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity<List<Campanha>> getAllCampanhas() {
        try {
            return ResponseEntity.ok(campanhaRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    @Override
    public ResponseEntity<Campanha> getCampanhaById(int id) {
        Campanha campanha = campanhaRepository.findById(id).orElse(null);
        if(campanha == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(campanha);
    }

    @Override
    public ResponseEntity<List<Campanha>> getCampanhasByStatus(Boolean status) {
        try {
            return ResponseEntity.ok(campanhaRepository.findByStatus(status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
