package br.ufsm.elc1071.startthecount.rest.dto.retrieval;

import br.ufsm.elc1071.startthecount.rest.model.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"id", "cargo", "eleicao"})
public class CargoEleicaoRetrievalDTO {

    private Integer id;

    private Cargo cargo;

    private Eleicao eleicao;
}
