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
public class QRCodeBoletimUrnaIdDTO {

    @NotNull(message = "O índice do QR code deve ser informado.")
    private Integer indice;

    @NotBlank(message = "O username não pode estar em branco.")
    @Size(min = 1, max = 31, message = "O username deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotNull(message = "O código do pleito não pode ser nulo.")
    private Integer codigoPleitoTSE;

    @NotNull(message = "O número da seção não pode ser nulo.")
    private Integer numeroSecaoTSE;

    @NotNull(message = "O número da zona não pode ser nulo.")
    private Integer numeroZonaTSE;

    @NotBlank(message = "A sigla da UF não pode estar em branco.")
    @Size(min = 2, max = 2, message = "A sigla da UF deve ter 2 caracteres.")
    private String siglaUF;

    public void validate() {
        if (
            Objects.isNull(this.indice) ||
            StringUtils.isBlank(this.username) ||
            Objects.isNull(this.codigoPleitoTSE) ||
            Objects.isNull(this.numeroSecaoTSE) ||
            Objects.isNull(this.numeroZonaTSE) ||
            StringUtils.isBlank(this.siglaUF)
        ) {
            throw new CamposFaltantesException("O índice do QR code, username, código do pleito, número da seção, número da zona e a sigla da UF devem ser informados para buscar um QR code de boletim de urna.");
        }
    }
}
