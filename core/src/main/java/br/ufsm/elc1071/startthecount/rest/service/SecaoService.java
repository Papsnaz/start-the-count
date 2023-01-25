package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Secao;
import br.ufsm.elc1071.startthecount.rest.repository.SecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecaoService {

    private final SecaoRepository secaoRepository;
    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;
    private final SecaoEleicaoService secaoEleicaoService;

    public Secao findById(SecaoIdDTO id) {
        return this.secaoRepository
            .findByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF())
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma seção identificada por %s.", id));
            });
    }

    public List<Secao> findAll() {
        return this.secaoRepository.findAll();
    }

    public void saveIfDoesNotExist(Secao secao) {
        if (!this.secaoRepository.existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(
            secao.getNumeroTSE(),
            secao.getZona().getNumeroTSE(),
            secao.getZona().getUF().getSigla()
        )) {
            this.secaoRepository.save(secao);
        }
    }

    public void deleteById(SecaoIdDTO id) {
        id.validate();

        if (!this.secaoRepository.existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF())) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma seção identificada por %s.", id));
        }

        this.secaoProcessoEleitoralService.deleteBySecaoId(id);
        this.secaoEleicaoService.deleteBySecaoId(id);

        this.secaoRepository.deleteByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(id.getNumeroSecaoTSE(), id.getNumeroZonaTSE(), id.getSiglaUF());
    }

    public void deleteByZonaId(ZonaIdDTO zonaId) {
        zonaId.validate();

        this.secaoRepository.findByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(zonaId.getNumeroZonaTSE(), zonaId.getSiglaUF())
            .forEach(secao -> {
                this.secaoProcessoEleitoralService.deleteByZonaId(zonaId);
                this.secaoEleicaoService.deleteByZonaId(zonaId);
            });

        this.secaoRepository.deleteByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(zonaId.getNumeroZonaTSE(), zonaId.getSiglaUF());
    }
}
