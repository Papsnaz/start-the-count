package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Pleito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PleitoRepository extends JpaRepository<Pleito, Integer> {

    Optional<Pleito> findByCodigoTSE(Integer codigoTSE);

    boolean existsByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteByProcessoEleitoralCodigoTSE(Integer codigoProcessoEleitoralTSE);
}
