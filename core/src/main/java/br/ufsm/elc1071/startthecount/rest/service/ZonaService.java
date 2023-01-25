package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Zona;
import br.ufsm.elc1071.startthecount.rest.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {

    private final ZonaRepository zonaRepository;
    private final LocalVotacaoService localVotacaoService;
    private final SecaoService secaoService;

    @Autowired
    public ZonaService(ZonaRepository zonaRepository, LocalVotacaoService localVotacaoService, @Lazy SecaoService secaoService) {
        this.zonaRepository = zonaRepository;
        this.localVotacaoService = localVotacaoService;
        this.secaoService = secaoService;
    }

    public Zona findById(ZonaIdDTO id) {
        id.validate();

        return this.zonaRepository
            .findByNumeroTSEAndUfSiglaEqualsIgnoreCase(id.getNumeroZonaTSE(), id.getSiglaUF())
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma zona identificada por %s.", id));
            });
    }

    public List<Zona> findAll() {
        return this.zonaRepository.findAll();
    }

    public void saveIfDoesNotExist(Zona zona) {
        if (!this.zonaRepository.existsByNumeroTSEAndUfSiglaEqualsIgnoreCase(
            zona.getNumeroTSE(),
            zona.getUF().getSigla()
        )) {
            this.zonaRepository.save(zona);
        }
    }

    public void deleteById(ZonaIdDTO id) {
        id.validate();

        if (!this.zonaRepository.existsByNumeroTSEAndUfSiglaEqualsIgnoreCase(id.getNumeroZonaTSE(), id.getSiglaUF())) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma zona identificada por %s.", id));
        }

        this.localVotacaoService.deleteByZonaId(id);
        this.secaoService.deleteByZonaId(id);

        this.zonaRepository.deleteByNumeroTSEAndUfSiglaEqualsIgnoreCase(id.getNumeroZonaTSE(), id.getSiglaUF());
    }
}
