package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.model.TipoUsuario;
import br.ufsm.elc1071.startthecount.rest.service.TipoUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tipos-usuario")
@RequiredArgsConstructor
@Tag(name = "tipos-usuario")
@SecurityRequirement(name = "bearerAuth")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    @Operation(summary = "Encontra um tipo de usuário.", description = "Encontra um tipo de usuário.")
    @GetMapping(value = "/{nome}")
    @ResponseStatus(value = HttpStatus.OK)
    public TipoUsuario findByNome(
        @Parameter(description = "O nome do tipo de usuário que deve ser encontrado.")
        @PathVariable("nome") String nome
    ) {
        return this.tipoUsuarioService.findByNome(nome);
    }

    @Operation(summary = "Encontra todos os tipos de usuário.", description = "Encontra todos os tipos de usuário.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<TipoUsuario> findAll() {
        return this.tipoUsuarioService.findAll();
    }
}
