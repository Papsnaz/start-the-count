package br.ufsm.elc1071.startthecount.rest.dto.response;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.BoletimUrnaRetrievalDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoletimUrnaBuildResponseDTO {

    private String message;

    @JsonProperty(value = "boletimUrna")
    private BoletimUrnaRetrievalDTO boletimUrnaRetrievalDTO;
}
