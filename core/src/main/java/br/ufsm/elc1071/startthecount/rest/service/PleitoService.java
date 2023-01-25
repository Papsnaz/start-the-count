package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Pleito;
import br.ufsm.elc1071.startthecount.rest.repository.PleitoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PleitoService {

    private final PleitoRepository pleitoRepository;
    private final EleicaoService eleicaoService;

    public Pleito findByCodigoTSE(int codigoTSE) {
        return this.pleitoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum pleito com o código %d.", codigoTSE));
            });
    }

    public List<Pleito> findAll() {
        return this.pleitoRepository.findAll();
    }

    public void saveIfDoesNotExist(Pleito pleito) {
        if (!this.pleitoRepository.existsByCodigoTSE(pleito.getCodigoTSE())) {
            this.pleitoRepository.save(pleito);
        }
    }

    public void deleteByCodigoTSE(int codigoTSE) {
        if (!this.pleitoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum pleito com o código %d.", codigoTSE));
        }

        this.eleicaoService.deleteByCodigoPleitoTSE(codigoTSE);

        this.pleitoRepository.deleteByCodigoTSE(codigoTSE);
    }

    public void deleteByCodigoProcessoEleitoralTSE(int codigoProcessoEleitoralTSE) {
        this.pleitoRepository.deleteByProcessoEleitoralCodigoTSE(codigoProcessoEleitoralTSE);
    }
}
