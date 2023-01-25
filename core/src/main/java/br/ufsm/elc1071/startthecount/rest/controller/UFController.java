package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.UF;
import br.ufsm.elc1071.startthecount.rest.service.UFService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ufs")
@RequiredArgsConstructor
@Tag(name = "ufs")
@SecurityRequirement(name = "bearerAuth")
public class UFController {

    private final UFService ufService;

    @Operation(summary = "Encontra uma UF pela sigla.", description = "Encontra uma UF pela sigla.")
    @GetMapping(value = "/{sigla}")
    @ResponseStatus(value = HttpStatus.OK)
    public UF findBySigla(
        @Parameter(description = "A sigla da UF que deve ser encontrada.")
        @PathVariable("sigla") String sigla
    ) {
        return this.ufService.findBySigla(sigla);
    }

    @Operation(summary = "Encontra todas as UFs.", description = "Encontra todas as UFs.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<UF> findAll() {
        return this.ufService.findAll();
    }
}
