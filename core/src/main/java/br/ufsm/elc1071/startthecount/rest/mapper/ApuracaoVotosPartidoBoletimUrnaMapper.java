package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosPartidoBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosPartidoBoletimUrna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApuracaoVotosPartidoBoletimUrnaMapper {

    private final BoletimUrnaMapper boletimUrnaMapper;

    public ApuracaoVotosPartidoBoletimUrnaRetrievalDTO toApuracaoVotosPartidoBoletimUrnaRetrievalDTO(ApuracaoVotosPartidoBoletimUrna apuracaoVotosPartidoBoletimUrna) {
        return new ApuracaoVotosPartidoBoletimUrnaRetrievalDTO(
            apuracaoVotosPartidoBoletimUrna.getId(),
            apuracaoVotosPartidoBoletimUrna.getPartido(),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(apuracaoVotosPartidoBoletimUrna.getBoletimUrna()),
            apuracaoVotosPartidoBoletimUrna.getEleicao(),
            apuracaoVotosPartidoBoletimUrna.getQuantidadeVotosDeLegenda(),
            apuracaoVotosPartidoBoletimUrna.getTotalVotosApurados()
        );
    }
}
