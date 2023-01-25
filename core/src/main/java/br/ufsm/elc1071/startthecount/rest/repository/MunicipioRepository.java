package br.ufsm.elc1071.startthecount.rest.repository;

import br.ufsm.elc1071.startthecount.rest.model.Municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    boolean existsByCodigoTSE(Integer codigoTSE);

    Optional<Municipio> findByCodigoTSE(Integer codigoTSE);

    List<Municipio> findAllByUfSiglaEqualsIgnoreCase(String siglaUF);

    @Modifying
    @Transactional
    void deleteByCodigoTSE(Integer codigoTSE);

    @Modifying
    @Transactional
    void deleteAllByUfSiglaEqualsIgnoreCase(String siglaUF);
}
