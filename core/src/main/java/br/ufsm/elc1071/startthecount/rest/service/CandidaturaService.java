package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.CandidaturaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.CargoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.CandidaturaMapper;
import br.ufsm.elc1071.startthecount.rest.model.Candidatura;
import br.ufsm.elc1071.startthecount.rest.repository.CandidaturaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final CandidaturaMapper candidaturaMapper;
    private final ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService;

    public Candidatura findById(CandidaturaIdDTO id) {
        return this.candidaturaRepository
            .findByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
                id.getNumeroCandidatoTSE(),
                id.getCodigoCargoTSE(),
                id.getCodigoEleicaoTSE()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma candidatura identificada por %s.", id));
            });
    }

    public List<Candidatura> findAll() {
        return this.candidaturaRepository.findAll();
    }

    public void saveIfDoesNotExist(Candidatura candidatura) {
        if (!this.candidaturaRepository.existsByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
            candidatura.getCandidato().getNumeroTSE(),
            candidatura.getCargoEleicao().getCargo().getCodigoTSE(),
            candidatura.getCargoEleicao().getEleicao().getCodigoTSE()
        )) {
            this.candidaturaRepository.save(candidatura);
        }
    }

    public void deleteById(CandidaturaIdDTO id) {
        id.validate();

        if (!this.candidaturaRepository.existsByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
            id.getNumeroCandidatoTSE(),
            id.getCodigoCargoTSE(),
            id.getCodigoEleicaoTSE()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma candidatura identificada por %s.", id));
        }

        this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByCandidaturaId(id);

        this.candidaturaRepository.deleteByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
            id.getNumeroCandidatoTSE(),
            id.getCodigoCargoTSE(),
            id.getCodigoEleicaoTSE()
        );
    }

    public void deleteByCargoEleicaoId(CargoEleicaoIdDTO cargoEleicaoId) {
        cargoEleicaoId.validate();

        this.candidaturaRepository
            .findByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(cargoEleicaoId.getCodigoCargoTSE(), cargoEleicaoId.getCodigoEleicaoTSE())
            .forEach(candidatura -> this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByCandidaturaId(
                this.candidaturaMapper.toCandidaturaIdDTO(candidatura)
            ));

        this.candidaturaRepository.deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(cargoEleicaoId.getCodigoCargoTSE(), cargoEleicaoId.getCodigoEleicaoTSE());
    }

    public void deleteByNumeroPartidoTSE(int numeroPartidoTSE) {
        this.candidaturaRepository
            .findByPartidoNumeroTSE(numeroPartidoTSE)
            .forEach(candidatura -> this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByCandidaturaId(
                this.candidaturaMapper.toCandidaturaIdDTO(candidatura)
            ));

        this.candidaturaRepository.deleteByPartidoNumeroTSE(numeroPartidoTSE);
    }

    public void deleteByCodigoCandidatoTSE(String codigoCandidatoTSE) {
        this.candidaturaRepository
            .findByCandidatoCodigoTSEEqualsIgnoreCase(codigoCandidatoTSE)
            .forEach(candidatura -> this.apuracaoVotosCandidaturaBoletimUrnaService.deleteByCandidaturaId(
                    this.candidaturaMapper.toCandidaturaIdDTO(candidatura)
            ));

        this.candidaturaRepository.deleteByCandidatoCodigoTSEEqualsIgnoreCase(codigoCandidatoTSE);
    }
}
