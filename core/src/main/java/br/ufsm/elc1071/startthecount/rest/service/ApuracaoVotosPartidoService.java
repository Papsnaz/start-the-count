package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosPartidoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.repository.ApuracaoVotosPartidoBoletimUrnaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApuracaoVotosPartidoService {

    private final ApuracaoVotosPartidoBoletimUrnaRepository apuracaoVotosPartidoBoletimUrnaRepository;

    public List<ApuracaoVotosPartidoRetrievalDTO> findAll() {
        return this.apuracaoVotosPartidoBoletimUrnaRepository.findApuracaoVotosPartidos();
    }
}
