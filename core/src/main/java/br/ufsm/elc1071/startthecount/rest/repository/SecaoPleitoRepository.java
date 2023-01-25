package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.SecaoPleito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SecaoPleitoRepository extends JpaRepository<SecaoPleito, Integer> {

    boolean existsBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoPleitoTSE);

    Optional<SecaoPleito> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoPleitoTSE);

    List<SecaoPleito> findBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    List<SecaoPleito> findByPleitoCodigoTSE(Integer codigoPleitoTSE);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCaseAndPleitoCodigoTSE(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF, Integer codigoPleitoTSE);

    @Modifying
    @Transactional
    void deleteBySecaoNumeroTSEAndSecaoZonaNumeroTSEAndSecaoZonaUfSiglaEqualsIgnoreCase(Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByPleitoCodigoTSE(Integer codigoPleitoTSE);
}
