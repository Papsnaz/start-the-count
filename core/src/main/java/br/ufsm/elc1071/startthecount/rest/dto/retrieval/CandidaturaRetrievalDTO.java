package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.Candidato;
import br.ufsm.elc1071.startthecount.rest.model.Partido;
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
@JsonPropertyOrder(value = {"id", "candidato", "cargoEleicao", "partido"})
public class CandidaturaRetrievalDTO {

    private Integer id;

    private Candidato candidato;

    @JsonProperty(value = "cargoEleicao")
    private CargoEleicaoRetrievalDTO cargoEleicaoRetrievalDTO;

    private Partido partido;
}
