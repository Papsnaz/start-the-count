package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Secao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SecaoRepository extends JpaRepository<Secao, Integer> {

    boolean existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<Secao> findByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    List<Secao> findByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);
}
