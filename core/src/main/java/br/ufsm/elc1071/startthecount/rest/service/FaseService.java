package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Fase;
import br.ufsm.elc1071.startthecount.rest.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaseService {

    private final FaseRepository faseRepository;
    private final BoletimUrnaService boletimUrnaService;

    @Autowired
    public FaseService(FaseRepository faseRepository, @Lazy BoletimUrnaService boletimUrnaService) {
        this.faseRepository = faseRepository;
        this.boletimUrnaService = boletimUrnaService;
    }

    public Fase findByCodigoTSE(int codigoTSE) {
        return this.faseRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma fase com o código %d.", codigoTSE));
            });
    }

    public Fase findByNome(String nome) {
        return this.faseRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma fase com o nome \"%s\".", nome));
            });
    }

    public List<Fase> findAll() {
        return this.faseRepository.findAll();
    }

    public void saveIfDoesNotExist(Fase fase) {
        if (!this.faseRepository.existsByNomeEqualsIgnoreCase(fase.getNome())) {
            this.faseRepository.save(fase);
        }
    }

    public void deleteByNome(String nome) {
        if (!this.faseRepository.existsByNomeEqualsIgnoreCase(nome)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma fase com o nome \"%s\".", nome));
        }

        this.boletimUrnaService.deleteByNomeFase(nome);

        this.faseRepository.deleteByNomeEqualsIgnoreCase(nome);
    }
}
