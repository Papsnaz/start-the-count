package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoProcessoEleitoralRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.SecaoProcessoEleitoral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SecaoProcessoEleitoralMapper {

    private final LocalVotacaoMapper localVotacaoMapper;
    private final SecaoMapper secaoMapper;

    @Autowired
    public SecaoProcessoEleitoralMapper(@Lazy LocalVotacaoMapper localVotacaoMapper, SecaoMapper secaoMapper) {
        this.localVotacaoMapper = localVotacaoMapper;
        this.secaoMapper = secaoMapper;
    }

    public SecaoProcessoEleitoralRetrievalDTO toSecaoProcessoEleitoralRetrievalDTO(SecaoProcessoEleitoral secaoProcessoEleitoral) {
        return new SecaoProcessoEleitoralRetrievalDTO(
            secaoProcessoEleitoral.getId(),
            this.localVotacaoMapper.toLocalVotacaoRetrievalDTO(secaoProcessoEleitoral.getLocalVotacao()),
            this.secaoMapper.toSecaoRetrievalDTO(secaoProcessoEleitoral.getSecao()),
            secaoProcessoEleitoral.getProcessoEleitoral()
        );
    }
}
