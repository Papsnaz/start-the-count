package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.OrigemConfiguracaoProcessoEleitoral;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrigemConfiguracaoProcessoEleitoralRepository extends JpaRepository<OrigemConfiguracaoProcessoEleitoral, Integer> {

    boolean existsByAbreviacaoEqualsIgnoreCase(String abreviacao);

    Optional<OrigemConfiguracaoProcessoEleitoral> findByAbreviacaoEqualsIgnoreCase(String abreviacao);
}
