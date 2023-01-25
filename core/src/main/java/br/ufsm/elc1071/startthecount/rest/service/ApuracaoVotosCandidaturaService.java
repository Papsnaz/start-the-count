package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosCandidaturaBoletimUrnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosCandidaturaService {

    private final ApuracaoVotosCandidaturaBoletimUrnaRepository apuracaoVotosCandidaturaBoletimUrnaRepository;

    public List<ApuracaoVotosCandidaturaRetrievalDTO> findAll() {
        return this.apuracaoVotosCandidaturaBoletimUrnaRepository.findApuracaoVotosCandidaturas();
    }
}
