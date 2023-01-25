package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.build.BoletimUrnaBuildDTO;
import br.ufsm.elc1071.startthecount.rest.dto.id.BoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.response.BoletimUrnaBuildResponseDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.BoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.exception.UsuarioNaoAutenticadoException;
import br.ufsm.elc1071.startthecount.rest.mapper.BoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.service.BoletimUrnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/boletins-urna")
@RequiredArgsConstructor
@Tag(name = "boletins-urna")
@SecurityRequirement(name = "bearerAuth")
public class BoletimUrnaController {

    private final BoletimUrnaService boletimUrnaService;

    private final BoletimUrnaMapper boletimUrnaMapper;

    @Operation(
        summary = "Constrói um boletim de urna a partir de um ou mais payload(s) de QR code(s).",
        description = "Constrói um boletim de urna a partir de um ou mais payload(s) de QR code(s)."
    )
    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public BoletimUrnaBuildResponseDTO build(
        Authentication authentication,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "O(s) payload(s) do(s) QR code(s) do boletim de urna.")
        @Valid @RequestBody BoletimUrnaBuildDTO boletimUrnaBuildDTO
    ) {
        if (Objects.isNull(authentication)) {
            throw new UsuarioNaoAutenticadoException("O usuário precisa estar autenticado para efetuar uma requisição de construção de boletim de urna.");
        }

        return new BoletimUrnaBuildResponseDTO(
            "O boletim de urna foi criado com sucesso!",
            this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(
                this.boletimUrnaService.build(
                        boletimUrnaBuildDTO,
                    authentication.getName()
                )
            )
        );
    }

    @Operation(summary = "Encontra um boletim de urna.", description = "Encontra um boletim de urna.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public BoletimUrnaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam o boletim de urna.")
        @Valid @RequestBody BoletimUrnaIdDTO id
    ) {
        return this.boletimUrnaMapper.toBoletimUrnaRetrievalDTO(this.boletimUrnaService.findById(id));
    }

    @Operation(summary = "Encontra todos os boletins de urna.", description = "Encontra todos os boletins de urna.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<BoletimUrnaRetrievalDTO> findAll() {
        return this.boletimUrnaService
            .findAll()
            .stream()
            .map(this.boletimUrnaMapper::toBoletimUrnaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
