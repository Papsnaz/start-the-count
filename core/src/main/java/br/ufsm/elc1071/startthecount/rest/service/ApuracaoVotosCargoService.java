package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosCargoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosCargoService {

    private final ApuracaoVotosCargoBoletimUrnaRepository apuracaoVotosCargoBoletimUrnaRepository;

    public List<ApuracaoVotosCargoRetrievalDTO> findAll() {
        return this.apuracaoVotosCargoBoletimUrnaRepository.findApuracaoVotosCargos();
    }
}
