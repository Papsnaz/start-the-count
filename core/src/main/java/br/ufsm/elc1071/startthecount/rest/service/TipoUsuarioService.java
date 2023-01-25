package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.TipoUsuario;
import br.ufsm.elc1071.startthecount.rest.repository.TipoUsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuario findByNome(String nome) {
        return this.tipoUsuarioRepository
            .findByNomeEqualsIgnoreCase(nome)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum tipo de usuário com o nome \"%s\".", nome));
            });
    }

    public List<TipoUsuario> findAll() {
        return this.tipoUsuarioRepository.findAll();
    }
}
