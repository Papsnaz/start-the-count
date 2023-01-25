package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.CargoMapper;
import br.ufsm.elc1071.startthecount.rest.model.Cargo;
import br.ufsm.elc1071.startthecount.rest.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final CargoEleicaoService cargoEleicaoService;

    @Autowired
    public CargoService(CargoRepository cargoRepository, @Lazy CargoEleicaoService cargoEleicaoService) {
        this.cargoRepository = cargoRepository;
        this.cargoEleicaoService = cargoEleicaoService;
    }

    public Cargo findByCodigoTSE(int codigoTSE) {
        return this.cargoRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum cargo com o código %d.", codigoTSE));
            });
    }

    public List<Cargo> findAll() {
        return this.cargoRepository.findAll();
    }

    public void saveIfDoesNotExists(Cargo cargo) {
        if (!this.cargoRepository.existsByCodigoTSE(cargo.getCodigoTSE())) {
            this.cargoRepository.save(cargo);
        }
    }

    public void deleteByCodigoTSE(int codigoTSE) {
        if (!this.cargoRepository.existsByCodigoTSE(codigoTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum cargo com o código %d.", codigoTSE));
        }

        this.cargoEleicaoService.deleteByCodigoCargoTSE(codigoTSE);

        this.cargoRepository.deleteByCodigoTSE(codigoTSE);
    }

    public void deleteByCodigoTipoCargoTSE(int codigoTipoCargoTSE) {
        this.cargoRepository.deleteByTipoCodigoTSE(codigoTipoCargoTSE);
    }
}
