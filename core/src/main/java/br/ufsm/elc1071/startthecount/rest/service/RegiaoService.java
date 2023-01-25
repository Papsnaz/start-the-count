package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Regiao;
import br.ufsm.elc1071.startthecount.rest.repository.RegiaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegiaoService {

    private final RegiaoRepository regiaoRepository;

    public Regiao findById(int id) {
        return this.regiaoRepository
            .findById(id)
            .orElseThrow(() -> {
              throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma região com o ID %d.", id));
            });
    }

    public List<Regiao> findAll() {
        return this.regiaoRepository.findAll();
    }
}
