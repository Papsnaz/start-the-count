package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.QRCodeBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.QRCodeBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.QRCodeBoletimUrna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QRCodeBoletimUrnaMapper {

    private final BoletimUrnaMapper boletimUrnaMapper;

    public QRCodeBoletimUrnaRetrievalDTO toQRCodeBoletimUrnaRetrievalDTO(QRCodeBoletimUrna qrCodeBoletimUrna) {
        return new QRCodeBoletimUrnaRetrievalDTO(
            qrCodeBoletimUrna.getId(),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(qrCodeBoletimUrna.getBoletimUrna()),
            qrCodeBoletimUrna.getCabecalho(),
            qrCodeBoletimUrna.getConteudo(),
            qrCodeBoletimUrna.getHash(),
            qrCodeBoletimUrna.getIndice(),
            qrCodeBoletimUrna.getNumeroCiclosEleitoraisDesdeImplementacao(),
            qrCodeBoletimUrna.getNumeroRevisoesFormatoCiclo(),
            qrCodeBoletimUrna.getNumeroVersaoChaveAssinatura()
        );
    }

    public QRCodeBoletimUrnaIdDTO toQRCodeBoletimUrnaIdDTO(QRCodeBoletimUrna qrCodeBoletimUrna) {
        return new QRCodeBoletimUrnaIdDTO(
            qrCodeBoletimUrna.getIndice(),
            qrCodeBoletimUrna.getBoletimUrna().getUsuario().getUsername(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getPleito().getCodigoTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getNumeroTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getNumeroTSE(),
            qrCodeBoletimUrna.getBoletimUrna().getSecaoPleito().getSecao().getZona().getUF().getSigla()
        );
    }
}
