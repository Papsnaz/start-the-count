package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.CargoEleicao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CargoEleicaoRepository extends JpaRepository<CargoEleicao, Integer> {

    boolean existsByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    Optional<CargoEleicao> findByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    List<CargoEleicao> findByCargoCodigoTSE(Integer codigoCargoTSE);

    List<CargoEleicao> findByEleicaoCodigoTSE(Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteByCargoCodigoTSEAndEleicaoCodigoTSE(Integer codigoCargoTSE, Integer codigoEleicaoTSE);

    @Modifying
    @Transactional
    void deleteByCargoCodigoTSE(Integer codigoCargoTSE);

    @Modifying
    @Transactional
    void deleteByEleicaoCodigoTSE(Integer codigoEleicaoTSE);
}
