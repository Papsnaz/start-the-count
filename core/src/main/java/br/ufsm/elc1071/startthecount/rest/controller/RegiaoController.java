package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Regiao;
import br.ufsm.elc1071.startthecount.rest.service.RegiaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/regioes")
@RequiredArgsConstructor
@Tag(name = "regioes")
@SecurityRequirement(name = "bearerAuth")
public class RegiaoController {

    private final RegiaoService regiaoService;

    @Operation(summary = "Encontra uma região.", description = "Encontra uma região.")
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Regiao findById(
        @Parameter(description = "O ID da região que deve ser encontrada.")
        @PathVariable("id") int id
    ) {
        return this.regiaoService.findById(id);
    }

    @Operation(summary = "Encontra todas as regiões.", description = "Encontra todas as regiões.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Regiao> findAll() {
        return this.regiaoService.findAll();
    }
}
