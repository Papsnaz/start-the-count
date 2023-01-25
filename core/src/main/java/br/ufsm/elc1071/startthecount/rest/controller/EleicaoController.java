package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Eleicao;
import br.ufsm.elc1071.startthecount.rest.service.EleicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/eleicoes")
@RequiredArgsConstructor
@Tag(name = "eleicoes")
@SecurityRequirement(name = "bearerAuth")
public class EleicaoController {

    private final EleicaoService eleicaoService;

    @Operation(summary = "Encontra uma eleição.", description = "Encontra uma eleição.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Eleicao findByCodigoTSE(
        @Parameter(description = "O código da eleição que deve ser encontrada.")
        @PathVariable("codigoTSE") int codigoTSE
    ) {
        return this.eleicaoService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todas as eleições.", description = "Encontra todas as eleições.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Eleicao> findAll() {
        return this.eleicaoService.findAll();
    }
}
