package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.ProcessoEleitoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProcessoEleitoralRepository extends JpaRepository<ProcessoEleitoral, Integer> {

    Optional<ProcessoEleitoral> findByCodigoTSE(Integer codigoTSE);

    boolean existsByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);
}
