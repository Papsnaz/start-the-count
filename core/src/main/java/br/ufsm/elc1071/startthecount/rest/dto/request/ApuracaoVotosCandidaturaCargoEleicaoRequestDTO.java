package br.ufsm.elc1071.startthecount.rest.dto.request;

import br.ufsm.elc1071.startthecount.rest.exception.CamposFaltantesException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApuracaoVotosCandidaturaCargoEleicaoRequestDTO {

    @NotBlank(message = "O nome do cargo deve ser informado.")
    @Size(min = 1, max = 31, message = "O nome do cargo deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String nomeCargo;

    @NotNull(message = "O código da eleição deve ser informado.")
    private Integer codigoEleicaoTSE;

    public void validate() {
        if (StringUtils.isBlank(this.nomeCargo) || Objects.isNull(this.codigoEleicaoTSE)) {
            throw new CamposFaltantesException("O nome do cargo e o código da eleição devem ser informados para buscar uma apuração de votos de candidatura.");
        }
    }
}
