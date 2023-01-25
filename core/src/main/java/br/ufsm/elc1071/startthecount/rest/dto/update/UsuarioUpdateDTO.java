package br.ufsm.elc1071.startthecount.rest.dto.update;

import br.ufsm.elc1071.startthecount.rest.exception.CamposFaltantesException;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateDTO {

    @Size(min = 1, max = 31, message = "O username do usuário deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @Size(min = 1, max = 72, message = "A senha do usuário deve ter no mínimo 1 caractere e no máximo 72 caracteres.")
    private String senha;

    @Size(min = 1, max = 127, message = "O nome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String nome;

    @Size(min = 1, max = 127, message = "O sobrenome do usuário deve ter no mínimo 1 caractere e no máximo 127 caracteres.")
    private String sobrenome;

    @Size(min = 1, max = 7, message = "O nome do tipo de usuário deve ter no mínimo 1 caractere e no máximo 7 caracteres.")
    private String nomeTipoUsuario;

    public void validate() {
        if (
            StringUtils.isBlank(this.username) &&
            StringUtils.isBlank(this.senha) &&
            StringUtils.isBlank(this.nome) &&
            StringUtils.isBlank(this.sobrenome) &&
            StringUtils.isBlank(this.nomeTipoUsuario)
        ) {
            throw new CamposFaltantesException("O username, senha, nome, sobrenome, nome do tipo de usuário ou todos os campos mencionados anteriormente devem ser informados para atualizar um usuário.");
        }
    }
}
