package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosCargoBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCargoBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.ApuracaoVotosCargoBoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.service.ApuracaoVotosCargoBoletimUrnaService;

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
@RequestMapping(value = "/apuracoes-votos-cargos-boletins-urna")
@RequiredArgsConstructor
@Tag(name = "apuracoes-votos-cargos-boletins-urna")
@SecurityRequirement(name = "bearerAuth")
public class ApuracaoVotosCargoBoletimUrnaController {

    private final ApuracaoVotosCargoBoletimUrnaService apuracaoVotosCargoBoletimUrnaService;

    private final ApuracaoVotosCargoBoletimUrnaMapper apuracaoVotosCargoBoletimUrnaMapper;

    @Operation(summary = "Encontra uma apuração de votos de cargo.", description = "Encontra uma apuração de votos de cargo.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosCargoBoletimUrnaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a apuração de votos de cargo.")
        @Valid @RequestBody ApuracaoVotosCargoBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosCargoBoletimUrnaMapper
            .toApuracaoVotosCargoBoletimUrnaRetrievalDTO(
                this.apuracaoVotosCargoBoletimUrnaService.findById(id)
            );
    }

    @Operation(summary = "Encontra todas as apurações de votos de cargos.", description = "Encontra todas as apurações de votos de cargos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCargoBoletimUrnaRetrievalDTO> findAll() {
        return this.apuracaoVotosCargoBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosCargoBoletimUrnaMapper::toApuracaoVotosCargoBoletimUrnaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
