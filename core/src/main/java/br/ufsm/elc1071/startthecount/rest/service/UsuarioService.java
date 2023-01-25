package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.creation.UsuarioCreationDTO;
import br.ufsm.elc1071.startthecount.rest.dto.update.UsuarioUpdateDTO;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeJaExisteException;
import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.mapper.UsuarioMapper;
import br.ufsm.elc1071.startthecount.rest.model.Usuario;
import br.ufsm.elc1071.startthecount.rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final BoletimUrnaService boletimUrnaService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, @Lazy BoletimUrnaService boletimUrnaService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.boletimUrnaService = boletimUrnaService;
    }

    public Usuario findByUsername(String username) {
        return this.usuarioRepository
            .findByUsernameEqualsIgnoreCase(username)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum usuário com o username \"%s\".", username));
            });
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public void updateByUsername(String username, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = this.usuarioRepository
            .findByUsernameEqualsIgnoreCase(username)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum usuário com o username \"%s\".", username));
            });

        usuarioUpdateDTO.validate();

        usuario.update(this.usuarioMapper.toUsuario(usuarioUpdateDTO));

        this.usuarioRepository.save(usuario);
    }

    public Usuario save(UsuarioCreationDTO usuarioCreationDTO) {
        usuarioCreationDTO.validate();

        if (this.usuarioRepository.existsByUsernameEqualsIgnoreCase(usuarioCreationDTO.getUsername())) {
            throw new EntidadeJaExisteException(String.format("Já existe um usuário com o username \"%s\".", usuarioCreationDTO.getUsername()));
        }

        return this.usuarioRepository.save(this.usuarioMapper.toUsuario(usuarioCreationDTO));
    }

    public void saveIfDoesNotExist(Usuario usuario) {
        if (!this.usuarioRepository.existsByUsernameEqualsIgnoreCase(usuario.getUsername())) {
            this.usuarioRepository.save(usuario);
        }
    }

    public void deleteByUsername(String username) {
        if (!this.usuarioRepository.existsByUsernameEqualsIgnoreCase(username)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum usuário com o username \"%s\".", username));
        }

        this.boletimUrnaService.deleteByUsername(username);

        this.usuarioRepository.deleteByUsernameEqualsIgnoreCase(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usuarioRepository
            .findByUsernameEqualsIgnoreCase(username)
            .orElseThrow(() -> {
                throw new UsernameNotFoundException(String.format("Não foi encontrado nenhum usuário com o username \"%s\".", username));
            });
    }
}
