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
public class PermissaoTipoUsuarioIdDTO {

    @NotNull(message = "O ID da permissão não pode ser nulo.")
    private Integer idPermissao;

    @NotNull(message = "O ID do tipo de usuário não pode ser nulo.")
    private Integer idTipoUsuario;

    public void validate() {
        if (Objects.isNull(this.idPermissao) || Objects.isNull(this.idTipoUsuario)) {
            throw new CamposFaltantesException("O ID da permissão e o ID do tipo de usuário devem ser informados para buscar uma instância de PermissaoTipoUsuario.");
        }
    }
}
