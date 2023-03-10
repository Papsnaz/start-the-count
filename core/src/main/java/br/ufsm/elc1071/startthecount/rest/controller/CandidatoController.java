package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Candidato;
import br.ufsm.elc1071.startthecount.rest.service.CandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidatos")
@RequiredArgsConstructor
@Tag(name = "candidatos")
@SecurityRequirement(name = "bearerAuth")
public class CandidatoController {

    private final CandidatoService candidatoService;

    @Operation(summary = "Encontra um candidato.", description = "Encontra um candidato.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Candidato findByCodigoTSE(
        @Parameter(description = "O número do candidato que deve ser encontrado.")
        @PathVariable("codigoTSE") String codigoTSE
    ) {
        return this.candidatoService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todos os candidatos.", description = "Encontra todos os candidatos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Candidato> findAll() {
        return this.candidatoService.findAll();
    }
}
