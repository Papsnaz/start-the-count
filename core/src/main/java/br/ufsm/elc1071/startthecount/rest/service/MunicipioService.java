package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Municipio;
import br.ufsm.elc1071.startthecount.rest.repository.MunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    public Municipio findByCodigoTSE(int codigoTSE) {
        return this.municipioRepository
            .findByCodigoTSE(codigoTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum município com o código %d.", codigoTSE));
            });
    }

    public List<Municipio> findAll() {
        return this.municipioRepository.findAll();
    }

    public void saveIfDoesNotExist(Municipio municipio) {
        if (!this.municipioRepository.existsByCodigoTSE(municipio.getCodigoTSE())) {
            this.municipioRepository.save(municipio);
        }
    }
}
