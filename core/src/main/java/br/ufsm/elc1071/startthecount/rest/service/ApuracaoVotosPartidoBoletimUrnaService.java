package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosPartidoBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosPartidoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosPartidoBoletimUrnaService {

    private final ApuracaoVotosPartidoBoletimUrnaRepository apuracaoVotosPartidoBoletimUrnaRepository;

    public ApuracaoVotosPartidoBoletimUrna findById(ApuracaoVotosPartidoBoletimUrnaIdDTO id) {
        return this.apuracaoVotosPartidoBoletimUrnaRepository
            .findByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getNumeroPartidoTSE(),
                id.getCodigoEleicaoTSE(),
                id.getUsername(),
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de partido de boletim de urna identificada por %s.", id));
            });
    }

    public List<ApuracaoVotosPartidoBoletimUrna> findAll() {
        return this.apuracaoVotosPartidoBoletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna) {
        if (!this.apuracaoVotosPartidoBoletimUrnaRepository.existsByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            apuracaoVotosPartidoBoletimUrna.getPartido().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getEleicao().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getUsuario().getUsername(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            apuracaoVotosPartidoBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla()
        )) {
            this.apuracaoVotosPartidoBoletimUrnaRepository.save(apuracaoVotosPartidoBoletimUrna);
        }
    }

    public void deleteById(ApuracaoVotosPartidoBoletimUrnaIdDTO id) {
        id.validate();

        if (!this.apuracaoVotosPartidoBoletimUrnaRepository.existsByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getNumeroPartidoTSE(),
            id.getCodigoEleicaoTSE(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma apuração de votos de partido de boletim de urna identificada por %s.", id));
        }

        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByPartidoNumeroTSEAndEleicaoCodigoTSEAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getNumeroPartidoTSE(),
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

        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            boletimUrnaId.getUsername(),
            boletimUrnaId.getCodigoPleitoTSE(),
            boletimUrnaId.getNumeroSecaoTSE(),
            boletimUrnaId.getNumeroZonaTSE(),
            boletimUrnaId.getSiglaUF()
        );
    }

    public void deleteByNumeroPartidoTSE(int numeroPartidoTSE) {
        this.apuracaoVotosPartidoBoletimUrnaRepository.deleteByPartidoNumeroTSE(numeroPartidoTSE);
    }
}
