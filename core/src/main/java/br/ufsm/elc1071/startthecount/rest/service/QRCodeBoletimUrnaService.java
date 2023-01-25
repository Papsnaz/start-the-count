package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.QRCodeBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.QRCodeBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.repository.QRCodeBoletimUrnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QRCodeBoletimUrnaService {

    private final QRCodeBoletimUrnaRepository qrCodeBoletimUrnaRepository;

    public QRCodeBoletimUrna findById(QRCodeBoletimUrnaIdDTO id) {
        return this.qrCodeBoletimUrnaRepository
            .findByIndiceAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
                id.getIndice(),
                id.getUsername(),
                id.getCodigoPleitoTSE(),
                id.getNumeroSecaoTSE(),
                id.getNumeroZonaTSE(),
                id.getSiglaUF()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum QR code de boletim de urna identificado por %s.", id));
            });
    }

    public List<QRCodeBoletimUrna> findAll() {
        return this.qrCodeBoletimUrnaRepository.findAll();
    }

    public void saveIfDoesNotExist(QRCodeBoletimUrna qrCodeBoletimUrna) {
        if (!this.qrCodeBoletimUrnaRepository.existsByIndiceAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            qrCodeBoletimUrna.getIndice(),
            qrCodeBoletimUrna.getBoletimUrna().getUsuario().getUsername(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla()
        )) {
            this.qrCodeBoletimUrnaRepository.save(qrCodeBoletimUrna);
        }
    }

    public void deleteById(QRCodeBoletimUrnaIdDTO id) {
        id.validate();

        if (!this.qrCodeBoletimUrnaRepository.existsByIndiceAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getIndice(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        )) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum QR code de boletim de urna identificado por %s.", id));
        }

        this.qrCodeBoletimUrnaRepository.deleteByIndiceAndBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            id.getIndice(),
            id.getUsername(),
            id.getCodigoPleitoTSE(),
            id.getNumeroSecaoTSE(),
            id.getNumeroZonaTSE(),
            id.getSiglaUF()
        );
    }

    public void deleteByBoletimUrnaId(BoletimUrnaIdDTO boletimUrnaId) {
        boletimUrnaId.validate();

        this.qrCodeBoletimUrnaRepository.deleteByBoletimUrnaUsuarioUsernameEqualsIgnoreCaseAndBoletimUrnaSecaoPleitoPleitoCodigoTSEAndBoletimUrnaSecaoPleitoSecaoNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaNumeroTSEAndBoletimUrnaSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(
            boletimUrnaId.getUsername(),
            boletimUrnaId.getCodigoPleitoTSE(),
            boletimUrnaId.getNumeroSecaoTSE(),
            boletimUrnaId.getNumeroZonaTSE(),
            boletimUrnaId.getSiglaUF()
        );
    }
}
