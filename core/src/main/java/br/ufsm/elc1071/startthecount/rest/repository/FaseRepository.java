package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Fase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaseRepository extends JpaRepository<Fase, Integer> {

    boolean existsByNomeEqualsIgnoreCase(String nome);

    Optional<Fase> findByNomeEqualsIgnoreCase(String nome);

    Optional<Fase> findByCodigoTSE(Integer codigoTSE);

    void deleteByNomeEqualsIgnoreCase(String nome);
}
