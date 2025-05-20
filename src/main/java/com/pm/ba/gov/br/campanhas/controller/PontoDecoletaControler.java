package com.pm.ba.gov.br.campanhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.pm.ba.gov.br.campanhas.dto.itemDTO;
import com.pm.ba.gov.br.campanhas.models.Item;
import com.pm.ba.gov.br.campanhas.services.CadastrarItensNoPontoService;


@RestController
@RequestMapping("/ponto-de-coleta")
public class PontoDecoletaControler {
    @Autowired
    private CadastrarItensNoPontoService cadastrarItensNoPontoService;

    @PostMapping("/cadastrar-itens/{idPonto}")
    public ResponseEntity<?> cadastrarItensNoPonto(@RequestBody Item item, @PathVariable Integer idPonto) {
        return cadastrarItensNoPontoService.cadastrarItensNoEstoqueDoPonto(idPonto, item);
    }

    @PutMapping("/atualizar-itens/ponto/{idPonto}/item/{idItem}")
    public ResponseEntity<?> atualizarItensNoPonto(@RequestBody itemDTO item, @PathVariable Integer idPonto, @PathVariable Integer idItem) {
        return cadastrarItensNoPontoService.updateQuantidadeItensNoEstoqueDoPonto(idPonto, idItem, item);
    }
    
    @DeleteMapping("/deletar-itens/ponto/{idPonto}/item/{idItem}")
    public ResponseEntity<?> deletarItensNoPonto(@PathVariable Integer idPonto, @PathVariable Integer idItem) {
        return cadastrarItensNoPontoService.deleteItensNoEstoqueDoPonto(idPonto, idItem);
    }

    @GetMapping("/{idPonto}")
    public ResponseEntity<?> getItensNoEstoqueDoPonto(@PathVariable Integer idPonto) {
        return cadastrarItensNoPontoService.getItensNoEstoqueDoPonto(idPonto);
    }
    
}
