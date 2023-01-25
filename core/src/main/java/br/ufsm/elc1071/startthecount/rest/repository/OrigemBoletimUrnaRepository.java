package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.OrigemBoletimUrna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrigemBoletimUrnaRepository extends JpaRepository<OrigemBoletimUrna, Integer> {

    boolean existsByAbreviacaoEqualsIgnoreCase(String abreviacao);

    Optional<OrigemBoletimUrna> findByAbreviacaoEqualsIgnoreCase(String abreviacao);
}
