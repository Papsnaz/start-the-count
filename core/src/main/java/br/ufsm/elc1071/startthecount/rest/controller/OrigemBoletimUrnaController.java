package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.OrigemBoletimUrna;
import br.ufsm.elc1071.startthecount.rest.service.OrigemBoletimUrnaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/origens-boletim-urna")
@RequiredArgsConstructor
@Tag(name = "origens-boletim-urna")
@SecurityRequirement(name = "bearerAuth")
public class OrigemBoletimUrnaController {

    private final OrigemBoletimUrnaService origemBoletimUrnaService;

    @Operation(summary = "Encontra uma origem de boletim de urna.", description = "Encontra uma origem de boletim de urna.")
    @GetMapping(value = "/{abreviacao}")
    @ResponseStatus(value = HttpStatus.OK)
    public OrigemBoletimUrna findByAbreviacao(
        @Parameter(description = "A abreviação da origem de boletim de urna que deve ser encontrada.")
        @PathVariable("abreviacao") String abreviacao
    ) {
        return this.origemBoletimUrnaService.findByAbreviacao(abreviacao);
    }

    @Operation(summary = "Encontra todas origens de boletim de urna.", description = "Encontra todas origens de boletim de urna.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<OrigemBoletimUrna> findAll() {
        return this.origemBoletimUrnaService.findAll();
    }
}
