package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.BoletimUrna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoletimUrnaRepository extends JpaRepository<BoletimUrna, Integer> {

    boolean existsByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    Optional<BoletimUrna> findByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    List<BoletimUrna> findBySecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    List<BoletimUrna> findByUsuarioUsernameEqualsIgnoreCase(String username);

    List<BoletimUrna> findByFaseNomeEqualsIgnoreCase(String nomeFase);

    @Modifying
    @Transactional
    void deleteByUsuarioUsernameEqualsIgnoreCaseAndSecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(String username, Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteBySecaoPleitoPleitoCodigoTSEAndSecaoPleitoSecaoNumeroTSEAndSecaoPleitoSecaoZonaNumeroTSEAndSecaoPleitoSecaoZonaUfSiglaEqualsIgnoreCase(Integer codigoPleitoTSE, Integer numeroSecaoTSE, Integer numeroZonaTSE, String siglaUF);

    @Modifying
    @Transactional
    void deleteByUsuarioUsernameEqualsIgnoreCase(String username);

    @Modifying
    @Transactional
    void deleteByFaseNomeEqualsIgnoreCase(String nomeFase);
}
