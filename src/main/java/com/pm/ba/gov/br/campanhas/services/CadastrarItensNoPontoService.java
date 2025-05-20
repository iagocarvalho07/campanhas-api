package com.pm.ba.gov.br.campanhas.services;

import org.springframework.http.ResponseEntity;

import com.pm.ba.gov.br.campanhas.dto.itemDTO;
import com.pm.ba.gov.br.campanhas.models.Item;

public interface CadastrarItensNoPontoService {
    public ResponseEntity<?> cadastrarItensNoEstoqueDoPonto(Integer idPonto, Item itens);
    public ResponseEntity<?> updateQuantidadeItensNoEstoqueDoPonto(Integer idPonto, Integer idItem, itemDTO itens);
    public ResponseEntity<?> deleteItensNoEstoqueDoPonto(Integer idPonto, Integer idItem);
    public ResponseEntity<?> getItensNoEstoqueDoPonto(Integer idPonto);
    public ResponseEntity<?> getItensNoEstoqueDoPontoByNome(Integer idPonto, String nome);
    public ResponseEntity<?> getItensNoEstoqueDoPontoByCategoria(Integer idPonto, String categoria);

  
    



    
}
