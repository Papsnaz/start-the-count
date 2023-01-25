package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.CargoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.CargoEleicaoMapper;
import br.ufsm.elc1071.startthecount.rest.model.CargoEleicao;
import br.ufsm.elc1071.startthecount.rest.repository.CargoEleicaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoEleicaoService {

    private final CargoEleicaoRepository cargoEleicaoRepository;

    private final CargoEleicaoMapper cargoEleicaoMapper;

    private final CandidaturaService candidaturaService;

    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;

    @Autowired
    public CargoEleicaoService(CargoEleicaoRepository cargoEleicaoRepository, CargoEleicaoMapper cargoEleicaoMapper, @Lazy CandidaturaService candidaturaService, ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService) {
        this.cargoEleicaoRepository = cargoEleicaoRepository;
        this.cargoEleicaoMapper = cargoEleicaoMapper;
        this.candidaturaService = candidaturaService;
        this.apuracaoVotosCargoBoletimUrnaService = apuracaoVotosCargoBoletimUrnaService;
    }

    public CargoEleicao findById(CargoEleicaoIdDTO id) {
        return this.cargoEleicaoRepository
            .findByCargoCodigoTSEAndEleicaoCodigoTSE(id.getCodigoCargoTSE(), id.getCodigoEleicaoTSE())
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de CargoEleicao identificada por %s.", id));
            });
    }

    public List<CargoEleicao> findAll() {
        return this.cargoEleicaoRepository.findAll();
    }

    public void saveIfDoesNotExist(CargoEleicao cargoEleicao) {
        if (!this.cargoEleicaoRepository.existsByCargoCodigoTSEAndEleicaoCodigoTSE(
            cargoEleicao.getCargo().getCodigoTSE(),
            cargoEleicao.getEleicao().getCodigoTSE()
        )) {
            this.cargoEleicaoRepository.save(cargoEleicao);
        }
    }

    public void deleteById(CargoEleicaoIdDTO id) {
        id.validate();

        if (!this.cargoEleicaoRepository.existsByCargoCodigoTSEAndEleicaoCodigoTSE(id.getCodigoCargoTSE(), id.getCodigoEleicaoTSE())) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de CargoEleicao identificada por %s.", id));
        }

        this.candidaturaService.deleteByCargoEleicaoId(id);
        this.apuracaoVotosCargoBoletimUrnaService.deleteByCargoEleicaoId(id);

        this.cargoEleicaoRepository.deleteByCargoCodigoTSEAndEleicaoCodigoTSE(id.getCodigoCargoTSE(), id.getCodigoEleicaoTSE());
    }

    public void deleteByCodigoCargoTSE(int codigoCargoTSE) {
        this.cargoEleicaoRepository
            .findByCargoCodigoTSE(codigoCargoTSE)
            .forEach(cargoEleicao -> {
                this.candidaturaService.deleteByCargoEleicaoId(
                    this.cargoEleicaoMapper.toCargoEleicaoIdDTO(cargoEleicao)
                );

                this.apuracaoVotosCargoBoletimUrnaService.deleteByCargoEleicaoId(
                    this.cargoEleicaoMapper.toCargoEleicaoIdDTO(cargoEleicao)
                );
            });

        this.cargoEleicaoRepository.deleteByCargoCodigoTSE(codigoCargoTSE);
    }

    public void deleteByCodigoEleicaoTSE(int codigoEleicaoTSE) {
        this.cargoEleicaoRepository
            .findByEleicaoCodigoTSE(codigoEleicaoTSE)
            .forEach(cargoEleicao -> {
                this.candidaturaService.deleteByCargoEleicaoId(
                    this.cargoEleicaoMapper.toCargoEleicaoIdDTO(cargoEleicao)
                );

                this.apuracaoVotosCargoBoletimUrnaService.deleteByCargoEleicaoId(
                    this.cargoEleicaoMapper.toCargoEleicaoIdDTO(cargoEleicao)
                );
            });

        this.cargoEleicaoRepository.deleteByEleicaoCodigoTSE(codigoEleicaoTSE);
    }
}
