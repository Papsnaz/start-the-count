package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.LocalVotacaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoProcessoEleitoralIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.SecaoProcessoEleitoral;
import br.ufsm.elc1071.startthecount.rest.repository.SecaoProcessoEleitoralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecaoProcessoEleitoralService {

    private final SecaoProcessoEleitoralRepository secaoProcessoEleitoralRepository;

    public SecaoProcessoEleitoral findById(SecaoProcessoEleitoralIdDTO id) {
        return this.secaoProcessoEleitoralRepository
            .findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF(),
                id.getCodigoProcessoEleitoralTSE()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de SecaoProcessoEleitoral identificada por %s.", id));
            });
    }

    public List<SecaoProcessoEleitoral> findAll() {
        return this.secaoProcessoEleitoralRepository.findAll();
    }

    public void saveIfDoesNotExist(SecaoProcessoEleitoral secaoProcessoEleitoral) {
        if (!this.secaoProcessoEleitoralRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(
            secaoProcessoEleitoral.getSecao().getNumeroTSE(),
            secaoProcessoEleitoral.getSecao().getZona().getNumeroTSE(),
            secaoProcessoEleitoral.getSecao().getZona().getUF().getSigla(),
            secaoProcessoEleitoral.getProcessoEleitoral().getCodigoTSE()
        )) {
            this.secaoProcessoEleitoralRepository.save(secaoProcessoEleitoral);
        }
    }

    public void deleteById(SecaoProcessoEleitoralIdDTO id) {
        id.validate();

        if (!this.secaoProcessoEleitoralRepository.existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF(),
            id.getCodigoProcessoEleitoralTSE()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de SecaoProcessoEleitoral identificada por %s.", id));
        }

        this.secaoProcessoEleitoralRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF(),
            id.getCodigoProcessoEleitoralTSE()
        );
    }

    public void deleteByLocalVotacaoId(LocalVotacaoIdDTO localVotacaoId) {
        localVotacaoId.validate();

        this.secaoProcessoEleitoralRepository.deleteByLocalVotacaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(
            localVotacaoId.getNumeroLocalVotacaoTSE(),
            localVotacaoId.getNumeroZonaTSE(),
            localVotacaoId.getSiglaUF()
        );
    }

    public void deleteByZonaId(ZonaIdDTO zonaId) {
        zonaId.validate();

        this.secaoProcessoEleitoralRepository.deleteBySecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(
            zonaId.getNumeroZonaTSE(),
            zonaId.getSiglaUF()
        );
    }

    public void deleteBySecaoId(SecaoIdDTO secaoId) {
        secaoId.validate();

        this.secaoProcessoEleitoralRepository.deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(
            secaoId.getNumeroSecaoTSE(),
            secaoId.getNumeroZonaTSE(),
            secaoId.getSiglaUF()
        );
    }

    public void deleteByCodigoProcessoEleitoralTSE(int codigoProcessoEleitoralTSE) {
        this.secaoProcessoEleitoralRepository.deleteByProcessoEleitoralCodigoTSE(codigoProcessoEleitoralTSE);
    }
}
