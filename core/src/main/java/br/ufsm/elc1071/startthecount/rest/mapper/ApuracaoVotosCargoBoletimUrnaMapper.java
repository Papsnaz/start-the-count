package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCargoBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.ApuracaoVotosCargoBoletimUrna;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApuracaoVotosCargoBoletimUrnaMapper {

    private final CargoEleicaoMapper cargoEleicaoMapper;
    private final BoletimUrnaMapper boletimUrnaMapper;

    public ApuracaoVotosCargoBoletimUrnaRetrievalDTO toApuracaoVotosCargoBoletimUrnaRetrievalDTO(ApuracaoVotosCargoBoletimUrna apuracaoVotosCargoBoletimUrna) {
        return new ApuracaoVotosCargoBoletimUrnaRetrievalDTO(
            apuracaoVotosCargoBoletimUrna.getId(),
            this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(apuracaoVotosCargoBoletimUrna.getCargoEleicao()),
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(apuracaoVotosCargoBoletimUrna.getBoletimUrna()),
            apuracaoVotosCargoBoletimUrna.getQuantidadeEleitoresAptos(),
            apuracaoVotosCargoBoletimUrna.getQuantidadeComparecimentoCargoSemCandidatos(),
            apuracaoVotosCargoBoletimUrna.getQuantidadeVotosNominais(),
            apuracaoVotosCargoBoletimUrna.getQuantidadeVotosDeLegenda(),
            apuracaoVotosCargoBoletimUrna.getQuantidadeVotosEmBranco(),
            apuracaoVotosCargoBoletimUrna.getQuantidadeVotosNulos(),
            apuracaoVotosCargoBoletimUrna.getTotalVotosApurados()
        );
    }
}
