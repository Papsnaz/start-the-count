package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.UrnaEletronica;
import br.ufsm.elc1071.startthecount.rest.repository.UrnaEletronicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrnaEletronicaService {

    private final UrnaEletronicaRepository urnaEletronicaRepository;

    public UrnaEletronica findByNumeroSerieTSE(int numeroSerieTSE) {
        return this.urnaEletronicaRepository
            .findByNumeroSerieTSE(numeroSerieTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhuma urna eletrônica com o número de série %d.", numeroSerieTSE));
            });
    }

    public List<UrnaEletronica> findAll() {
        return this.urnaEletronicaRepository.findAll();
    }

    public void saveIfDoesNotExist(UrnaEletronica urnaEletronica) {
        if (!this.urnaEletronicaRepository.existsByNumeroSerieTSE(urnaEletronica.getNumeroSerieTSE())) {
            this.urnaEletronicaRepository.save(urnaEletronica);
        }
    }
}
