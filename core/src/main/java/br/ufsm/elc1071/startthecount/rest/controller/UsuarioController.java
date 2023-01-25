package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.config.JwtService;
import br.ufsm.elc1071.startthecount.rest.dto.creation.UsuarioCreationDTO;
import br.ufsm.elc1071.startthecount.rest.dto.request.AuthenticationRequestDTO;
import br.ufsm.elc1071.startthecount.rest.dto.response.AuthenticationResponseDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.UsuarioRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.dto.update.UsuarioUpdateDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.UsuarioMapper;
import br.ufsm.elc1071.startthecount.rest.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
@RequiredArgsConstructor
@Tag(name = "usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Operation(
        summary = "Encontra um usuário pelo username.",
        description = "Encontra um usuário pelo username.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping(value = "/{username}")
    @ResponseStatus(value = HttpStatus.OK)
    public UsuarioRetrievalDTO findByUsername(
        @Parameter(description = "O username do usuário que deve ser encontrado.")
        @PathVariable("username") String username
    ) {
        return this.usuarioMapper.toUsuarioRetrievalDTO(this.usuarioService.findByUsername(username));
    }

    @Operation(
        summary = "Encontra todos os usuários.",
        description = "Encontra todos os usuários.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<UsuarioRetrievalDTO> findAll() {
        return this.usuarioService
            .findAll()
            .stream()
            .map(this.usuarioMapper::toUsuarioRetrievalDTO)
            .collect(Collectors.toList());
    }

    @Operation(
        summary = "Atualiza um usuário pelo username.",
        description = "Atualiza um usuário pelo username.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PatchMapping(value = "/{username}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void updateByUsername(
        @Parameter(description = "O username do usuário que deve ser atualizado.")
        @PathVariable("username")
        String username,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os novos dados do usuário.")
        @Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        this.usuarioService.updateByUsername(username, usuarioUpdateDTO);
    }

    @Operation(summary = "Cria um usuário.", description = "Cria um usuário.")
    @PostMapping(value = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthenticationResponseDTO register(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados do novo usuário.")
        @Valid @RequestBody UsuarioCreationDTO usuarioCreationDTO
    ) {
        return new AuthenticationResponseDTO(
            this.jwtService.generateToken(
                this.usuarioService.save(usuarioCreationDTO)
            )
        );
    }

    @Operation(
        summary = "Remove um usuário.",
        description = "Remove um usuário.",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping(value = "/{username}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteByUsername(
        @Parameter(description = "O username do usuário que deve ser removido.")
        @PathVariable("username") String username
    ) {
        this.usuarioService.deleteByUsername(username);
    }

    @Operation(summary = "Autentica um usuário.", description = "Autentica um usuário.")
    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AuthenticationResponseDTO login(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados de autenticação do usuário.")
            @Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO
    ) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getUsername(),
                authenticationRequestDTO.getSenha()
            )
        );

        return new AuthenticationResponseDTO(
            this.jwtService.generateToken(
                this.usuarioService.findByUsername(authenticationRequestDTO.getUsername())
            )
        );
    }
}
