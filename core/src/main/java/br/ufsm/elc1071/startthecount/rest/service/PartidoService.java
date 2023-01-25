package br.ufsm.elc1071.startthecount.rest.service;

import br.ufsm.elc1071.startthecount.rest.exception.EntidadeNaoEncontradaException;
import br.ufsm.elc1071.startthecount.rest.model.Partido;
import br.ufsm.elc1071.startthecount.rest.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final CandidaturaService candidaturaService;
    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;

    @Autowired
    public PartidoService(PartidoRepository partidoRepository, @Lazy CandidaturaService candidaturaService, ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService) {
        this.partidoRepository = partidoRepository;
        this.candidaturaService = candidaturaService;
        this.apuracaoVotosPartidoBoletimUrnaService = apuracaoVotosPartidoBoletimUrnaService;
    }

    public Partido findByNumeroTSE(int numeroTSE) {
        return this.partidoRepository
            .findByNumeroTSE(numeroTSE)
            .orElseThrow(() -> {
                throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum partido com o número %d.", numeroTSE));
            });
    }

    public List<Partido> findAll() {
        return this.partidoRepository.findAll();
    }

    public void saveIfDoesNotExist(Partido partido) {
        if (!this.partidoRepository.existsByNumeroTSE(partido.getNumeroTSE())) {
            this.partidoRepository.save(partido);
        }
    }

    public void deleteByNumeroTSE(int numeroTSE) {
        if (!this.partidoRepository.existsByNumeroTSE(numeroTSE)) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum partido com o número %d.", numeroTSE));
        }

        this.candidaturaService.deleteByNumeroPartidoTSE(numeroTSE);
        this.apuracaoVotosPartidoBoletimUrnaService.deleteByNumeroPartidoTSE(numeroTSE);

        this.partidoRepository.deleteByNumeroTSE(numeroTSE);
    }
}
