package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Permissao;
import br.ufsm.elc1071.startthecount.rest.repository.PermissaoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

    public Permissao findById(int id) {
        return this.permissaoRepository
            .findById(id)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma permissao com o ID %d.", id));
            });
    }

    public List<Permissao> findAll() {
        return this.permissaoRepository.findAll();
    }
}
