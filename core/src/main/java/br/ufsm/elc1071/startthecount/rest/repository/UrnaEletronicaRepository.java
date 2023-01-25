package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.UrnaEletronica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UrnaEletronicaRepository extends JpaRepository<UrnaEletronica, Integer> {

    Optional<UrnaEletronica> findByNumeroSerieTSE(Integer numeroSerieTSE);

    boolean existsByNumeroSerieTSE(Integer numeroSerieTSE);

    @Modifying
    @Transactional
    void deleteByNumeroSerieTSE(Integer numeroSerieTSE);
}
