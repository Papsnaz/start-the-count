package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Candidatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer> {

    boolean existsByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    Optional<Candidatura> findByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    List<Candidatura> findByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    List<Candidatura> findByPartidoNumeroTSE(Integer numeroPartidoTSE);

    List<Candidatura> findByCandidatoCodigoTSEEqualsIgnoreCase(String codigoCandidatoTSE);

    @Modifying
    @Transactional
    void deleteByCandidatoNumeroTSEAndCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer numeroCandidatoTSE, Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteByCargoEleicaoCargoCodigoTSEAndCargoEleicaoEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteByPartidoNumeroTSE(Integer numeroPartidoTSE);

    @Modifying
    @Transactional
    void deleteByCandidatoCodigoTSEEqualsIgnoreCase(String codigoCandidatoTSE);
}
