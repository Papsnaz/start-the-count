package br.ufsm.elc1071.startthecount.rest.dto.id;

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
@ToString(doNotUseGetters = true)
public class SecaoProcessoEleitoralIdDTO {

    @NotNull(message = "O número da seção não pode ser nulo.")
    private Integer numeroSecaoTSE;

    @NotNull(message = "O número da zona não pode ser nulo.")
    private Integer numeroZonaTSE;

    @NotBlank(message = "A sigla da UF não pode estar em branco.")
    @Size(min = 2, max = 2, message = "A sigla da UF deve ter 2 caracteres.")
    private String siglaUF;

    @NotNull(message = "O código do processo eleitoral não pode ser nulo.")
    private Integer codigoProcessoEleitoralTSE;

    public void validate() {
        if (
            Objects.isNull(this.numeroSecaoTSE) ||
            Objects.isNull(this.numeroZonaTSE) ||
            StringUtils.isBlank(this.siglaUF) ||
        Objects.isNull(this.codigoProcessoEleitoralTSE)
        ) {
            throw new CamposFaltantesException("O número da seção, número da zona, sigla da UF e o código do processo eleitoral devem ser informados para buscar uma instância de SecaoProcessoEleitoral.");
        }
    }
}
