package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Candidato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    Optional<Candidato> findByCodigoTSEEqualsIgnoreCase(String codigoTSE);

    Optional<Candidato> findByNumeroTSE(int numeroTSE);

    boolean existsByCodigoTSEEqualsIgnoreCase(String codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSEEqualsIgnoreCase(String codigoTSE);
}
