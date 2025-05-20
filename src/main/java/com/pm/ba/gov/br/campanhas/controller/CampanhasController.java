package com.pm.ba.gov.br.campanhas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.ba.gov.br.campanhas.dto.CentralColetaDTO;
import com.pm.ba.gov.br.campanhas.dto.CentralColetaResponseDTO;
import com.pm.ba.gov.br.campanhas.dto.PontoColetaDTO;
import com.pm.ba.gov.br.campanhas.models.Campanha;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;
import com.pm.ba.gov.br.campanhas.services.CampanhasServices;
import com.pm.ba.gov.br.campanhas.services.CentralDeColetaService;
import com.pm.ba.gov.br.campanhas.services.PontoDeColetaService;


@RestController
@RequestMapping("api/campanhas")
public class CampanhasController {

    @Autowired
    private CampanhasServices campanhasServices;

    @Autowired
    private PontoDeColetaService pontoDeColetaService;

    @Autowired
    private CentralDeColetaService centralColetaService;


    @PostMapping
    public ResponseEntity<Campanha> createCampanha(@RequestBody Campanha campanha) {
        return campanhasServices.createCampanha(campanha);
    }
    

    @GetMapping
    public ResponseEntity<List<Campanha>> getAllCampanhas() {
        return campanhasServices.getAllCampanhas();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Campanha>> getCampanhasByStatus(@PathVariable Boolean status) {
        return campanhasServices.getCampanhasByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campanha> getCampanhaById(@PathVariable int id) {
        return campanhasServices.getCampanhaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campanha> updateCampanha(@PathVariable int id, @RequestBody Campanha campanha) {
        return campanhasServices.updateCampanha(campanha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Campanha> deleteCampanha(@PathVariable int id) {
        return campanhasServices.deleteCampanha(id);
    }


    @GetMapping("/ponto-de-coleta")
    public ResponseEntity<List<PontoColeta>> getAllPontoDeColeta() {
        return pontoDeColetaService.getAllPontoDeColeta();
    }

    @GetMapping("/central-de-coleta")
    public ResponseEntity<List<CentralColetaResponseDTO>> getAllCentralDeColeta() {
        return centralColetaService.getAllCentralDeColeta();
    }

    @GetMapping("/central-de-coleta/campanha/{id}")
    public ResponseEntity<?> getCentralDeColetaByCampanha(@PathVariable int id) {
        return centralColetaService.getCentralDeColetaByCampanha(id);
    }


    @GetMapping("/ponto-de-coleta/campanha/{id}")
       public ResponseEntity<List<PontoColeta>> getPontoDeColetaByCampanha(@PathVariable int id) {
           return pontoDeColetaService.getPontoDeColetaByCampanha(id);
       }

       @PostMapping("create/central-de-coleta")
       public ResponseEntity<?> createCentralDeColeta(@RequestBody CentralColetaDTO centralColetaDTO) {
           return centralColetaService.createCentralDeColeta(centralColetaDTO);
       }

       @PostMapping("create/ponto-de-coleta")
       public ResponseEntity<?> createPontoDeColeta(@RequestBody PontoColetaDTO pontoColetaDTO) {
           return pontoDeColetaService.createPontoDeColeta(pontoColetaDTO);
       }

       @PutMapping("update/central-de-coleta/{id}")
       public ResponseEntity<?> updateCentralDeColeta(@PathVariable Integer id, @RequestBody CentralColetaDTO centralColetaDTO) {
           return centralColetaService.updateCentralDeColeta(id, centralColetaDTO);
       }

       @PutMapping("update/ponto-de-coleta/{id}")
       public ResponseEntity<PontoColeta> updatePontoDeColeta(@PathVariable int id, @RequestBody PontoColeta pontoColeta) {
           return pontoDeColetaService.updatePontoDeColeta(id, pontoColeta);
       }
       
       
        
}