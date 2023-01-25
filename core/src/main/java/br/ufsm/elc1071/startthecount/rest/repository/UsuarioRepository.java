package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsernameEqualsIgnoreCase(String username);

    Optional<Usuario> findByUsernameEqualsIgnoreCase(String username);

    @Modifying
    @Transactional
    void deleteByUsernameEqualsIgnoreCase(String username);
}
