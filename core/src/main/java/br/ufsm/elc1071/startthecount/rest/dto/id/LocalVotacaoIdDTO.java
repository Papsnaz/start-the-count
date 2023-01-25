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
public class LocalVotacaoIdDTO {

    @NotNull(message = "O número do local de votação não pode ser nulo.")
    private Integer numeroLocalVotacaoTSE;

    @NotNull(message = "O número da zona não pode ser nulo.")
    private Integer numeroZonaTSE;

    @NotBlank(message = "A sigla da UF não pode estar em branco.")
    @Size(min = 2, max = 2, message = "A sigla da UF deve ter 2 caracteres.")
    private String siglaUF;

    public void validate() {
        if (Objects.isNull(this.numeroLocalVotacaoTSE) || Objects.isNull(this.numeroZonaTSE) || StringUtils.isBlank(this.siglaUF)) {
            throw new CamposFaltantesException("O número do local de votação, número da zona e a sigla da UF devem ser informados para buscar um local de votação.");
        }
    }
}
