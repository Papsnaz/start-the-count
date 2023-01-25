package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.ProcessoEleitoral;
import br.ufsm.elc1071.startthecount.rest.service.ProcessoEleitoralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/processos-eleitorais")
@RequiredArgsConstructor
@Tag(name = "processos-eleitorais")
@SecurityRequirement(name = "bearerAuth")
public class ProcessoEleitoralController {

    private final ProcessoEleitoralService processoEleitoralService;

    @Operation(summary = "Encontra um processo eleitoral.", description = "Encontra um processo eleitoral.")
    @GetMapping(value = "/{codigoTSE}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProcessoEleitoral findByCodigoTSE(
        @Parameter(description = "O código do processo eleitoral que deve ser encontrado.")
        @PathVariable("codigoTSE") int codigoTSE
    ) {
        return this.processoEleitoralService.findByCodigoTSE(codigoTSE);
    }

    @Operation(summary = "Encontra todos os processos eleitorais.", description = "Encontra todos os processos eleitorais.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProcessoEleitoral> findAll() {
        return this.processoEleitoralService.findAll();
    }}
