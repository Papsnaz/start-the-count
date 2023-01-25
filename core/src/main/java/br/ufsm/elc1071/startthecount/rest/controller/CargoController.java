package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Cargo;
import br.ufsm.elc1071.startthecount.rest.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cargos")
@RequiredArgsConstructor
@Tag(name = "cargos")
@SecurityRequirement(name = "bearerAuth")
public class CargoController {

    private final CargoService cargoService;

    @Operation(summary = "Encontra um cargo.", description = "Encontra um cargo.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Cargo findByCodigoTSE(
        @Parameter(description = "O código do cargo que deve ser encontrado.")
        @PathVariable("codigoTSE") int codigoTSE
    ) {
        return this.cargoService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todos os cargos.", description = "Encontra todos os cargos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Cargo> findAll() {
        return this.cargoService.findAll();
    }
}
