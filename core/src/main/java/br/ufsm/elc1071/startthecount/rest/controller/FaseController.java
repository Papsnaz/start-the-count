package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Fase;
import br.ufsm.elc1071.startthecount.rest.service.FaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/fases")
@RequiredArgsConstructor
@Tag(name = "fases")
@SecurityRequirement(name = "bearerAuth")
public class FaseController {

    private final FaseService faseService;

    @Operation(summary = "Encontra uma fase.", description = "Encontra uma fase.")
    @GetMapping(value = "/{nome}")
    @ResponseStatus(value = HttpStatus.OK)
    public Fase findByNome(
        @Parameter(description = "O nome da fase que deve ser encontrada.")
        @PathVariable("nome") String nome
    ) {
        return this.faseService.findByNome(nome);
    }

    @Operation(summary = "Encontra todas as fases.", description = "Encontra todas as fases.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Fase> findAll() {
        return this.faseService.findAll();
    }
}
