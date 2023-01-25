package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Partido;
import br.ufsm.elc1071.startthecount.rest.service.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/partidos")
@RequiredArgsConstructor
@Tag(name = "partidos")
@SecurityRequirement(name = "bearerAuth")
public class PartidoController {

    private final PartidoService partidoService;

    @Operation(summary = "Encontra um partido.", description = "Encontra um partido.")
    @GetMapping(value = "/{numeroTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public Partido findByNumeroTSE(
        @Parameter(description = "O número do partido que deve ser encontrado.")
        @PathVariable("numeroTSE") int numeroTSE
    ) {
        return this.partidoService.findByNumeroTSE(numeroTSE);
    }

    @Operation(summary = "Encontra todos os partidos.", description = "Encontra todos os partidos.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Partido> findAll() {
        return this.partidoService.findAll();
    }
}
