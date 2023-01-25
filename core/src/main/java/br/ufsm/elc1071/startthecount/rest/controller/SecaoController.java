package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.SecaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.SecaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.SecaoMapper;
import br.ufsm.elc1071.startthecount.rest.service.SecaoService;

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
@RequestMapping(value = "/secoes")
@RequiredArgsConstructor
@Tag(name = "secoes")
@SecurityRequirement(name = "bearerAuth")
public class SecaoController {

    private final SecaoService secaoService;

    private final SecaoMapper secaoMapper;

    @Operation(summary = "Encontra uma seção.", description = "Encontra uma seção.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public SecaoRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a seção.")
        @Valid @RequestBody SecaoIdDTO id
    ) {
        return this.secaoMapper.toSecaoRetrievalDTO(this.secaoService.findById(id));
    }

    @Operation(summary = "Encontra todas as seções.", description = "Encontra todas as seções.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<SecaoRetrievalDTO> findAll() {
        return this.secaoService
            .findAll()
            .stream()
            .map(this.secaoMapper::toSecaoRetrievalDTO)
            .collect(Collectors.toList());
    }
}
