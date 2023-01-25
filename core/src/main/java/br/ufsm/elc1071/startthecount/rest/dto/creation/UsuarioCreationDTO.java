package br.ufsm.elc1071.startthecount.rest.dto.creation;

import br.ufsm.elc1071.startthecount.rest.exception.CamposFaltantesException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreationDTO {

    @NotBlank(message = "O username não pode estar em branco.")
    @Size(min = 1, max = 31, message = "O username deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotBlank(message = "A senha do usuário não pode estar em branco.")
    @Size(min = 1, max = 72, message = "A senha do usuário deve ter no mínimo 1 caractere e no máximo 72 caracteres.")
    private String senha;

    @Size(min = 1, max = 72, message = "O nome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String nome;

    @Size(min = 1, max = 72, message = "O sobrenome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String sobrenome;

    @NotBlank(message = "O nome do tipo de usuário não pode estar em branco.")
    @Size(min = 1, max = 7, message = "O nome do tipo de usuário deve ter no mínimo 1 caractere e no máximo 72 caracteres.")
    private String nomeTipoUsuario;

    public void validate() {
        if (StringUtils.isBlank(this.username) || StringUtils.isBlank(this.senha) || StringUtils.isBlank(this.nomeTipoUsuario)) {
            throw new CamposFaltantesException("O username, a senha e o nome do tipo de usuário devem ser informados para criar um usuário.");
        }
    }
}
