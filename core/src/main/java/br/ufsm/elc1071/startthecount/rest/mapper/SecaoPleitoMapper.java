package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoPleitoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoPleitoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.SecaoPleito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecaoPleitoMapper {

    private final SecaoMapper secaoMapper;

    public SecaoPleitoRetrievalDTO toSecaoPleitoRetrievalDTO(SecaoPleito secaoPleito) {
        return new SecaoPleitoRetrievalDTO(
            secaoPleito.getId(),
            this.secaoMapper.toSecaoRetrievalDTO(secaoPleito.getSecao()),
            secaoPleito.getPleito()
        );
    }

    public SecaoPleitoIdDTO toSecaoPleitoIdDTO(SecaoPleito secaoPleito) {
        return new SecaoPleitoIdDTO(
            secaoPleito.getSecao().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getNumeroTSE(),
            secaoPleito.getSecao().getZona().getUF().getSigla(),
            secaoPleito.getPleito().getCodigoTSE()
        );
    }
}
