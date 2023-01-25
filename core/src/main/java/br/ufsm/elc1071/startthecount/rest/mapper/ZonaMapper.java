package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ZonaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.Zona;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZonaMapper {

    public ZonaRetrievalDTO toZonaRetrievalDTO(Zona zona) {
        return new ZonaRetrievalDTO(
            zona.getId(),
            zona.getNumeroTSE(),
            zona.getUF()
        );
    }
}
