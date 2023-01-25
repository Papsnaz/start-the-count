package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.SecaoProcessoEleitoral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SecaoProcessoEleitoralRepository extends JpaRepository<SecaoProcessoEleitoral, Integer> {

    boolean existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoProcessoEleitoralTSE);

    Optional<SecaoProcessoEleitoral> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoProcessoEleitoralTSE);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndProcessoEleitoralCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoProcessoEleitoralTSE);

    @Modifying
    @Transactional
    void deleteByLocalVotacaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroLocalVotacaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteBySecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByProcessoEleitoralCodigoTSE(Integer codigoProcessoEleitoralTSE);
}
