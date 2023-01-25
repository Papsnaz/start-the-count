package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.id.PermissaoTipoUsuarioIdDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.PermissaoTipoUsuario;
import br.ufsm.elc1071.startthecount.rest.repository.PermissaoTipoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissaoTipoUsuarioService {

    private final PermissaoTipoUsuarioRepository permissaoTipoUsuarioRepository;

    public PermissaoTipoUsuario findById(PermissaoTipoUsuarioIdDTO id) {
        return this.permissaoTipoUsuarioRepository
            .findByPermissaoIdAndTipoUsuarioId(
                id.getIdPermissao(),
                id.getIdTipoUsuario()
            )
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma instância de PermissaoTipoUsuario identificada por %s.", id));
            });
    }

    public List<PermissaoTipoUsuario> findAll() {
        return this.permissaoTipoUsuarioRepository.findAll();
    }
}
