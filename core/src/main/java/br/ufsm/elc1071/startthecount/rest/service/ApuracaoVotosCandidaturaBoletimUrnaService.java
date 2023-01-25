package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosCandidaturaBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.CandidaturaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCandidaturaBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosCandidaturaBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaBoletimUrnaService {

    private final ApuracaoVotosCandidaturaBoletimUrnaRepository apuracaoVotosCandidaturaBoletimUrnaRepository;

    public ApuracaoVotosCandidaturaBoletimUrna findById(ApuracaoVotosCandidaturaBoletimUrnaIdDTO id) {
        return this.apuracaoVotosCandidaturaBoletimUrnaRepository
            .findByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getNumeroCandidatoTSE(),
                id.getCodigoCargoTSE(),
                id.getCodigoEleicaoTSE(),
                id.getUsername(),
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de candidatura de boletim de urna identificada por %s.", id));
            });
    }

    public List<ApuracaoVotosCandidaturaBoletimUrna> findAll() {
        return this.apuracaoVotosCandidaturaBoletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(ApuracaoVotosCandidaturaBoletimUrna apuracaoVotosCandidaturaBoletimUrna) {
        if (!this.apuracaoVotosCandidaturaBoletimUrnaRepository.existsByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            apuracaoVotosCandidaturaBoletimUrna.getCandidatura().getCandidato().getNumeroTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getCandidatura().getCargoEleicao().getCargo().getCodigoTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getCandidatura().getCargoEleicao().getEleicao().getCodigoTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna().getUsuario().getUsername(),
            apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla()
        )) {
            this.apuracaoVotosCandidaturaBoletimUrnaRepository.save(apuracaoVotosCandidaturaBoletimUrna);
        }
    }

    public void deleteById(ApuracaoVotosCandidaturaBoletimUrnaIdDTO id) {
        id.validate();

        if (!this.apuracaoVotosCandidaturaBoletimUrnaRepository.existsByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getNumeroCandidatoTSE(),
            id.getCodigoCargoTSE(),
            id.getCodigoEleicaoTSE(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de candidatura de boletim de urna identificada por %s.", id));
        }

        this.apuracaoVotosCandidaturaBoletimUrnaRepository.deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getNumeroCandidatoTSE(),
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

        this.apuracaoVotosCandidaturaBoletimUrnaRepository.deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            boletimUrnaId.getUsername(),
            boletimUrnaId.getCodigoPleitoTSE(),
            boletimUrnaId.getNumeroSecaoTSE(),
            boletimUrnaId.getNumeroZonaTSE(),
            boletimUrnaId.getSiglaUF()
        );
    }

    public void deleteByCandidaturaId(CandidaturaIdDTO candidaturaId) {
        candidaturaId.validate();

        this.apuracaoVotosCandidaturaBoletimUrnaRepository.deleteByCandidaturaCandidatoNumeroTSEAndCandidaturaCargoEleicaoCargoCodigoTSEAndCandidaturaCargoEleicaoEleicaoCodigoTSE(
            candidaturaId.getNumeroCandidatoTSE(),
            candidaturaId.getCodigoCargoTSE(),
            candidaturaId.getCodigoEleicaoTSE()
        );
    }
}
