package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.Secao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecaoMapper {

    private final ZonaMapper zonaMapper;

    public SecaoRetrievalDTO toSecaoRetrievalDTO(Secao secao) {
        return new SecaoRetrievalDTO(
            secao.getId(),
            secao.getNumeroTSE(),
            this.zonaMapper.toZonaRetrievalDTO(secao.getZona())
        );
    }
}
