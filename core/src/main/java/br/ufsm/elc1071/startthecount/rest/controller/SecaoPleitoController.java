package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoPleitoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoPleitoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoPleitoMapper;
import br.ufsm.elc1071.startthecount.rest.service.SecaoPleitoService;
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
@RequestMapping(value = "/secoes-pleitos")
@RequiredArgsConstructor
@Tag(name = "secoes-pleitos")
@SecurityRequirement(name = "bearerAuth")
public class SecaoPleitoController {

    private final SecaoPleitoService secaoPleitoService;

    private final SecaoPleitoMapper secaoPleitoMapper;

    @Operation(summary = "Encontra uma instância de SecaoPleito.", description = "Encontra uma instância de SecaoPleito.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public SecaoPleitoRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a instância de SecaoPleito.")
        @Valid @RequestBody SecaoPleitoIdDTO id
    ) {
        return this.secaoPleitoMapper.toSecaoPleitoRetrievalDTO(this.secaoPleitoService.findById(id));
    }

    @Operation(summary = "Encontra todas as instâncias de SecaoPleito.", description = "Encontra todas as instâncias de SecaoPleito.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<SecaoPleitoRetrievalDTO> findAll() {
        return this.secaoPleitoService
            .findAll()
            .stream()
            .map(this.secaoPleitoMapper::toSecaoPleitoRetrievalDTO)
            .collect(Collectors.toList());
    }
}
