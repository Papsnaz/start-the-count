package br.ufsm.elc1071.startthecount.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequestDTO {

    @NotBlank(message = "O username deve ser informado.")
    @Size(min = 1, max = 31, message = "O username deve ter no mínimo 1 caractere e no máximo 31 caracteres.")
    private String username;

    @NotBlank(message = "A senha do usuário deve ser informada.")
    @Size(min = 1, max = 72, message = "A senha do usuário deve ter no mínimo 1 caractere e no máximo 72 caracteres.")
    private String senha;
}
