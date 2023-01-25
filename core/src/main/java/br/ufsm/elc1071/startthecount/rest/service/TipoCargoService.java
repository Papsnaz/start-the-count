package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.TipoCargo;
import br.ufsm.elc1071.startthecount.rest.repository.TipoCargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCargoService {

    private final TipoCargoRepository tipoCargoRepository;
    private final CargoService cargoService;

    public TipoCargo findByCodigoTSE(int codigoTSE) {
        return this.tipoCargoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum tipo de cargo com o código do TSE %d.", codigoTSE));
            });
    }

    public List<TipoCargo> findAll() {
        return this.tipoCargoRepository.findAll();
    }

    public void saveIfDoesNotExist(TipoCargo tipoCargo) {
        if (!this.tipoCargoRepository.existsByCodigoTSE(tipoCargo.getCodigoTSE())) {
            this.tipoCargoRepository.save(tipoCargo);
        }
    }

    public void deleteByCodigoTSE(int codigoTSE) {
        if (!this.tipoCargoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum tipo de cargo com o código %d.", codigoTSE));
        }

        this.cargoService.deleteByCodigoTipoCargoTSE(codigoTSE);

        this.tipoCargoRepository.deleteByCodigoTSE(codigoTSE);
    }
}
