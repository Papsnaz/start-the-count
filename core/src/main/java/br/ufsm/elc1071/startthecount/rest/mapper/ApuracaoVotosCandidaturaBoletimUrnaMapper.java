package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCandidaturaBoletimUrna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaBoletimUrnaMapper {

    private final CandidaturaMapper candidaturaMapper;
    private final BoletimUrnaMapper boletimUrnaMapper;

    public ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO(ApuracaoVotosCandidaturaBoletimUrna apuracaoVotosCandidaturaBoletimUrna) {
        return new ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO(
            apuracaoVotosCandidaturaBoletimUrna.getId(),
            this.candidaturaMapper.toCandidaturaRetrievalDTO(apuracaoVotosCandidaturaBoletimUrna.getCandidatura()),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(apuracaoVotosCandidaturaBoletimUrna.getBoletimUrna()),
            apuracaoVotosCandidaturaBoletimUrna.getTotalVotosApurados()
        );
    }
}
