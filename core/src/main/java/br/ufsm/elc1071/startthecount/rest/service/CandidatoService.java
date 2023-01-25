package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Candidato;
import br.ufsm.elc1071.startthecount.rest.repository.CandidatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final CandidaturaService candidaturaService;

    @Autowired
    public CandidatoService(CandidatoRepository candidatoRepository, @Lazy CandidaturaService candidaturaService) {
        this.candidatoRepository = candidatoRepository;
        this.candidaturaService = candidaturaService;
    }

    public Candidato findByCodigoTSE(String codigoTSE) {
        return this.candidatoRepository
            .findByCodigoTSEEqualsIgnoreCase(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum candidato com o código %s.", codigoTSE));
            });
    }

    public Candidato findByNumeroTSE(int numeroTSE) {
        return this.candidatoRepository
            .findByNumeroTSE(numeroTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum candidato com o número %s.", numeroTSE));
            });
    }

    public List<Candidato> findAll() {
        return this.candidatoRepository.findAll();
    }

    public void saveIfDoesNotExist(Candidato candidato) {
        if (!this.candidatoRepository.existsByCodigoTSEEqualsIgnoreCase(candidato.getCodigoTSE())) {
            this.candidatoRepository.save(candidato);
        }
    }

    public void deleteByCodigoTSE(String codigoTSE) {
        if (!this.candidatoRepository.existsByCodigoTSEEqualsIgnoreCase(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum candidato com o código %s.", codigoTSE));
        }

        this.candidaturaService.deleteByCodigoCandidatoTSE(codigoTSE);

        this.candidatoRepository.deleteByCodigoTSEEqualsIgnoreCase(codigoTSE);
    }
}
