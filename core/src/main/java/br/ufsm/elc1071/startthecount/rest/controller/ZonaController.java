package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.ZonaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.ZonaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.ZonaMapper;
import br.ufsm.elc1071.startthecount.rest.service.ZonaService;

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
@RequestMapping(value = "/zonas")
@RequiredArgsConstructor
@Tag(name = "zonas")
@SecurityRequirement(name = "bearerAuth")
public class ZonaController {

    private final ZonaService zonaService;

    private final ZonaMapper zonaMapper;

    @Operation(summary = "Encontra uma zona.", description = "Encontra uma zona.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public ZonaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a zona.")
        @Valid @RequestBody ZonaIdDTO id
    ) {
        return this.zonaMapper.toZonaRetrievalDTO(this.zonaService.findById(id));
    }

    @Operation(summary = "Encontra todas as zonas.", description = "Encontra todas as zonas.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<ZonaRetrievalDTO> findAll() {
        return this.zonaService
            .findAll()
            .stream()
            .map(this.zonaMapper::toZonaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
