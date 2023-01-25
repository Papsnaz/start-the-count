package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Municipio;
import br.ufsm.elc1071.startthecount.rest.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/municipios")
@RequiredArgsConstructor
@Tag(name = "municipios")
@SecurityRequirement(name = "bearerAuth")
public class MunicipioController {

    private final MunicipioService municipioService;

    @Operation(summary = "Encontra um município.", description = "Encontra um município.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Municipio findByCodigoTSE(
        @Parameter(description = "O código do município que deve ser encontrado.")
        @PathVariable("codigoTSE") int codigoTSE
    ) {
        return this.municipioService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todos os municípios.", description = "Encontra todos os municípios.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Municipio> findAll() {
        return this.municipioService.findAll();
    }
}
