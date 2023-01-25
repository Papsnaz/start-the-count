package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.LocalVotacaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.LocalVotacaoMapper;
import br.ufsm.elc1071.startthecount.rest.model.LocalVotacao;
import br.ufsm.elc1071.startthecount.rest.repository.LocalVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalVotacaoService {

    private final LocalVotacaoRepository localVotacaoRepository;
    private final LocalVotacaoMapper localVotacaoMapper;
    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    @Autowired
    public LocalVotacaoService(LocalVotacaoRepository localVotacaoRepository, @Lazy LocalVotacaoMapper localVotacaoMapper, SecaoProcessoEleitoralService secaoProcessoEleitoralService) {
        this.localVotacaoRepository = localVotacaoRepository;
        this.localVotacaoMapper = localVotacaoMapper;
        this.secaoProcessoEleitoralService = secaoProcessoEleitoralService;
    }

    public LocalVotacao findById(LocalVotacaoIdDTO id) {
        id.validate();

        return this.localVotacaoRepository
            .findByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroLocalVotacaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF())
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum local de votação identificado por %s.", id));
            });
    }

    public List<LocalVotacao> findAll() {
        return this.localVotacaoRepository.findAll();
    }

    public void saveIfDoesNotExist(LocalVotacao localVotacao) {
        if (!this.localVotacaoRepository.existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(
            localVotacao.getNumeroTSE(),
            localVotacao.getZona().getNumeroTSE(),
            localVotacao.getZona().getUF().getSigla()
        )) {
            this.localVotacaoRepository.save(localVotacao);
        }
    }

    public void deleteById(LocalVotacaoIdDTO id) {
        id.validate();

        if (!this.localVotacaoRepository.existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroLocalVotacaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF())) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum local de votação identificado por %s.", id));
        }

        this.secaoProcessoEleitoralService.deleteByLocalVotacaoId(id);

        this.localVotacaoRepository.deleteByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroLocalVotacaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF());
    }

    public void deleteByZonaId(ZonaIdDTO zonaId) {
        zonaId.validate();

        this.localVotacaoRepository
            .findByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(zonaId.getNumeroZonaTSE(), zonaId.getSiglaUF())
            .forEach(localVotacao -> this.secaoProcessoEleitoralService.deleteByLocalVotacaoId(
                this.localVotacaoMapper.toLocalVotacaoIdDTO(localVotacao)
            ));

        this.localVotacaoRepository.deleteByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(zonaId.getNumeroZonaTSE(), zonaId.getSiglaUF());
    }

    public void deleteByCodigoMunicipioTSE(int codigoMunicipioTSE) {
        this.localVotacaoRepository
            .findByMunicipioCodigoTSE(codigoMunicipioTSE)
            .forEach(localVotacao -> this.secaoProcessoEleitoralService.deleteByLocalVotacaoId(
                this.localVotacaoMapper.toLocalVotacaoIdDTO(localVotacao)
            ));

        this.localVotacaoRepository.deleteByMunicipioCodigoTSE(codigoMunicipioTSE);
    }
}
