package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.*;

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
@JsonPropertyOrder(value = {"id", "partido", "boletimUrna", "quantidadeVotosDeLegenda", "totalVotosApurados"})
public class ApuracaoVotosPartidoBoletimUrnaRetrievalDTO {

    private Integer id;

    private Partido partido;

    @JsonProperty(value = "boletimUrna")
    private BoletimUrnaRetrievalDTO boletimUrnaRetrievalDTO;

    private Eleicao eleicao;

    private Integer quantidadeVotosDeLegenda;

    private Integer totalVotosApurados;
}
