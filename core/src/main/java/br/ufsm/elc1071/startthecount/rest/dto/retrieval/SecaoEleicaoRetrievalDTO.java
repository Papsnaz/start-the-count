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
@JsonPropertyOrder(value = {"id", "secao", "eleicao", "quantidadeEleitoresAptos", "quantidadeEleitoresComparecentes", "quantidadeEleitoresFaltosos", "quantidadeEleitoresHabilitadosPorAnoNascimento"})
public class SecaoEleicaoRetrievalDTO {

    private Integer id;

    @JsonProperty(value = "secao")
    private SecaoRetrievalDTO secaoRetrievalDTO;

    private Eleicao eleicao;

    private Integer quantidadeEleitoresAptos;

    private Integer quantidadeEleitoresComparecentes;

    private Integer quantidadeEleitoresFaltosos;

    private Integer quantidadeEleitoresHabilitadosPorAnoNascimento;
}
