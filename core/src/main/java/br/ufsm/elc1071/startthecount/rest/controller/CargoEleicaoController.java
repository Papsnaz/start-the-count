package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.CargoEleicaoIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.CargoEleicaoRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.CargoEleicaoMapper;
import br.ufsm.elc1071.startthecount.rest.service.CargoEleicaoService;

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
@RequestMapping(value = "/cargos-eleicoes")
@RequiredArgsConstructor
@Tag(name = "cargos-eleicoes")
@SecurityRequirement(name = "bearerAuth")
public class CargoEleicaoController {

    private final CargoEleicaoService cargoEleicaoService;

    private final CargoEleicaoMapper cargoEleicaoMapper;

    @Operation(summary = "Encontra uma instância de CargoEleicao.", description = "Encontra uma instância de CargoEleicao.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public CargoEleicaoRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a instância de CargoEleicao.")
        @Valid @RequestBody CargoEleicaoIdDTO id
    ) {
        return this.cargoEleicaoMapper.toCargoEleicaoRetrievalDTO(this.cargoEleicaoService.findById(id));
    }

    @Operation(summary = "Encontra todas as instâncias de CargoEleicao.", description = "Encontra todas as instâncias de CargoEleicao.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<CargoEleicaoRetrievalDTO> findAll() {
        return this.cargoEleicaoService
            .findAll()
            .stream()
            .map(this.cargoEleicaoMapper::toCargoEleicaoRetrievalDTO)
            .collect(Collectors.toList());
    }
}
