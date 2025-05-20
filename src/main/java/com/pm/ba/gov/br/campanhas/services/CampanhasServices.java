package com.pm.ba.gov.br.campanhas.services;

import com.pm.ba.gov.br.campanhas.models.Campanha;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CampanhasServices {
    public ResponseEntity<Campanha> createCampanha(Campanha campanha);
    public ResponseEntity<Campanha> updateCampanha(Campanha campanha);
    public ResponseEntity<Campanha> deleteCampanha(int id);
    public ResponseEntity<List<Campanha>> getAllCampanhas();
    public ResponseEntity<List<Campanha>> getCampanhasByStatus(Boolean status);
    public ResponseEntity<Campanha> getCampanhaById(int id);
}
