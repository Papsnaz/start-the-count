package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.ApuracaoVotosCandidaturaBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.ApuracaoVotosCandidaturaBoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.service.ApuracaoVotosCandidaturaBoletimUrnaService;

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
@RequestMapping(value = "/apuracoes-votos-candidaturas-boletins-urna")
@RequiredArgsConstructor
@Tag(name = "apuracoes-votos-candidaturas-boletins-urna")
@SecurityRequirement(name = "bearerAuth")
public class ApuracaoVotosCandidaturaBoletimUrnaController {

    private final ApuracaoVotosCandidaturaBoletimUrnaService apuracaoVotosCandidaturaBoletimUrnaService;

    private final ApuracaoVotosCandidaturaBoletimUrnaMapper apuracaoVotosCandidaturaBoletimUrnaMapper;

    @Operation(summary = "Encontra uma apuração de votos de candidatura.", description = "Encontra uma apuração de votos de candidatura.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a apuração de votos de candidatura.")
        @Valid @RequestBody ApuracaoVotosCandidaturaBoletimUrnaIdDTO id
    ) {
        return this.apuracaoVotosCandidaturaBoletimUrnaMapper
            .toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO(
                this.apuracaoVotosCandidaturaBoletimUrnaService.findById(id)
            );
    }

    @Operation(summary = "Encontra todas as apurações de votos de candidaturas.", description = "Encontra todas as apurações de votos de candidaturas.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO> findAll() {
        return this.apuracaoVotosCandidaturaBoletimUrnaService
            .findAll()
            .stream()
            .map(this.apuracaoVotosCandidaturaBoletimUrnaMapper::toApuracaoVotosCandidaturaBoletimUrnaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
