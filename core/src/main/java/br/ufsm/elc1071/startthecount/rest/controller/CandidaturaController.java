package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.CandidaturaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.CandidaturaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.CandidaturaMapper;
import br.ufsm.elc1071.startthecount.rest.service.CandidaturaService;

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
@RequestMapping(value = "/candidaturas")
@RequiredArgsConstructor
@Tag(name = "candidaturas")
@SecurityRequirement(name = "bearerAuth")
public class CandidaturaController {

    private final CandidaturaService candidaturaService;

    private final CandidaturaMapper candidaturaMapper;

    @Operation(summary = "Encontra uma candidatura.", description = "Encontra uma candidatura.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public CandidaturaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam uma candidatura.")
        @Valid @RequestBody CandidaturaIdDTO id
    ) {
        return this.candidaturaMapper.toCandidaturaRetrievalDTO(this.candidaturaService.findById(id));
    }

    @Operation(summary = "Encontra todas as candidaturas.", description = "Encontra todas as candidaturas.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<CandidaturaRetrievalDTO> findAll() {
        return this.candidaturaService
            .findAll()
            .stream()
            .map(this.candidaturaMapper::toCandidaturaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
