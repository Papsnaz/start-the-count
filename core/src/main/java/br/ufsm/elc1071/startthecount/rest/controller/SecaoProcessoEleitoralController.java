package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoProcessoEleitoralIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoProcessoEleitoralRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoProcessoEleitoralMapper;
import br.ufsm.elc1071.startthecount.rest.service.SecaoProcessoEleitoralService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/secoes-processos-eleitorais")
@RequiredArgsConstructor
@Tag(name = "secoes-processos-eleitorais")
@SecurityRequirement(name = "bearerAuth")
public class SecaoProcessoEleitoralController {

    private final SecaoProcessoEleitoralService secaoProcessoEleitoralService;

    private final SecaoProcessoEleitoralMapper secaoProcessoEleitoralMapper;

    @Operation(summary = "Encontra uma instância de SecaoProcessoEleitoral.", description = "Encontra uma instância de SecaoProcessoEleitoral.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public SecaoProcessoEleitoralRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a instância de SecaoProcessoEleitoral.")
        @Valid @RequestBody SecaoProcessoEleitoralIdDTO id
    ) {
        return this.secaoProcessoEleitoralMapper
                .toSecaoProcessoEleitoralRetrievalDTO(this.secaoProcessoEleitoralService.findById(id));
    }

    @Operation(summary = "Encontra todas as instâncias de SecaoProcessoEleitoral.", description = "Encontra todas as instâncias de SecaoProcessoEleitoral.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<SecaoProcessoEleitoralRetrievalDTO> findAll() {
        return this.secaoProcessoEleitoralService
            .findAll()
            .stream()
            .map(this.secaoProcessoEleitoralMapper::toSecaoProcessoEleitoralRetrievalDTO)
            .collect(Collectors.toList());
    }
}
