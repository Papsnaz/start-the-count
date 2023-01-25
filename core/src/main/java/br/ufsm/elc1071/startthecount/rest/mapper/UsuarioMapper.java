package br.ufsm.elc1071.startthecount.rest.mapper;

import br.ufsm.elc1071.startthecount.rest.dto.creation.UsuarioCreationDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.UsuarioRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.dto.update.UsuarioUpdateDTO;
import br.ufsm.elc1071.startthecount.rest.model.Usuario;
import br.ufsm.elc1071.startthecount.rest.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final TipoUsuarioService tipoUsuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioMapper(TipoUsuarioService tipoUsuarioService, @Lazy PasswordEncoder passwordEncoder) {
        this.tipoUsuarioService = tipoUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario toUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        return new Usuario(
            usuarioUpdateDTO.getUsername(),
            this.passwordEncoder.encode(usuarioUpdateDTO.getSenha()),
            usuarioUpdateDTO.getNome(),
            usuarioUpdateDTO.getSobrenome(),
            this.tipoUsuarioService.findByNome(usuarioUpdateDTO.getNomeTipoUsuario())
        );
    }

    public Usuario toUsuario(UsuarioCreationDTO usuarioCreationDTO) {
        return new Usuario(
            usuarioCreationDTO.getUsername(),
            this.passwordEncoder.encode(usuarioCreationDTO.getSenha()),
            usuarioCreationDTO.getNome(),
            usuarioCreationDTO.getSobrenome(),
            this.tipoUsuarioService.findByNome(usuarioCreationDTO.getNomeTipoUsuario())
        );
    }

    public UsuarioRetrievalDTO toUsuarioRetrievalDTO(Usuario usuario) {
        return new UsuarioRetrievalDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getNome(),
            usuario.getSobrenome(),
            usuario.getTipo(),
            usuario.getCriadoEm(),
            usuario.getAtualizadoEm()
        );
    }
}
