package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.LocalVotacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LocalVotacaoRepository extends JpaRepository<LocalVotacao, Integer> {

    boolean existsByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<LocalVotacao> findByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    List<LocalVotacao> findByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    List<LocalVotacao> findByMunicipioCodigoTSE(Integer codigoMunicipioTSE);

    @Modifying
    @Transactional
    void deleteByNumeroTSEAndZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByZonaNumeroTSEAndZonaUfSiglaEqualsIgnoreCase(Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByMunicipioCodigoTSE(Integer codigoMunicipioTSE);
}
