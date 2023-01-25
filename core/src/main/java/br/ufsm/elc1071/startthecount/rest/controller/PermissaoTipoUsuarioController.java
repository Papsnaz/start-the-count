package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.PermissaoTipoUsuarioIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.PermissaoTipoUsuarioRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.PermissaoTipoUsuarioMapper;
import br.ufsm.elc1071.startthecount.rest.service.PermissaoTipoUsuarioService;

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
@RequestMapping(value = "/permissoes-tipos-usuario")
@RequiredArgsConstructor
@Tag(name = "permissoes-tipos-usuario")
@SecurityRequirement(name = "bearerAuth")
public class PermissaoTipoUsuarioController {

    private final PermissaoTipoUsuarioService permissaoTipoUsuarioService;

    private final PermissaoTipoUsuarioMapper permissaoTipoUsuarioMapper;

    @Operation(summary = "Encontra uma instância de PermissaoTipoUsuario.", description = "Encontra uma instância de PermissaoTipoUsuario.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public PermissaoTipoUsuarioRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam a instância de PermissaoTipoUsuario.")
        @Valid @RequestBody PermissaoTipoUsuarioIdDTO id
    ) {
        return this.permissaoTipoUsuarioMapper
                .toPermissaoTipoUsuarioRetrievalDTO(this.permissaoTipoUsuarioService.findById(id));
    }

    @Operation(summary = "Encontra todas as instâncias de PermissaoTipoUsuario.", description = "Encontra todas as instâncias de PermissaoTipoUsuario.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<PermissaoTipoUsuarioRetrievalDTO> findAll() {
        return this.permissaoTipoUsuarioService
            .findAll()
            .stream()
            .map(this.permissaoTipoUsuarioMapper::toPermissaoTipoUsuarioRetrievalDTO)
            .collect(Collectors.toList());
    }
}
