package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.LocalVotacaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.LocalVotacaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.LocalVotacaoMapper;
import br.ufsm.elc1071.startthecount.rest.service.LocalVotacaoService;

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
@RequestMapping(value = "/locais-votacao")
@RequiredArgsConstructor
@Tag(name = "locais-votacao")
@SecurityRequirement(name = "bearerAuth")
public class LocalVotacaoController {

    private final LocalVotacaoService localVotacaoService;

    private final LocalVotacaoMapper localVotacaoMapper;

    @Operation(summary = "Encontra um local de votação.", description = "Encontra um local de votação.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public LocalVotacaoRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam o local de votação.")
        @Valid @RequestBody LocalVotacaoIdDTO id
    ) {
        return this.localVotacaoMapper.toLocalVotacaoRetrievalDTO(this.localVotacaoService.findById(id));
    }

    @Operation(summary = "Encontra todos os locais de votação.", description = "Encontra todos os locais de votação.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<LocalVotacaoRetrievalDTO> findAll() {
        return this.localVotacaoService
            .findAll()
            .stream()
            .map(this.localVotacaoMapper::toLocalVotacaoRetrievalDTO)
            .collect(Collectors.toList());
    }
}
