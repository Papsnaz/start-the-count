package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.OrigemConfiguracaoProcessoEleitoral;
import br.ufsm.elc1071.startthecount.rest.repository.OrigemConfiguracaoProcessoEleitoralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrigemConfiguracaoProcessoEleitoralService {

    private final OrigemConfiguracaoProcessoEleitoralRepository origemConfiguracaoProcessoEleitoralRepository;

    public OrigemConfiguracaoProcessoEleitoral findById(int id) {
        return this.origemConfiguracaoProcessoEleitoralRepository
            .findById(id)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma origem de configuração de processo eleitoral com o ID %d.", id));
            });
    }

    public OrigemConfiguracaoProcessoEleitoral findByAbreviacao(String abreviacao) {
        return this.origemConfiguracaoProcessoEleitoralRepository
            .findByAbreviacaoEqualsIgnoreCase(abreviacao)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma origem de configuração de processo eleitoral com a abreviação \"%s\".", abreviacao));
            });
    }

    public List<OrigemConfiguracaoProcessoEleitoral> findAll() {
        return this.origemConfiguracaoProcessoEleitoralRepository.findAll();
    }

    public void saveIfDoesNotExist(OrigemConfiguracaoProcessoEleitoral origemConfiguracaoProcessoEleitoral) {
        if (!this.origemConfiguracaoProcessoEleitoralRepository.existsByAbreviacaoEqualsIgnoreCase(origemConfiguracaoProcessoEleitoral.getAbreviacao())) {
            this.origemConfiguracaoProcessoEleitoralRepository.save(origemConfiguracaoProcessoEleitoral);
        }
    }
}
