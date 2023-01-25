package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.OrigemBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.repository.OrigemBoletimUrnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrigemBoletimUrnaService {

    private final OrigemBoletimUrnaRepository origemBoletimUrnaRepository;

    public OrigemBoletimUrna findById(int id) {
        return this.origemBoletimUrnaRepository
            .findById(id)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma origem de boletim de urna com o ID %d.", id));
            });
    }

    public OrigemBoletimUrna findByAbreviacao(String abreviacao) {
        return this.origemBoletimUrnaRepository
            .findByAbreviacaoEqualsIgnoreCase(abreviacao)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma origem de boletim de urna com a abreviação \"%s\".", abreviacao));
            });
    }

    public List<OrigemBoletimUrna> findAll() {
        return this.origemBoletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(OrigemBoletimUrna origemBoletimUrna) {
        if (!this.origemBoletimUrnaRepository.existsByAbreviacaoEqualsIgnoreCase(origemBoletimUrna.getAbreviacao())) {
            this.origemBoletimUrnaRepository.save(origemBoletimUrna);
        }
    }
}
