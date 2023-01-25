package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.UrnaEletronica;
import br.ufsm.elc1071.startthecount.rest.service.UrnaEletronicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/urnas-eletronicas")
@RequiredArgsConstructor
@Tag(name = "urnas-eletronicas")
@SecurityRequirement(name = "bearerAuth")
public class UrnaEletronicaController {

    private final UrnaEletronicaService urnaEletronicaService;

    @Operation(summary = "Encontra uma urna eletrônica.", description = "Encontra uma urna eletrônica.")
    @GetMapping(value = "/{numeroSerieTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public UrnaEletronica findByNumeroSerieTSE(
        @Parameter(description = "O número de série da urna eletrônica que deve ser encontrada.")
        @PathVariable("numeroSerieTSE") int numeroSerieTSE
    ) {
        return this.urnaEletronicaService.findByNumeroSerieTSE(numeroSerieTSE);
    }

    @Operation(summary = "Encontra todas as urnas eletrônicas.", description = "Encontra todas as urnas eletrônicas.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<UrnaEletronica> findAll() {
        return this.urnaEletronicaService.findAll();
    }
}
