package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.ProcessoEleitoral;
import br.ufsm.elc1071.startthecount.rest.repository.ProcessoEleitoralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessoEleitoralService {

    private final ProcessoEleitoralRepository processoEleitoralRepository;
    private final PleitoService pleitoService;
    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    public ProcessoEleitoral findByCodigoTSE(int codigoTSE) {
        return this.processoEleitoralRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum processo eleitoral com o código do TSE %d.", codigoTSE));
            });
    }

    public List<ProcessoEleitoral> findAll() {
        return this.processoEleitoralRepository.findAll();
    }

    public void saveIfDoesNotExist(ProcessoEleitoral processoEleitoral) {
        if (!this.processoEleitoralRepository.existsByCodigoTSE(processoEleitoral.getCodigoTSE())) {
            this.processoEleitoralRepository.save(processoEleitoral);
        }
    }

    public void deleteByCodigoTSE(int codigoTSE) {
        if (!this.processoEleitoralRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum processo eleitoral com o código do TSE %d.", codigoTSE));
        }

        this.pleitoService.deleteByCodigoProcessoEleitoralTSE(codigoTSE);
        this.secaoProcessoEleitoralService.deleteByCodigoProcessoEleitoralTSE(codigoTSE);

        this.processoEleitoralRepository.deleteByCodigoTSE(codigoTSE);
    }
}
