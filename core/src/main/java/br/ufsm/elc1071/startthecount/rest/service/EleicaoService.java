package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Eleicao;
import br.ufsm.elc1071.startthecount.rest.repository.EleicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleicaoService {

    private final EleicaoRepository eleicaoRepository;
    private final SecaoEleicaoService secaoEleicaoService;
    private final CargoEleicaoService cargoEleicaoService;

    @Autowired
    public EleicaoService(EleicaoRepository eleicaoRepository, SecaoEleicaoService secaoEleicaoService, @Lazy CargoEleicaoService cargoEleicaoService) {
        this.eleicaoRepository = eleicaoRepository;
        this.secaoEleicaoService = secaoEleicaoService;
        this.cargoEleicaoService = cargoEleicaoService;
    }

    public Eleicao findByCodigoTSE(int codigoTSE) {
        return this.eleicaoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma eleição com o código %d.", codigoTSE));
            });
    }

    public List<Eleicao> findAll() {
        return this.eleicaoRepository.findAll();
    }

    public void saveIfDoesNotExist(Eleicao eleicao) {
        if (!this.eleicaoRepository.existsByCodigoTSE(eleicao.getCodigoTSE())) {
            this.eleicaoRepository.save(eleicao);
        }
    }

    public void deleteByCodigoTSE(int codigoTSE) {
        if (!this.eleicaoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma eleição com o código %d.", codigoTSE));
        }

        this.cargoEleicaoService.deleteByCodigoEleicaoTSE(codigoTSE);
        this.secaoEleicaoService.deleteByCodigoEleicaoTSE(codigoTSE);

        this.eleicaoRepository.deleteByCodigoTSE(codigoTSE);
    }

    public void deleteByCodigoPleitoTSE(int codigoPleitoTSE) {
        this.eleicaoRepository.deleteByPleitoCodigoTSE(codigoPleitoTSE);
    }
}
