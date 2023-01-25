package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ApuracaoVotosCargoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.service.ApuracaoVotosCargoService;

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
@RequestMapping(value = "/apuracoes-votos-cargos")
@RequiredArgsConstructor
@Tag(name = "apuracoes-votos-cargos")
@SecurityRequirement(name = "bearerAuth")
public class ApuracaoVotosCargoController {

    private final ApuracaoVotosCargoService apuracaoVotosCargoService;

    @Operation(summary = "Encontra a apuração de votos de cargos.", description = "Encontra a apuração de votos de cargos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ApuracaoVotosCargoRetrievalDTO> findAll() {
        return this.apuracaoVotosCargoService.findAll();
    }
}
