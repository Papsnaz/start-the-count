package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.LocalVotacaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.LocalVotacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalVotacaoMapper {

    private final ZonaMapper zonaMapper;

    public LocalVotacaoRetrievalDTO toLocalVotacaoRetrievalDTO(LocalVotacao localVotacao) {
        return new LocalVotacaoRetrievalDTO(
            localVotacao.getId(),
            localVotacao.getNumeroTSE(),
            localVotacao.getNome(),
            this.zonaMapper.toZonaRetrievalDTO(localVotacao.getZona()),
            localVotacao.getMunicipio()
        );
    }

    public LocalVotacaoIdDTO toLocalVotacaoIdDTO(LocalVotacao localVotacao) {
        return new LocalVotacaoIdDTO(
            localVotacao.getNumeroTSE(),
            localVotacao.getZona().getNumeroTSE(),
            localVotacao.getZona().getUF().getSigla()
        );
    }
}
