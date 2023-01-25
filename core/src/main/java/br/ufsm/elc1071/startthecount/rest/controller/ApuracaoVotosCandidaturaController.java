package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCandidaturaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.service.ApuracaoVotosCandidaturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/apuracoes-votos-candidaturas")
@RequiredArgsConstructor
@Tag(name = "apuracoes-votos-candidaturas")
@SecurityRequirement(name = "bearerAuth")
public class ApuracaoVotosCandidaturaController {

    private final ApuracaoVotosCandidaturaService apuracaoVotosCandidaturaService;

    @Operation(summary = "Encontra a apuração de votos de candidaturas.", description = "Encontra a apuração de votos de candidaturas.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCandidaturaRetrievalDTO> findAll() {
        return this.apuracaoVotosCandidaturaService.findAll();
    }
}
