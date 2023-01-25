package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.Permissao;
import br.ufsm.elc1071.startthecount.rest.service.PermissaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/permissoes")
@RequiredArgsConstructor
@Tag(name = "permissoes")
@SecurityRequirement(name = "bearerAuth")
public class PermissaoController {

    private final PermissaoService permissaoService;

    @Operation(summary = "Encontra uma permissão.", description = "Encontra uma permissão.")
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Permissao findById(
        @Parameter(description = "O ID da permissão que deve ser encontrada.")
        @PathVariable("id") int id
    ) {
        return this.permissaoService.findById(id);
    }

    @Operation(summary = "Encontra todas as permissões.", description = "Encontra todas as permissões.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Permissao> findAll() {
        return this.permissaoService.findAll();
    }
}
