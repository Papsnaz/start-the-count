package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.PermissaoTipoUsuarioRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.model.PermissaoTipoUsuario;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissaoTipoUsuarioMapper {

    public PermissaoTipoUsuarioRetrievalDTO toPermissaoTipoUsuarioRetrievalDTO(PermissaoTipoUsuario permissaoTipoUsuario) {
        return new PermissaoTipoUsuarioRetrievalDTO(
            permissaoTipoUsuario.getId(),
            permissaoTipoUsuario.getPermissao(),
            permissaoTipoUsuario.getTipoUsuario()
        );
    }
}
