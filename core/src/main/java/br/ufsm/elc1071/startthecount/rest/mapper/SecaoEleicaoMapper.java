package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoEleicaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.SecaoEleicao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecaoEleicaoMapper {

    private final SecaoMapper secaoMapper;

    public SecaoEleicaoRetrievalDTO toSecaoEleicaoRetrievalDTO(SecaoEleicao secaoEleicao) {
        return new SecaoEleicaoRetrievalDTO(
            secaoEleicao.getId(),
            this.secaoMapper.toSecaoRetrievalDTO(secaoEleicao.getSecao()),
            secaoEleicao.getEleicao(),
            secaoEleicao.getQuantidadeEleitoresAptos(),
            secaoEleicao.getQuantidadeEleitoresComparecentes(),
            secaoEleicao.getQuantidadeEleitoresFaltosos(),
            secaoEleicao.getQuantidadeEleitoresHabilitadosPorAnoNascimento()
        );
    }

    public SecaoEleicaoIdDTO toSecaoEleicaoIdDTO(SecaoEleicao secaoEleicao) {
        return new SecaoEleicaoIdDTO(
            secaoEleicao.getSecao().getNumeroTSE(),
            secaoEleicao.getSecao().getZona().getNumeroTSE(),
            secaoEleicao.getSecao().getZona().getUF().getSigla(),
            secaoEleicao.getEleicao().getCodigoTSE()
        );
    }
}
