package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.Municipio;

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
@JsonPropertyOrder(value = {"id", "numeroTSE", "nome", "zona", "municipio"})
public class LocalVotacaoRetrievalDTO {

    private Integer id;

    private Integer numeroTSE;

    private String nome;

    @JsonProperty(value = "zona")
    private ZonaRetrievalDTO zonaRetrievalDTO;

    private Municipio municipio;
}
