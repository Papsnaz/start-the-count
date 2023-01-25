package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Pleito;
import br.ufsm.elc1071.startthecount.rest.service.PleitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pleitos")
@RequiredArgsConstructor
@Tag(name = "pleitos")
@SecurityRequirement(name = "bearerAuth")
public class PleitoController {

    private final PleitoService pleitoService;

    @Operation(summary = "Encontra um pleito.", description = "Encontra um pleito.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Pleito findByCodigoTSE(
        @Parameter(description = "O código do pleito que deve ser encontrado.")
        @PathVariable("codigoTSE") int codigoTSE
    ) {
        return this.pleitoService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todos os pleitos.", description = "Encontra todos os pleitos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Pleito> findAll() {
        return this.pleitoService.findAll();
    }
}
