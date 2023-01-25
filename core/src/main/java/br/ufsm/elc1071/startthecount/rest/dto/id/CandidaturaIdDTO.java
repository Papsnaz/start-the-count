package br.ufsm.elc1071.startthecount.rest.dto.id;

import br.ufsm.elc1071.startthecount.rest.exception.CamposFaltantesException;

import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class CandidaturaIdDTO {

    @NotNull(message = "O número do candidato não pode ser nulo.")
    private Integer numeroCandidatoTSE;

    @NotNull(message = "O código do cargo não pode ser nulo.")
    private Integer codigoCargoTSE;

    @NotNull(message = "O código da eleição não pode ser nulo.")
    private Integer codigoEleicaoTSE;

    public void validate() {
        if (
            Objects.isNull(this.numeroCandidatoTSE) ||
            Objects.isNull(this.codigoCargoTSE) ||
            Objects.isNull(this.codigoEleicaoTSE)
        ) {
            throw new CamposFaltantesException("O número do candidato, código do cargo e o código da eleição devem ser informados para buscar uma candidatura.");
        }
    }
}
