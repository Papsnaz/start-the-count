package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.ProcessoEleitoral;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "localVotacao", "secao", "processoEleitoral"})
public class SecaoProcessoEleitoralRetrievalDTO {

    private Integer id;

    @JsonProperty(value = "localVotacao")
    private LocalVotacaoRetrievalDTO localVotacaoRetrievalDTO;

    @JsonProperty(value = "secao")
    private SecaoRetrievalDTO secaoRetrievalDTO;

    private ProcessoEleitoral processoEleitoral;
}
