package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.SecaoEleicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SecaoEleicaoRepository extends JpaRepository<SecaoEleicao, Integer> {

    boolean existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoEleicaoTSE);

    Optional<SecaoEleicao> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoEleicaoTSE);

    List<SecaoEleicao> findBySecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    List<SecaoEleicao> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    List<SecaoEleicao> findByEleicaoCodigoTSE(Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndEleicaoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteBySecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByEleicaoCodigoTSE(Integer codigoEleicaoTSE);
}

