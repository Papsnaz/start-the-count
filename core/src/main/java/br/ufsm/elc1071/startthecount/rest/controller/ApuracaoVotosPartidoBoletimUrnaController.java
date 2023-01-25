package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosPartidoBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosPartidoBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.ApuracaoVotosPartidoBoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.service.ApuracaoVotosPartidoBoletimUrnaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/apuracoes-votos-partidos-boletins-urna")
@RequiredArgsConstructor
@Tag(name = "apuracoes-votos-partidos-boletins-urna")
@SecurityRequirement(name = "bearerAuth")
public class ApuracaoVotosPartidoBoletimUrnaController {

    private final ApuracaoVotosPartidoBoletimUrnaService apuracaoVotosPartidoBoletimUrnaService;

    private final ApuracaoVotosPartidoBoletimUrnaMapper apuracaoVotosPartidoBoletimUrnaMapper;

    @Operation(summary = "Encontra uma apuração de votos de partido.", description = "Encontra uma apuração de votos de partido.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosPartidoBoletimUrnaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a apuração de votos de partido.")
        @Valid @RequestBody ApuracaoVotosPartidoBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosPartidoBoletimUrnaMapper
            .toApuracaoVotosPartidoBoletimUrnaRetrievalDTO(
                this.apuracaoVotosPartidoBoletimUrnaService.findById(id)
            );
    }

    @Operation(summary = "Encontra todas as apurações de votos de partidos.", description = "Encontra todas as apurações de votos de partidos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosPartidoBoletimUrnaRetrievalDTO> findAll() {
        return this.apuracaoVotosPartidoBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosPartidoBoletimUrnaMapper::toApuracaoVotosPartidoBoletimUrnaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
