package com.pm.ba.gov.br.campanhas.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.ba.gov.br.campanhas.models.EstoquePonto;
import com.pm.ba.gov.br.campanhas.models.Item;
import com.pm.ba.gov.br.campanhas.models.PontoColeta;
import com.pm.ba.gov.br.campanhas.repository.EstoquePontoRepository;
import com.pm.ba.gov.br.campanhas.repository.ItemRepository;
import com.pm.ba.gov.br.campanhas.repository.PontoColetaRepository;
import com.pm.ba.gov.br.campanhas.services.CadastrarItensNoPontoService;
import com.pm.ba.gov.br.campanhas.exceptions.EntityNotFoundException;
import com.pm.ba.gov.br.campanhas.models.EstoquePontoId;
import com.pm.ba.gov.br.campanhas.dto.EstoquePontoDTO;
import com.pm.ba.gov.br.campanhas.dto.itemDTO;

@Service
public class CadastrarIntesNoPontoServiceImpl implements CadastrarItensNoPontoService {

    @Autowired
    private PontoColetaRepository pontoDeColetaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EstoquePontoRepository estoquePontoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> cadastrarItensNoEstoqueDoPonto(Integer idPonto, Item itens) {
        try {
            // Busca o ponto de coleta
            PontoColeta pontoColeta = pontoDeColetaRepository.findById(idPonto)
                .orElseThrow(() -> new EntityNotFoundException("Ponto de coleta não encontrado"));

            // Valida o item antes de salvar
            if (itens == null || itens.getUnidade() <= 0) {
                return ResponseEntity.badRequest().body("Item inválido ou quantidade deve ser maior que zero");
            }

            // Salva o item
            Item itemSalvo = itemRepository.save(itens);
            if (itemSalvo == null || itemSalvo.getIdItem() == null) {
                throw new RuntimeException("Erro ao salvar o item");
            }
            
            // Verifica se já existe estoque para este item neste ponto
            EstoquePonto estoqueExistente = estoquePontoRepository
                .findByPontoAndItem(pontoColeta, itemSalvo)
                .orElse(null);

            if (estoqueExistente != null) {
                // Atualiza a quantidade do estoque existente
                estoqueExistente.setQuantidade(estoqueExistente.getQuantidade() + itemSalvo.getUnidade());
                EstoquePonto estoqueAtualizado = estoquePontoRepository.save(estoqueExistente);
                if (estoqueAtualizado == null) {
                    throw new RuntimeException("Erro ao atualizar o estoque");
                }
                return ResponseEntity.ok("Quantidade do item atualizada no estoque do ponto de coleta");
            } else {
                // Cria novo registro de estoque
                EstoquePonto novoEstoque = new EstoquePonto();
                // Configura a chave composta
                EstoquePontoId id = new EstoquePontoId(pontoColeta.getIdPonto(), itemSalvo.getIdItem());
                novoEstoque.setId(id);
                novoEstoque.setPonto(pontoColeta);
                novoEstoque.setItem(itemSalvo);
                novoEstoque.setQuantidade(itemSalvo.getUnidade());
                EstoquePonto estoqueSalvo = estoquePontoRepository.save(novoEstoque);
                if (estoqueSalvo == null) {
                    throw new RuntimeException("Erro ao criar novo estoque");
                }
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Item cadastrado no estoque do ponto de coleta com sucesso");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o cadastro: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateQuantidadeItensNoEstoqueDoPonto(Integer idPonto, Integer idItem, itemDTO itens) {
        try {
            // Busca o ponto de coleta
            PontoColeta pontoColeta = pontoDeColetaRepository.findById(idPonto)
                .orElseThrow(() -> new EntityNotFoundException("Ponto de coleta não encontrado"));

            // Busca o item
            Item item = itemRepository.findById(idItem)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

            // Busca o estoque do ponto
            EstoquePonto estoque = estoquePontoRepository.findByPontoAndItem(pontoColeta, item)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado no estoque do ponto de coleta"));

            // Atualiza a quantidade do estoque
            item.setUnidade(itens.getUnidade());
            estoque.setQuantidade(itens.getUnidade());
            itemRepository.save(item);
            EstoquePonto estoqueAtualizado = estoquePontoRepository.save(estoque);

            if (estoqueAtualizado == null) {
                throw new RuntimeException("Erro ao atualizar o estoque");
            }
            return ResponseEntity.ok("Quantidade do item atualizada no estoque do ponto de coleta " + estoqueAtualizado.getQuantidade());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a atualização: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> deleteItensNoEstoqueDoPonto(Integer idPonto, Integer idItem) {
        try {
            EstoquePontoId id = new EstoquePontoId(idPonto, idItem);
            // Busca o estoque do ponto
             estoquePontoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado no estoque do ponto de coleta"));
            
            itemRepository.deleteById(idItem);
            estoquePontoRepository.deleteById(id);
            return ResponseEntity.ok("Item deletado do estoque do ponto de coleta");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a exclusão: " + e.getMessage(), e);
        }   
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> getItensNoEstoqueDoPonto(Integer idPonto) {
        PontoColeta pontoColeta = pontoDeColetaRepository.findById(idPonto).orElse(null);
        if (pontoColeta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ponto de coleta não encontrado");
        }
        List<EstoquePonto> estoque = estoquePontoRepository.findByPontoId(idPonto);
        if (estoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há itens no estoque do ponto de coleta");
        }
        
        List<EstoquePontoDTO> estoqueDTO = estoque.stream()
            .map(EstoquePontoDTO::new)
            .collect(Collectors.toList());
            
        return ResponseEntity.status(HttpStatus.OK).body(estoqueDTO);
    }

    @Override
    public ResponseEntity<?> getItensNoEstoqueDoPontoByNome(Integer idPonto, String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItensNoEstoqueDoPontoByNome'");
    }

    @Override
    public ResponseEntity<?> getItensNoEstoqueDoPontoByCategoria(Integer idPonto, String categoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItensNoEstoqueDoPontoByCategoria'");
    }


    
}
