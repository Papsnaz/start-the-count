package br.ufsm.elc1071.startthecount.rest.controller;

import br.ufsm.elc1071.startthecount.rest.dto.id.QRCodeBoletimUrnaIdDTO;
import br.ufsm.elc1071.startthecount.rest.dto.retrieval.QRCodeBoletimUrnaRetrievalDTO;
import br.ufsm.elc1071.startthecount.rest.mapper.QRCodeBoletimUrnaMapper;
import br.ufsm.elc1071.startthecount.rest.service.QRCodeBoletimUrnaService;
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
@RequestMapping(value = "/qr-codes-boletim-urna")
@RequiredArgsConstructor
@Tag(name = "qr-codes-boletim-urna")
@SecurityRequirement(name = "bearerAuth")
public class QRCodeBoletimUrnaController {

    private final QRCodeBoletimUrnaService qrCodeBoletimUrnaService;

    private final QRCodeBoletimUrnaMapper qrCodeBoletimUrnaMapper;

    @Operation(summary = "Encontra um QR code de boletim de urna.", description = "Encontra um QR code de boletim de urna.")
    @GetMapping(value = "/id")
    @ResponseStatus(value = HttpStatus.OK)
    public QRCodeBoletimUrnaRetrievalDTO findById(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Os dados que identificam um QR code de boletim de urna.")
        @Valid @RequestBody QRCodeBoletimUrnaIdDTO id
    ) {
        return this.qrCodeBoletimUrnaMapper.toQRCodeBoletimUrnaRetrievalDTO(this.qrCodeBoletimUrnaService.findById(id));
    }

    @Operation(summary = "Encontra todos os QR codes de boletim de urna.", description = "Encontra todos os QR codes de boletim de urna.")
    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<QRCodeBoletimUrnaRetrievalDTO> findAll() {
        return this.qrCodeBoletimUrnaService
            .findAll()
            .stream()
            .map(this.qrCodeBoletimUrnaMapper::toQRCodeBoletimUrnaRetrievalDTO)
            .collect(Collectors.toList());
    }
}
