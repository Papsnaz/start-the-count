package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.PermissaoTipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PermissaoTipoUsuarioRepository extends JpaRepository<PermissaoTipoUsuario, Integer> {

    boolean existsByPermissaoIdAndTipoUsuarioId(Integer idPermissao, Integer idTipoUsuario);

    Optional<PermissaoTipoUsuario> findByPermissaoIdAndTipoUsuarioId(Integer idPermissao, Integer idTipoUsuario);

    @Modifying
    @Transactional
    void deleteByPermissaoIdAndTipoUsuarioId(Integer idPermissao, Integer idTipoUsuario);
}
