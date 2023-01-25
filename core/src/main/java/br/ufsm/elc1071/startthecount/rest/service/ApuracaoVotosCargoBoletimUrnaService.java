package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosCargoBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.CargoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCargoBoletimUrna;

import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosCargoBoletimUrnaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosCargoBoletimUrnaService {

    private final ApuracaoVotosCargoBoletimUrnaRepository apuracaoVotosCargoBoletimUrnaRepository;

    public ApuracaoVotosCargoBoletimUrna findById(ApuracaoVotosCargoBoletimUrnaIdDTO id) {
        return this.apuracaoVotosCargoBoletimUrnaRepository
            .findByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getCodigoCargoTSE(),
                id.getCodigoEleicaoTSE(),
                id.getUsername(),
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de cargo de boletim de urna identificada por %s.", id));
            });
    }

    public List<ApuracaoVotosCargoBoletimUrna> findAll() {
        return this.apuracaoVotosCargoBoletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(ApuracaoVotosCargoBoletimUrna apuracaoVotosCargoBoletimUrna) {
        if (!this.apuracaoVotosCargoBoletimUrnaRepository.existsByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            apuracaoVotosCargoBoletimUrna.getCargoEleicao().getCargo().getCodigoTSE(),
            apuracaoVotosCargoBoletimUrna.getCargoEleicao().getEleicao().getCodigoTSE(),
            apuracaoVotosCargoBoletimUrna.getBoletimUrna().getUsuario().getUsername(),
            apuracaoVotosCargoBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE(),
            apuracaoVotosCargoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            apuracaoVotosCargoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            apuracaoVotosCargoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla()
        )) {
            this.apuracaoVotosCargoBoletimUrnaRepository.save(apuracaoVotosCargoBoletimUrna);
        }
    }

    public void deleteById(ApuracaoVotosCargoBoletimUrnaIdDTO id) {
        id.validate();

        if (!this.apuracaoVotosCargoBoletimUrnaRepository.existsByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getCodigoCargoTSE(),
            id.getCodigoEleicaoTSE(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de cargo de boletim de urna identificada por %s.", id));
        }

        this.apuracaoVotosCargoBoletimUrnaRepository.deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getCodigoCargoTSE(),
            id.getCodigoEleicaoTSE(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        );
    }

    public void deleteByBoletimUrnaId(BoletimUrnaIdDTO boletimUrnaId) {
        boletimUrnaId.validate();

        this.apuracaoVotosCargoBoletimUrnaRepository.deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            boletimUrnaId.getUsername(),
            boletimUrnaId.getCodigoPleitoTSE(),
            boletimUrnaId.getNumeroSecaoTSE(),
            boletimUrnaId.getNumeroZonaTSE(),
            boletimUrnaId.getSiglaUF()
        );
    }

    public void deleteByCargoEleicaoId(CargoEleicaoIdDTO cargoEleicaoId) {
        cargoEleicaoId.validate();

        this.apuracaoVotosCargoBoletimUrnaRepository.deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(
            cargoEleicaoId.getCodigoCargoTSE(),
            cargoEleicaoId.getCodigoEleicaoTSE()
        );
    }
}
