package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoEleicaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoEleicaoMapper;
import br.ufsm.elc1071.startthecount.rest.service.SecaoEleicaoService;

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
@RequestMapping(value = "/secoes-eleicoes")
@RequiredArgsConstructor
@Tag(name = "secoes-eleicoes")
@SecurityRequirement(name = "bearerAuth")
public class SecaoEleicaoController {

    private final SecaoEleicaoService secaoEleicaoService;

    private final SecaoEleicaoMapper secaoEleicaoMapper;

    @Operation(summary = "Encontra uma instância de SecaoEleicao.", description = "Encontra uma instância de SecaoEleicao.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public SecaoEleicaoRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a instância de SecaoEleicao.")
        @Valid @RequestBody SecaoEleicaoIdDTO id
    ) {
        return this.secaoEleicaoMapper.toSecaoEleicaoRetrievalDTO(this.secaoEleicaoService.findById(id));
    }

    @Operation(summary = "Encontra todas as instâncias de SecaoEleicao.", description = "Encontra todas as instâncias de SecaoEleicao.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<SecaoEleicaoRetrievalDTO> findAll() {
        return this.secaoEleicaoService
            .findAll()
            .stream()
            .map(this.secaoEleicaoMapper::toSecaoEleicaoRetrievalDTO)
            .collect(Collectors.toList());
    }
}
