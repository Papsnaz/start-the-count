package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.UF;
import br.ufsm.elc1071.startthecount.rest.repository.UFRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UFService {

    private final UFRepository ufRepository;

    public UF findBySigla(String sigla) {
        return this.ufRepository
            .findBySiglaEqualsIgnoreCase(sigla)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma UF com a sigla \"%s\".", sigla));
            });
    }

    public List<UF> findAll() {
        return this.ufRepository.findAll();
    }
}
